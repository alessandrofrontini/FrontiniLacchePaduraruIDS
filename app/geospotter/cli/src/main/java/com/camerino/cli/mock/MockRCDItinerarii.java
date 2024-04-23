package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockRCDItinerarii implements IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> {

    ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> rcdi = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    @Override
    public ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> get(HashMap<String, Object> filters) {
        ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> tmp = new ArrayList<>();
        if(filters.containsKey("id")) {
            tmp.add(findById((String) filters.get("id")));
            return tmp;
        }
        return rcdi;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRichiestaAzioneDiContribuzioneItinerario object) {
        if(filters.containsKey("id"))
            return updateRCDI(filters.get("id").toString(), object);
        return false;
    }

    @Override
    public boolean insert(ClsRichiestaAzioneDiContribuzioneItinerario object) {
        idCounter++;
        object.setId(""+idCounter);
        return rcdi.add(object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        ClsRichiestaAzioneDiContribuzioneItinerario tmp = findById(filters.get("id").toString());
        return rcdi.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(String id, ClsRichiestaAzioneDiContribuzioneItinerario object) {
        ClsRichiestaAzioneDiContribuzioneItinerario tmp = findById(id);
        int index = rcdi.indexOf(tmp);
        if(index<0)
            return false;
        rcdi.set(index, object);
        return true;
    }

    private ClsRichiestaAzioneDiContribuzioneItinerario findById(String id) {
        List<ClsRichiestaAzioneDiContribuzioneItinerario> tmp =
                rcdi.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    //endregion
}
