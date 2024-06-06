package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsRecensione;

@Deprecated
public interface ILoggedUserAction {
    //TODO: da finire di aggiungere i parametri ai metodi
    boolean inserisciRecensione(ClsRecensione recensione);

    boolean eliminaRecensione(Long id);

    boolean modificaRecensione(Long IDDaModificare, ClsRecensione newrec);

    boolean inserisciImmagine(ClsImmagine immagine);

    //TODO: cambiare da boolean a ClsRecensione
    ClsRecensione[] visualizzaRecensioniPosessore();
}
