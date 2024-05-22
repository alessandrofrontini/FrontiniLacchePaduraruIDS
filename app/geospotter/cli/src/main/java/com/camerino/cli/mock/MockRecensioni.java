package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO: implementare
public class MockRecensioni implements IPersistenceModel<ClsRecensione>
{
    private ArrayList<ClsRecensione> recensioni = new ArrayList<ClsRecensione>();
    //TODO: add to vpp
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsRecensione> get(HashMap<String, Object> filters) {
        if(filters!=null) {
            if(recensioni.isEmpty())
                return null;
            ArrayList<ClsRecensione> r = new ArrayList<>();
            if (filters.containsKey("id")) {
                r.add(getRecensioneByID(filters.get("id").toString()));
                return r;
            }
            if (filters.containsKey("usernameCreatore")) {
                r.addAll(getRecensioneByUsername((String) filters.get("usernameCreatore")));
                return r;
            }

        }
        return this.recensioni;
    }

    private ClsRecensione getRecensioneByID(String id){
        List<ClsRecensione> tmp =  recensioni.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private List<ClsRecensione> getRecensioneByUsername(String username){
        List<ClsRecensione> tmp =  recensioni.stream().filter(n->n.getUsernameCreatore().equals(username)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    @Override
    public boolean update(HashMap<String, Object> filters, ClsRecensione object) {
        if(filters.containsKey("id"))
            return modificaRecensione(filters.get("id").toString(), object);
        return false;
    }
    private boolean modificaRecensione(String id, ClsRecensione r){
        ClsRecensione tmp = getRecensioneByID(r.getId());
        int index = recensioni.indexOf(tmp);
        if(index<0)
            return false;
        recensioni.set(index, r);
        return true;
    }

    @Override
    public boolean insert(ClsRecensione object) {
        return aggiungiRecensione(object);
    }
    private boolean aggiungiRecensione(ClsRecensione s){
        idCounter++;
        s.setId(""+idCounter);
        return recensioni.add(s);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        if(filters.containsKey("id"))
            return eliminaRecensione(filters.get("id").toString());
        return false;
    }
    private boolean eliminaRecensione(String id){
        return recensioni.remove(getRecensioneByID(id));
    }

    public void leggiRecensioni(){
        File f = new File("CLIsave/recensioni.csv");
        if(f.exists()) {
            try {
                FileReader input = new FileReader(f);
                StringBuilder tutte = new StringBuilder();
                int c;
                while ((c = input.read()) != -1) {
                    tutte.append((char) c);
                }
                if(tutte.length()>1) {
                    String tutteRecensioni = String.valueOf(tutte);
                    String[] recensioni = tutteRecensioni.split("\r\n");
                    for (String recensione : recensioni) {
                        String[] dati = recensione.split(",");
                        ClsRecensione nuova = new ClsRecensione();
                        nuova.setId(dati[0]);
                        nuova.setUsernameCreatore(dati[1]);
                        nuova.setIdContenutoAssociato(dati[2]);
                        nuova.setValutazione(Double.parseDouble(dati[3]));
                        nuova.setOggetto(dati[4]);
                        nuova.setContenuto(dati[5]);
                        aggiungiRecensione(nuova);
                    }
                    maxID();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void maxID(){
        for(ClsRecensione i:recensioni){
            if(Long.parseLong(i.getId())>this.idCounter)
                this.idCounter = Long.parseLong(i.getId());
        }
    }

    public void scriviRecensioni(){
        try{
            FileWriter output = new FileWriter("CLIsave/recensioni.csv");
            StringBuilder tutte = new StringBuilder();
            for(ClsRecensione r:recensioni){
                tutte.append(r.getId() + "," + r.getUsernameCreatore() + "," + r.getIdContenutoAssociato() + "," + r.getValutazione() + "," + r.getOggetto() + "," + r.getContenuto() + "\r\n");
            }
            output.write(String.valueOf(tutte));
            output.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    //endregion
}
