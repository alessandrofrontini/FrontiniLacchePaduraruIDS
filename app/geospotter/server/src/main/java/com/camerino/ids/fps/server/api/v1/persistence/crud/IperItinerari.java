package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoItinerari;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoNodi;
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
        if(filters==null)
            return new ArrayList<>(repoItinerari.findAll());
        if(filters.containsKey("idItinerario")) {
            ArrayList<String> ids = new ArrayList<>();
            ids.add(filters.get("idItinerario").toString());
            return new ArrayList<>(repoItinerari.findAllById(ids));
        }
        return new ArrayList<>(repoItinerari.findAll());
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsItinerario object) {
        if(filters==null)
            return false;
        if(!filters.containsKey("idItinerario"))
            return false;
        repoItinerari.save(object);
        return true;
    }

    @Override
    public boolean insert(ClsItinerario object) {
        repoItinerari.save(object);
        return true;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        if(filters==null)
            return false;
        if(!filters.containsKey("idItinerario"))
            return false;
        repoItinerari.deleteById(filters.get("idItinierario").toString());
        return true;
    }
}