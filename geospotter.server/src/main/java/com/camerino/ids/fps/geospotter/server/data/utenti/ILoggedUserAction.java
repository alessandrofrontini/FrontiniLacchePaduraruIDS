package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsRecensione;

/**
 * TODO: commentare
 */
public interface ILoggedUserAction {
    //TODO: da finire di aggiungere i parametri ai metodi
    boolean inserisciRecensione(ClsRecensione recensione, String IDContenuto);
    boolean eliminaRecensione(String id);
    boolean modificaRecensione();
    boolean inserisciImmagine();
    //TODO: cambiare da boolean a ClsRecensione
    ClsRecensione[] visualizzaRecensioniPosessore();
}
