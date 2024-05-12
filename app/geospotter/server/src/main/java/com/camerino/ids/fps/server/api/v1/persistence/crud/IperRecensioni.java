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
        if(filters.containsKey("idNodo")){
            System.out.println(filters.get("idNodo"));
            return new ArrayList<>(repoRecensioni.findRecensioniByNodo(filters.get("idNodo").toString()));
        }
        return new ArrayList<>();
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRecensione object) {
        return false;
    }

    @Override
    public boolean insert(ClsRecensione object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
}
