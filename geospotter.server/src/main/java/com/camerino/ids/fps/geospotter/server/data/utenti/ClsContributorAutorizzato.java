package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsItinerario;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

public class ClsContributorAutorizzato extends ClsContributor {
    //Metodi override di Contributor Autorizzato
    @Bean
    @Override
    public boolean inserisciNodo(ClsNodo nodo) {
        return mNodi.insert(nodo);
    }
    @Override
    public boolean modificaNodo(String id, ClsNodo nodo) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
        return mNodi.update(tmp, nodo);
    }

    @Override
    public boolean inserisciItinerario(ClsItinerario itinerario) {
        return mItinerari.inserisciItinerario(itinerario);
    }

    @Override
    public boolean modificaItinerario(ClsItinerario itinerario, String id) {
        return false;
    }

    @Override
    public boolean eliminaItinerario(String id) {
        return false;
    }

    @Override
    public boolean visualizzaNodiPosessore() {
        return false;
    }

}