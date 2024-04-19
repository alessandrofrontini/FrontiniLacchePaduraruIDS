package com.camerino.ids.fps.geospotter.server.data.contenuti;

import com.camerino.ids.fps.geospotter.server.data.utenti.ClsCuratore;

<<<<<<< Updated upstream
/**
 * Contiene i dati di un comune
 */
public class ClsComune extends ClsContenuto{
=======
public class ClsComune extends ClsContenuto
{
>>>>>>> Stashed changes

    //TODO: usernameCreatore? ID creatore nel caso
    String usernameCreatore = "ADMIN";
    int abitanti;
    double superficie;
    ClsCuratore[] curatoriAssociati;

    //TODO: sviluppa
    public ClsNodo visualizzaNodi ()
    {
        return null;
    } 

}
