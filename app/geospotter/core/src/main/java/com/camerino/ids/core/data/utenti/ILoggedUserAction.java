package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;

/**
 * TODO: commentare
 */
public interface ILoggedUserAction {
    //TODO: da finire di aggiungere i parametri ai metodi
    boolean inserisciRecensione(ClsRecensione recensione);
    boolean eliminaRecensione(String id);
    boolean modificaRecensione(String id, ClsRecensione recensione);
    boolean inserisciImmagine();
    //TODO: cambiare da boolean a ClsRecensione
    ClsRecensione[] visualizzaRecensioniPosessore();
}
