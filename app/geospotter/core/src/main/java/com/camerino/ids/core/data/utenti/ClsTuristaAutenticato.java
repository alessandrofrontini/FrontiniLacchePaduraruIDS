package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.punteggio.IPunteggioManager;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Ruolo associato ad un utente autenticato base.
 * Ha la possibilità di richiedere l’aggiunta di immagini e può inserire,
 * modificare ed eliminare le proprie recensioni nella piattaforma.
 * E' il ruolo iniziale di un nuovo utente.
 */
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

    String id;
    Credenziali credenziali;
    int punteggio;
    eRUOLO_UTENTE ruoloUtente;

    IPersistenceModel<ClsRecensione> pRecensioni;
    IPersistenceModel<ClsRichiestaAzioneDiContribuzione>  pRichiestaImmagini;
    IPersistenceModel<ClsNodo> pNodi;

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
//endregion

    public ClsTuristaAutenticato(IPersistenceModel<ClsSegnalazione> segnalazioni, IPersistenceModel<ClsRecensione> recensioni, IPersistenceModel<ClsRichiestaAzioneDiContribuzione> immagini, IPersistenceModel<ClsNodo> nodi){
        super(segnalazioni);
        pRecensioni = recensioni;
        pRichiestaImmagini = immagini;
        pNodi = nodi;
    }
    public ClsTuristaAutenticato(IPersistenceModel<ClsSegnalazione> segnalazioni, Credenziali c, eRUOLO_UTENTE ruolo, IPersistenceModel<ClsRecensione> recensioni, IPersistenceModel<ClsRichiestaAzioneDiContribuzione> immagini){
        super(segnalazioni);
        credenziali = c;
        ruoloUtente = ruolo;
        punteggio = ruolo.getValue();
        pRecensioni = recensioni;
        pRichiestaImmagini = immagini;
    }
    @Override
    public boolean inserisciRecensione(ClsRecensione recensione) {

        return pRecensioni.insert(recensione);
    }
    @Override
    public boolean eliminaRecensione(String id) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", id);
        return pRecensioni.delete(tmp);
    }
    @Override
    public boolean modificaRecensione(ClsRecensione old, ClsRecensione newrec) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", old.getId());
        return pRecensioni.update(tmp, newrec);
    }
    @Override
    public boolean inserisciImmagine(ClsImmagine immagine) {
        ClsRichiestaAzioneDiContribuzione richiesta = new ClsRichiestaAzioneDiContribuzione();
        richiesta.setDatiImmagine(immagine);
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", immagine.getIdCOntenutoAssociato());
        richiesta.setDatiNodo(pNodi.get(filtro).get(0));
        richiesta.seteAzioneDiContribuzione(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        richiesta.setUsernameCreatoreRichiesta(this.credenziali.getUsername());
        return pRichiestaImmagini.insert(richiesta);
    }

    @Override
    public ArrayList<ClsRecensione> visualizzaRecensioniPosessore() {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("usernameCreatore", this.getCredenziali().getUsername());
        return pRecensioni.get(tmp);
    }
    @Override
    public boolean segnalaContenuto(ClsSegnalazione segnalazione){
        segnalazione.setIdUtente(this.id);
        return pSegnalazioni.insert(segnalazione);
    }
//endregion
}
