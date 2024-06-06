package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDCNodi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class IperRDCNodi implements IPersistenceModel<ClsRDCNodo> {
    RepoRDCNodi repoRDCNodi;

    @Autowired
    public IperRDCNodi(final RepoRDCNodi repoRDCI) {
        this.repoRDCNodi = repoRDCI;
    }
    @Override
    public ArrayList<ClsRDCNodo> get(Map<String, Object> filters) {
        if(filters==null)
            return new ArrayList<>(repoRDCNodi.findAll());
        if(filters.containsKey("idRDCNodo")) {
            ArrayList<String> ids = new ArrayList<>();
            ids.add(filters.get("idRDCNodo").toString());
            return new ArrayList<>(repoRDCNodi.findAllById(ids));
        }
        //if(filters.containsKey("idUser"))
        //   return new ArrayList<>(repoRDCNodi.getRDCIByUser(filters.get("idUser").toString()));

        return new ArrayList<>(repoRDCNodi.findAll());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsRDCNodo object) {
        if(filters==null)
            return false;
        if(!filters.containsKey("idRDCNodo"))
            return false;
        repoRDCNodi.save(object);
        return true;
    }

    @Override
    public boolean insert(ClsRDCNodo object) {
        repoRDCNodi.save(object);
        return true;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        if(filters==null)
            return false;
        if(!filters.containsKey("idRDCNodo"))
            return false;
        repoRDCNodi.deleteById(filters.get("idRDCNodo").toString());
        return true;
    }
}
