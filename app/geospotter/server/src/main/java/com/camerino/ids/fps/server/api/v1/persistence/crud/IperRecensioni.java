package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRecensioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class IperRecensioni implements IPersistenceModel<ClsRecensione> {
    private final RepoRecensioni repoRecensioni;

    @Autowired
    public IperRecensioni(final RepoRecensioni repoRecensioni) {
        this.repoRecensioni = repoRecensioni;
    }

    @Override
    public List<ClsRecensione> get(Map<String, Object> filters) {
        if (filters == null)
            return new ArrayList<>(repoRecensioni.findAll());
        else if (filters.containsKey("idNodo"))
            return new ArrayList<>(repoRecensioni.findRecensioniByNodo((Long) filters.get("idNodo")));
        else if (filters.containsKey("idRecensione")) {
            List<Long> ids = new ArrayList<>();
            ids.add((Long) filters.get("idRecensione"));
            return new ArrayList<>(repoRecensioni.findAllById(ids));
        } else if (filters.containsKey("idUtente"))
            return new ArrayList<>(repoRecensioni.findRecensioniByUtente(Long.valueOf(Objects.toString(filters.get("idUtente")))));
        else if (filters.containsKey("owner"))
            return new ArrayList<>(repoRecensioni.findRecensioniByUtente(Long.valueOf(Objects.toString(filters.get("owner")))));
        return new ArrayList<>(repoRecensioni.findAll());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsRecensione object) {
        if (filters == null)
            return false;
        if (!filters.containsKey("idRecensione"))
            return false;
        repoRecensioni.save(object);
        return true;
    }

    @Override
    public boolean insert(ClsRecensione object) {
        repoRecensioni.save(object);
        return true;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        if (filters == null)
            return false;
        if (!filters.containsKey("idRecensione"))
            return false;
        repoRecensioni.deleteById((Long) filters.get("idRecensione"));
        return true;
    }
}
