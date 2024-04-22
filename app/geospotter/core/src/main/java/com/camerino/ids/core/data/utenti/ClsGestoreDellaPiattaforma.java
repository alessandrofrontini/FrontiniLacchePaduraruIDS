package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Questo ruolo ha potere assoluto.
 * Viene assegnato soltanto ai creatori della piattaforma.
 * Non è possibile diventare Gestore della Piattaforma tramite sistema a punteggi.
 */
public class ClsGestoreDellaPiattaforma extends ClsAnimatore implements ITownHallAdministrator
{
    IPersistenceModel<ClsComune> mockComuni;

    //region Getters and Setters
    public void setMockComuni (IPersistenceModel<ClsComune> mockComuni)
    {
        this.mockComuni = mockComuni;
    }
    //endregion

    //region Override ITownHallAdministrator
    /**
     * Aggiunge direttamente un comune.
     * @param comune Comune da aggiungere.
     * @return True se l'inserimento ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean inserisciComune(ClsComune comune)
    {
        return this.mockComuni.insert(comune);
    }

    /**
     * Modifica direttamente un comune.
     * @param comune Comune modificato
     * @param id Is del comune da modificare.
     * @return True se la modifica ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean modificaComune(ClsComune comune, String id)
    {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);

        return this.mockComuni.update(tmp,comune);
    }

    /**
     * Elimina direttamente un comune.
     *
     * @param id Id del comune da eliminare
     * @return True se l'eliminazione ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean eliminaComune(String id)
    {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);

        return this.mockComuni.delete(tmp);
    }
    //TODO
    @Override
    public ArrayList<ClsComune> visualizzaComuni()
    {
        return this.mockComuni.get(null);
    }
//endregion
}
