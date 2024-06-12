package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.azioni.EStatusRDC;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class MockRCDItinerari implements IPersistenceModel<ClsRdcItinerario> {

    ArrayList<ClsRdcItinerario> rcdi = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    public ArrayList<ClsRdcItinerario> get(Map<String, Object> filters) {
        if(filters!=null) {
            ArrayList<ClsRdcItinerario> tmp = new ArrayList<>();
            if (filters.containsKey("idRDCItinerario")) {
                tmp.add(findById(Long.valueOf(filters.get("idRDCItinerario").toString())));
                return tmp;
            }
            if (filters.containsKey("usernameCuratore")) {
                if (findLibere() != null) {
                    tmp.addAll(findLibere());
                    return tmp;
                } else return null;
            }
            if(filters.containsKey("idValidazione")){
                ClsRdcItinerario rdcIt = findById(Long.valueOf(filters.get("idValidazione").toString()));
                if((filters.containsKey("accetta"))&&(filters.get("accetta").toString().equals("true"))){
                    switch (rdcIt.getTipo()){
                        case INSERISCI_ITINERARIO:{
                            rdcIt.getNewData().setIdCreatore(rdcIt.getCreatore().getId());
                            rdcIt.setStato(EStatusRDC.ACCETTATO);
                            MockLocator.getMockItinerari().insert(rdcIt.getNewData());
                            break;
                        }
                        case MODIFICA_ITINERARIO:{
                            HashMap<String, Object> filtro = new HashMap<>();
                            filtro.put("idItinerario", rdcIt.getOldData().getId());
                            rdcIt.getNewData().setIdCreatore(rdcIt.getCreatore().getId());
                            rdcIt.getNewData().setId(rdcIt.getOldData().getId());
                            rdcIt.setStato(EStatusRDC.ACCETTATO);
                            MockLocator.getMockItinerari().update(filtro, rdcIt.getNewData());
                            break;
                        }
                        case ELIMINA_ITINERARIO:{
                            HashMap<String, Object> filtro = new HashMap<>();
                            filtro.put("id", rdcIt.getOldData().getId());
                            rdcIt.setStato(EStatusRDC.ACCETTATO);
                            MockLocator.getMockItinerari().delete(filtro);
                            break;
                        }
                    }
                }
                else rdcIt.setStato(EStatusRDC.RIFUTATO);
            }
        }
        return rcdi;
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsRdcItinerario object) {
        if(filters.containsKey("id"))
            return updateRCDI(Long.valueOf(filters.get("id").toString()), object);
        return false;
    }

    @Override
    public boolean insert(ClsRdcItinerario object) {
        idCounter++;
        object.setIdRichiesta(idCounter);
        return rcdi.add(object);
    }


    public boolean delete(Map<String, Object> filters) {
        ClsRdcItinerario tmp = findById(Long.valueOf(filters.get("id").toString()));
        return rcdi.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(Long id, ClsRdcItinerario object) {
        ClsRdcItinerario tmp = findById(id);
        int index = rcdi.indexOf(tmp);
        if(index<0)
            return false;
        rcdi.set(index, object);
        return true;
    }

    private ClsRdcItinerario findById(Long id) {
        List<ClsRdcItinerario> tmp =
                rcdi.stream().filter(n->n.getIdRichiesta().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private List<ClsRdcItinerario> findLibere() {
        List<ClsRdcItinerario> tmp =
                rcdi.stream().filter(n-> Objects.equals(n.getStato(), EStatusRDC.NUOVO)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    public void scriviRCDItinerari(){
        try{
            FileWriter output = new FileWriter("CLIsave/rcdi.csv");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsRdcItinerario r:rcdi){
                daScrivere.append(r.getTipo() +","+ r.getIdRichiesta() + "," + r.getCreatore().getId()+ ",");
                switch (r.getTipo()){
                    case ELIMINA_ITINERARIO: daScrivere = scriviNuovoItinerario(r.getOldData(), daScrivere); break;
                    case INSERISCI_ITINERARIO: daScrivere = scriviNuovoItinerario(r.getNewData(), daScrivere); break;
                    case MODIFICA_ITINERARIO: {
                        ClsItinerario i = r.getOldData();
                        daScrivere.append(i.getId() + ",");
                        daScrivere = scriviNuovoItinerario(r.getNewData(), daScrivere); break;}
                }
                daScrivere.append(r.getStato()+ "\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void leggiRCDItinerari(){
        File f = new File("CLIsave/rcdi.csv");
        if(f.exists()) {
            try {
                FileReader input = new FileReader(f);
                StringBuilder tutti = new StringBuilder();
                int c;
                while ((c = input.read()) != -1) {
                    tutti.append((char) c);
                }
                if(tutti.length()>1){

                    String[] acapo = String.valueOf(tutti).split("\n");
                    for (String richiesta : acapo) {
                        HashMap<String, Object> filtro = new HashMap<>();
                        String[] dati = richiesta.split(",");
                        ClsRdcItinerario r = new ClsRdcItinerario();
                        switch (dati[0]) {
                            case "INSERISCI_ITINERARIO":{
                                ClsItinerario i = leggiItinerarioNuovo(3, dati.length - 1, dati);
                                ClsRdcItinerario tmp = new ClsRdcItinerario(null, i);
                                r = tmp;
                                break;
                            }
                                case "ELIMINA_ITINERARIO": {
                                    ClsItinerario i = leggiItinerarioNuovo(3, dati.length - 1, dati);
                                    ClsRdcItinerario tmp = new ClsRdcItinerario(i, null);
                                    r = tmp;
                                    break;
                                }
                            case "MODIFICA_ITINERARIO": {
                                filtro = new HashMap<>();
                                filtro.put("id", dati[3]);
                                ClsItinerario itnew = leggiItinerarioNuovo(4, dati.length-1, dati);
                                ClsItinerario itold = MockLocator.getMockItinerari().get(filtro).get(0);
                                ClsRdcItinerario tmp = new ClsRdcItinerario(itold, itnew);
                                r = tmp;
                                break;
                            }
                        }
                        r.setTipo(EAzioniDiContribuzione.valueOf(dati[0]));
                        r.setIdRichiesta(Long.valueOf(dati[1]));
                        filtro.put("idUtente", dati[2]);
                        r.setCreatore( MockLocator.getMockTuristi().get(filtro).get(0));
                        r.setStato(EStatusRDC.valueOf(dati[dati.length-1]));
                        rcdi.add(r);
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            maxID();
        }
    }
    private ClsItinerario leggiItinerarioNuovo(int i, int fine, String[] dati){
        ClsItinerario it = new ClsItinerario();
            it.setId(Long.valueOf(dati[i]));
            it.setNome(dati[i+1]);
            it.setOrdinato(Boolean.parseBoolean(dati[i+2]));
            it.setIdCreatore(Long.valueOf(dati[i+3]));
            ArrayList<ClsNodo> tappe = new ArrayList<>();
            for(int j = i+4; j<fine; j++){
                HashMap<String,Object> filtro = new HashMap<>();
                filtro.put("idNodo", dati[j]);
                tappe.add(MockLocator.getMockNodi().get(filtro).get(0));
            }
            it.setTappe(tappe);
        return it;
    }
    private StringBuilder scriviNuovoItinerario(ClsItinerario itinerario, StringBuilder daScrivere){
        daScrivere.append(itinerario.getId() + ",");
        daScrivere.append(itinerario.getNome() + ",");
        daScrivere.append(itinerario.isOrdinato() + ",");
        daScrivere.append(itinerario.getIdCreatore() + ",");
        for(ClsNodo n:itinerario.getTappe()){
            daScrivere.append(n.getId() + ",");
        }
        return daScrivere;
    }

    private void maxID(){
        for(ClsRdcItinerario r:rcdi){
            if(r.getIdRichiesta()>this.idCounter)
                this.idCounter = r.getIdRichiesta();
        }
    }
    //endregion
}
