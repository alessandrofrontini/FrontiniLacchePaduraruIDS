package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;
//TODO: implementare
public class MockRecensioni implements IPersistenceModel<ClsRecensione> {
    @Override
    public ClsRecensione[] get(HashMap<String, Object> filters) {
        return new ClsRecensione[0];
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
}
