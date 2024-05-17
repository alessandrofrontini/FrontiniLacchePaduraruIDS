package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockContest implements IPersistenceModel<ClsContestDiContribuzione> {

    ArrayList<ClsContestDiContribuzione> contests = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    @Override
    public ArrayList<ClsContestDiContribuzione> get(HashMap<String, Object> filters) {
        ArrayList<ClsContestDiContribuzione> tmp = new ArrayList<>();
        if(filters.containsKey("id")) {
            tmp.add(findById(filters.get("id").toString()));
            return tmp;
        }
        return contests;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsContestDiContribuzione object) {
        if(filters.containsKey("id"))
            return updateRCDI(filters.get("id").toString(), object);
        return false;
    }

    @Override
    public boolean insert(ClsContestDiContribuzione object) {
        idCounter++;
        object.setId(""+idCounter);
        return contests.add(object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        ClsContestDiContribuzione tmp = findById(filters.get("id").toString());
        return contests.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(String id, ClsContestDiContribuzione object) {
        ClsContestDiContribuzione tmp = findById(id);
        int index = contests.indexOf(tmp);
        if(index<0)
            return false;
        contests.set(index, object);
        return true;
    }

    private ClsContestDiContribuzione findById(String id) {
        List<ClsContestDiContribuzione> tmp =
                contests.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

}
