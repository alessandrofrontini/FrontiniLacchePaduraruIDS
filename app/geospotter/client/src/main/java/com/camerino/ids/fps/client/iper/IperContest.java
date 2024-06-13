package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.client.Controller_SezioneLogin;
import com.camerino.ids.fps.client.api.ApiComuni;
import com.camerino.ids.fps.client.api.ApiContest;
import com.camerino.ids.fps.client.api.IApi;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class IperContest implements IPersistenceModel<ClsContestDiContribuzione> {
    IApi<ClsContestDiContribuzione> api = new ApiContest();

    @Override
    public List<ClsContestDiContribuzione> get(Map<String, Object> filters) {
        return api.Get(Controller_SezioneLogin.UTENTE, null);
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsContestDiContribuzione object) {
        return api.Put(Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean insert(ClsContestDiContribuzione object) {
        return api.Post(Controller_SezioneLogin.UTENTE, object);
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return api.Delete(Controller_SezioneLogin.UTENTE,
                new Pair<>("idComune", filters.get("idComune").toString()));
    }
}
