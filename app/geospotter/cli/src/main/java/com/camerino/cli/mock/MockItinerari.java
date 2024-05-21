package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MockItinerari implements IPersistenceModel<ClsItinerario> {

    private ArrayList<ClsItinerario> itinerari = new ArrayList<ClsItinerario>();
    private long idCounter = 0;
    private MockNodi mNodi;

    public MockItinerari(MockNodi mNodi){
        this.mNodi = mNodi;
    }

    //region CRUD metodi
    public boolean inserisciItinerario(ClsItinerario itinerario){
        idCounter++;
        itinerario.setId(""+idCounter);
        return itinerari.add(itinerario);
    }

    @Override
    public ArrayList<ClsItinerario> get(HashMap<String, Object> filters) {
        if(filters!=null) {
            ArrayList<ClsItinerario> tmp = new ArrayList<>();
            if (filters.containsKey("id")) {
                tmp.add(getItinerarioById(filters.get("id").toString()));
                return tmp;
            }
            if (filters.containsKey("usernameCreatore")) {
                tmp.add(getItinerarioByCreatore(filters.get("usernameCreatore").toString()));
                return tmp;
            }
        }
        return itinerari;
    }

    private ClsItinerario getItinerarioById(String id){
        List<ClsItinerario> tmp =  itinerari.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private ClsItinerario getItinerarioByCreatore(String username){
        List<ClsItinerario> tmp =  itinerari.stream().filter(n->n.getUsernameCreatore().equals(username)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsItinerario object) {
        if(filters.containsKey("id"))
            return modificaItinerario(filters.get("id").toString(), object);
        return false;
    }

    private boolean modificaItinerario(String id, ClsItinerario itinerario){
        ClsItinerario tmp = getItinerarioById(id);
        int index = itinerari.indexOf(tmp);
        if(index<0)
            return false;
        itinerari.set(index, itinerario);
        return true;
    }

    @Override
    public boolean insert(ClsItinerario object) {
        idCounter++;
        object.setId(""+idCounter);
        return itinerari.add(object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        if (filters.containsKey("id"))
            return eliminaItinerario(filters.get("id").toString());
        return false;
    }

    private boolean eliminaItinerario(String id){
        return itinerari.remove(getItinerarioById(id));
    }
    //endregion

    public void leggiItinerari(){
        File f = new File("CLIsave/itinerari.csv");
        if(f.exists()){
            try{
                FileReader input = new FileReader(f);
                StringBuilder tutti = new StringBuilder();
                int c;
                while ((c = input.read()) != -1) {
                    tutti.append((char) c);
                }
                if(tutti.length()>1){
                    String [] acapo = String.valueOf(tutti).split("\r\n");
                    for(String itinerario:acapo){
                        ClsItinerario daAggiungere = new ClsItinerario();
                        String [] dati = itinerario.split(",");
                        daAggiungere.setId(dati[0]);
                        daAggiungere.setUsernameCreatore(dati[1]);
                        daAggiungere.setNome(dati[2]);
                        daAggiungere.setOrdinato(Boolean.parseBoolean(dati[3]));
                        ArrayList<ClsNodo> nodi = new ArrayList<>();
                        for(int j = 4; j<dati.length; j++){
                            HashMap<String, Object> filtro = new HashMap<>();
                            filtro.put("id", dati[j]);
                            nodi.add(MockLocator.getMockNodi().get(filtro).get(0));
                        }
                        daAggiungere.setTappe(nodi);
                        itinerari.add(daAggiungere);
                    }
                }
                maxID();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void scriviItinerari(){
        try {
            FileWriter output = new FileWriter("CLIsave/itinerari.csv");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsItinerario i:itinerari){
                daScrivere.append(i.getId() + ",");
                daScrivere.append(i.getUsernameCreatore() + ",");
                daScrivere.append(i.getNome() + ",");
                daScrivere.append(i.isOrdinato() + ",");
                for(ClsNodo n:i.getTappe()){
                    daScrivere.append(n.getId() + ",");
                }
                daScrivere.deleteCharAt(daScrivere.length()-1);
                daScrivere.append("\r\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    private void maxID(){
        for(ClsItinerario i:itinerari){
            if(Long.parseLong(i.getId())>this.idCounter)
                this.idCounter = Long.parseLong(i.getId());
        }
    }
}
