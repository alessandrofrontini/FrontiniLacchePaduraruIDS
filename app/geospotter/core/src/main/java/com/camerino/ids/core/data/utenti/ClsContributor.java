package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.*;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Questo ruolo può effettuare Richieste Di Contribuzione
 * ({@link com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione})
 * , modifica ed eliminazione
 * di Nodi e Itinerari nella piattaforma.
 * Le richieste dovranno poi essere accettate o rifiutate da un Curatore di competenza.
 * Si diventa contributor a 50+ punti.
 *
 */
public class ClsContributor extends ClsTuristaAutenticato implements IContributable{
    IPersistenceModel<ClsItinerario> pItinerari;
    IPersistenceModel<ClsRDCNodo> pRDC;
    IPersistenceModel<ClsRDCItinerario> pRDCI;

    public ClsContributor(IPersistenceModel<ClsRecensione> r, IPersistenceModel<ClsSegnalazione> s, IPersistenceModel<ClsRDCImmagine> pRCDImmagini,IPersistenceModel<ClsRDCNodo> pRCDNodo,  IPersistenceModel<ClsRDCItinerario> pRCDItinerari, IPersistenceModel<ClsNodo> nodi, IPersistenceModel<ClsItinerario> itinerari) {
        super(s, r, pRCDImmagini, nodi);
        this.pRDC = pRCDNodo;
        this.pRDCI = pRCDItinerari;
        this.pItinerari = itinerari;
    }

//region Getters and Setters
//endregion
    //Region Override IContributable

    /**
     * Crea una richiesta di inserimento nodo.
     * @param nodo Il nodo da aggiungere
     * @return True se la creazione della richiesta ha avuto successo,
     *         False altrimenti
     */
    @Override
    public boolean inserisciNodo(ClsNodo nodo) {
        ClsRDCNodo req = new ClsRDCNodo(null, nodo);
        req.setCreatore(this);
        req.setTipo(EAzioniDiContribuzione.INSERISCI_NODO);
        req.setStato(EStatusRDC.ASSEGNATO);
        return pRDC.insert(req);
    }

    /**
     * Crea una richiesta di modifica di un nodo in suo possesso.
     * @param oldnodo nodo da modificare
     * @param nodo Il nodo contenente i dati modificati
     * @return True se la creazione della richiesta o la modifica ha avuto successo,
     *         False altrimenti.
     */
    @Override
    public boolean modificaNodo(ClsNodo oldnodo, ClsNodo nodo) {
        ClsRDCNodo req = new ClsRDCNodo(oldnodo, nodo);
        req.setCreatore(this);
        req.setTipo(EAzioniDiContribuzione.MODIFICA_NODO);
        req.setStato(EStatusRDC.ASSEGNATO);
        return pRDC.insert(req);
    }

    /**
     * Crea una richiesta di eliminazione di un nodo in suo possesso.
     * @param nodo nodo da eliminare
     * @return True se la creazione della richiesta o l'eliminazione ha avuto successo,
     *         False altrimenti.
     */
    @Override
    public boolean eliminaNodo(ClsNodo nodo) {
        ClsRDCNodo req = new ClsRDCNodo(null, nodo);
        req.setCreatore(this);
        req.setTipo(EAzioniDiContribuzione.ELIMINA_NODO);
        req.setStato(EStatusRDC.ASSEGNATO);
        return pRDC.insert(req);
    }

    /**
     * Crea una richiesta di inserimento itinerario.
     * (L'itinerario può contenere nodi che non sono di proprietà del Curatore)
     * @param itinerario L'itinerario da aggiungere
     * @return True se la richiesta viene creata con successo,
     *         False altrimenti.
     */
    @Override
    public boolean inserisciItinerario(ClsItinerario itinerario) {
        ClsRDCItinerario req = new ClsRDCItinerario(null, itinerario);
        req.setCreatore(this);
        req.setTipo(EAzioniDiContribuzione.INSERISCI_ITINERARIO);
        req.setStato(EStatusRDC.ASSEGNATO);
        return pRDCI.insert(req);
    }
    @Override
    public boolean modificaItinerario(ClsItinerario itinerario, ClsItinerario itinerariovecchio) {
        ClsRDCItinerario req = new ClsRDCItinerario(itinerariovecchio, itinerario);
        req.setCreatore(this);
        req.setTipo(EAzioniDiContribuzione.MODIFICA_ITINERARIO);
        req.setStato(EStatusRDC.ASSEGNATO);
        return pRDCI.insert(req);
    }

    /**
     * Crea una richiesta di eliminazione di un proprio itinerario.
     * @param itinerario l'itinerario da eliminare.
     * @return True se l'eliminazione o la creazione della richiesta ha successo,
     *         False altirmenti.
     */
    @Override
    public boolean eliminaItinerario(ClsItinerario itinerario) {
        ClsRDCItinerario req = new ClsRDCItinerario(null, itinerario);
        req.setCreatore(this);
        req.setTipo(EAzioniDiContribuzione.ELIMINA_ITINERARIO);
        req.setStato(EStatusRDC.ASSEGNATO);
        return pRDCI.insert(req);
    }
    //TODO: i nodi di chi?
    @Override
    public ArrayList<ClsNodo> visualizzaNodiPosessore() {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("usernameCreatore", this.credenziali.getUsername());
        return pNodi.get(tmp);
    }
    public ArrayList<ClsItinerario> visualizzaItinerariPossessore(){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("usernameCreatore", this.credenziali.getUsername());
        return pItinerari.get(filtro);
    }

    //endregion
}
