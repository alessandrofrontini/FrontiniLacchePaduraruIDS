package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsComune;

import java.util.ArrayList;

/**
 * TODO: commentare
 */
public interface ITownHallAdministrator {
    boolean inserisciComune(ClsComune comune);
    boolean modificaComune(ClsComune comune, String id);
    boolean eliminaComune(String id);
    ArrayList<ClsComune> visualizzaComuni();
}
