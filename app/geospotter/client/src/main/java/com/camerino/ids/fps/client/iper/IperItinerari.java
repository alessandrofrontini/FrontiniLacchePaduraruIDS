package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiItinerari;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class IperItinerari implements IPersistenceModel<ClsItinerario> {
    IApi<ClsItinerario> api = new ApiItinerari();

    @Override
    public List<ClsItinerario> get(Map<String, Object> filters) {
        if(filters == null)
            return api.Get(Controller_SezioneLogin.UTENTE, null);

        if(filters.containsKey("idItinerario"))
            return api.Get(Controller_SezioneLogin.UTENTE, "idItinerario=" + filters.get("idItinerario"));

        return api.Get(Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsItinerario object) {
        return api.Put(Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsItinerario object) {
        return api.Post(Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return api.Delete(Controller_SezioneLogin.UTENTE,
                new Pair<>("idItinerario", filters.get("idItinerario").toString()));
    }
}
