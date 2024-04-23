package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockRCDNodi implements IPersistenceModel<ClsRichiestaAzioneDiContribuzione> {
    ArrayList<ClsRichiestaAzioneDiContribuzione> rcdi = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    @Override
    public ArrayList<ClsRichiestaAzioneDiContribuzione> get(HashMap<String, Object> filters) {
        ArrayList<ClsRichiestaAzioneDiContribuzione> tmp = new ArrayList<>();
        if(filters.containsKey("id")) {
            tmp.add(findById(filters.get("id").toString()));
            return tmp;
        }
        return rcdi;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRichiestaAzioneDiContribuzione object) {
        if(filters.containsKey("id"))
            return updateRCDI(filters.get("id").toString(), object);
        return false;
    }

    @Override
    public boolean insert(ClsRichiestaAzioneDiContribuzione object) {
        idCounter++;
        object.setId(""+idCounter);
        return rcdi.add(object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        ClsRichiestaAzioneDiContribuzione tmp = findById(filters.get("id").toString());
        return rcdi.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(String id, ClsRichiestaAzioneDiContribuzione object) {
        ClsRichiestaAzioneDiContribuzione tmp = findById(id);
        int index = rcdi.indexOf(tmp);
        if(index<0)
            return false;
        rcdi.set(index, object);
        return true;
    }

    private ClsRichiestaAzioneDiContribuzione findById(String id) {
        List<ClsRichiestaAzioneDiContribuzione> tmp =
                rcdi.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    //endregion
}
