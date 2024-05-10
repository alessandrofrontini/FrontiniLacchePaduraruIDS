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
        return null;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRichiestaAzioneDiContribuzioneItinerario object) {
        return false;
    }

    @Override
    public boolean insert(ClsRichiestaAzioneDiContribuzioneItinerario object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
}
