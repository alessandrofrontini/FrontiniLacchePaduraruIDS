package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
//TODO: implementare
public class MockRecensioni implements IPersistenceModel<ClsRecensione>
{
    private ArrayList<ClsRecensione> recensioni = new ArrayList<ClsRecensione>();
    //TODO: add to vpp
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsRecensione> get(HashMap<String, Object> filters) {
        return this.recensioni;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRecensione object) {
        return false;
    }

    @Override
    public boolean insert(ClsRecensione object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
    //endregion
}
