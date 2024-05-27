package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRDCItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MockRCDItinerari implements IPersistenceModel<ClsRDCItinerario> {

    ArrayList<ClsRDCItinerario> rcdi = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    @Override
    public ArrayList<ClsRDCItinerario> get(HashMap<String, Object> filters) {
        if(filters!=null) {
            ArrayList<ClsRDCItinerario> tmp = new ArrayList<>();
            if (filters.containsKey("id")) {
                tmp.add(findById((String) filters.get("id")));
                return tmp;
            }
            if (filters.containsKey("usernameCuratore")) {
                if (findLibere() != null) {
                    tmp.addAll(findLibere());
                    return tmp;
                } else return null;
            }
        }
        return rcdi;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRDCItinerario object) {
        if(filters.containsKey("id"))
            return updateRCDI(filters.get("id").toString(), object);
        return false;
    }

    @Override
    public boolean insert(ClsRDCItinerario object) {
        idCounter++;
        object.setIdRichiesta(""+idCounter);
        return rcdi.add(object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        ClsRDCItinerario tmp = findById(filters.get("id").toString());
        return rcdi.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(String id, ClsRDCItinerario object) {
        ClsRDCItinerario tmp = findById(id);
        int index = rcdi.indexOf(tmp);
        if(index<0)
            return false;
        rcdi.set(index, object);
        return true;
    }

    private ClsRDCItinerario findById(String id) {
        List<ClsRDCItinerario> tmp =
                rcdi.stream().filter(n->n.getIdRichiesta().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private List<ClsRDCItinerario> findLibere() {
        List<ClsRDCItinerario> tmp =
                rcdi.stream().filter(n-> Objects.equals(n.getResponsabile(), null)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    public void scriviRCDItinerari(){
        try{
            FileWriter output = new FileWriter("CLIsave/rcdi.csv");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsRDCItinerario r:rcdi){
                daScrivere.append(r.getTipo() +","+ r.getIdRichiesta() + "," + r.getCreatore().getCredenziali().getUsername() + ",");
                switch (r.getTipo()){
                    case ELIMINA_ITINERARIO:
                    case INSERISCI_ITINERARIO: daScrivere = scriviNuovoItinerario(r.getNewData(), daScrivere); break;
                    case MODIFICA_ITINERARIO: {
                        ClsItinerario i = r.getOldData();
                        daScrivere.append(i.getId() + ",");
                        daScrivere = scriviNuovoItinerario(r.getNewData(), daScrivere); break;}
                }
                daScrivere.append((r.getResponsabile()!=null? r.getResponsabile().getCredenziali().getUsername() : "null") + "\r\n");
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

                    String[] acapo = String.valueOf(tutti).split("\r\n");
                    for (String richiesta : acapo) {
                        HashMap<String, Object> filtro = new HashMap<>();
                        String[] dati = richiesta.split(",");
                        ClsRDCItinerario r = new ClsRDCItinerario();
                        switch (dati[0]) {
                            case "INSERISCI_ITINERARIO":
                                case "ELIMINA_ITINERARIO": {
                                    ClsItinerario i = leggiItinerarioNuovo(3, dati.length - 1, dati);
                                    ClsRDCItinerario tmp = new ClsRDCItinerario(null,i);
                                    r = tmp;
                                    break;
                                }
                            case "MODIFICA_ITINERARIO": {
                                filtro = new HashMap<>();
                                filtro.put("id", dati[3]);
                                ClsItinerario itnew = leggiItinerarioNuovo(4, dati.length-1, dati);
                                ClsItinerario itold = MockLocator.getMockItinerari().get(filtro).get(0);
                                ClsRDCItinerario tmp = new ClsRDCItinerario(itold, itnew);
                                r = tmp;
                                break;
                            }
                        }
                        r.setTipo(EAzioniDiContribuzione.valueOf(dati[0]));
                        r.setIdRichiesta(dati[1]);
                        filtro.put("username", dati[2]);
                        r.setCreatore( MockLocator.getMockTuristi().get(filtro).get(0));
                        filtro = new HashMap<>();
                        filtro.put("username", dati[dati.length-1]);
                        if(!Objects.equals(dati[dati.length - 1], "null"))
                            r.setResponsabile((ClsCuratore) MockLocator.getMockTuristi().get(filtro).get(0));
                        else r.setResponsabile(null);
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
            it.setId(dati[i]);
            it.setNome(dati[i+1]);
            it.setOrdinato(Boolean.parseBoolean(dati[i+2]));
            it.setUsernameCreatore(dati[i+3]);
            ArrayList<ClsNodo> tappe = new ArrayList<>();
            for(int j = i+4; j<fine; j++){
                HashMap<String,Object> filtro = new HashMap<>();
                filtro.put("id", dati[j]);
                tappe.add(MockLocator.getMockNodi().get(filtro).get(0));
            }
            it.setTappe(tappe);
        return it;
    }
    private StringBuilder scriviNuovoItinerario(ClsItinerario itinerario, StringBuilder daScrivere){
        daScrivere.append(itinerario.getId() + ",");
        daScrivere.append(itinerario.getNome() + ",");
        daScrivere.append(itinerario.isOrdinato() + ",");
        daScrivere.append(itinerario.getUsernameCreatore() + ",");
        for(ClsNodo n:itinerario.getTappe()){
            daScrivere.append(n.getId() + ",");
        }
        return daScrivere;
    }

    private void maxID(){
        for(ClsRDCItinerario r:rcdi){
            if(Long.parseLong(r.getIdRichiesta())>this.idCounter)
                this.idCounter = Long.parseLong(r.getIdRichiesta());
        }
    }
    //endregion
}
