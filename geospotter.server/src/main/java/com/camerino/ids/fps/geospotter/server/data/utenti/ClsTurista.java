package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.fps.geospotter.server.data.segnalazioni.ISignalable;

/**
 * Rappresenta un utente non autenticato.
 */
public class ClsTurista implements ISignalable {
    /**
     * Crea una segnalazione per il contenuto segnalato (Nodo, Foto, Recensione)
     * che verrà poi vista da un curatore.
     * @param segnalazione Segnalazione fatta
     * @return True se la creazione della segnalazione ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean segnalaContenuto(ClsSegnalazione segnalazione) {
        return false;
    }
}
