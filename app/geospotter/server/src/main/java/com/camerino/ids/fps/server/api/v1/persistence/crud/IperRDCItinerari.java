package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoItinerari;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDCItinerari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class IperRDCItinerari implements IPersistenceModel<ClsRdcItinerario> {
    RepoRDCItinerari repoRDCItinerari;
    RepoItinerari repoItinerari;//todo: aggiungere alal documentazione

    @Autowired
    public IperRDCItinerari(final RepoRDCItinerari repoRDCItinerari,
                       final RepoItinerari repoItinerario) {
        this.repoRDCItinerari = repoRDCItinerari;
        this.repoItinerari = repoItinerario;
    }

    @Override
    public List<ClsRdcItinerario> get(Map<String, Object> filters) {
        if (filters == null)
            return new ArrayList<>(repoRDCItinerari.findAll());
        if (filters.containsKey("idRDCNodo")) {
            List<Long> ids = new ArrayList<>();
            ids.add((Long) filters.get("idRDCNodo"));
            return new ArrayList<>(repoRDCItinerari.findAllById(ids));
        }
        //if(filters.containsKey("idUser"))
        //   return new ArrayList<>(repoRDCNodi.getRDCIByUser(filters.get("idUser").toString()));

        return new ArrayList<>(repoRDCItinerari.findAll());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsRdcItinerario object) {
        if (filters == null)
            return false;
        if (!filters.containsKey("idRDCItinerario"))
            return false;
        repoRDCItinerari.save(object);
        return true;
    }

    @Override
    public boolean insert(ClsRdcItinerario object) {
        if(object.getNewData()!= null)
            object.setNewData(repoItinerari.save(object.getNewData()));
        if(object.getOldData()!=null)
            object.setOldData(repoItinerari.save(object.getOldData()));
        repoRDCItinerari.save(object);
        return true;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        if (filters == null)
            return false;
        if (!filters.containsKey("idRDCNodo"))
            return false;
        repoRDCItinerari.deleteById((Long) filters.get("idRDCItinerario"));
        return true;
    }

}
