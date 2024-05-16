package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
@Component
public class IperRDC implements IPersistenceModel<ClsRichiestaAzioneDiContribuzione> {
    RepoRDC repoRDC;

    @Autowired
    public IperRDC(final RepoRDC repoRDC) {
        this.repoRDC = repoRDC;
    }
    @Override
    public ArrayList<ClsRichiestaAzioneDiContribuzione> get(HashMap<String, Object> filters) {
        if(filters==null)
            return new ArrayList<>(repoRDC.findAll());
        if(filters.containsKey("idRDC")) {
            ArrayList<String> ids = new ArrayList<>();
            ids.add(filters.get("idRDC").toString());
            return new ArrayList<>(repoRDC.findAllById(ids));
        }
        if(filters.containsKey("idUser"))
            return new ArrayList<>(repoRDC.getRDCByUser(filters.get("idUser").toString()));

        return new ArrayList<>(repoRDC.findAll());
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRichiestaAzioneDiContribuzione object) {
        if(filters==null)
            return false;
        if(!filters.containsKey("idRDC"))
            return false;
        repoRDC.updateRDCById(object, filters.get("idRDC").toString());
        return true;
    }

    @Override
    public boolean insert(ClsRichiestaAzioneDiContribuzione object) {
        repoRDC.save(object);
        return true;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        if(filters==null)
            return false;
        if(!filters.containsKey("idRDC"))
            return false;
        repoRDC.deleteById(filters.get("idRDC").toString());
        return true;
    }
}
