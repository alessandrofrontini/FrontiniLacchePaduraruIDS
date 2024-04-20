package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsRecensione;

/**
 * TODO: commentare
 */
public interface ILoggedUserAction {
    //TODO: da finire di aggiungere i parametri ai metodi
    boolean inserisciRecensione();
    boolean eliminaRecensione(String id);
    boolean modificaRecensione();
    boolean inserisciImmagine();
    //TODO: cambiare da boolean a ClsRecensione
    ClsRecensione[] visualizzaRecensioniPosessore();
}
