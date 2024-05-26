package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.*;
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
    IPersistenceModel<ClsTuristaAutenticato> pUtenti;
    ClsComune comuneAssociato;
    public ClsComune getComuneAssociato() {
        return comuneAssociato;
    }
    public void setComuneAssociato(ClsComune comuneAssociato) {
        this.comuneAssociato = comuneAssociato;
    }
    public ClsCuratore(IPersistenceModel<ClsRecensione> r, IPersistenceModel<ClsSegnalazione> s, IPersistenceModel<ClsImmagine> i, IPersistenceModel<ClsRDCImmagine> pRCDImmagini, IPersistenceModel<ClsRDCNodo> pRCDNodo, IPersistenceModel<ClsRDCItinerario> pRCDItinerari, IPersistenceModel<ClsNodo> nodi, IPersistenceModel<ClsItinerario> itinerari, IPersistenceModel<ClsContestDiContribuzione> contest, ClsComune c, IPersistenceModel<ClsTuristaAutenticato>utenti){
        super(r, s, i, pRCDImmagini,pRCDNodo, pRCDItinerari, nodi, itinerari, contest);
        comuneAssociato = c;
        pUtenti = utenti;
    }

    public boolean registraUtente(ClsTuristaAutenticato utente){
        return pUtenti.insert(utente);
    }
    public boolean modificaInformazioniUtente(HashMap<String, Object> filtri, ClsTuristaAutenticato utente){
        return pUtenti.update(filtri, utente);
    }

    public void eliminaUtente(String idUtente){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", idUtente);
        pUtenti.delete(filtro);
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
    public void validaRichiesta(AbsDefaultAction richiesta, boolean esito){
        if(esito){
            switch (richiesta.getTipo()){
                case INSERISCI_NODO -> validaNodo(richiesta);
                case MODIFICA_NODO -> validaModificaNodo(richiesta);
                case ELIMINA_NODO -> validaEliminaNodo(richiesta);
                case INSERISCI_IMMAGINE -> validaImmagine(richiesta);
            }
        }
    }
    private void validaNodo(AbsDefaultAction richiesta){
        richiesta.setResponsabile(this);
        richiesta.setStato(EStatusRDC.ACCETTATO);
        ClsNodo n = (ClsNodo) richiesta.getNewData();
        n.setUsernameCreatore(richiesta.getCreatore().getCredenziali().getUsername());
        pNodi.insert(n);
    }
    private void validaImmagine(AbsDefaultAction richiesta){
        richiesta.setResponsabile(this);
        richiesta.setStato(EStatusRDC.ACCETTATO);
        ClsImmagine i = (ClsImmagine)richiesta.getNewData();
        i.setUsernameCreatore(richiesta.getCreatore().getCredenziali().getUsername());
        pImmagini.insert(i);
    }
    private void validaModificaNodo(AbsDefaultAction richiesta){
        richiesta.setResponsabile(this);
        richiesta.setStato(EStatusRDC.ACCETTATO);
        ClsNodo nodoold = (ClsNodo) richiesta.getOldData();
        ClsNodo nodonew = (ClsNodo) richiesta.getNewData();
        nodonew.setUsernameCreatore(richiesta.getCreatore().getCredenziali().getUsername());
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", nodoold.getId());
        pNodi.update(filtro, nodonew);
    }
    private void validaEliminaNodo(AbsDefaultAction richiesta){
        richiesta.setResponsabile(this);
        richiesta.setStato(EStatusRDC.ACCETTATO);
        HashMap<String, Object> id = new HashMap<>();
        richiesta.setResponsabile(this);
        ClsNodo n = (ClsNodo) richiesta.getNewData();
        id.put("id", n.getId());
        pNodi.delete(id);
    }
    public boolean validaRichiestaItinerario(ClsRDCItinerario richiesta, boolean esito){
        if(esito){
            switch (richiesta.getTipo()){
                case INSERISCI_ITINERARIO -> validaItinerario(richiesta);
                case MODIFICA_ITINERARIO -> validaModificaItinerario(richiesta);
                case ELIMINA_ITINERARIO -> validaEliminaItinerario(richiesta);
            }
        }
        return esito;
    }
    private void validaItinerario(ClsRDCItinerario richiesta){
        richiesta.setResponsabile(this);
        richiesta.setStato(EStatusRDC.ACCETTATO);
        ClsItinerario i = richiesta.getNewData();
        i.setUsernameCreatore(richiesta.getCreatore().getCredenziali().getUsername());
        pItinerari.insert(i);
    }
    private void validaModificaItinerario(ClsRDCItinerario richiesta){
        richiesta.setResponsabile(this);
        richiesta.setStato(EStatusRDC.ACCETTATO);
        ClsItinerario itold = richiesta.getOldData();
        ClsItinerario itnew = richiesta.getNewData();
        HashMap<String, Object> id = new HashMap<>();
        id.put("id", itold.getId());
        itnew.setUsernameCreatore(richiesta.getCreatore().getCredenziali().getUsername());
        pItinerari.update(id, itnew);
    }
    private void validaEliminaItinerario(ClsRDCItinerario richiesta){
        richiesta.setResponsabile(this);
        richiesta.setStato(EStatusRDC.ACCETTATO);
        ClsItinerario itnew = richiesta.getNewData();
        HashMap<String, Object> id = new HashMap<>();
        id.put("id", itnew.getId());
        pItinerari.delete(id);
    }
    public boolean validaSegnalazione(ClsSegnalazione segnalazione, boolean esito){
        return esito;
    }
    public ArrayList<ClsRDCNodo> getRichieste(){
        ArrayList<ClsRDCNodo> richieste = new ArrayList<>();
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("usernameCuratore",  "null");
        richieste.addAll(filtraComuneNodi(pRDC.get(filtro)));
        return richieste;
    }
    public ArrayList<ClsRDCImmagine> getRichiesteImmagini(){
        ArrayList<ClsRDCImmagine> richieste = new ArrayList<>();
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("usernameCuratore",  "null");
        richieste.addAll(filtraComuneImmagini(pRichiestaImmagini.get(filtro)));
        return richieste;
    }
    public ArrayList<ClsRDCItinerario> getRichiesteItinerari(){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("usernameCuratore",  "null");
        return filtraComuneItinerario(pRDCI.get(filtro));
    }

    public ArrayList<ClsSegnalazione> getSegnalazioni(){
        HashMap<String, Object> filtri = new HashMap<>();
        filtri.put("idComune", comuneAssociato.getId());
        return pSegnalazioni.get(filtri);
    }
    private ArrayList<ClsRDCNodo> filtraComuneNodi(ArrayList<ClsRDCNodo> richieste){
        ArrayList<ClsRDCNodo> finale = new ArrayList<>();
        if(richieste!=null) {
            for (ClsRDCNodo r : richieste) {
                ClsNodo n = r.getNewData();
                if (Objects.equals(n.getIdComune(), this.getComuneAssociato().getId()))
                    finale.add(r);
            }
        }
        return finale;
    }
    private ArrayList<ClsRDCImmagine> filtraComuneImmagini(ArrayList<ClsRDCImmagine> richieste){
        ArrayList<ClsRDCImmagine> finale = new ArrayList<>();
        if(richieste!=null) {
            for (ClsRDCImmagine r : richieste) {
                ClsImmagine n = r.getNewData();
                HashMap<String, Object> filtro = new HashMap<>();
                filtro.put("id", n.getIdCOntenutoAssociato());
                if (Objects.equals(pNodi.get(filtro).get(0).getIdComune(), this.getComuneAssociato().getId()))
                    finale.add(r);
            }
        }
        return finale;
    }

    private ArrayList<ClsRDCItinerario> filtraComuneItinerario(ArrayList<ClsRDCItinerario> richieste){
        ArrayList<ClsRDCItinerario> finale = new ArrayList<>();
        if(richieste!=null) {
            for (ClsRDCItinerario r : richieste) {
                ClsItinerario it = r.getNewData();
                if (Objects.equals(it.getTappe().get(0).getIdComune(), comuneAssociato.getId()))
                    finale.add(r);
            }
        }
        return finale;
    }
}
