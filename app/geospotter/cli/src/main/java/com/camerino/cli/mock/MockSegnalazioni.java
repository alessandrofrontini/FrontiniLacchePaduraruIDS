package com.camerino.cli.mock;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

//TODO: implementare
public class MockSegnalazioni implements IPersistenceModel<ClsSegnalazione>
{
    private ArrayList<ClsSegnalazione> segnalazioni = new ArrayList<ClsSegnalazione>();

    //TODO:add to vpp
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsSegnalazione> get(Map<String, Object> filters) {
        if(filters!=null) {
            ArrayList<ClsSegnalazione> s = new ArrayList<>();
            if (filters.containsKey("id")) {
                s.add(getSegnalazioneByID(Long.valueOf(filters.get("id").toString())));
                return s;
            }
            if (filters.containsKey("idComune")) {
                s.addAll(getSegnalazioneByIDComune(Long.valueOf(filters.get("idComune").toString())));
                return s;
            }
        }
        return this.segnalazioni;
    }
    private ClsSegnalazione getSegnalazioneByID(Long id){
        List<ClsSegnalazione> tmp =  segnalazioni.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    private List<ClsSegnalazione>getSegnalazioneByIDComune(Long id){
        HashMap<String, Object> filtro = new HashMap<>();
        List<ClsSegnalazione> tmp = new ArrayList<>();
        for(ClsSegnalazione s:segnalazioni){
            filtro.put("id", s.getIdContenuto());
            if(Objects.equals(MockLocator.getMockNodi().get(filtro).get(0).getIdComuneAssociato(), id)){
                tmp.add(s);
            }
        }
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    @Override
    public boolean update(Map<String, Object> filters, ClsSegnalazione object) {
        if(filters.containsKey("id"))
            return modificaSegnalazione(Long.valueOf(filters.get("id").toString()), object);
        return false;
    }
    private boolean modificaSegnalazione(Long id, ClsSegnalazione s){
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
        s.setId(idCounter);
        return segnalazioni.add(s);
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        if(filters.containsKey("id"))
            return eliminaSegnalazione(Long.valueOf(filters.get("id").toString()));

        return false;
    }
    private boolean eliminaSegnalazione(Long id){
        return segnalazioni.remove(getSegnalazioneByID(id));
    }

    public void leggiSegnalazioni(){
        File f = new File("CLIsave/segnalazioni.txt");
        if(f.exists()) {
            try {
                FileReader input = new FileReader(f);
                StringBuilder tutte = new StringBuilder();
                int c;
                while ((c = input.read()) != -1) {
                    tutte.append((char) c);
                }
                if(tutte.length()>1) {
                    String tuttesegnalazioni = String.valueOf(tutte);
                    String[] segnalazioni = tuttesegnalazioni.split("\n");
                    for (String segnalazione : segnalazioni) {
                        String[] dati = segnalazione.split(",");
                        ClsSegnalazione daAggiungere = new ClsSegnalazione();
                        daAggiungere.setId(Long.valueOf(dati[0]));
                        daAggiungere.setIdContenuto(Long.valueOf(dati[1]));
                        daAggiungere.setIdCuratore(Long.valueOf(dati[2]));
                        daAggiungere.setDescrizione(dati[3]);
                        aggiungiSegnalazione(daAggiungere);
                    }
                    maxID();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void maxID(){
        for(ClsSegnalazione i:segnalazioni){
            if(i.getId()>this.idCounter)
                this.idCounter = i.getId();
        }
    }
    public void scriviSegnalazioni(){
        try{
            FileWriter output = new FileWriter("CLIsave/segnalazioni.txt");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsSegnalazione s:segnalazioni){
                output.append(s.getId() + "," + s.getIdContenuto() + "," + s.getIdCuratore() + "," + s.getDescrizione() + "\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
