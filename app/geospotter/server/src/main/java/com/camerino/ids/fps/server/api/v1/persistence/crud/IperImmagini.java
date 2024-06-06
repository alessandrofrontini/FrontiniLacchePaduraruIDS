package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoImmagini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class IperImmagini implements IPersistenceModel<ClsImmagine> {
    RepoImmagini repoImmagini;

    @Autowired
    public IperImmagini(final RepoImmagini repoImmagini){
        this.repoImmagini = repoImmagini;
    }

    @Override
    public List<ClsImmagine> get(Map<String, Object> filters) {
        if(filters == null)
            return new ArrayList<>(repoImmagini.findAll());

        return new ArrayList<>(repoImmagini.findAll());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsImmagine object) {
        return false;
    }

    @Override
    public boolean insert(ClsImmagine object) {
        return false;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return false;
    }
}
