package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.api.ApiNodi;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

//https://www.youtube.com/watch?v=9oq7Y8n1t00
public class IperNodi implements IPersistenceModel<ClsNodo> {

    IApi<ClsNodo> api = new ApiNodi();

    @Override
    public List<ClsNodo> get(Map<String, Object> filters) {
        if(filters==null)
            return api.Get(
                    Controller_SezioneLogin.UTENTE, null);
        if(filters.containsKey("owner"))
            return api.Get(Controller_SezioneLogin.UTENTE, "owner="+filters.get("owner"));
        if(filters.containsKey("idNodo"))
            return api.Get(Controller_SezioneLogin.UTENTE, "idNodo="+filters.get("idNodo"));
        return api.Get(
                Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsNodo object) {
        return api.Put(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsNodo object) {
        return api.Post(
                (ClsContributor)Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return api.Delete(
                (ClsContributor)Controller_SezioneLogin.UTENTE,
                new Pair<>("idNodo", filters.get("idNodo").toString()));
    }
}
