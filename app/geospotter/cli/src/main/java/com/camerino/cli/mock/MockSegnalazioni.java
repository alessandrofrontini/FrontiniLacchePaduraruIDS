package com.camerino.cli.mock;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;
//TODO: implementare
public class MockSegnalazioni implements IPersistenceModel<ClsSegnalazione> {
    @Override
    public ClsSegnalazione[] get(HashMap<String, Object> filters) {
        return new ClsSegnalazione[0];
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsSegnalazione object) {
        return false;
    }

    @Override
    public boolean insert(ClsSegnalazione object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
}
