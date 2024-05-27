package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.*;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Ha la  possibilità di inserire, modificare ed eliminare
 * Nodi e Itinerari direttamente senza creare richieste.
 * Questo ruolo si ottiene avendo 600+ punti.
 */
public class ClsContributorAutorizzato extends ClsContributor {
    //region Override Contributor Autorizzato
    IPersistenceModel<ClsImmagine> pImmagini;
    public ClsContributorAutorizzato(IPersistenceModel<ClsRecensione> r, IPersistenceModel<ClsSegnalazione> s, IPersistenceModel<ClsImmagine> i, IPersistenceModel<ClsRDCImmagine> pRCDImmagini, IPersistenceModel<ClsRDCNodo> pRCDNodo, IPersistenceModel<ClsRDCItinerario> pRCDItinerari, IPersistenceModel<ClsNodo> nodi, IPersistenceModel<ClsItinerario> itinerari){
        super(r, s,pRCDImmagini,pRCDNodo, pRCDItinerari, nodi, itinerari);
        pImmagini = i;
    }
    /**
     * Inserisce direttamente il nodo creato.
     * @param nodo Il nodo da aggiungere
     * @return True se l'inserimento ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean inserisciNodo(ClsNodo nodo) {
        nodo.setUsernameCreatore(this.credenziali.getUsername());
        return pNodi.insert(nodo);
    }

    @Override
    public boolean inserisciImmagine(ClsImmagine immagine) {
        immagine.setUsernameCreatore(this.credenziali.getUsername());
        return pImmagini.insert(immagine);
    }

    /**
     * Modifica direttamente un qualsiasi nodo.
     *
     * @param oldnodo Id del nodo dal modificare
     * @param nodo Il nodo contenente i dati modificati
     * @return True se la modifica ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean modificaNodo(ClsNodo oldnodo, ClsNodo nodo) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", oldnodo.getId());
        nodo.setUsernameCreatore(this.getCredenziali().getUsername());
        return pNodi.update(tmp, nodo);
    }
    @Override
    public boolean eliminaNodo(ClsNodo n){
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", n.getId());
        return pNodi.delete(tmp);
    }

    /**
     * Inserisce direttamente un itinerario.
     *
     * @param itinerario L'itinerario da aggiungere
     * @return True se l'eliminazione ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean inserisciItinerario(ClsItinerario itinerario) {
        itinerario.setUsernameCreatore(this.credenziali.getUsername());
        return pItinerari.insert(itinerario);
    }

    @Override
    public boolean modificaItinerario(ClsItinerario itinerario, ClsItinerario itinerariovecchio) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", itinerariovecchio.getId());
        itinerario.setUsernameCreatore(this.getCredenziali().getUsername());
        return pItinerari.update(tmp, itinerario);
    }

    /**
     * Elimina direttamente un itinerario.
     *
     * @param itinerario Id dell'itinerario da eliminare.
     * @return True se l'eliminazione ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean eliminaItinerario(ClsItinerario itinerario) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", itinerario.getId());
        return pItinerari.delete(tmp);
    }
//endregion
}