package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiRDCImmagini;
import com.camerino.ids.fps.client.api.ApiRDCItinerari;
import com.camerino.ids.fps.client.api.ApiRDCNodi;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class IperRDCItinerari implements IPersistenceModel<ClsRdcItinerario> {
    IApi<ClsRdcItinerario> api = new ApiRDCItinerari();

    @Override
    public List<ClsRdcItinerario> get(Map<String, Object> filters) {
        if(filters == null)
            return api.Get(Controller_SezioneLogin.UTENTE, null);

        if(filters.containsKey("idUtente"))
            return api.Get(Controller_SezioneLogin.UTENTE, "idUtente=" + filters.get("idUtente"));

        if(filters.containsKey("idValidazione") && filters.containsKey("accetta")){
            if((Boolean)filters.get("accetta"))
                return api.Get(Controller_SezioneLogin.UTENTE, "trueidValidazione="+filters.get("idValidazione"));
            else
                return api.Get(Controller_SezioneLogin.UTENTE, "falseidValidazione="+filters.get("idValidazione"));
        }
        return api.Get(Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsRdcItinerario object) {
        return api.Put(Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsRdcItinerario object) {
        return api.Post(Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return api.Delete(Controller_SezioneLogin.UTENTE,
                new Pair<>("idRDCItinerario", filters.get("idRDCItinerario").toString()));
    }
}
