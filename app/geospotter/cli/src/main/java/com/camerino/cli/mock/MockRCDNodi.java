package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;

public class MockRCDNodi implements IPersistenceModel<ClsRichiestaAzioneDiContribuzione> {
    ArrayList<ClsRichiestaAzioneDiContribuzione> rcdi = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    @Override
    public ArrayList<ClsRichiestaAzioneDiContribuzione> get(HashMap<String, Object> filters) {
        ArrayList<ClsRichiestaAzioneDiContribuzione> tmp = new ArrayList<>();
        if(filters.containsKey("id")) {
            tmp.add(findById(filters.get("id").toString()));
            return tmp;
        }
        if(filters.containsKey("usernameCuratore")) {
            if(findLibere() != null){
                tmp.addAll(findLibere());
                return tmp;
            }
            else return null;
        }
        return rcdi;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRichiestaAzioneDiContribuzione object) {
        if(filters.containsKey("id"))
            return updateRCDI(filters.get("id").toString(), object);
        return false;
    }

    @Override
    public boolean insert(ClsRichiestaAzioneDiContribuzione object) {
        idCounter++;
        object.setId(""+idCounter);
        return rcdi.add(object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        ClsRichiestaAzioneDiContribuzione tmp = findById(filters.get("id").toString());
        return rcdi.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(String id, ClsRichiestaAzioneDiContribuzione object) {
        ClsRichiestaAzioneDiContribuzione tmp = findById(id);
        int index = rcdi.indexOf(tmp);
        if(index<0)
            return false;
        rcdi.set(index, object);
        return true;
    }

    private ClsRichiestaAzioneDiContribuzione findById(String id) {
        List<ClsRichiestaAzioneDiContribuzione> tmp =
                rcdi.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    private List<ClsRichiestaAzioneDiContribuzione> findLibere() {
        List<ClsRichiestaAzioneDiContribuzione> tmp =
                rcdi.stream().filter(n-> Objects.equals(n.getUsernameCuratore(), "null")).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    public void leggiRCD(){
        try{
            FileReader input = new FileReader("CLIsave/rcd.txt");
            StringBuilder tutti = new StringBuilder();
            int c;
            while((c= input.read())!=-1) {
                tutti.append((char) c);
            }
            String rcdstutti = String.valueOf(tutti);
            String [] rcds = rcdstutti.split("\r\n");
            if(!Objects.equals(rcds[0], "")) {
                for (String s : rcds) {
                    String[] dati = s.split(",");
                    ClsRichiestaAzioneDiContribuzione rcd = new ClsRichiestaAzioneDiContribuzione();
                    switch (dati[0]) {
                        case "ELIMINA_NODO":
                        case "MODIFICA_NODO":
                        case "INSERISCI_NODO":
                            rcd = leggiRichiestaNodo(dati);
                            break;
                        case "INSERISCI_IMMAGINE":
                            rcd = leggiRichiestaImmagine(dati);
                            break;
                    }
                    rcdi.add(rcd);
                }
                maxID(rcdi);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    private ClsRichiestaAzioneDiContribuzione leggiRichiestaNodo(String [] dati) throws ParseException {
        ClsRichiestaAzioneDiContribuzione r = new ClsRichiestaAzioneDiContribuzione();
        r.seteAzioneDiContribuzione(EAzioniDiContribuzione.valueOf(dati[0]));
        r.setUsernameCreatoreRichiesta(dati[1]);
        r.setId(dati[2]);
        ClsNodo n = new ClsNodo();
        n.setId(dati[3]);
        n.setIdComune(dati[4]);
        n.setaTempo(Boolean.parseBoolean(dati[5]));
        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        n.setDataFine(tmp.parse(dati[6]));
        n.setTipologiaNodo(ClsNodo.eTologiaNodo.valueOf(dati[7]));
        n.setUsernameCreatore(dati[8]);
        n.setNome(dati[9]);
        n.setPosizione(new Posizione(Double.parseDouble(dati[10]), Double.parseDouble(dati[11])));
        r.setDatiNodo(n);
        r.setUsernameCuratore(dati[12]);
        return r;
    }
    private ClsRichiestaAzioneDiContribuzione leggiRichiestaImmagine(String [] dati) throws Exception{
        ClsRichiestaAzioneDiContribuzione rcd = leggiRichiestaNodo(arraySplit(dati, 13));
        ClsImmagine i = new ClsImmagine();
        i.setId(dati[13]);
        i.setIdCOntenutoAssociato(dati[14]);
        i.setUsernameCreatore(dati[15]);
        i.setURL(dati[16]);
        rcd.setDatiImmagine(i);
        return rcd;
    }
    private String[] arraySplit(String [] s, int max){
        String [] array = new String[max];
        for(int i = 0; i<max; i++){
            array[i] = s[i];
        }
        return array;
    }

    public void scriviRCD(){
        try{
            FileWriter output = new FileWriter("CLIsave/rcd.txt");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsRichiestaAzioneDiContribuzione r:rcdi){
                daScrivere.append(r.geteAzioneDiContribuzione() + "," + r.getUsernameCreatoreRichiesta() + "," + r.getId() + ",");
                //dump del nodo se
                SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                daScrivere.append(r.getDatiNodo().getId() + "," + r.getDatiNodo().getIdComune() + "," + r.getDatiNodo().isaTempo() + "," + d.format(r.getDatiNodo().getDataFine()) + "," + r.getDatiNodo().getTipologiaNodo() + "," + r.getDatiNodo().getUsernameCreatore() + "," + r.getDatiNodo().getNome() + "," + r.getDatiNodo().getPosizione().getY() + "," + r.getDatiNodo().getPosizione().getX() + "," + r.getUsernameCuratore());
                //dump dell'immagine se la richiesta è corretta
                if(r.geteAzioneDiContribuzione().equals(EAzioniDiContribuzione.INSERISCI_IMMAGINE)) {
                    daScrivere.append("," + r.getDatiImmagine().getId() + "," + r.getDatiImmagine().getIdCOntenutoAssociato() + "," + r.getDatiImmagine().getUsernameCreatore() + "," + r.getDatiImmagine().getURL());
                }
                daScrivere.append("\r\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void maxID(ArrayList<ClsRichiestaAzioneDiContribuzione> r){
        for(ClsRichiestaAzioneDiContribuzione rc:r){
            if(this.idCounter<Long.parseLong(rc.getId()))
                this.idCounter = Long.parseLong(rc.getId());
        }
    }
    //endregion
}
