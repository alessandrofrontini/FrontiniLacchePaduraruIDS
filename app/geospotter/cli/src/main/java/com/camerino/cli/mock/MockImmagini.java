package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
//TODO:implementare
public class MockImmagini implements IPersistenceModel<ClsImmagine>
{
    private ArrayList<ClsImmagine> immagini = new ArrayList<>();
    //TODO:add to vpp
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsImmagine> get(HashMap<String, Object> filters) {
        return this.immagini;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsImmagine object) {
        return false;
    }

    @Override
    public boolean insert(ClsImmagine object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
    //endregion

    private void generaImmagini()
    {
        ClsImmagine immagine1 = new ClsImmagine();
        immagine1.setId("1");
        immagine1.setURL("https://picsum.photos/200");
        immagine1.setIdNodoAssociato("1");//Comune
        immagine1.setIdCreatore("");

        ClsImmagine immagine2 = new ClsImmagine();
        immagine2.setId("2");
        immagine2.setURL("https://picsum.photos/200");
        immagine2.setIdNodoAssociato("2");//Nodo
        immagine2.setIdCreatore("");

        ClsImmagine immagine3 = new ClsImmagine();
        immagine3.setId("3");
        immagine3.setURL("https://picsum.photos/200");
        immagine3.setIdNodoAssociato("3");
        immagine3.setIdCreatore("");

        ClsImmagine immagine4 = new ClsImmagine();
        immagine4.setId("4");
        immagine4.setURL("https://picsum.photos/200");
        immagine4.setIdNodoAssociato(" ");
        immagine4.setIdCreatore("");
    }
}
