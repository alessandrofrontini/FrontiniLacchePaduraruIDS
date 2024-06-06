package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoSegnalazioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class IperSegnalazioni implements IPersistenceModel<ClsSegnalazione> {
    private final RepoSegnalazioni repoSegnalazioni;

    @Autowired
    public IperSegnalazioni(RepoSegnalazioni repoSegnalazioni) {
        this.repoSegnalazioni = repoSegnalazioni;
    }

    @Override
    public ArrayList<ClsSegnalazione> get(Map<String, Object> filters) {
        if(filters == null)
            return new ArrayList<>(repoSegnalazioni.findAll());
        if(filters.containsKey("idContenuto"))
            return new ArrayList<>(repoSegnalazioni.filterByContenuto(filters.get("idContenuto").toString()));
        if(filters.containsKey("idUtente"))
            return new ArrayList<>(repoSegnalazioni.getSegnalazioneByUser(filters.get("idUtente").toString()));

        return new ArrayList<>(repoSegnalazioni.findAll());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsSegnalazione object) {
        return false;
    }

    @Override
    public boolean insert(ClsSegnalazione object) {
        repoSegnalazioni.save(object);
        return true;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return false;
    }
}
