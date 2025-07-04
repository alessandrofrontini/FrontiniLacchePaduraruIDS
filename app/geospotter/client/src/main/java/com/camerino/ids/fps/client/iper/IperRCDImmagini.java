package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiRDCImmagini;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class IperRCDImmagini implements IPersistenceModel<ClsRDCImmagine> {
    IApi<ClsRDCImmagine> api = new ApiRDCImmagini();

    @Override
    public List<ClsRDCImmagine> get(Map<String, Object> filters) {
        if(filters == null)
            return api.Get(Controller_SezioneLogin.UTENTE, null);

        if(filters.containsKey("idUtente")){
            String query = "idUtente="+filters.get("idUtente");
            if(filters.containsKey("onlyContest"))
                query+="&onlyContest="+filters.get("onlyContest");
            return api.Get(Controller_SezioneLogin.UTENTE, query);
        }

        if(filters.containsKey("idValidazione") && filters.containsKey("accetta")){
            if((Boolean)filters.get("accetta"))
                return api.Get(Controller_SezioneLogin.UTENTE, "trueidValidazione="+filters.get("idValidazione"));
            else
                return api.Get(Controller_SezioneLogin.UTENTE, "falseidValidazione="+filters.get("idValidazione"));
        }

        return api.Get(Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsRDCImmagine object) {
        return api.Put(
                (ClsContributor) Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsRDCImmagine object) {
        return api.Post(
                (ClsContributor) Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return api.Delete(
                (ClsContributor) Controller_SezioneLogin.UTENTE,
                new Pair<>("idRDCImmagine", filters.get("idRDCImmagine").toString()));
    }
}