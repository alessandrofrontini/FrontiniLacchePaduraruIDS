package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsItinerario;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import org.springframework.context.annotation.Bean;

public class ClsContributorAutorizzato extends ClsContributor {
    //Metodi override di Contributor Autorizzato
    @Bean
    @Override
    public boolean inserisciNodo(ClsNodo nodo) {
        return mNodi.aggiungiNodo(nodo);
    }
    @Override
    public boolean modificaNodo(String id, ClsNodo nodo) {
        return mNodi.modificaNodo(nodo);
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