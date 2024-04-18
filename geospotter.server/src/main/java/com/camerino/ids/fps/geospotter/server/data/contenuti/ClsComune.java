package com.camerino.ids.fps.geospotter.server.data.contenuti;

import com.camerino.ids.fps.geospotter.server.data.utenti.ClsCuratore;

/**
 * Contiene i dati di un comune
 */
public class ClsComune extends ClsContenuto{

    //TODO: usernameCreatore? ID creatore nel caso
    String usernameCreatore = "ADMIN";
    int abitanti;
    double superficie;
    ClsCuratore[] curatoriAssociati;

}
