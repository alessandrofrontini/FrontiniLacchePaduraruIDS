package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO:implementare
public class MockImmagini implements IPersistenceModel<ClsImmagine>
{
    private ArrayList<ClsImmagine> immagini = new ArrayList<>();
    //TODO:add to vpp
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsImmagine> get(HashMap<String, Object> filters) {
        if(filters!=null) {
            ArrayList<ClsImmagine> tmp = new ArrayList<>();
            if (filters.containsKey("id")) {
                tmp.add(findById(filters.get("id").toString()));
                return tmp;
            }
        }
        return immagini;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsImmagine object) {
        if(filters.containsKey("id"))
            return updateImmagini(filters.get("id").toString(), object);
        return false;
    }
    private boolean updateImmagini(String id, ClsImmagine object) {
        ClsImmagine tmp = findById(id);
        int index = immagini.indexOf(tmp);
        if(index<0)
            return false;
        immagini.set(index, object);
        return true;
    }
    private ClsImmagine findById(String id) {
        List<ClsImmagine> tmp =
                immagini.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    @Override
    public boolean insert(ClsImmagine object) {
        idCounter++;
        object.setId(""+idCounter);
        return immagini.add(object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
    //endregion

    public void leggiImmagini(){
        File f = new File("CLIsave/immagini.csv");
        if(f.exists()) {
            try {
                FileReader input = new FileReader(f);
                StringBuilder file = new StringBuilder();
                int c;
                while ((c = input.read()) != -1) {
                    file.append((char) c);
                }
                String[] immagini = String.valueOf(file).split("\r\n");
                for (String immagine : immagini) {
                    String[] dati = immagine.split(",");
                    ClsImmagine daInserire = new ClsImmagine();
                    daInserire.setId(dati[0]);
                    daInserire.setIdCOntenutoAssociato(dati[1]);
                    daInserire.setUsernameCreatore(dati[2]);
                    daInserire.setURL(dati[3]);
                    this.immagini.add(daInserire);
                }
                maxID();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void maxID(){
        for(ClsImmagine rc:immagini){
            if(this.idCounter<Long.parseLong(rc.getId()))
                this.idCounter = Long.parseLong(rc.getId());
        }
    }
    public void scriviImmagini(){
        try{
            FileWriter output = new FileWriter("CLIsave/immagini.csv");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsImmagine i:immagini){
                daScrivere.append(i.getId() + "," + i.getIdCOntenutoAssociato() + "," + i.getUsernameCreatore() + "," + i.getURL() + "\r\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
