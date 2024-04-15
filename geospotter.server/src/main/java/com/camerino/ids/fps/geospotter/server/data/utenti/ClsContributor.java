package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsItinerario;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsRecensione;
import com.camerino.ids.fps.geospotter.server.persistance.mock.MockNodi;

public class ClsContributor extends ClsTuristaAutenticato implements IContributable{

    MockNodi mNodi = MockNodi.getInstance();
    //Metodi di ClsTuristaAutenticato
    @Override
    public boolean inserisciRecensione() {
        return false;
    }

    @Override
    public boolean eliminaRecensione(String id) {
        return false;
    }

    @Override
    public boolean modificaRecensione() {
        return false;
    }

    @Override
    public boolean inserisciImmagine() {
        return false;
    }

    @Override
    public ClsRecensione[] visualizzaRecensioniPosessore() {
        return null;
    }

    //Metodi di IContributable
    @Override
    public boolean inserisciNodo(ClsNodo nodo) {
        //TODO: modificare
        return mNodi.aggiungiNodo(nodo);
    }

    @Override
    public boolean modificaNodo(String id, ClsNodo nodo) {
        return false;
    }

    @Override
    public boolean eliminaNodo(String id) {
        return mNodi.eliminaNodo(id);
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
