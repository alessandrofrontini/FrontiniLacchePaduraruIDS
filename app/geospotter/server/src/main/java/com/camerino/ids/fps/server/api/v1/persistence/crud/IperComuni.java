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
        return null;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsComune object) {
        return false;
    }

    @Override
    public boolean insert(ClsComune object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
}
