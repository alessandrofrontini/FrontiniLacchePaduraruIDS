package com.camerino.cli.mock;

import com.camerino.cli.menu.Input;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.camerino.cli.CliUtils.getResourceAsFile;

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
        itinerario.setId(idCounter);
        return itinerari.add(itinerario);
    }


    public ArrayList<ClsItinerario> get(Map<String, Object> filters) {
        if(filters!=null) {
            ArrayList<ClsItinerario> tmp = new ArrayList<>();
            if (filters.containsKey("idItinerario")) {
                tmp.add(getItinerarioById(Long.valueOf(filters.get("idItinerario").toString())));
                return tmp;
            }
            if (filters.containsKey("owner")) {
                tmp.addAll(getItinerarioByCreatore(Long.valueOf(filters.get("owner").toString())));
                return tmp;
            }
        }
        return itinerari;
    }

    private ClsItinerario getItinerarioById(Long id){
        List<ClsItinerario> tmp =  itinerari.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private List<ClsItinerario> getItinerarioByCreatore(Long idCreatore){
        List<ClsItinerario> tmp =  itinerari.stream().filter(n->n.getIdCreatore().equals(idCreatore)).toList();
        return tmp;
    }


    public boolean update(Map<String, Object> filters, ClsItinerario object) {
        if(filters.containsKey("idItinerario"))
            return modificaItinerario(Long.valueOf(filters.get("idItinerario").toString()), object);
        return false;
    }

    private boolean modificaItinerario(Long id, ClsItinerario itinerario){
        ClsItinerario tmp = getItinerarioById(id);
        int index = itinerari.indexOf(tmp);
        if(index<0)
            return false;
        itinerari.set(index, itinerario);
        return true;
    }


    public boolean insert(ClsItinerario object) {
        idCounter++;
        object.setId(idCounter);
        return itinerari.add(object);
    }


    public boolean delete(Map<String, Object> filters) {
        if (filters.containsKey("idItinerario"))
            return eliminaItinerario(Long.valueOf(filters.get("idItinerario").toString()));
        return false;
    }

    private boolean eliminaItinerario(Long id){
        return itinerari.remove(getItinerarioById(id));
    }
    //endregion

    public void leggiItinerari(){
        File f = getResourceAsFile("CLIsave/itinerari.csv");
        if(f.exists()){
            try{
                FileReader input = new FileReader(f);
                StringBuilder tutti = new StringBuilder();
                int c;
                while ((c = input.read()) != -1) {
                    tutti.append((char) c);
                }
                if(tutti.length()>1){
                    String [] acapo = Input.removeCarriageReturn(String.valueOf(tutti).split("\n"));
                    for(String itinerario:acapo){
                        ClsItinerario daAggiungere = new ClsItinerario();
                        String [] dati = itinerario.split(",");
                        daAggiungere.setId(Long.valueOf(dati[0]));
                        daAggiungere.setIdCreatore(Long.valueOf(dati[1]));
                        daAggiungere.setNome(dati[2]);
                        daAggiungere.setOrdinato(Boolean.parseBoolean(dati[3]));
                        ArrayList<ClsNodo> nodi = new ArrayList<>();
                        for(int j = 4; j<dati.length; j++){
                            HashMap<String, Object> filtro = new HashMap<>();
                            filtro.put("idNodo", dati[j]);
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
            FileWriter output = new FileWriter(getResourceAsFile("CLIsave/itinerari.csv"));
            StringBuilder daScrivere = new StringBuilder();
            for(ClsItinerario i:itinerari){
                daScrivere.append(i.getId() + ",");
                daScrivere.append(i.getIdCreatore() + ",");
                daScrivere.append(i.getNome() + ",");
                daScrivere.append(i.isOrdinato() + ",");
                for(ClsNodo n:i.getTappe()){
                    daScrivere.append(n.getId() + ",");
                }
                daScrivere.deleteCharAt(daScrivere.length()-1);
                daScrivere.append("\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    private void maxID(){
        for(ClsItinerario i:itinerari){
            if(i.getId()>this.idCounter)
                this.idCounter = i.getId();
        }
    }
}
