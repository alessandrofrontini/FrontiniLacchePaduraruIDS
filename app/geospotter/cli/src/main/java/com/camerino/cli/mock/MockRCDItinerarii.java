package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MockRCDItinerarii implements IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> {

    ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> rcdi = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    @Override
    public ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> get(HashMap<String, Object> filters) {
        ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> tmp = new ArrayList<>();
        if(filters.containsKey("id")) {
            tmp.add(findById((String) filters.get("id")));
            return tmp;
        }
        if(filters.containsKey("usernameCuratore")){
            if(findLibere() != null){
                tmp.addAll(findLibere());
                return tmp;
            }
            else return null;
        }
        return rcdi;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRichiestaAzioneDiContribuzioneItinerario object) {
        if(filters.containsKey("id"))
            return updateRCDI(filters.get("id").toString(), object);
        return false;
    }

    @Override
    public boolean insert(ClsRichiestaAzioneDiContribuzioneItinerario object) {
        idCounter++;
        object.setId(""+idCounter);
        return rcdi.add(object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        ClsRichiestaAzioneDiContribuzioneItinerario tmp = findById(filters.get("id").toString());
        return rcdi.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(String id, ClsRichiestaAzioneDiContribuzioneItinerario object) {
        ClsRichiestaAzioneDiContribuzioneItinerario tmp = findById(id);
        int index = rcdi.indexOf(tmp);
        if(index<0)
            return false;
        rcdi.set(index, object);
        return true;
    }

    private ClsRichiestaAzioneDiContribuzioneItinerario findById(String id) {
        List<ClsRichiestaAzioneDiContribuzioneItinerario> tmp =
                rcdi.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private List<ClsRichiestaAzioneDiContribuzioneItinerario> findLibere() {
        List<ClsRichiestaAzioneDiContribuzioneItinerario> tmp =
                rcdi.stream().filter(n-> Objects.equals(n.getUsernameCuratore(), "null")).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    public void scriviRCDItinerari(){
        try{
            FileWriter output = new FileWriter("CLIsave/rcdi.csv");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsRichiestaAzioneDiContribuzioneItinerario r:rcdi){
                daScrivere.append(r.geteAzioniDiContribuzione() +","+ r.getId() + "," + r.getUsernameCreatore() + ",");
                switch (r.geteAzioniDiContribuzione()){
                    case ELIMINA_ITINERARIO:
                    case INSERISCI_ITINERARIO: daScrivere = scriviNuovoItinerario(r, daScrivere); break;
                    case MODIFICA_ITINERARIO: daScrivere.append(r.getDatiItinerarioVecchio().getId() + ","); daScrivere = scriviNuovoItinerario(r, daScrivere); break;
                }
                daScrivere.append(r.getUsernameCuratore() + "\r\n");
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
                        String[] dati = richiesta.split(",");
                        ClsRichiestaAzioneDiContribuzioneItinerario r = new ClsRichiestaAzioneDiContribuzioneItinerario();
                        r.seteAzioniDiContribuzione(EAzioniDiContribuzione.valueOf(dati[0]));
                        r.setId(dati[1]);
                        r.setUsernameCreatore(dati[2]);
                        switch (dati[0]) {
                            case "INSERISCI_ITINERARIO":
                                case "ELIMINA_ITINERARIO":
                                    r.setDatiItinerarioNuovo(leggiItinerarioNuovo(3, dati.length-1, dati)); break;
                            case "MODIFICA_ITINERARIO": {
                                HashMap<String, Object> filtro = new HashMap<>();
                                filtro.put("id", dati[3]);
                                r.setDatiItinerarioVecchio(MockLocator.getMockItinerari().get(filtro).get(0));
                                r.setDatiItinerarioNuovo(leggiItinerarioNuovo(4, dati.length-1, dati));;
                                break;
                            }
                        }
                        r.setUsernameCuratore(dati[dati.length-1]);
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
    private StringBuilder scriviNuovoItinerario(ClsRichiestaAzioneDiContribuzioneItinerario r, StringBuilder daScrivere){
        daScrivere.append(r.getDatiItinerarioNuovo().getId() + ",");
        daScrivere.append(r.getDatiItinerarioNuovo().getNome() + ",");
        daScrivere.append(r.getDatiItinerarioNuovo().isOrdinato() + ",");
        daScrivere.append(r.getDatiItinerarioNuovo().getUsernameCreatore() + ",");
        for(ClsNodo n:r.getDatiItinerarioNuovo().getTappe()){
            daScrivere.append(n.getId() + ",");
        }
        return daScrivere;
    }

    private void maxID(){
        for(ClsRichiestaAzioneDiContribuzioneItinerario r:rcdi){
            if(Long.parseLong(r.getId())>this.idCounter)
                this.idCounter = Long.parseLong(r.getId());
        }
    }
    //endregion
}
