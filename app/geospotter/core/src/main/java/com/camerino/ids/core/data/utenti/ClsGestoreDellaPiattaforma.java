package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Questo ruolo ha potere assoluto.
 * Viene assegnato soltanto ai creatori della piattaforma.
 * Non è possibile diventare Gestore della Piattaforma tramite sistema a punteggi.
 */
@Entity
public class ClsGestoreDellaPiattaforma extends ClsAnimatore implements ITownHallAdministrator
{
    public ClsGestoreDellaPiattaforma(){}
    public ClsGestoreDellaPiattaforma(ClsAnimatore usr){

        this.pRDC = usr.pRDC;
        this.pRDCI = usr.pRDCI;

        this.credenziali = usr.credenziali;
        this.id = usr.id;

        this.pNodi = usr.pNodi;
        this.pItinerari = usr.pItinerari;
        this.mockComuni = usr.mockComuni;
        this.iperRecensioni = usr.iperRecensioni;
        this.iperSegnalazioni = usr.iperSegnalazioni;

        this.iperUtenti = usr.iperUtenti;
        this.iperRDCImmagini = usr.iperRDCImmagini;

    }
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
        return this.mockComuni.update(null,comune);
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
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idComune", id);
        return this.mockComuni.delete(filters);
    }
    //TODO
    @Override
    public ClsComune[] visualizzaComuni(){
        return null;
    }
/*@JsonIgnore
    public List<ClsCuratore> getAllCuratori() {
        ArrayList tmp = getUtentiByRuolo(eRUOLO_UTENTE.CURATORE);
        return tmp.stream().map(c->new ClsCuratore((ClsTuristaAutenticato)c)).toList();
    }*/
@JsonIgnore
    public ArrayList<ClsTuristaAutenticato> getUtentiByRuolo(eRUOLO_UTENTE ruolo) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("ruolo", eRUOLO_UTENTE.CURATORE);
        return iperUtenti.get(filters);
    }
//endregion
}
