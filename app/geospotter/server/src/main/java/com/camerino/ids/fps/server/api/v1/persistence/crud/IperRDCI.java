package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoNodi;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDCI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
@Component
public class IperRDCI implements IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> {
    RepoRDCI repoRDCI;

    @Autowired
    public IperRDCI(final RepoRDCI repoRDCI) {
        this.repoRDCI = repoRDCI;
    }
    @Override
    public ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> get(HashMap<String, Object> filters) {
        if(filters==null)
            return new ArrayList<>(repoRDCI.findAll());
        if(filters.containsKey("idRDCI")) {
            ArrayList<String> ids = new ArrayList<>();
            ids.add(filters.get("idRDCI").toString());
            return new ArrayList<>(repoRDCI.findAllById(ids));
        }
        if(filters.containsKey("idUser"))
            return new ArrayList<>(repoRDCI.getRDCIByUser(filters.get("idUser").toString()));

        return new ArrayList<>(repoRDCI.findAll());
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRichiestaAzioneDiContribuzioneItinerario object) {
        if(filters==null)
            return false;
        if(!filters.containsKey("idRDCI"))
            return false;
        repoRDCI.updateRDCIById(object, filters.get("idRDCI").toString());
        return true;
    }

    @Override
    public boolean insert(ClsRichiestaAzioneDiContribuzioneItinerario object) {
        repoRDCI.save(object);
        return true;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        if(filters==null)
            return false;
        if(!filters.containsKey("idRDCI"))
            return false;
        repoRDCI.deleteById(filters.get("idRDCI").toString());
        return true;
    }
}