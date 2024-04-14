package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsRecensione;
import com.camerino.ids.fps.geospotter.server.data.utils.Credenziali;

public class ClsTuristaAutenticato extends ClsTurista implements ILoggedUserAction{
    String id;
    Credenziali credenziali;
    int punteggio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Credenziali getCredenziali() {
        return credenziali;
    }

    public void setCredenziali(Credenziali credenziali) {
        this.credenziali = credenziali;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

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
