package com.camerino.cli.mock;

import com.camerino.cli.menu.Input;
import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;


public class MockNodi implements IPersistenceModel<ClsNodo> {

    private ArrayList<ClsNodo> nodi = new ArrayList<ClsNodo>();
    private long idCounter = 0;

    //region CRUD metodi
        public ArrayList<ClsNodo> get(Map<String, Object> filters) {
            if(filters!=null) {
                ArrayList<ClsNodo> nodi = new ArrayList<>();
                if (filters.containsKey("idNodo")) {
                    nodi.add(getNodoById(Long.valueOf(filters.get("idNodo").toString())));
                    return nodi;
                }
                if (filters.containsKey("idComune")) {
                    nodi.addAll(filterByIdComune((Long) filters.get("idComune")));
                    return nodi;
                }
                if((filters.containsKey("owner"))){
                    nodi.addAll(filterByUsername((Long) filters.get("owner")));
                    return nodi;
                }
            }
            return this.nodi;
        }
        private List<ClsNodo> filterByIdComune(Long id)
        {
            List<ClsNodo> tmp =
                    nodi.stream().filter(n->n.getIdComuneAssociato().equals(id)).toList();
            return tmp;
        }

        private ClsNodo getNodoById(Long id){
            List<ClsNodo> tmp =  nodi.stream().filter(n->n.getId().equals(id)).toList();
            if(tmp.isEmpty())
                return null;
            return tmp.get(0);
        }
        private List<ClsNodo> filterByUsername(Long user)
        {
            List<ClsNodo> tmp =
                    nodi.stream().filter(n->n.getIdCreatore().equals(user)).toList();
            return tmp;
        }


        public boolean update(Map<String, Object> filters, ClsNodo object) {
            if(filters.containsKey("idNodo"))
                return modificaNodo(Long.valueOf(filters.get("idNodo").toString()), object);
            return false;
        }

        private boolean modificaNodo(Long id, ClsNodo nodo){
            ClsNodo tmp = getNodoById(id);
            int index = nodi.indexOf(tmp);
            if(index<0)
                return false;
            nodi.set(index, nodo);
            return true;
        }


        public boolean insert(ClsNodo object) {
            return aggiungiNodo(object);
        }

        private boolean aggiungiNodo(ClsNodo nodo){
            idCounter++;
            nodo.setId(idCounter);
            return nodi.add(nodo);
        }

        public boolean delete(Map<String, Object> filters) {
            if(filters.containsKey("idNodo")) {
                cascadeNodo(getNodoById(Long.valueOf(filters.get("idNodo").toString())));
                return eliminaNodo(Long.valueOf(filters.get("idNodo").toString()));
            }

            return false;
        }

        private boolean eliminaNodo(Long id){
            return nodi.remove(getNodoById(id));
        }
        //endregion

        public void leggiNodi(){
            File f = new File("CLIsave/nodi.csv");
            if(f.exists()) {
                try {
                    FileReader input = new FileReader(f);
                    StringBuilder nodiFile = new StringBuilder();
                    int c;
                    while ((c = input.read()) != -1) {
                        nodiFile.append((char) c);
                    }
                    if(nodiFile.length()>1) {
                        String tuttiNodi = String.valueOf(nodiFile);
                        String[] nodiSingoli = Input.removeCarriageReturn(tuttiNodi.split("\n"));
                        for (String nodoSingolo : nodiSingoli) {
                            ClsNodo daAggiungere = new ClsNodo();
                            String[] datiNodo = nodoSingolo.split(",");
                            daAggiungere.setId(Long.valueOf(datiNodo[0]));
                            daAggiungere.setIdComuneAssociato(Long.valueOf(datiNodo[1]));
                            daAggiungere.setaTempo(Boolean.parseBoolean(datiNodo[2]));
                            daAggiungere.setDataFine(datiNodo[3]);
                            daAggiungere.setTipologiaNodo(ClsNodo.eTipologiaNodo.valueOf(datiNodo[4]));
                            daAggiungere.setIdCreatore(Long.valueOf(datiNodo[5]));
                            daAggiungere.setNome(datiNodo[6]);
                            daAggiungere.setPosizione(new Posizione(Double.parseDouble(datiNodo[7]), Double.parseDouble(datiNodo[8])));
                            nodi.add(daAggiungere);
                        }
                        maxID();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        private void maxID(){
            for(ClsNodo i:nodi){
                if(i.getId()>this.idCounter)
                    this.idCounter = i.getId();
            }
        }
        public void scriviNodi(){
            try{
                FileWriter output = new FileWriter("CLIsave/nodi.csv");
                StringBuilder daScrivere = new StringBuilder();
                for(ClsNodo nodo:nodi){
                    daScrivere.append(nodo.getId() + "," + nodo.getIdComuneAssociato() + "," + nodo.isaTempo() + "," + nodo.getDataFine() + "," + nodo.getTipologiaNodo() + "," + nodo.getIdCreatore() + "," + nodo.getNome() + "," + nodo.getPosizione().getY() + "," + nodo.getPosizione().getX() + "\n");
                }
                output.write(String.valueOf(daScrivere));
                output.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }

    /**
     * Il metodo effettua un'eliminazione a cascata di tutto ciò che è connesso al nodo da eliminare.
     * In ordine, si eliminano:
     * - tutte le Richieste di Contribuzione aventi come oggetto quel nodo
     * - tutte le Richieste di Contribuzione Immagini aventi come target quel nodo
     * - tutte le Richieste di Contribuzione Itinerari aventi come tappa quel nodo: il nodo viene rimosso dalle tappe, e se l'itinerario rimane con meno di 2 tappe, l'intera richiesta viene eliminata
     * - tutti gli Itinerari aventi come tappa quel nodo: se l'itinerario rimane con meno di 2 tappe, l'itinerario stesso viene eliminato
     * - tutte le Immagini aventi come nodo target quel nodo
     * - tutte le Recensioni aventi come nodo target quel nodo
     * - tutte le Segnalazioni aventi come nodo target quel nodo
     * @param nodo il nodo da eliminare
     */
    private void cascadeNodo(ClsNodo nodo){
        ArrayList<ClsRDCNodo> rcdnodi = MockLocator.getMockRCD().get(null);
        Iterator<ClsRDCNodo> itrcd = rcdnodi.iterator();
        ArrayList<ClsRDCImmagine> rcdimmagini = MockLocator.getMockRCDImmagini().get(null);
        Iterator<ClsRDCImmagine> itrrcdimmagini = rcdimmagini.iterator();
        ArrayList<ClsRdcItinerario> rcdItinerari = MockLocator.getMockRCDI().get(null);
        Iterator<ClsRdcItinerario> itrrcdi = rcdItinerari.iterator();
        ArrayList<ClsImmagine> immagini = MockLocator.getMockImmagini().get(null);
        Iterator<ClsImmagine> itrimmagini = immagini.iterator();
        ArrayList<ClsRecensione> recensioni = MockLocator.getMockRecensioni().get(null);
        Iterator<ClsRecensione> itrecensioni = recensioni.iterator();
        ArrayList<ClsSegnalazione> segnalazioni = MockLocator.getMockSegnalazioni().get(null);
        Iterator<ClsSegnalazione> itsegnalazioni = segnalazioni.iterator();
        ArrayList<ClsItinerario> itinerari = MockLocator.getMockItinerari().get(null);
        Iterator<ClsItinerario> itritinerari = itinerari.iterator();

        //RCD Nodo
        while(itrcd.hasNext()){
            ClsRDCNodo r = itrcd.next();
            if((Objects.equals(r.getNewData(), nodo))||((r.getOldData()!=null)&&(Objects.equals(r.getOldData(), nodo)))){
                itrcd.remove();
            }
        }
        //RCD Immagini
        while(itrrcdimmagini.hasNext()){
            ClsRDCImmagine r = itrrcdimmagini.next();
            if(Objects.equals(r.getNewData().getIdNodoAssociato(), nodo.getId())){
                itrcd.remove();
            }
        }
        //RCD Itinerari
        while(itrrcdi.hasNext()){
            ClsRdcItinerario r = itrrcdi.next();
            ClsItinerario it = r.getNewData();
            it.getTappe().remove(nodo);
            if(it.getTappe().size()<2)
                itrrcdi.remove();
        }
        //Itinerari
        while(itritinerari.hasNext()){
            ClsItinerario i = itritinerari.next();
            i.getTappe().remove(nodo);
            if(i.getTappe().size()<2)
                itritinerari.remove();
        }
        //Immagini
        while(itrimmagini.hasNext()){
            ClsImmagine i = itrimmagini.next();
            if(Objects.equals(i.getIdNodoAssociato(), nodo.getId())){
                itrimmagini.remove();
            }
        }
        //Recensioni
        while(itrecensioni.hasNext()){
            ClsRecensione r = itrecensioni.next();
            if(Objects.equals(r.getIdNodoAssociato(), nodo.getId()))
                itrecensioni.remove();
        }
        //Segnalazioni
        while(itsegnalazioni.hasNext()){
            ClsSegnalazione s = itsegnalazioni.next();
            if(Objects.equals(s.getIdContenuto(), nodo.getId()))
                itsegnalazioni.remove();
        }
    }
}
