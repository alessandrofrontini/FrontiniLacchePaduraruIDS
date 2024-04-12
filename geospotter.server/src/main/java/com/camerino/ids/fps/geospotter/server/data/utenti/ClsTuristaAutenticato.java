package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsRecensione;
import com.camerino.ids.fps.geospotter.server.data.utils.Credenziali;

public class ClsTuristaAutenticato extends ClsTurista implements ILoggedUserAction{
    long id;
    Credenziali credenziali;
    int punteggio;

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
}
