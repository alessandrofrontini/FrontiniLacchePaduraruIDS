package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiRDCNodi;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class IperRDCNodi implements IPersistenceModel<ClsRDCNodo> {
    IApi<ClsRDCNodo> api = new ApiRDCNodi();

    @Override
    public ArrayList<ClsRDCNodo> get(HashMap<String, Object> filters) {
        return api.Get(Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRDCNodo object) {
        return api.Put(
                Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsRDCNodo object) {
        return api.Post(
                Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return api.Delete(
                Controller_SezioneLogin.UTENTE,
                new Pair<>("idRDCNodo", filters.get("idRDCNodo").toString()));
    }
}
