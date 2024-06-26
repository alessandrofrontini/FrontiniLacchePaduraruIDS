package com.camerino.cli.mock;

import com.camerino.cli.menu.Input;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MockRecensioni implements IPersistenceModel<ClsRecensione>
{
    private ArrayList<ClsRecensione> recensioni = new ArrayList<ClsRecensione>();

    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsRecensione> get(Map<String, Object> filters) {
        if(filters!=null) {
            if(recensioni.isEmpty())
                return null;
            ArrayList<ClsRecensione> r = new ArrayList<>();
            if (filters.containsKey("idRecensione")) {
                r.add(getRecensioneByID(Long.valueOf(filters.get("idRecensione").toString())));
                return r;
            }
            if (filters.containsKey("idCreatore")) {
                r.addAll(getRecensioneByUsername(Long.valueOf((String) filters.get("idCreatore"))));
                return r;
            }

        }
        return this.recensioni;
    }

    private ClsRecensione getRecensioneByID(Long id){
        List<ClsRecensione> tmp =  recensioni.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private List<ClsRecensione> getRecensioneByUsername(Long idCreatore){
        List<ClsRecensione> tmp =  recensioni.stream().filter(n->n.getIdCreatore().equals(idCreatore)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    @Override
    public boolean update(Map<String, Object> filters, ClsRecensione object) {
        if(filters.containsKey("idRecensione"))
            return modificaRecensione(Long.valueOf(filters.get("idRecensione").toString()), object);
        return false;
    }
    private boolean modificaRecensione(Long id, ClsRecensione r){
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
        s.setId(idCounter);
        return recensioni.add(s);
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        if(filters.containsKey("idRecensione"))
            return eliminaRecensione(Long.valueOf(filters.get("idRecensione").toString()));
        return false;
    }
    private boolean eliminaRecensione(Long id){
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
                    String[] recensioni = Input.removeCarriageReturn(tutteRecensioni.split("\n"));
                    for (String recensione : recensioni) {
                        String[] dati = recensione.split(",");
                        ClsRecensione nuova = new ClsRecensione();
                        nuova.setId(Long.valueOf(dati[0]));
                        nuova.setIdCreatore(Long.valueOf(dati[1]));
                        nuova.setIdNodoAssociato(Long.valueOf(dati[2]));
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
            if(i.getId()>this.idCounter)
                this.idCounter = i.getId();
        }
    }

    public void scriviRecensioni(){
        try{
            FileWriter output = new FileWriter("CLIsave/recensioni.csv");
            StringBuilder tutte = new StringBuilder();
            for(ClsRecensione r:recensioni){
                tutte.append(r.getId() + "," + r.getIdCreatore() + "," + r.getIdNodoAssociato() + "," + r.getValutazione() + "," + r.getOggetto() + "," + r.getContenuto() + "\n");
            }
            output.write(String.valueOf(tutte));
            output.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    //endregion
}
