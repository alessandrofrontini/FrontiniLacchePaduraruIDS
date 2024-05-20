package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiSegnalazioni;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class IperSegnalazioni implements IPersistenceModel<ClsSegnalazione> {

    IApi<ClsSegnalazione> api = new ApiSegnalazioni();

    @Override
    public ArrayList<ClsSegnalazione> get(HashMap<String, Object> filters) {
        return api.Get(
                Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsSegnalazione object) {
        return api.Put(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsSegnalazione object) {
        return api.Post(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return api.Delete(
                (ClsContributor)Controller_SezioneLogin.UTENTE,
                new Pair<>("idSegnalazione", filters.get("idSegnalazione").toString()));
    }

}
