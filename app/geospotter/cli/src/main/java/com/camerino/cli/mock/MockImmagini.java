package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO:implementare
public class MockImmagini implements IPersistenceModel<ClsImmagine>
{
    private ArrayList<ClsImmagine> immagini = new ArrayList<>();
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsImmagine> get(HashMap<String, Object> filters) {
        ArrayList<ClsImmagine> tmp = new ArrayList<ClsImmagine>();

        if(filters != null)
        {
            if(filters.containsKey("id"))
            {
                tmp.add(getImmagineById(filters.get("id").toString()));
                return tmp;
            }
        }

        return this.immagini;
    }
    @Override
    public boolean update(HashMap<String, Object> filters, ClsImmagine object) {
        if(object.getUsernameCreatore() == getImmagineById(filters.get("id").toString()).getUsernameCreatore())
        {
            if(filters.containsKey("id"))
            {
                return modificaImmagine(filters.get("id").toString(), object);
            }
        }

        return false;
    }
    @Override
    public boolean insert(ClsImmagine object) {
        if(!this.immagini.contains(object))
        {
            return aggiungiImmagine(object);
        }
        return false;
    }
    @Override
    public boolean delete(HashMap<String, Object> filters) {
        //TODO: come faccio il controllo sullo username?
        if(filters.containsKey("id"))
            return eliminaImmagine(filters.get("id").toString());
        return false;
    }
    //endregion

    //region  ------------------------------ Metodi Privati (CRUD) ------------------------------------------
    private ClsImmagine getImmagineById(String id) {
        List<ClsImmagine> tmp =  immagini.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private boolean modificaImmagine(String id, ClsImmagine immagine){
        ClsImmagine tmp = getImmagineById(id);
        int index = immagini.indexOf(tmp);
        if(index<0)
            return false;
        immagini.set(index, immagine);
        return true;
    }
    private boolean aggiungiImmagine(ClsImmagine immagine){
        idCounter++;
        //nodo.setId(""+idCounter);
        return immagini.add(immagine);
    }
    private boolean eliminaImmagine(String id){
        return immagini.remove(getImmagineById(id));
    }
    //endregion

    //region  ------------------------------ Metodi Privati (UTILS) ------------------------------------------
    private void generaImmagini() {
        ClsImmagine immagine1 = new ClsImmagine();
        immagine1.setId("1");
        immagine1.setURL("https://picsum.photos/200");
        immagine1.setIdCOntenutoAssociato("1");//Comune
        immagine1.setUsernameCreatore("");

        ClsImmagine immagine2 = new ClsImmagine();
        immagine2.setId("2");
        immagine2.setURL("https://picsum.photos/200");
        immagine2.setIdCOntenutoAssociato("2");//Nodo
        immagine2.setUsernameCreatore("");

        ClsImmagine immagine3 = new ClsImmagine();
        immagine3.setId("3");
        immagine3.setURL("https://picsum.photos/200");
        immagine3.setIdCOntenutoAssociato("3");
        immagine3.setUsernameCreatore("");

        ClsImmagine immagine4 = new ClsImmagine();
        immagine4.setId("4");
        immagine4.setURL("https://picsum.photos/200");
        immagine4.setIdCOntenutoAssociato(" ");
        immagine4.setUsernameCreatore("");
    }
    //endregion

}
