package com.camerino.ids.core.data.contenuti;

import com.camerino.ids.core.data.utenti.ClsCuratore;

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
