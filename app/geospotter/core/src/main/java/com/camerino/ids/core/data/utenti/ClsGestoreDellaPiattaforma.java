package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.persistence.IPersistenceModel;
import jakarta.persistence.Entity;

/**
 * Questo ruolo ha potere assoluto.
 * Viene assegnato soltanto ai creatori della piattaforma.
 * Non è possibile diventare Gestore della Piattaforma tramite sistema a punteggi.
 */
@Entity
public class ClsGestoreDellaPiattaforma extends ClsAnimatore implements ITownHallAdministrator
{
    transient IPersistenceModel<ClsComune> mockComuni;

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
    public boolean modificaComune(ClsComune comune, String id){
        return  false;
    }

    /**
     * Elimina direttamente un comune.
     *
     * @param id Id del comune da eliminare
     * @return True se l'eliminazione ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean eliminaComune(String id){
        return false;
    }
    //TODO
    @Override
    public ClsComune[] visualizzaComuni(){
        return null;
    }
//endregion
}
