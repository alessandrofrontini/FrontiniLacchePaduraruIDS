package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsItinerario;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsRecensione;
import com.camerino.ids.fps.geospotter.server.persistence.IPersistenceModel;
import com.camerino.ids.fps.geospotter.server.persistence.mock.MockItinerari;
import com.camerino.ids.fps.geospotter.server.persistence.mock.MockNodi;

import java.util.HashMap;

/**
 * Questo ruolo può effettuare richieste di inserimento, modifica ed eliminazione
 * di Nodi e Itinerari nella piattaforma.
 * Le richieste dovranno poi essere accettate o rifiutate da un Curatore di competenza.
 * Si diventa contributor a 50+ punti.
 */
public class ClsContributor extends ClsTuristaAutenticato implements IContributable{

    IPersistenceModel<ClsNodo> mNodi = MockNodi.getInstance();
    MockItinerari mItinerari = MockItinerari.getInstance();

    //Metodi di IContributable

    /**
     * Crea una richiesta di inserimento nodo.
     * @param nodo Il nodo da aggiungere
     * @return True se la creazione della richiesta ha avuto successo,
     *         False altrimenti
     */
    @Override
    public boolean inserisciNodo(ClsNodo nodo) {
        //TODO: creare richiesta invece di aggiungere nodo
        return mNodi.insert(nodo);
    }

    /**TODO: giusto?
     * Se il nodo è di sua proprietò la modifica avviene subito,
     * sennò viene creata una richiesta di modifica.
     * @param id Id del nodo dal modificare
     * @param nodo Il nodo contenente i dati modificati
     * @return True se la creazione della richiesta o la modifica ha avuto successo,
     *         False altrimenti.
     */
    @Override
    public boolean modificaNodo(String id, ClsNodo nodo) {
        return false;
    }

    /**TODO: giusto?
     * Se il nodo è di sua proprietà l'eliminazione avviene istantaneamente.
     * sennò viene creata una richiesta di eliminazione.
     * @param id Id del nodo da eliminare
     * @return True se la creazione della richiesta o l'eliminazione ha avuto successo,
     *         False altrimenti.
     */
    @Override
    public boolean eliminaNodo(String id) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
        return mNodi.delete(tmp);
    }

    /**
     * Crea una richiesta di inserimento itinerario.
     * @param itinerario L'itinerario da aggiungere
     * @return True se la richiesta viene creata con successo,
     *         False altrimenti.
     */
    @Override
    public boolean inserisciItinerario(ClsItinerario itinerario) {
        return false;
    }

    /**TODO: giusto?
     * Se l'itinerario è di sua proprietà, la modifica avviene subito,
     * @param itinerario Itinerario modificato
     * @param id Id dell'itinerario da modificare
     * @return True se la modifica o la creazione della rihiesta ha successo,
     *         False altrimenti,
     */
    @Override
    public boolean modificaItinerario(ClsItinerario itinerario, String id) {
        return false;
    }

    /**TODO: giusto?
     * Se l'itinerario è di sua proprietà, l'eliminazione avviene subito,
     * sennò viene creata una richiesta di eliminazione itinerario.
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
}
