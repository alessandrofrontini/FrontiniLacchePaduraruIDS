package com.camerino.cli.mock;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TODO: implementare
public class MockSegnalazioni implements IPersistenceModel<ClsSegnalazione>
{
    private List<ClsSegnalazione> segnalazioni = new ArrayList<ClsSegnalazione>();

    //TODO:add to vpp
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public List<ClsSegnalazione> get(Map<String, Object> filters) {
        return this.segnalazioni;
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsSegnalazione object) {
        return false;
    }

    @Override
    public boolean insert(ClsSegnalazione object) {
        return false;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return false;
    }
    //endregion
}
