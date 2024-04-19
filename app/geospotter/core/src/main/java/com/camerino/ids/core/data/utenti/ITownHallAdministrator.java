package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsComune;

/**
 * TODO: commentare
 */
public interface ITownHallAdministrator {
    boolean aggiungiComune(ClsComune comune);
    boolean modificaComune(ClsComune comune, String id);
    boolean eliminaComune(String id);
    ClsComune[] visualizzaComuni();
}
