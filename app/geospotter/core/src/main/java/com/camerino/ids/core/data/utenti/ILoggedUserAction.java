package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsRecensione;

/**
 * TODO: commentare
 */
public interface ILoggedUserAction {
    //TODO: da finire di aggiungere i parametri ai metodi
    boolean inserisciRecensione(ClsRecensione recensione);
    boolean eliminaRecensione(String id);
    boolean modificaRecensione(ClsRecensione old, ClsRecensione newrec);
    boolean inserisciImmagine(ClsImmagine immagine);
    //TODO: cambiare da boolean a ClsRecensione
    ClsRecensione[] visualizzaRecensioniPosessore();
}
