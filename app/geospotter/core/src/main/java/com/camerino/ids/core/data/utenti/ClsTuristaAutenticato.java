package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.persistence.convertors.ConvCredenziali;
import com.camerino.ids.core.persistence.convertors.ConvPosizione;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

/**
 * Ruolo associato ad un utente autenticato base.
 * Ha la possibilità di richiedere l’aggiunta di immagini e può inserire,
 * modificare ed eliminare le proprie recensioni nella piattaforma.
 * E' il ruolo iniziale di un nuovo utente.
 */
@Entity
public class ClsTuristaAutenticato extends ClsTurista implements ILoggedUserAction{
    /**
     * Contiene i diversi ruoli nella piattaforma
     * e il loro punteggio massimo per appartenere a quel ruolo.
     */
    public enum eRUOLO_UTENTE {
        TURISTA_AUTENTICATO(49),
        CONTRIBUTOR(599),
        CONTRIBUTOR_AUTORIZZATO(999),
        ANIMATORE(1000),
        CURATORE(Integer.MAX_VALUE),
        GESTORE_DELLA_PIATTAFORMA(Integer.MAX_VALUE);

        private Integer value;

        public Integer getValue(){
            return value;
        }

        eRUOLO_UTENTE(Integer value){
            this.value = value;
        }
    }
    @Id
    @UuidGenerator
    String id;
    @Convert(converter = ConvCredenziali.class)
    Credenziali credenziali;
    int punteggio;
    eRUOLO_UTENTE ruoloUtente;

    //region Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public eRUOLO_UTENTE getRuoloUtente() {
        return ruoloUtente;
    }

    public void setRuoloUtente(eRUOLO_UTENTE ruoloUtente) {
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
    public boolean pubblicaRecensione(ClsRecensione recensione) {
        recensione.setUsernameCreatore(this.getCredenziali().getUsername());
        return iperRecensioni.insert(recensione);
    }
//endregion
    //region Override ILoggedUserAction
    @Override
    public boolean inserisciRecensione() {
        return false;
    }
    @Override
    public boolean eliminaRecensione(String id) {
        return false;
    }
    @Override
    public boolean modificaRecensione() {
        return false;
    }
    @Override
    public boolean inserisciImmagine() {
        return false;
    }
    @Override
    public ClsRecensione[] visualizzaRecensioniPosessore() {
        return null;
    }
//endregion
}
