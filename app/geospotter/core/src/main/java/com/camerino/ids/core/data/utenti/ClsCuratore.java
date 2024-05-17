package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
    public void validaRichiesta(ClsRichiestaAzioneDiContribuzione richiesta, boolean esito){
        if(esito){
            switch (richiesta.geteAzioneDiContribuzione()){
                case INSERISCI_NODO -> validaNodo(richiesta);
                case MODIFICA_NODO -> validaModificaNodo(richiesta);
                case ELIMINA_NODO -> validaEliminaNodo(richiesta);
                case INSERISCI_IMMAGINE -> validaImmagine(richiesta);
            }
        }
    }
    private void validaNodo(ClsRichiestaAzioneDiContribuzione richiesta){
        richiesta.setUsernameCuratore(this.getCredenziali().getUsername());
        richiesta.getDatiNodo().setUsernameCreatore(richiesta.getUsernameCreatoreRichiesta());
        pNodi.insert(richiesta.getDatiNodo());
    }
    private void validaImmagine(ClsRichiestaAzioneDiContribuzione richiesta){
        richiesta.setUsernameCuratore(this.getCredenziali().getUsername());
        richiesta.getDatiImmagine().setUsernameCreatore(richiesta.getUsernameCreatoreRichiesta());
        pImmagini.insert(richiesta.getDatiImmagine());
    }
    private void validaModificaNodo(ClsRichiestaAzioneDiContribuzione richiesta){
        HashMap<String, Object> id = new HashMap<>();
        richiesta.setUsernameCuratore(this.getCredenziali().getUsername());
        id.put("id", richiesta.getDatiNodo().getId());
        pNodi.update(id, richiesta.getDatiNodo());
    }
    private void validaEliminaNodo(ClsRichiestaAzioneDiContribuzione richiesta){
        HashMap<String, Object> id = new HashMap<>();
        richiesta.setUsernameCuratore(this.getCredenziali().getUsername());
        id.put("id", richiesta.getDatiNodo().getId());
        pNodi.delete(id);
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
        richiesta.setUsernameCuratore(this.getCredenziali().getUsername());
        pItinerari.insert(richiesta.getDatiItinerarioVecchio());
    }
    private void validaModificaItinerario(ClsRichiestaAzioneDiContribuzioneItinerario richiesta){
        richiesta.setUsernameCuratore(this.getCredenziali().getUsername());
        HashMap<String, Object> id = new HashMap<>();
        id.put("id", richiesta.getDatiItinerarioVecchio().getId());
        pItinerari.update(id, richiesta.getDatiItinerarioNuovo());
    }
    private void validaEliminaItinerario(ClsRichiestaAzioneDiContribuzioneItinerario richiesta){
        richiesta.setUsernameCuratore(this.getCredenziali().getUsername());
        HashMap<String, Object> id = new HashMap<>();
        id.put("id", richiesta.getDatiItinerarioVecchio().getId());
        pItinerari.delete(id);
    }
    public boolean validaSegnalazione(ClsSegnalazione segnalazione, boolean esito){
        //TODO IT4
        return esito;
    }
    public ArrayList<ClsRichiestaAzioneDiContribuzione> getRichieste(){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("usernameCuratore",  "null");
        return filtraComune(pRDC.get(filtro));
    }
    public ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> getRichiesteItinerari(){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("usernameCuratore",  "null");
        return filtraComuneItinerario(pRDCI.get(filtro));
    }

    public ArrayList<ClsSegnalazione> getSegnalazioni(){
        HashMap<String, Object> filtri = new HashMap<>();
        filtri.put("idComune", comuneAssociato.getId());
        return pSegnalazioni.get(filtri);
    }
    private ArrayList<ClsRichiestaAzioneDiContribuzione> filtraComune(ArrayList<ClsRichiestaAzioneDiContribuzione> richieste){
        ArrayList<ClsRichiestaAzioneDiContribuzione> finale = new ArrayList<>();
        if(richieste!=null) {
            for (ClsRichiestaAzioneDiContribuzione r : richieste) {
                if (Objects.equals(r.getDatiNodo().getIdComune(), this.getComuneAssociato().getId()))
                    finale.add(r);
            }
        }
        return finale;
    }
    private ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> filtraComuneItinerario(ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> richieste){
        ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> finale = new ArrayList<>();
        if(richieste!=null) {
            for (ClsRichiestaAzioneDiContribuzioneItinerario r : richieste) {
                if (Objects.equals(r.getDatiItinerarioNuovo().getTappe().get(0).getIdComune(), comuneAssociato.getId()))
                    finale.add(r);
            }
        }
        return finale;
    }
}
