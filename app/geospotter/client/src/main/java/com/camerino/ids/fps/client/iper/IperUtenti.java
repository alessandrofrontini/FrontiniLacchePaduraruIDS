package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiTuristi;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class IperUtenti implements IPersistenceModel<ClsTuristaAutenticato> {

    IApi<ClsTuristaAutenticato> api = new ApiTuristi();

    @Override
    public ArrayList<ClsTuristaAutenticato> get(Map<String, Object> filters) {
        if(filters== null)
            return api.Get(Controller_SezioneLogin.UTENTE, null);
        if(filters.containsKey("ruolo"))
            return api.Get(Controller_SezioneLogin.UTENTE, "ruolo=" + filters.get("ruolo"));
        return api.Get(
                Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsTuristaAutenticato object) {
        return api.Put(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsTuristaAutenticato object) {
        return api.Post(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return api.Delete(
                (ClsContributor)Controller_SezioneLogin.UTENTE,
                new Pair<>("idSegnalazione", filters.get("idSegnalazione").toString()));
    }
}
