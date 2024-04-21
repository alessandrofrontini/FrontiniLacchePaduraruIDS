package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;

import static com.camerino.ids.core.data.azioni.EAzioniDiContribuzione.MODIFICA_NODO;

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

    IPersistenceModel<ClsNodo> pNodi;
    IPersistenceModel<ClsItinerario> pItinerari;
    IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRDC;

    public ClsContributor() {super();}
    public ClsContributor(IPersistenceModel<ClsNodo> pNodo, IPersistenceModel<ClsItinerario> pItinerari) {
        super();
        pNodi = pNodo;
        this.pItinerari = pItinerari;
    }
//region Getters and Setters

    public void setpNodi(IPersistenceModel<ClsNodo> pNodi) {
        this.pNodi = pNodi;
    }

    public void setpItinerari(IPersistenceModel<ClsItinerario> pItinerari) {
        this.pItinerari = pItinerari;
    }

    public void setpRDC(IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRDC) {
        this.pRDC = pRDC;
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
        //TODO
        ClsRichiestaAzioneDiContribuzione req = new ClsRichiestaAzioneDiContribuzione();
        return pNodi.insert(nodo);
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
        req.setDatiNodo(nodo);
        req.seteAzioneDiContribuzione(MODIFICA_NODO);
        req.setUsernameCreatoreRichiesta(this.credenziali.getUsername());
        return false;
    }

    /**
     * Crea una richiesta di eliminazione di un nodo in suo possesso.
     * @param id Id del nodo da eliminare
     * @return True se la creazione della richiesta o l'eliminazione ha avuto successo,
     *         False altrimenti.
     */
    @Override
    public boolean eliminaNodo(String id) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
        return pNodi.delete(tmp);
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
        return false;
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
        return false;
    }

    /**
     * Crea una richiesta di eliminazione di un proprio itinerario.
     * @param id Id dell'itinerario da eliminare.
     * @return True se l'eliminazione o la creazione della richiesta ha successo,
     *         False altirmenti.
     */
    @Override
    public boolean eliminaItinerario(String id) {
        return false;
    }
    //TODO: i nodi di chi?
    @Override
    public boolean visualizzaNodiPosessore() {
        return false;
    }

    //endregion
}
