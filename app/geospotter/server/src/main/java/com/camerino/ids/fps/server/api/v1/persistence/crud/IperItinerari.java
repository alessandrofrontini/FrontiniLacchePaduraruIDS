package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoItinerari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class IperItinerari implements IPersistenceModel<ClsItinerario> {

    RepoItinerari repoItinerari;

    @Autowired
    public IperItinerari(final RepoItinerari repoItinerari) {
        this.repoItinerari = repoItinerari;
    }

    @Override
    public List<ClsItinerario> get(Map<String, Object> filters) {
        if (filters == null)
            return new ArrayList<>(repoItinerari.findAllOfficial());
        if (filters.containsKey("idItinerario")) {
            List<Long> ids = new ArrayList<>();
            ids.add((Long) filters.get("idItinerario"));
            return new ArrayList<>(repoItinerari.findAllById(ids));
        }
        return new ArrayList<>(repoItinerari.findAllOfficial());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsItinerario object) {
        if (filters == null)
            return false;
        if (!filters.containsKey("idItinerario"))
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
    public boolean delete(Map<String, Object> filters) {
        if (filters == null)
            return false;
        if (!filters.containsKey("idItinerario"))
            return false;
        repoItinerari.deleteById((Long) filters.get("idItinierario"));
        return true;
    }
}
