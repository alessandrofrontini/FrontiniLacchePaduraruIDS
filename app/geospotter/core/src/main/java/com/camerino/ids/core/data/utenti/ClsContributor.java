package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.*;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.HashMap;
import java.util.List;

/**
 * Questo ruolo può effettuare Richieste Di Contribuzione
 * ({@link com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione})
 * , modifica ed eliminazione
 * di Nodi e Itinerari nella piattaforma.
 * Le richieste dovranno poi essere accettate o rifiutate da un Curatore di competenza.
 * Si diventa contributor a 50+ punti.
 */
@Entity
public class ClsContributor extends ClsTuristaAutenticato implements IAzioniContributor {
    @Transient
    transient IPersistenceModel<ClsRDCNodo> iperRDCNodi;
    @Transient
    transient IPersistenceModel<ClsRdcItinerario> iperRDCItinerari;
    @Transient
    transient IPersistenceModel<ClsContestDiContribuzione> iperContest;

    //region Constructors
    public ClsContributor() {
        super();
    }

    public ClsContributor(IPersistenceModel<ClsNodo> pNodo, IPersistenceModel<ClsItinerario> pItinerari) {
        super();
        iperNodi = pNodo;
        this.iperItinerari = pItinerari;
    }

    public ClsContributor(ClsTuristaAutenticato usr) {
        this.credenziali = usr.credenziali;
        this.id = usr.id;

        this.iperNodi = usr.iperNodi;
        this.iperItinerari = usr.iperItinerari;
        this.iperComuni = usr.iperComuni;
        this.iperRecensioni = usr.iperRecensioni;
        this.iperSegnalazioni = usr.iperSegnalazioni;

        this.iperUtenti = usr.iperUtenti;
        this.iperRDCImmagini = usr.iperRDCImmagini;
    }
//endregion

    //region Getters and Setters
    @JsonIgnore
    public IPersistenceModel<ClsRDCNodo> _getIperRDCNodi() {
        return iperRDCNodi;
    }

    public void _setIperRDCNodi(IPersistenceModel<ClsRDCNodo> iperRDCNodi) {
        this.iperRDCNodi = iperRDCNodi;
    }

    @Transient
    public IPersistenceModel<ClsRdcItinerario> _getIperRDCItinerari() {
        return iperRDCItinerari;
    }

    public void _setIperRDCItinerari(IPersistenceModel<ClsRdcItinerario> iperRDCItinerari) {
        this.iperRDCItinerari = iperRDCItinerari;
    }

    public void setIperContest(IPersistenceModel<ClsContestDiContribuzione> iperContest) {
        this.iperContest = iperContest;
    }

    public void setIperRDCItinerari(IPersistenceModel<ClsRdcItinerario> iperRDCItinerari) {
        this.iperRDCItinerari = iperRDCItinerari;
    }

    public void setIperRDCNodi(IPersistenceModel<ClsRDCNodo> iperRDCNodi) {
        this.iperRDCNodi = iperRDCNodi;
    }
    //endregion

    //region Override IContributable

    @Override
    public boolean inserisciImmagine(ClsImmagine immagine, ClsContestDiContribuzione contest) {
        ClsRDCImmagine rdcImmagine = new ClsRDCImmagine(null, immagine);
        rdcImmagine.setCreatore(this);
        rdcImmagine.setTipo(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        rdcImmagine.setStato(EStatusRDC.NUOVO);
        rdcImmagine.setIdContestAppartenenza(contest);

        return iperRDCImmagini.insert(rdcImmagine);
    }

    /**
     * Crea una richiesta di inserimento nodo.
     *
     * @param nodo Il nodo da aggiungere
     * @return True se la creazione della richiesta ha avuto successo,
     * False altrimenti
     */
    public boolean inserisciNodo(ClsNodo nodo) {
        ClsRDCNodo rdc = new ClsRDCNodo(null, nodo);
        rdc.setTipo(EAzioniDiContribuzione.INSERISCI_NODO);
        rdc.setCreatore(this);
        rdc.setStato(EStatusRDC.NUOVO);
        return this.postRDCNodo(rdc);
    }

    public boolean inserisciNodo(ClsNodo nodo, ClsContestDiContribuzione contest) {
        ClsRDCNodo rdc = new ClsRDCNodo(null, nodo);
        rdc.setTipo(EAzioniDiContribuzione.INSERISCI_NODO);
        rdc.setCreatore(this);
        rdc.setStato(EStatusRDC.NUOVO);
        rdc.setIdContestAppartenenza(contest);
        nodo.setIdComuneAssociato(contest.getLocation().getId());
        return this.postRDCNodo(rdc);
    }

    /**
     * Crea una richiesta di modifica di un nodo in suo possesso.
     *
     * @param id   Id del nodo dal modificare
     * @param nodo Il nodo contenente i dati modificati
     * @return True se la creazione della richiesta o la modifica ha avuto successo,
     * False altrimenti.
     */
    @Override
    public boolean modificaNodo(Long id, ClsNodo nodo) {
        List<ClsNodo> old = getNodoById(nodo.getId());
        nodo.setId(0L);
        if (old.size() != 1)
            return false;
        ClsRDCNodo rdc = new ClsRDCNodo(old.get(0), nodo);
        rdc.setCreatore(this);
        rdc.setTipo(EAzioniDiContribuzione.MODIFICA_NODO);
        return iperRDCNodi.insert(rdc);
    }

    /**
     * Crea una richiesta di eliminazione di un nodo in suo possesso.
     *
     * @param id Id del nodo da eliminare
     * @return True se la creazione della richiesta o l'eliminazione ha avuto successo,
     * False altrimenti.
     */
    public boolean eliminaNodo(Long id) {
        List<ClsNodo> old = getNodoById(id);
        if (old.size() != 1)
            return false;
        ClsRDCNodo rdc = new ClsRDCNodo(old.get(0), null);
        rdc.setCreatore(this);
        rdc.setTipo(EAzioniDiContribuzione.ELIMINA_ITINERARIO);
        rdc.setStato(EStatusRDC.NUOVO);
        return iperRDCNodi.insert(rdc);
    }

    /**
     * Crea una richiesta di inserimento itinerario.
     * (L'itinerario può contenere nodi che non sono di proprietà del Curatore)
     *
     * @param itinerario L'itinerario da aggiungere
     * @return True se la richiesta viene creata con successo,
     * False altrimenti.
     */
    @Override
    public boolean inserisciItinerario(ClsItinerario itinerario) {
        ClsRdcItinerario req = new ClsRdcItinerario(null, itinerario);
        req.setStato(EStatusRDC.NUOVO);
        req.setCreatore(this);
        req.setTipo(EAzioniDiContribuzione.INSERISCI_ITINERARIO);

        itinerario.setIdCreatore(this.id);

        return this.iperRDCItinerari.insert(req);
    }

    /**
     * Crea una richiesta di modifica di un itinerario di sua proprietà.
     *
     * @param itinerario Itinerario modificato
     * @param id         Id dell'itinerario da modificare
     * @return True se la modifica o la creazione della rihiesta ha successo,
     * False altrimenti,
     */
    @Override
    public boolean modificaItinerario(ClsItinerario itinerario, Long id) {
        List<ClsItinerario> old = getItinerarioById(itinerario.getId());
        itinerario.setId(0L);
        if (old.size() != 1)
            return false;
        ClsRdcItinerario rdc = new ClsRdcItinerario(old.get(0), itinerario);
        rdc.setCreatore(this);
        rdc.setTipo(EAzioniDiContribuzione.MODIFICA_ITINERARIO);
        return iperRDCItinerari.insert(rdc);
    }

    /**
     * Crea una richiesta di eliminazione di un proprio itinerario.
     *
     * @param id Id dell'itinerario da eliminare.
     * @return True se l'eliminazione o la creazione della richiesta ha successo,
     * False altirmenti.
     */
    @Override
    public boolean eliminaItinerario(Long id) {
        List<ClsItinerario> old = getItinerarioById(id);
        if (old.size() != 1)
            return false;
        ClsRdcItinerario rdc = new ClsRdcItinerario(old.get(0), null);
        rdc.setCreatore(this);
        rdc.setTipo(EAzioniDiContribuzione.ELIMINA_ITINERARIO);
        rdc.setStato(EStatusRDC.NUOVO);
        return iperRDCItinerari.insert(rdc);
    }

    @JsonIgnore
    public List<ClsNodo> getNodiPossessore() {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("owner", this.id);
        return this.iperNodi.get(filters);
    }

    public boolean deleteNodo(Long idNodo) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("idNodo", idNodo);
        return iperNodi.delete(tmp);
    }

    @JsonIgnore
    public List<ClsRDCNodo> getRDCNodiById(Long idRDC) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDC", idRDC);
        return iperRDCNodi.get(filters);
    }

    public boolean deleteRDCById(Long idRDC) {
        /*HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDC", idRDC);
        return pRDC.delete(filters);*/
        return false;
    }

    public boolean putRDCNodo(ClsRDCNodo rdc) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDCNodo", rdc.getIdRichiesta());
        return iperRDCNodi.update(filters, rdc);
    }

    public boolean postRDCNodo(ClsRDCNodo rdc) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idUtente", this.id);
        rdc.setCreatore(iperUtenti.get(filters).get(0));
        return iperRDCNodi.insert(rdc);
    }

    public boolean postRDCItinerario(ClsRdcItinerario rdc) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idUtente", this.id);
        rdc.setCreatore(iperUtenti.get(filters).get(0));
        return iperRDCItinerari.insert(rdc);
    }

    @Override
    public List<ClsContestDiContribuzione> getAllContest() {
        return this.iperContest.get(null);
    }
    //endregion
}
