package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsComune;

/**
 * Questo ruolo ha potere assoluto.
 * Viene assegnato soltanto ai creatori della piattaforma.
 * Non è possibile diventare Gestore della Piattaforma tramite sistema a punteggi.
 */
public class ClsGestoreDellaPiattaforma extends ClsAnimatore implements ITownHallAdministrator{
    //Metodi di ITownHallAdministrator

    /**
     * Aggiunge direttamente un comune.
     * @param comune Comune da aggiungere.
     * @return True se l'inserimento ha successo,
     *         False altrimenti.
     */
    @Override
    public boolean aggiungiComune(ClsComune comune){
        return false;
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
}
