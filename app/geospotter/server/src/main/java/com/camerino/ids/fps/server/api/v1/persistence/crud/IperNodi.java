package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoNodi;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class IperNodi implements IPersistenceModel<ClsNodo> {

    RepoNodi repoNodi;

    @Autowired
    public IperNodi(final RepoNodi repoNodi) {
        this.repoNodi = repoNodi;
    }

    @Override
    public List<ClsNodo> get(Map<String, Object> filters) {
        if (filters == null)
            return new ArrayList<>(repoNodi.findAllOfficial());
        List<ClsNodo> lNodi = new ArrayList<>();
        if (filters.containsKey("idNodo")) {
            Optional<ClsNodo> nodo = repoNodi.findById((Long) filters.get("idNodo"));
            if (nodo.isEmpty())
                return lNodi;
            lNodi.add(nodo.get());
            return lNodi;
        }
        if (filters.containsKey("idComune")) {
            return new ArrayList<>(repoNodi.findNodiByComune((Long) filters.get("idComune")));
        }
        if (filters.containsKey("owner"))
            return new ArrayList<>(repoNodi.findNodoByUtente(Long.valueOf(filters.get("owner").toString())));
        lNodi = new ArrayList<>(repoNodi.findAllOfficial());
        return lNodi;
    }

    @Transactional
    @Override
    public boolean update(Map<String, Object> filters, ClsNodo object) {
        repoNodi.save(object);
        return true;
    }

    @Override
    public boolean insert(ClsNodo object) {
        repoNodi.save(object);
        return true;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        repoNodi.deleteById((Long) filters.get("idNodo"));
        return true;
    }
}
