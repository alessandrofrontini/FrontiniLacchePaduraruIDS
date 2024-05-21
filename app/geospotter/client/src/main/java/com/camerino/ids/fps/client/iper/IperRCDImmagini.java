package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiComuni;
import com.camerino.ids.fps.client.api.ApiRDCImmagini;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class IperRCDImmagini implements IPersistenceModel<ClsRDCImmagine> {
    IApi<ClsRDCImmagine> api = new ApiRDCImmagini();

    @Override
    public ArrayList<ClsRDCImmagine> get(HashMap<String, Object> filters) {
        return api.Get(
                Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRDCImmagine object) {
        return api.Put(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsRDCImmagine object) {
        return api.Post(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return api.Delete(
                (ClsContributor)Controller_SezioneLogin.UTENTE,
                new Pair<>("idRDCImmagine", filters.get("idRDCImmagine").toString()));
    }
}