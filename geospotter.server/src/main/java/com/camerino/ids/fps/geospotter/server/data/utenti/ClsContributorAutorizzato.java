package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsItinerario;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;

public class ClsContributorAutorizzato extends ClsContributor {
    //Metodi override di Contributor Autorizzato
    @Override
    public boolean inserisciNodo(ClsNodo nodo) {
        return false;
    }

    @Override
    public boolean modificaNodo(String id, ClsNodo nodo) {
        return false;
    }

    @Override
    public boolean inserisciItinerario(ClsItinerario irinerario) {
        return false;
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