package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TODO: implementare
public class MockRecensioni implements IPersistenceModel<ClsRecensione>
{
    private List<ClsRecensione> recensioni = new ArrayList<ClsRecensione>();
    //TODO: add to vpp
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public List<ClsRecensione> get(Map<String, Object> filters) {
        return this.recensioni;
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsRecensione object) {
        return false;
    }

    @Override
    public boolean insert(ClsRecensione object) {
        return false;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return false;
    }
    //endregion
}
