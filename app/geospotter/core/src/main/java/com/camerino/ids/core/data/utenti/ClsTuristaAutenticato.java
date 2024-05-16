package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.persistence.convertors.ConvCredenziali;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;

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
    public ClsTuristaAutenticato(){}
    public ClsTuristaAutenticato(ClsTurista usr){
        this.pNodi = usr.pNodi;
        this.pItinerari = usr.pItinerari;
        this.mockComuni = usr.mockComuni;
        this.iperRecensioni = usr.iperRecensioni;
        this.iperSegnalazioni = usr.iperSegnalazioni;
    }

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
    
    public ClsTuristaAutenticato(IPersistenceModel<ClsSegnalazione> segnalazioni, IPersistenceModel<ClsRecensione> recensioni, IPersistenceModel<ClsImmagine> immagini){
        //super(segnalazioni);
        iperRecensioni = recensioni;
        pImmagini = immagini;
    }
    public ClsTuristaAutenticato(IPersistenceModel<ClsSegnalazione> segnalazioni, Credenziali c, eRUOLO_UTENTE ruolo, IPersistenceModel<ClsRecensione> recensioni, IPersistenceModel<ClsImmagine> immagini){
        //super(segnalazioni);
        credenziali = c;
        ruoloUtente = ruolo;
        punteggio = ruolo.getValue();
        iperRecensioni = recensioni;
        pImmagini = immagini;
    }
    //region Override ILoggedUserAction
    @Override
    public boolean inserisciRecensione(ClsRecensione recensione) {
        //TODO: merge con richiesta azione di conribuzione
        return iperRecensioni.insert(recensione);
    }
    @Override
    public boolean eliminaRecensione(String id) {
        //TODO: merge con richiesta azione di contribuzione
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
        return iperRecensioni.delete(tmp);
    }
    @Override
    public boolean modificaRecensione(ClsRecensione old, ClsRecensione newrec) {
        //TODO: merge con richiesta azione di contribuzione
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", old.getId());
        return iperRecensioni.update(tmp, newrec);
    }
    @Override
    public boolean inserisciImmagine(ClsImmagine immagine) {
        //TODO: merge con richiesta azione di contribuzione
        return pImmagini.insert(immagine);
    }
    @Override
    public ClsRecensione[] visualizzaRecensioniPosessore() {
        //TODO: manca l'associazione recensione - utente che la scrive
        return null;
    }
//endregion

    public String visualizzaUtente()
    {
        String tmp = "";

        tmp += "ID: " + this.getId() + "\n";
        tmp += "Username: " + this.getCredenziali().getUsername() + "\n";
        tmp += "Punteggio: " + this.getPunteggio() + "\n";
        tmp += "Ruolo: " + this.getRuoloUtente().toString() + "\n";

        return tmp;

    }

    @Override
    public ClsTuristaAutenticato clone() {
        ClsTuristaAutenticato clone = new ClsTuristaAutenticato();

        clone.setId(this.id);
        clone.setPunteggio(this.getPunteggio());
        clone.setCredenziali(this.credenziali);
        clone.setRuoloUtente(this.ruoloUtente);

        return clone;
    }

    public static eRUOLO_UTENTE convertRuoloFromString (String ruolo)
    {
        switch(ruolo)
        {
            case "TURISTA_AUTENTICATO":
                return eRUOLO_UTENTE.TURISTA_AUTENTICATO;


            case "CONTRIBUTOR":
                return eRUOLO_UTENTE.CONTRIBUTOR;


            case "CONTRIBUTOR_AUTORIZZATO":
                return eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO;

            case "ANIMATORE":
                return eRUOLO_UTENTE.ANIMATORE;


            default:
                return null;

        }

    }
}
