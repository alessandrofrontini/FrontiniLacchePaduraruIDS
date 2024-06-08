package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoNodi;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDCNodi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class IperRDCNodi implements IPersistenceModel<ClsRDCNodo> {
    RepoRDCNodi repoRDCNodi;
    RepoNodi repoNodi;//todo: aggiungere alal documentazione

    @Autowired
    public IperRDCNodi(final RepoRDCNodi repoRDCI,
                       final RepoNodi repoNodi) {
        this.repoRDCNodi = repoRDCI;
        this.repoNodi = repoNodi;
    }

    @Override
    public List<ClsRDCNodo> get(Map<String, Object> filters) {
        if (filters == null)
            return new ArrayList<>(repoRDCNodi.findAll());
        if (filters.containsKey("idRDCNodo")) {
            List<Long> ids = new ArrayList<>();
            ids.add((Long) filters.get("idRDCNodo"));
            return new ArrayList<>(repoRDCNodi.findAllById(ids));
        }
        //if(filters.containsKey("idUser"))
        //   return new ArrayList<>(repoRDCNodi.getRDCIByUser(filters.get("idUser").toString()));

        return new ArrayList<>(repoRDCNodi.findAll());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsRDCNodo object) {
        if (filters == null)
            return false;
        if (!filters.containsKey("idRDCNodo"))
            return false;
        repoRDCNodi.save(object);
        return true;
    }

    @Override
    public boolean insert(ClsRDCNodo object) {
        if(object.getNewData()!= null)
            object.setNewData(repoNodi.save(object.getNewData()));
        if(object.getOldData()!=null)
            object.setOldData(repoNodi.save(object.getOldData()));
        repoRDCNodi.save(object);
        return true;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        if (filters == null)
            return false;
        if (!filters.containsKey("idRDCNodo"))
            return false;
        repoRDCNodi.deleteById((Long) filters.get("idRDCNodo"));
        return true;
    }
}
