package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
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
    public ClsContributorAutorizzato(IPersistenceModel<ClsRecensione> r, IPersistenceModel<ClsSegnalazione> s, IPersistenceModel<ClsImmagine> i, IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRCDNodo, IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRCDItinerari, IPersistenceModel<ClsNodo> nodi, IPersistenceModel<ClsItinerario> itinerari){
        super(r, s, pRCDNodo, pRCDItinerari, nodi, itinerari);
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

    public boolean eliminaNodo(String id){
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
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
        return pItinerari.insert(itinerario);
    }

    @Override
    public boolean modificaItinerario(ClsItinerario itinerario, ClsItinerario itinerariovecchio) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
        return pItinerari.update(tmp, itinerario);
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
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
        return pItinerari.delete(tmp);
    }
//endregion
}