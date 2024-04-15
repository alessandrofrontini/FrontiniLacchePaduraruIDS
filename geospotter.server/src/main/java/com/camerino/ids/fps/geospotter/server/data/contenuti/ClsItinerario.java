package com.camerino.ids.fps.geospotter.server.data.contenuti;

public class ClsItinerario {
    long id;
    //TODO: idCreatore invece di username(?)
    String usernameCreatore;
    ClsNodo[] tappe;
    boolean ordinato;
}
