package com.camerino.ids.fps.client.visual;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;

public class ClsUtenteJWTDecode
{
    private String jwt;

    private String username;

    private ClsTuristaAutenticato.eRUOLI_UTENTE ruolo;

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

    public ClsTuristaAutenticato.eRUOLI_UTENTE getRuolo() {
        return ruolo;
    }

    public void setRuolo(ClsTuristaAutenticato.eRUOLI_UTENTE ruolo) {
        this.ruolo = ruolo;
    }
}
