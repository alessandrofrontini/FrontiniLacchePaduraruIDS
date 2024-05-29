package com.camerino.ids.fps.client.iper;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiRecensioni;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class IperRecensioni implements IPersistenceModel<ClsRecensione> {
    IApi<ClsRecensione> api = new ApiRecensioni();

    @Override
    public ArrayList<ClsRecensione> get(HashMap<String, Object> filters) {
        String query = "";
        if(filters.containsKey("idUtente"))
            query = "idUtente="+filters.get("idUtente").toString();
        return api.Get(
                Controller_SezioneLogin.UTENTE, query);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRecensione object) {
        return api.Put(
                Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsRecensione object) {
        return api.Post(
                Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return api.Delete(
                Controller_SezioneLogin.UTENTE,
                new Pair<>("idRecensione", filters.get("idRecensione").toString()));
    }
}
