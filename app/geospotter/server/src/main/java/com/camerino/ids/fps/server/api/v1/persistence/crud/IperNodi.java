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
        if(filters == null)
            return new ArrayList<>(repoNodi.findAll());
        ArrayList<ClsNodo> lNodi = new ArrayList<>();
        if(filters.containsKey("idNodo")) {
            Optional<ClsNodo> nodo = repoNodi.findById(filters.get("idNodo").toString());
            if (nodo.isEmpty())
                return lNodi;
            lNodi.add(nodo.get());
            return lNodi;
        }
        if(filters.containsKey("idComune")) {
            return new ArrayList<>(repoNodi.findNodiByComune(filters.get("idComune").toString()));
        }
        lNodi = new ArrayList<>(repoNodi.findAll());
        return lNodi;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsNodo object) {
        repoNodi.updateNodoById(object, object.getId());
        return true;
    }

    @Override
    public boolean insert(ClsNodo object) {
        repoNodi.save(object);
        return true;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        repoNodi.deleteById(filters.get("idNodo").toString());
        return true;
    }
}
