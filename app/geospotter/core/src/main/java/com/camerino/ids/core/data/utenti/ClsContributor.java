package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.*;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

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
@Entity
public class ClsContributor extends ClsTuristaAutenticato implements IContributable{

    @Deprecated
    @Transient
    transient IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRDC;
    @Transient
    transient IPersistenceModel<ClsRDCNodo> iperRDCNodi;
    @Deprecated
    @Transient
    transient IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRDCI;
    @Transient
    transient IPersistenceModel<ClsItinerario> iperRDCItinerari;

    public ClsContributor() {super();}
    public ClsContributor(IPersistenceModel<ClsNodo> pNodo, IPersistenceModel<ClsItinerario> pItinerari) {
        super();
        pNodi = pNodo;
        this.pItinerari = pItinerari;
    }

    public ClsContributor(ClsTuristaAutenticato usr){
        this.credenziali = usr.credenziali;
        this.id = usr.id;

        this.pNodi = usr.pNodi;
        this.pItinerari = usr.pItinerari;
        this.mockComuni = usr.mockComuni;
        this.iperRecensioni = usr.iperRecensioni;
        this.iperSegnalazioni = usr.iperSegnalazioni;

        this.iperUtenti = usr.iperUtenti;
    }

//region Getters and Setters
@Transient
    public IPersistenceModel<ClsRDCNodo> _getIperRDCNodi() {
        return iperRDCNodi;
    }

    public void _setIperRDCNodi(IPersistenceModel<ClsRDCNodo> iperRDCNodi) {
        this.iperRDCNodi = iperRDCNodi;
    }
    @Transient
    public IPersistenceModel<ClsItinerario> _getIperRDCItinerari() {
        return iperRDCItinerari;
    }

    public void _setIperRDCItinerari(IPersistenceModel<ClsItinerario> iperRDCItinerari) {
        this.iperRDCItinerari = iperRDCItinerari;
    }
@Deprecated
    public void setpRDC(IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRDC) {
        this.pRDC = pRDC;
    }
@Deprecated
    public void setpRDCI(IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRDCI) {
        this.pRDCI = pRDCI;
    }
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
        ClsRDCNodo rdc = new ClsRDCNodo(null, nodo);
        rdc.setCreatore(this);
        rdc.setTipo(EAzioniDiContribuzione.INSERISCI_NODO);
        return iperRDCNodi.insert(rdc);
    }

    /**
     * Crea una richiesta di modifica di un nodo in suo possesso.
     * @param id Id del nodo dal modificare
     * @param nodo Il nodo contenente i dati modificati
     * @return True se la creazione della richiesta o la modifica ha avuto successo,
     *         False altrimenti.
     */
    @Override
    public boolean modificaNodo(String id, ClsNodo nodo) {
        ArrayList<ClsNodo> old = getNodoById(nodo.getId());
        if(old.size() != 1)
            return false;
        ClsRDCNodo rdc = new ClsRDCNodo(old.get(0), nodo);
        rdc.setCreatore(this);
        rdc.setTipo(EAzioniDiContribuzione.MODIFICA_NODO);
        return iperRDCNodi.insert(rdc);
    }

    /**
     * Crea una richiesta di eliminazione di un nodo in suo possesso.
     * @param id Id del nodo da eliminare
     * @return True se la creazione della richiesta o l'eliminazione ha avuto successo,
     *         False altrimenti.
     */
    @Override
    public boolean eliminaNodo(String id) {
        ArrayList<ClsNodo> old = getNodoById(id);
        if(old.size() != 1)
            return false;
        ClsRDCNodo rdc = new ClsRDCNodo(old.get(0), null);
        rdc.setCreatore(this);
        rdc.setTipo(EAzioniDiContribuzione.ELIMINA_NODO);
        return iperRDCNodi.insert(rdc);
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
        ClsRichiestaAzioneDiContribuzioneItinerario req = new ClsRichiestaAzioneDiContribuzioneItinerario();
        req.setUsernameCreatore(this.getCredenziali().getUsername());
        req.seteAzioniDiContribuzione(EAzioniDiContribuzione.INSERISCI_ITINERARIO);
        req.setDatiItinerario(itinerario);
        return pRDCI.insert(req);
    }

    /**
     * Crea una richiesta di modifica di un itinerario di sua proprietà.
     * @param itinerario Itinerario modificato
     * @param id Id dell'itinerario da modificare
     * @return True se la modifica o la creazione della rihiesta ha successo,
     *         False altrimenti,
     */
    @Override
    public boolean modificaItinerario(ClsItinerario itinerario, String id) {
        ClsRichiestaAzioneDiContribuzioneItinerario req = new ClsRichiestaAzioneDiContribuzioneItinerario();
        req.setUsernameCreatore(this.getCredenziali().getUsername());
        req.seteAzioniDiContribuzione(EAzioniDiContribuzione.MODIFICA_ITINERARIO);
        req.setDatiItinerario(itinerario);
        return pRDCI.insert(req);
    }

    /**
     * Crea una richiesta di eliminazione di un proprio itinerario.
     * @param id Id dell'itinerario da eliminare.
     * @return True se l'eliminazione o la creazione della richiesta ha successo,
     *         False altirmenti.
     */
    @Override
    public boolean eliminaItinerario(String id) {
        ClsRichiestaAzioneDiContribuzioneItinerario req = new ClsRichiestaAzioneDiContribuzioneItinerario();
        req.setUsernameCreatore(this.getCredenziali().getUsername());
        req.seteAzioniDiContribuzione(EAzioniDiContribuzione.ELIMINA_ITINERARIO);
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
        req.setDatiItinerario(pItinerari.get(tmp).get(0));
        return pRDCI.insert(req);
    }

    @Override
    public boolean visualizzaNodiPosessore() {
        return false;
    }

    public boolean deleteNodo(String idNodo) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("idNodo", idNodo);
        return pNodi.delete(tmp);
    }

    public ArrayList<ClsRDCNodo> getRDCNodiById(String idRDC) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDC", idRDC);
        return iperRDCNodi.get(filters);
    }

    public boolean deleteRDCById(String idRDC) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDC", idRDC);
        return pRDC.delete(filters);
    }

    public boolean putRDCNodo(ClsRDCNodo rdc) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDCNodo", rdc.getIdRichiesta());
        return iperRDCNodi.update(filters, rdc);
    }

    public boolean postRDCNodo(ClsRDCNodo rdc) {
        return iperRDCNodi.insert(rdc);
    }
    //endregion
}
