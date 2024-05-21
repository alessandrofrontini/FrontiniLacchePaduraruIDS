package com.camerino.cli.mock;

import com.camerino.cli.loggers.ClsConsoleLogger;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;

public class MockNodi implements IPersistenceModel<ClsNodo> {

    private ArrayList<ClsNodo> nodi = new ArrayList<ClsNodo>();
    private long idCounter = 0;

    //region CRUD metodi
        @Override
        public ArrayList<ClsNodo> get(HashMap<String, Object> filters) {
            if(filters!=null) {
                ArrayList<ClsNodo> nodi = new ArrayList<>();
                if (filters.containsKey("id")) {
                    nodi.add(getNodoById(filters.get("id").toString()));
                    return nodi;
                }
                if (filters.containsKey("idComune")) {
                    nodi.addAll(filterByIdComune(filters.get("idComune")));
                    return nodi;
                }
                if (filters.containsKey("usernameCreatore")) {
                    nodi.addAll(filterByUsername(filters.get("usernameCreatore")));
                    return nodi;
                }
            }
            return this.nodi;
        }
        private List<ClsNodo> filterByIdComune(Object id)
        {
            List<ClsNodo> tmp =
                    nodi.stream().filter(n->n.getIdComune().equals(id.toString())).toList();
            return tmp;
        }

        private ClsNodo getNodoById(String id){
            List<ClsNodo> tmp =  nodi.stream().filter(n->n.getId().equals(id)).toList();
            if(tmp.isEmpty())
                return null;
            return tmp.get(0);
        }
        private List<ClsNodo> filterByUsername(Object user)
        {
            List<ClsNodo> tmp =
                    nodi.stream().filter(n->n.getUsernameCreatore().equals(user.toString())).toList();
            return tmp;
        }

        @Override
        public boolean update(HashMap<String, Object> filters, ClsNodo object) {
            if(filters.containsKey("id"))
                return modificaNodo(filters.get("id").toString(), object);
            return false;
        }

        private boolean modificaNodo(String id, ClsNodo nodo){
            ClsNodo tmp = getNodoById(id);
            int index = nodi.indexOf(tmp);
            if(index<0)
                return false;
            nodi.set(index, nodo);
            return true;
        }

        @Override
        public boolean insert(ClsNodo object) {
            return aggiungiNodo(object);
        }

        private boolean aggiungiNodo(ClsNodo nodo){
            idCounter++;
            nodo.setId(""+idCounter);
            return nodi.add(nodo);
        }

        @Override
        public boolean delete(HashMap<String, Object> filters) {
            if(filters.containsKey("id"))
                return eliminaNodo(filters.get("id").toString());

            return false;
        }

        private boolean eliminaNodo(String id){
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
                        String[] nodiSingoli = tuttiNodi.split("\r\n");
                        for (String nodoSingolo : nodiSingoli) {
                            ClsNodo daAggiungere = new ClsNodo();
                            String[] datiNodo = nodoSingolo.split(",");
                            daAggiungere.setId(datiNodo[0]);
                            daAggiungere.setIdComune(datiNodo[1]);
                            daAggiungere.setaTempo(Boolean.parseBoolean(datiNodo[2]));
                            SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
                            daAggiungere.setDataFine(tmp.parse(datiNodo[3]));
                            daAggiungere.setTipologiaNodo(ClsNodo.eTologiaNodo.valueOf(datiNodo[4]));
                            daAggiungere.setUsernameCreatore(datiNodo[5]);
                            daAggiungere.setNome(datiNodo[6]);
                            daAggiungere.setPosizione(new Posizione(Double.parseDouble(datiNodo[7]), Double.parseDouble(datiNodo[8])));
                            aggiungiNodo(daAggiungere);
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
                if(Long.parseLong(i.getId())>this.idCounter)
                    this.idCounter = Long.parseLong(i.getId());
            }
        }
        public void scriviNodi(){
            try{
                FileWriter output = new FileWriter("CLIsave/nodi.csv");
                StringBuilder daScrivere = new StringBuilder();
                for(ClsNodo nodo:nodi){
                    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

                    daScrivere.append(nodo.getId() + "," + nodo.getIdComune() + "," + nodo.isaTempo() + "," + d.format(nodo.getDataFine()) + "," + nodo.getTipologiaNodo() + "," + nodo.getUsernameCreatore() + "," + nodo.getNome() + "," + nodo.getPosizione().getY() + "," + nodo.getPosizione().getX() + "\r\n");
                }
                output.write(String.valueOf(daScrivere));
                output.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
}
