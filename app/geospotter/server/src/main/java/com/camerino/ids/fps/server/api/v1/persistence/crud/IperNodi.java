package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoNodi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Component
public class IperNodi implements IPersistenceModel<ClsNodo> {

    RepoNodi repoNodi;

    @Autowired
    public IperNodi(final RepoNodi repoNodi) {
        this.repoNodi = repoNodi;
    }

    @Override
    public ArrayList<ClsNodo> get(HashMap<String, Object> filters) {
        ArrayList<ClsNodo> lNodi = new ArrayList<>();
        if(filters.containsKey("id")) {
            Optional<ClsNodo> nodo = repoNodi.findById(filters.get("id").toString());
            if (nodo.isEmpty())
                return lNodi;
            lNodi.add(nodo.get());
            return lNodi;
        }
        lNodi = new ArrayList<>(repoNodi.findAll());
        return lNodi;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsNodo object) {
        return false;
    }

    @Override
    public boolean insert(ClsNodo object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
}
