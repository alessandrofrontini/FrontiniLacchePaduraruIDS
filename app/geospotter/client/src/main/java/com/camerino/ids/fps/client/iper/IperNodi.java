package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.api.ApiNodi;
import com.camerino.ids.fps.client.Controller_SezioneLogin;

import java.util.ArrayList;
import java.util.HashMap;

//https://www.youtube.com/watch?v=9oq7Y8n1t00
public class IperNodi implements IPersistenceModel<ClsNodo> {

    @Override
    public ArrayList<ClsNodo> get(HashMap<String, Object> filters) {
        return ApiNodi.GetAllNodi(Controller_SezioneLogin.UTENTE);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsNodo object) {
        return ApiNodi.PutNodo((ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsNodo object) {
        return ApiNodi.PostNodo(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return ApiNodi.DeleteNodo(
                (ClsContributor)Controller_SezioneLogin.UTENTE, filters.get("idNodo").toString());
    }
}
