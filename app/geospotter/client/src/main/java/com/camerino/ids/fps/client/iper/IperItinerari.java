package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiComuni;
import com.camerino.ids.fps.client.api.ApiItinerari;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class IperItinerari implements IPersistenceModel<ClsItinerario> {
    IApi<ClsItinerario> api = new ApiItinerari();

    @Override
    public ArrayList<ClsItinerario> get(HashMap<String, Object> filters) {
        return api.Get(
                Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsItinerario object) {
        return api.Put(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsItinerario object) {
        return api.Post(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return api.Delete(
                (ClsContributor)Controller_SezioneLogin.UTENTE,
                new Pair<>("idItinerario", filters.get("idItinerario").toString()));
    }
}