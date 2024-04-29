package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Ha la  possibilità di inserire, modificare ed eliminare
 * Nodi e Itinerari direttamente senza creare richieste.
 * Questo ruolo si ottiene avendo 600+ punti.
 */
public class ClsContributorAutorizzato extends ClsContributor {
    //region Override Contributor Autorizzato

    /**
     * Inserisce direttamente il nodo creato.
     * @param nodo Il nodo da aggiungere
     * @return True se l'inserimento ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean inserisciNodo(ClsNodo nodo) {
        return pNodi.insert(nodo);
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
    public boolean modificaNodo(String id, ClsNodo nodo) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
        return pNodi.update(tmp, nodo);
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
        return pItinerari.insert(itinerario);
    }

    /**
     * Modifica direttamente un itinerario.
     *
     * @param itinerario Itinerario modificato
     * @param id Id dell'itinerario da modificare
     * @return True se la modifica ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean modificaItinerario(ClsItinerario itinerario, String id) {
        return false;
    }

    /**
     * Elimina direttamente un itinerario.
     *
     * @param id Id dell'itinerario da eliminare.
     * @return True se l'eliminazione ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean eliminaItinerario(String id) {
        return false;
    }
    //TODO
    @Override
    public boolean visualizzaNodiPosessore() {
        return false;
    }

    public ArrayList<ClsNodo> getNodi(HashMap<String, Object> filters){
        return pNodi.get(filters);
    }
//endregion
}