package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiSegnalazioni;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class IperSegnalazioni implements IPersistenceModel<ClsSegnalazione> {

    IApi<ClsSegnalazione> api = new ApiSegnalazioni();

    @Override
    public List<ClsSegnalazione> get(Map<String, Object> filters) {
        return api.Get(
                Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsSegnalazione object) {
        return api.Put(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsSegnalazione object) {
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
