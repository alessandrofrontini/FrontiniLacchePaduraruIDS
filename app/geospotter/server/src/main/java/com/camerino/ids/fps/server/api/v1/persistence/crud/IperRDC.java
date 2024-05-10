package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
@Component
public class IperRDC implements IPersistenceModel<ClsRichiestaAzioneDiContribuzione> {
    RepoRDC repoRDC;

    @Autowired
    public IperRDC(final RepoRDC repoRDC) {
        this.repoRDC = repoRDC;
    }
    @Override
    public ArrayList<ClsRichiestaAzioneDiContribuzione> get(HashMap<String, Object> filters) {
        return null;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRichiestaAzioneDiContribuzione object) {
        return false;
    }

    @Override
    public boolean insert(ClsRichiestaAzioneDiContribuzione object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
}
