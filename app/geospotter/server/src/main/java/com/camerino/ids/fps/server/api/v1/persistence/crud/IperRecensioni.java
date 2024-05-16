package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRecensioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class IperRecensioni implements IPersistenceModel<ClsRecensione> {
    private final RepoRecensioni repoRecensioni;

    @Autowired
    public IperRecensioni(final RepoRecensioni repoRecensioni){
        this.repoRecensioni = repoRecensioni;
    }

    @Override
    public ArrayList<ClsRecensione> get(HashMap<String, Object> filters) {
        if(filters == null)
            return new ArrayList<>(repoRecensioni.findAll());
        if(filters.containsKey("idNodo"))
            return new ArrayList<>(repoRecensioni.findRecensioniByNodo(filters.get("idNodo").toString()));
        if(filters.containsKey("idRecensione")) {
            ArrayList<String> ids = new ArrayList<>();
            ids.add(filters.get("idRecensione").toString());
            return new ArrayList<>(repoRecensioni.findAllById(ids));
        }
        return new ArrayList<>(repoRecensioni.findAll());
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRecensione object) {
        if(filters==null)
            return false;
        if(!filters.containsKey("idRecensione"))
            return false;
        repoRecensioni.updateRecensioneById(object, filters.get("idRecensione").toString());
        return true;
    }

    @Override
    public boolean insert(ClsRecensione object) {
        repoRecensioni.save(object);
        return true;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        if(filters==null)
            return false;
        if(!filters.containsKey("idRecensione"))
            return false;
        repoRecensioni.deleteById(filters.get("idRecensione").toString());
        return true;
    }
}
