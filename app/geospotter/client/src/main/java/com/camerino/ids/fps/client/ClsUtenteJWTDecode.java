package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;

public class ClsUtenteJWTDecode
{
    private String jwt;

    private String username;

    private ClsTuristaAutenticato.eRUOLO_UTENTE ruolo;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ClsTuristaAutenticato.eRUOLO_UTENTE getRuolo() {
        return ruolo;
    }

    public void setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE ruolo) {
        this.ruolo = ruolo;
    }
}