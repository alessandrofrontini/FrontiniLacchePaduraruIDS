package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;
//TODO:implementare
public class MockImmagini implements IPersistenceModel<ClsImmagine> {
    @Override
    public ClsImmagine[] get(HashMap<String, Object> filters) {
        return new ClsImmagine[0];
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
}
