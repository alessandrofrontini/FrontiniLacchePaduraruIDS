package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.azioni.EStatusRDC;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.core.persistence.convertors.ConvCredenziali;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.HashMap;
import java.util.List;

/**
 * Ruolo associato ad un utente autenticato base.
 * Ha la possibilità di richiedere l’aggiunta di immagini e può inserire,
 * modificare ed eliminare le proprie recensioni nella piattaforma.
 * E' il ruolo iniziale di un nuovo utente.
 */
@Entity
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "ruoloUtente", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClsTuristaAutenticato.class, name = "TURISTA_AUTENTICATO"),
        @JsonSubTypes.Type(value = ClsContributor.class, name = "CONTRIBUTOR"),
        @JsonSubTypes.Type(value = ClsContributorAutorizzato.class, name = "CONTRIBUTOR_AUTORIZZATO"),
        @JsonSubTypes.Type(value = ClsAnimatore.class, name = "ANIMATORE"),
        @JsonSubTypes.Type(value = ClsCuratore.class, name = "CURATORE"),
        @JsonSubTypes.Type(value = ClsGDP.class, name = "GESTORE_DELLA_PIATTAFORMA"),
})
public class ClsTuristaAutenticato extends ClsTurista implements IAzioniTuristaAutenticato {
    /**
     * Contiene i diversi ruoli nella piattaforma
     * e il loro punteggio massimo per appartenere a quel ruolo.
     */
    public enum eRUOLI_UTENTE {
        TURISTA_AUTENTICATO(49),
        CONTRIBUTOR(599),
        CONTRIBUTOR_AUTORIZZATO(999),
        ANIMATORE(1000),
        CURATORE(Integer.MAX_VALUE),
        GESTORE_DELLA_PIATTAFORMA(Integer.MAX_VALUE);

        private Integer value;

        eRUOLI_UTENTE(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
    @Id
    @GeneratedValue
    Long id = 0L;
    @Convert(converter = ConvCredenziali.class)
    Credenziali credenziali = new Credenziali();
    Integer punteggio;
    eRUOLI_UTENTE ruoloUtente;

    transient IPersistenceModel<ClsRDCImmagine> iperRDCImmagini;

    //region Constructors
    public ClsTuristaAutenticato(IPersistenceModel<ClsSegnalazione> pSegnalazioni, IPersistenceModel<ClsRecensione> pRecensioni, IPersistenceModel<ClsImmagine> pImmagini) {
    }

    public ClsTuristaAutenticato(ClsTurista usr) {
        this.iperNodi = usr.iperNodi;
        this.iperItinerari = usr.iperItinerari;
        this.iperComuni = usr.iperComuni;
        this.iperRecensioni = usr.iperRecensioni;
        this.iperSegnalazioni = usr.iperSegnalazioni;
        this.iperImmagini = usr.iperImmagini;
        this.iperUtenti = usr.iperUtenti;
    }

    public ClsTuristaAutenticato() {
    }

    public ClsTuristaAutenticato(IPersistenceModel<ClsSegnalazione> segnalazioni, IPersistenceModel<ClsRecensione> recensioni, IPersistenceModel<ClsImmagine> immagini, IPersistenceModel<ClsTuristaAutenticato> utenti) {
//    TODO    super(segnalazioni);
        iperRecensioni = recensioni;
        iperImmagini = immagini;
        iperUtenti = utenti;
    }

    public ClsTuristaAutenticato(IPersistenceModel<ClsSegnalazione> segnalazioni, Credenziali c, eRUOLI_UTENTE ruolo, IPersistenceModel<ClsRecensione> recensioni, IPersistenceModel<ClsImmagine> immagini) {
        //  TODO     super(segnalazioni);
        credenziali = c;
        ruoloUtente = ruolo;
        punteggio = ruolo.getValue();
        iperRecensioni = recensioni;
        iperImmagini = immagini;
    }

    public static eRUOLI_UTENTE convertRuoloFromString(String ruolo) {
        switch (ruolo) {
            case "TURISTA_AUTENTICATO":
                return eRUOLI_UTENTE.TURISTA_AUTENTICATO;


            case "CONTRIBUTOR":
                return eRUOLI_UTENTE.CONTRIBUTOR;


            case "CONTRIBUTOR_AUTORIZZATO":
                return eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO;

            case "ANIMATORE":
                return eRUOLI_UTENTE.ANIMATORE;


            default:
                return null;

        }
    }

    //region Getters and Setters
    public Long getId() {
        return (id);
    }

    public void setId(Long id) {
        this.id = Long.valueOf(id);
    }

    public eRUOLI_UTENTE getRuoloUtente() {
        return ruoloUtente;
    }

    public void setRuoloUtente(eRUOLI_UTENTE ruoloUtente) {
        this.ruoloUtente = ruoloUtente;
    }

    public Credenziali getCredenziali() {
        return credenziali;
    }

    public void setCredenziali(Credenziali credenziali) {
        this.credenziali = credenziali;
    }

    public void setIperRDCImmagini(IPersistenceModel<ClsRDCImmagine> iperRDCImmagini) {
        this.iperRDCImmagini = iperRDCImmagini;
    }
    public Integer getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(Integer punteggio) {
        this.punteggio = punteggio;
    }
//endregion

    //region Override IAzioniTuristaAutenticato

    public boolean pubblicaRecensione(ClsRecensione recensione) {
        recensione.setIdCreatore(this.id);
        return iperRecensioni.insert(recensione);
    }

    public boolean inserisciRecensione(ClsRecensione recensione) {
        //TODO: merge con richiesta azione di conribuzione
        recensione.setIdCreatore(this.id);
        return iperRecensioni.insert(recensione);
    }

    @Override
    public boolean eliminaRecensione(Long id) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("idRecensione", id);
        return iperRecensioni.delete(tmp);
    }

    @Override
    public boolean modificaRecensione(Long IDDaModificare, ClsRecensione newrec) {
        //TODO: merge con richiesta azione di contribuzione
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("idRecensione", IDDaModificare);
        newrec.setIdCreatore(this.id);
        return iperRecensioni.update(tmp, newrec);
    }

    @Override
    public boolean inserisciImmagine(ClsImmagine immagine) {
        ClsRDCImmagine rdcImmagine = new ClsRDCImmagine(null, immagine);
        rdcImmagine.setCreatore(this);
        rdcImmagine.setTipo(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        rdcImmagine.setStato(EStatusRDC.NUOVO);
        return iperRDCImmagini.insert(rdcImmagine);
    }
//endregion

    public boolean postRDCImmagine(ClsRDCImmagine rdc) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idUtente", this.id);
        rdc.setCreatore(this.iperUtenti.get(filters).get(0));
        rdc.setTipo(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        return iperRDCImmagini.insert(rdc);
    }

    public boolean deleteRDCImmagineById(Long idRDCImmagine) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDCImmagini", idRDCImmagine);
        return iperRDCImmagini.delete(filters);
    }

    @JsonIgnore
    public List<ClsRecensione> getRecensioniPosessore() {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("owner", this.id);
        return iperRecensioni.get(filters);
    }

    public String visualizzaUtente() {
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

    @JsonIgnore
    public List<ClsTuristaAutenticato> getAllUtenti() {
        return iperUtenti.get(null);
    }
}
