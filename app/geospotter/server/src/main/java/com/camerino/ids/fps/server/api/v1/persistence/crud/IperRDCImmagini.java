package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoImmagini;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDCImmagini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class IperRDCImmagini implements IPersistenceModel<ClsRDCImmagine> {
    RepoRDCImmagini repoRDCIimmagini;
    RepoImmagini repoImmagini;

    @Autowired
    public IperRDCImmagini(final RepoRDCImmagini repoRDCI,
                           final RepoImmagini repoImmagini) {
        this.repoRDCIimmagini = repoRDCI;
        this.repoImmagini = repoImmagini;
    }

    @Override
    public List<ClsRDCImmagine> get(Map<String, Object> filters) {
        return new ArrayList<>(repoRDCIimmagini.findAll());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsRDCImmagine object) {
        /*if(filters==null)
            return false;
        if(!filters.containsKey("idRDCImmagini"))
            return false;*/
        repoRDCIimmagini.save(object);
        return true;
    }

    @Override
    public boolean insert(ClsRDCImmagine object) {
        if(object.getNewData()!= null)
            object.setNewData(repoImmagini.save(object.getNewData()));
        if(object.getOldData()!=null)
            object.setOldData(repoImmagini.save(object.getOldData()));
        repoRDCIimmagini.save(object);
        return true;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        if (filters == null)
            return false;
        if (!filters.containsKey("idRDCImmagini"))
            return false;
        repoRDCIimmagini.deleteById((Long) filters.get("idRDCImmagini"));
        return true;
    }
}
