package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoItinerari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class IperItinerari implements IPersistenceModel<ClsItinerario> {

    RepoItinerari repoItinerari;

    @Autowired
    public IperItinerari(final RepoItinerari repoItinerari){
        this.repoItinerari = repoItinerari;
    }
    @Override
    public ArrayList<ClsItinerario> get(HashMap<String, Object> filters) {
        return null;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsItinerario object) {
        return false;
    }

    @Override
    public boolean insert(ClsItinerario object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
}
