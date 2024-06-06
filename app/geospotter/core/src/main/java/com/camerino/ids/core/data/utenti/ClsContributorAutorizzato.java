package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import jakarta.persistence.Entity;

import java.util.HashMap;

/**
 * Ha la  possibilità di inserire, modificare ed eliminare
 * Nodi e Itinerari direttamente senza creare richieste.
 * Questo ruolo si ottiene avendo 600+ punti.
 */
@Entity
public class ClsContributorAutorizzato extends ClsContributor {
//region Constructors
    public ClsContributorAutorizzato(){}
    public ClsContributorAutorizzato(ClsContributor usr){

        this.pRDC = usr.pRDC;
        this.pRDCI = usr.pRDCI;

        this.credenziali = usr.credenziali;
        this.id = usr.id;

        this.iperNodi = usr.iperNodi;
        this.iperItinerari = usr.iperItinerari;
        this.iperComuni = usr.iperComuni;
        this.iperRecensioni = usr.iperRecensioni;
        this.iperSegnalazioni = usr.iperSegnalazioni;

        this.iperUtenti = usr.iperUtenti;
        this.iperRDCImmagini = usr.iperRDCImmagini;
        this.iperRDCNodi = usr.iperRDCNodi;
    }
//endregion

// region Override Contributor Autorizzato

    /**
     * Inserisce direttamente il nodo creato.
     * @param nodo Il nodo da aggiungere
     * @return True se l'inserimento ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean inserisciNodo(ClsNodo nodo) {
        nodo.setIdCreatore(this.id);
        return iperNodi.insert(nodo);
    }

    /**
     * Modifica direttamente un qualsiasi nodo.
     *
     * @param id Id del nodo dal modificare
     * @param nodo Il nodo contenente i dati modificati
     * @return True se la modifica ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean modificaNodo(Long id, ClsNodo nodo) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("idNodo", id);
        nodo.setIdCreatore(this.getId());
        return iperNodi.update(tmp, nodo);
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
        return iperItinerari.insert(itinerario);
    }

    /**
     * Modifica direttamente un itinerario.
     *
     * @param itinerario Itinerario modificato
     * @param idItinerario Id dell'itinerario da modificare
     * @return True se la modifica ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean modificaItinerario(ClsItinerario itinerario, Long idItinerario) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("idItinerario", idItinerario);
        return iperItinerari.update(tmp, itinerario);
    }

    /**
     * Elimina direttamente un itinerario.
     *
     * @param id Id dell'itinerario da eliminare.
     * @return True se l'eliminazione ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean eliminaItinerario(Long id) {
        return false;
    }
    //TODO
    @Override
    public boolean visualizzaNodiPosessore() {
        return false;
    }
    @Override
    public boolean eliminaNodo(Long id) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idNodo", id);
        return iperNodi.delete(filters);
    }
//endregion
}