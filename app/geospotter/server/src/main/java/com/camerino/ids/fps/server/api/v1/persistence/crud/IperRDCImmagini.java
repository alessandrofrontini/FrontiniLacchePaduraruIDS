package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDCImmagini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
@Component
public class IperRDCImmagini implements IPersistenceModel<ClsRDCImmagine> {
    RepoRDCImmagini repoRDCIimmagini;

    @Autowired
    public IperRDCImmagini(final RepoRDCImmagini repoRDCI) {
        this.repoRDCIimmagini = repoRDCI;
    }
    @Override
    public ArrayList<ClsRDCImmagine> get(HashMap<String, Object> filters) {
        return new ArrayList<>(repoRDCIimmagini.findAll());
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRDCImmagine object) {
        return false;
    }

    @Override
    public boolean insert(ClsRDCImmagine object) {
        repoRDCIimmagini.save(object);
        return true;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
}