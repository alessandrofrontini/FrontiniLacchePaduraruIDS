package com.camerino.ids.core.data.contenuti;

import java.util.ArrayList;

/**
 * TODO: commentare
 */
public class ClsItinerario {
    //TODO: idCreatore invece di username(?)
    String usernameCreatore;
    ArrayList<ClsNodo> tappe = new ArrayList<>();
    boolean ordinato;
    String id;
    //TODO: aggiungere titolo itinerario sulla documentazione
    String nome;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameCreatore() {
        return usernameCreatore;
    }

    public void setUsernameCreatore(String usernameCreatore) {
        this.usernameCreatore = usernameCreatore;
    }

    public ArrayList<ClsNodo> getTappe() {
        return tappe;
    }

    public void setTappe(ArrayList<ClsNodo> tappe) {
        this.tappe = tappe;
    }

    public boolean isOrdinato() {
        return ordinato;
    }

    public void setOrdinato(boolean ordinato) {
        this.ordinato = ordinato;
    }
}
