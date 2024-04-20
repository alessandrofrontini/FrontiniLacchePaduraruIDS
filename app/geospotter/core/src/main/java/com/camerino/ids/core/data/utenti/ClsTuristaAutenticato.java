package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utils.Credenziali;

/**
 * Ruolo associato ad un utente autenticato base.
 * Ha la possibilità di richiedere l’aggiunta di immagini e può inserire,
 * modificare ed eliminare le proprie recensioni nella piattaforma.
 *
 * E' il ruolo iniziale di un nuovo utente.
 */
public class ClsTuristaAutenticato extends ClsTurista implements ILoggedUserAction{
    /**
     * Contiene i diversi ruoli nella piattaforma
     * e il loro punteggio massimo per appartenere a quel ruolo.
     */
    public enum RUOLO_UTENTE {
        TURISTA_AUTENTICATO(49),
        CONTRIBUTOR(599),
        CONTRIBUTOR_AUTORIZZATO(999),
        ANIMATORE(1000),
        CURATORE(Integer.MAX_VALUE), GESTORE_DELLA_PIATTAFORMA(Integer.MAX_VALUE);

        private Integer value;

        public Integer getValue(){
            return value;
        }

        RUOLO_UTENTE(Integer value){
            this.value = value;
        }
    }

    String id;
    Credenziali credenziali;
    int punteggio;
    RUOLO_UTENTE ruoloUtente;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RUOLO_UTENTE getRuoloUtente() {
        return ruoloUtente;
    }

    public void setRuoloUtente(RUOLO_UTENTE ruoloUtente) {
        this.ruoloUtente = ruoloUtente;
    }

    public Credenziali getCredenziali() {
        return credenziali;
    }

    public void setCredenziali(Credenziali credenziali) {
        this.credenziali = credenziali;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    //TODO
    @Override
    public boolean inserisciRecensione() {
        return false;
    }
//TODO
    @Override
    public boolean eliminaRecensione(String id) {
        return false;
    }
//TODO
    @Override
    public boolean modificaRecensione() {
        return false;
    }
//TODO
    @Override
    public boolean inserisciImmagine() {
        return false;
    }
//TODO
    @Override
    public ClsRecensione[] visualizzaRecensioniPosessore() {
        return null;
    }

}
