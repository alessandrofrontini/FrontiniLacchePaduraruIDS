package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoComuni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class IperComuni implements IPersistenceModel<ClsComune> {

    RepoComuni repoComuni;

    @Autowired
    public IperComuni(final RepoComuni repoComuni){
        this.repoComuni = repoComuni;
    }

    @Override
    public ArrayList<ClsComune> get(HashMap<String, Object> filters) {
       if(filters == null)
           return new ArrayList<>(repoComuni.findAll());

       if (filters.containsKey("idComune")) {
            ArrayList<String> ids = new ArrayList<>();
            ids.add((String) filters.get("idComune"));
            return new ArrayList<>(repoComuni.findAllById(ids));
        }

        return new ArrayList<>(repoComuni.findAll());
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsComune object) {
        repoComuni.save(object);
        return true;
    }

    @Override
    public boolean insert(ClsComune object) {
        repoComuni.save(object);
        return true;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        if(filters == null)
            return false;
        if (!filters.containsKey("idComune"))
            return false;
        repoComuni.deleteById(filters.get("idComune").toString());
        return true;
    }
}
