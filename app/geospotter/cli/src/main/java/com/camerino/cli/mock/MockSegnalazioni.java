package com.camerino.cli.mock;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
//TODO: implementare
public class MockSegnalazioni implements IPersistenceModel<ClsSegnalazione>
{
    private ArrayList<ClsSegnalazione> segnalazioni = new ArrayList<ClsSegnalazione>();

    //TODO:add to vpp
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsSegnalazione> get(HashMap<String, Object> filters) {
        return this.segnalazioni;
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
    //endregion
}
