package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
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
    IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRDC;
    IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRDCI;

    public ClsContributor(IPersistenceModel<ClsRecensione> r, IPersistenceModel<ClsSegnalazione> s, IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRCDNodo, IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRCDItinerari, IPersistenceModel<ClsNodo> nodi, IPersistenceModel<ClsItinerario> itinerari) {
        super(s, r, pRCDNodo, nodi);
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
        ClsRichiestaAzioneDiContribuzione req = new ClsRichiestaAzioneDiContribuzione();
        req.setUsernameCreatoreRichiesta(this.getCredenziali().getUsername());
        req.seteAzioneDiContribuzione(EAzioniDiContribuzione.INSERISCI_NODO);
        req.setDatiNodo(nodo);
        return pRDC.insert(req);
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
        ClsRichiestaAzioneDiContribuzione req = new ClsRichiestaAzioneDiContribuzione();
        req.setUsernameCreatoreRichiesta(this.getCredenziali().getUsername());
        req.seteAzioneDiContribuzione(EAzioniDiContribuzione.MODIFICA_NODO);
        req.setDatiNodo(nodo);
        return pRDC.insert(req);
    }

    /**
     * Crea una richiesta di eliminazione di un nodo in suo possesso.
     * @param id Id del nodo da eliminare
     * @return True se la creazione della richiesta o l'eliminazione ha avuto successo,
     *         False altrimenti.
     */
    @Override
    public boolean eliminaNodo(String id) {
        ClsRichiestaAzioneDiContribuzione req = new ClsRichiestaAzioneDiContribuzione();
        req.setUsernameCreatoreRichiesta(this.getCredenziali().getUsername());
        req.seteAzioneDiContribuzione(EAzioniDiContribuzione.ELIMINA_NODO);
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
        req.setDatiNodo(pNodi.get(tmp).get(0));
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
        ClsRichiestaAzioneDiContribuzioneItinerario req = new ClsRichiestaAzioneDiContribuzioneItinerario();
        req.setUsernameCreatore(this.getCredenziali().getUsername());
        req.seteAzioniDiContribuzione(EAzioniDiContribuzione.INSERISCI_ITINERARIO);
        req.setDatiItinerarioNuovo(itinerario);
        return pRDCI.insert(req);
    }
    @Override
    public boolean modificaItinerario(ClsItinerario itinerario, ClsItinerario itinerariovecchio) {
        ClsRichiestaAzioneDiContribuzioneItinerario req = new ClsRichiestaAzioneDiContribuzioneItinerario();
        req.setUsernameCreatore(this.getCredenziali().getUsername());
        req.seteAzioniDiContribuzione(EAzioniDiContribuzione.MODIFICA_ITINERARIO);
        req.setDatiItinerarioVecchio(itinerariovecchio);
        req.setDatiItinerarioNuovo(itinerario);
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
        req.setDatiItinerarioNuovo(pItinerari.get(tmp).get(0));
        return pRDCI.insert(req);
    }
    //TODO: i nodi di chi?
    @Override
    public ArrayList<ClsNodo> visualizzaNodiPosessore() {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("idUtente", this.id);
        return pNodi.get(tmp);
    }
    public ArrayList<ClsItinerario> visualizzaItinerariPossessore(){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("usernameCreatore", this.credenziali.getUsername());
        return pItinerari.get(filtro);
    }
    public void partecipaContest(ClsContestDiContribuzione c){
        ClsPartecipazioneContestDiContribuzione partecipazione = new ClsPartecipazioneContestDiContribuzione();
        partecipazione.setIdContest(c.getId());
        partecipazione.setUsernamePartecipante(this.credenziali.getUsername());
    }

    //endregion
}
