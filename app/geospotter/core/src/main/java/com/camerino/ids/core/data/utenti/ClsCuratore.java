package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Questo ruolo accetta o respinge le richieste fatte dai vari utenti.
 * Ha anche la possibilità di regolare le autorizzazioni (ruoli) associati ai
 * vari utenti, per governare il loro comportamento.
 * Non è possibile diventare Curatore tramite sistema a punteggi.
 */
public class ClsCuratore extends ClsAnimatore{
    //TODO: Ha senso? é una relazione molti a molti dovremmo creare una classe
    // che associa comuni e curatori. Se un utente arriva ad essere curatore in pi comuni
    // creaiamo deve creare un'altro account?
    // (modificato id da String a long)
    IPersistenceModel<ClsTuristaAutenticato> pUtenti;
    ClsComune comuneAssociato;
    public ClsComune getComuneAssociato() {
        return comuneAssociato;
    }

    public void setComuneAssociato(ClsComune comuneAssociato) {
        this.comuneAssociato = comuneAssociato;
    }
    public ClsCuratore(IPersistenceModel<ClsRecensione> r, IPersistenceModel<ClsSegnalazione> s, IPersistenceModel<ClsImmagine> i, IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRCDNodo, IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRCDItinerari, IPersistenceModel<ClsNodo> nodi, IPersistenceModel<ClsItinerario> itinerari, IPersistenceModel<ClsContestDiContribuzione> contest, ClsComune c, IPersistenceModel<ClsTuristaAutenticato>utenti){
        super(r, s, i, pRCDNodo, pRCDItinerari, nodi, itinerari, contest);
        comuneAssociato = c;
        pUtenti = utenti;
    }

    public boolean registraUtente(ClsTuristaAutenticato utente){
        return pUtenti.insert(utente);
    }
    public boolean modificaInformazioniUtente(HashMap<String, Object> filtri, ClsTuristaAutenticato utente){
        return pUtenti.update(filtri, utente);
    }

    public boolean eliminaUtente(String idUtente){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", idUtente);
        return pUtenti.delete(filtro);
    }
    public boolean upRank(String idUtente){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", idUtente);
        ClsTuristaAutenticato utente = pUtenti.get(filtro).get(0);
        switch (utente.getRuoloUtente()){
            case TURISTA_AUTENTICATO: ClsContributor contributor = (ClsContributor) utente; eliminaUtente(idUtente); return registraUtente(contributor);
            case CONTRIBUTOR: ClsContributorAutorizzato contributorAuth = (ClsContributorAutorizzato) utente; eliminaUtente(idUtente); return registraUtente(contributorAuth);
            case CONTRIBUTOR_AUTORIZZATO: ClsAnimatore animatore = (ClsAnimatore) utente; eliminaUtente(idUtente); return registraUtente(animatore);
        }
        return false;
    }
    public boolean downRank(String idUtente){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", idUtente);
        ClsTuristaAutenticato utente = pUtenti.get(filtro).get(0);
        switch (utente.getRuoloUtente()){
            case CONTRIBUTOR: ClsTuristaAutenticato turistaAuth = utente; eliminaUtente(idUtente); return registraUtente(turistaAuth);
            case CONTRIBUTOR_AUTORIZZATO: ClsContributor contributor = (ClsContributor) utente; eliminaUtente(idUtente); return registraUtente(contributor);
            case ANIMATORE: ClsContributorAutorizzato contributorAuth = (ClsContributorAutorizzato) utente; eliminaUtente(idUtente); return registraUtente(contributorAuth);
        }
        return false;
    }
    public boolean resetRank(String idUtente){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", idUtente);
        ClsTuristaAutenticato utente = pUtenti.get(filtro).get(0);
        ClsTuristaAutenticato turistaAuth = (ClsTuristaAutenticato) utente;
        eliminaUtente(idUtente);
        return registraUtente(turistaAuth);
    }
    public boolean validaRichiesta(ClsRichiestaAzioneDiContribuzione richiesta, boolean esito){
        if(esito){
            switch (richiesta.geteAzioneDiContribuzione()){
                case INSERISCI_NODO -> validaNodo(richiesta);
                case MODIFICA_NODO -> validaModificaNodo(richiesta);
                case ELIMINA_NODO -> validaEliminaNodo(richiesta);
                case INSERISCI_IMMAGINE -> validaImmagine(richiesta);
            }
        }
        return esito;
    }
    private void validaNodo(ClsRichiestaAzioneDiContribuzione richiesta){
        associaCuratoreRichiesta(richiesta);
        pNodi.insert(richiesta.getDatiNodo());
    }
    private void validaImmagine(ClsRichiestaAzioneDiContribuzione richiesta){
        pImmagini.insert(richiesta.getDatiImmagine());
        associaCuratoreRichiesta(richiesta);
    }
    private void validaModificaNodo(ClsRichiestaAzioneDiContribuzione richiesta){
        HashMap<String, Object> id = new HashMap<>();
        id.put("id", richiesta.getDatiNodo().getId());
        pNodi.update(id, richiesta.getDatiNodo());
        associaCuratoreRichiesta(richiesta);
    }
    private void validaEliminaNodo(ClsRichiestaAzioneDiContribuzione richiesta){
        HashMap<String, Object> id = new HashMap<>();
        id.put("id", richiesta.getDatiNodo().getId());
        pNodi.delete(id);
        associaCuratoreRichiesta(richiesta);
    }
    private void associaCuratoreRichiesta(ClsRichiestaAzioneDiContribuzione richiesta){
        HashMap<String, Object> curatore = new HashMap<>();
        curatore.put("idCuratore", this.id);
        pRDC.update(curatore, richiesta);
    }
    private void associaCuratoreRichiestaItinerario(ClsRichiestaAzioneDiContribuzioneItinerario richiesta){
        HashMap<String, Object> curatore = new HashMap<>();
        curatore.put("idCuratore", this.id);
        pRDCI.update(curatore, richiesta);
    }
    public boolean validaRichiestaItinerario(ClsRichiestaAzioneDiContribuzioneItinerario richiesta, boolean esito){
        if(esito){
            switch (richiesta.geteAzioniDiContribuzione()){
                case INSERISCI_ITINERARIO -> validaItinerario(richiesta);
                case MODIFICA_ITINERARIO -> validaModificaItinerario(richiesta);
                case ELIMINA_ITINERARIO -> validaEliminaItinerario(richiesta);
            }
        }
        return esito;
    }
    private void validaItinerario(ClsRichiestaAzioneDiContribuzioneItinerario richiesta){
        pItinerari.insert(richiesta.getDatiItinerarioVecchio());
        associaCuratoreRichiestaItinerario(richiesta);
    }
    private void validaModificaItinerario(ClsRichiestaAzioneDiContribuzioneItinerario richiesta){
        HashMap<String, Object> id = new HashMap<>();
        id.put("id", richiesta.getDatiItinerarioVecchio().getId());
        pItinerari.update(id, richiesta.getDatiItinerarioNuovo());
        associaCuratoreRichiestaItinerario(richiesta);
    }
    private void validaEliminaItinerario(ClsRichiestaAzioneDiContribuzioneItinerario richiesta){
        HashMap<String, Object> id = new HashMap<>();
        id.put("id", richiesta.getDatiItinerarioVecchio().getId());
        pItinerari.delete(id);
        associaCuratoreRichiestaItinerario(richiesta);
    }
    public boolean validaSegnalazione(ClsSegnalazione segnalazione, boolean esito){
        //TODO IT4
        return esito;
    }
    public ArrayList<ClsRichiestaAzioneDiContribuzione> getRichieste(){
        HashMap<String, Object> filtri = new HashMap<>();
        filtri.put("idComune", comuneAssociato.getId());
        return pRDC.get(filtri);
    }
    public ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> getRichiesteItinerari(){
        HashMap<String, Object> filtri = new HashMap<>();
        filtri.put("idComune", comuneAssociato.getId());
        return pRDCI.get(filtri);
    }

    public ArrayList<ClsSegnalazione> getSegnalazioni(){
        HashMap<String, Object> filtri = new HashMap<>();
        filtri.put("idComune", comuneAssociato.getId());
        return pSegnalazioni.get(filtri);
    }

}
