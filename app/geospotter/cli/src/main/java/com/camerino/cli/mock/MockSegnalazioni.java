package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO: implementare
public class MockSegnalazioni implements IPersistenceModel<ClsSegnalazione>
{
    private ArrayList<ClsSegnalazione> segnalazioni = new ArrayList<ClsSegnalazione>();

    //TODO:add to vpp
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsSegnalazione> get(HashMap<String, Object> filters) {
        ArrayList<ClsSegnalazione> s = new ArrayList<>();
        if(filters.containsKey("id")){
            s.add(getSegnalazioneByID(filters.get("id").toString()));
            return s;
        }
        return this.segnalazioni;
    }
    private ClsSegnalazione getSegnalazioneByID(String id){
        List<ClsSegnalazione> tmp =  segnalazioni.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    @Override
    public boolean update(HashMap<String, Object> filters, ClsSegnalazione object) {
        if(filters.containsKey("id"))
            return modificaSegnalazione(filters.get("id").toString(), object);
        return false;
    }
    private boolean modificaSegnalazione(String id, ClsSegnalazione s){
        ClsSegnalazione tmp = getSegnalazioneByID(s.getId());
        int index = segnalazioni.indexOf(tmp);
        if(index<0)
            return false;
        segnalazioni.set(index, s);
        return true;
    }

    @Override
    public boolean insert(ClsSegnalazione object) {
        return aggiungiSegnalazione(object);
    }
    private boolean aggiungiSegnalazione(ClsSegnalazione s){
        idCounter++;
        s.setId(""+idCounter);
        return segnalazioni.add(s);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        if(filters.containsKey("id"))
            return eliminaSegnalazione(filters.get("id").toString());

        return false;
    }
    private boolean eliminaSegnalazione(String id){
        return segnalazioni.remove(getSegnalazioneByID(id));
    }

    public void leggiSegnalazioni(){
        try{
            FileReader input = new FileReader("segnalazioni.txt");
            StringBuilder tutte = new StringBuilder();
            int c;
            while((c= input.read())!=-1) {
                tutte.append((char) c);
            }
            String tuttesegnalazioni = String.valueOf(tutte);
            String [] segnalazioni = tuttesegnalazioni.split("\r\n");
            for(String segnalazione:segnalazioni){
                String [] dati = segnalazione.split(",");
                ClsSegnalazione daAggiungere = new ClsSegnalazione();
                daAggiungere.setId(dati[0]);
                daAggiungere.setIdContenuto(dati[1]);
                daAggiungere.setIdCuratore(dati[2]);
                daAggiungere.setDescrizione(dati[3]);
                daAggiungere.setIdUtente(dati[4]);
                aggiungiSegnalazione(daAggiungere);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void scriviSegnalazioni(){
        try{
            FileWriter output = new FileWriter("segnalazioni.txt");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsSegnalazione s:segnalazioni){
                output.append(s.getId() + "," + s.getIdContenuto() + "," + s.getIdCuratore() + "," + s.getDescrizione() + "," + s.getIdUtente() + "\r\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
