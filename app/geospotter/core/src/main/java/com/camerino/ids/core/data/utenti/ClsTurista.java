package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.segnalazioni.ISignalable;
import com.camerino.ids.core.persistence.IPersistenceModel;

/**
 * Rappresenta un utente non autenticato.
 */
public class ClsTurista implements ISignalable {
    IPersistenceModel<ClsSegnalazione> pSegnalazioni;
    public ClsTurista(IPersistenceModel<ClsSegnalazione> segnalazioni){ pSegnalazioni = segnalazioni;}
    /**
     * Crea una segnalazione per il contenuto segnalato (Nodo, Foto, Recensione, Itinerario)
     * che verrà poi vista da un curatore.
     * @param segnalazione Segnalazione fatta
     * @return True se la creazione della segnalazione ha successo,
     *         False altrimenti.
     */

    @Override
    public boolean segnalaContenuto(ClsSegnalazione segnalazione) {
        //Contenuti segnalabili: Nodo, Foto, Itinerario, Recensione (fonte: vpp)
        //ipotizzo che dal menu iniziale(in cui scegli anche se fare login o registrazione) si possa già segnalare
        pSegnalazioni.insert(segnalazione);
        return false;
    }
}
