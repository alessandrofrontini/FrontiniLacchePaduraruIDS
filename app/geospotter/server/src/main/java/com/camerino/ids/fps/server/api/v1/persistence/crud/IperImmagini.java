package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;

public class IperImmagini implements IPersistenceModel<ClsImmagine>
{
    @Override
    public ArrayList<ClsImmagine> get(HashMap<String, Object> filters) {
        return null;
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
