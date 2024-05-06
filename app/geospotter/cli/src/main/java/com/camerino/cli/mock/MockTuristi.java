package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsGestoreDellaPiattaforma;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.core.data.utils.Credenziali;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockTuristi implements IPersistenceModel<ClsTuristaAutenticato> {
    IPersistenceModel<ClsRecensione> pRecensioni;
    IPersistenceModel<ClsSegnalazione> pSegnalazioni;
    IPersistenceModel<ClsImmagine> pImmagini;
    IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRCDNodi;
    IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRDCItinerari;
    IPersistenceModel<ClsContestDiContribuzione> pContest;
    IPersistenceModel<ClsTuristaAutenticato> pUtenti;
    IPersistenceModel<ClsNodo> pNodi;
    IPersistenceModel<ClsItinerario> pItinerari;
    public MockTuristi(){
        creaTuristi();
    }

    private ArrayList<ClsTuristaAutenticato> turisti = new ArrayList<ClsTuristaAutenticato>();
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsTuristaAutenticato> get(HashMap<String, Object> filters) {
       ArrayList<ClsTuristaAutenticato> tmp = new ArrayList<ClsTuristaAutenticato>();
        if (filters.containsKey("credenziali"))
        {
            tmp.add(login((Credenziali) filters.get("credenziali")));
            return tmp;
        }
        if(filters.containsKey("ruoloUtente")) {
            tmp.add(findByRuolo((ClsTuristaAutenticato.eRUOLO_UTENTE) filters.get("ruoloUtente")));
            return tmp;
        }

        return this.turisti;
    }

    private ClsTuristaAutenticato login(Credenziali  credenziali){
        List<ClsTuristaAutenticato> tmp = turisti.stream().filter(t->t.getCredenziali().equals(credenziali)).toList();
        if(tmp.isEmpty())
            return null;
        return  tmp.get(0);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsTuristaAutenticato object) {
        return false;//TODO
    }

    @Override
    public boolean insert(ClsTuristaAutenticato object) {
        return inserisciUtente(object);
    }

    private boolean inserisciUtente(ClsTuristaAutenticato utente){
        idCounter++;
        utente.setId(""+idCounter);
        return turisti.add(utente);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;//TODO
    }
    //endregion

    private ClsTuristaAutenticato findByRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE ruolo) {
        List<ClsTuristaAutenticato> tmp =
                turisti.stream().filter(n->n.getRuoloUtente().equals(ruolo)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private void creaTuristi() {
        ClsContributorAutorizzato ca = new ClsContributorAutorizzato(pRecensioni, pSegnalazioni, pImmagini, pRCDNodi, pRDCItinerari, pNodi, pItinerari);
        Credenziali credenzialiCA = new Credenziali();
        credenzialiCA.setUsername("CA");
        credenzialiCA.setPassword("");
        ca.setCredenziali(credenzialiCA);
        ca.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue());
        ca.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
        inserisciUtente(ca);

        ClsGestoreDellaPiattaforma gdp = new ClsGestoreDellaPiattaforma(pRecensioni, pSegnalazioni, pImmagini, pRCDNodi, pRDCItinerari, pNodi, pItinerari, pContest, null, null);
        Credenziali credenzialiGdP = new Credenziali();
        credenzialiGdP.setUsername("GDP");
        credenzialiGdP.setPassword("");
        gdp.setCredenziali(credenzialiGdP);
        gdp.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA.getValue());
        gdp.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA);
        inserisciUtente(gdp);

        ClsCuratore curatore = new ClsCuratore(pRecensioni, pSegnalazioni, pImmagini, pRCDNodi, pRDCItinerari, pNodi, pItinerari, pContest, null, pUtenti);
        Credenziali c = new Credenziali();
        c.setUsername("c");
        c.setPassword("c");
        curatore.setCredenziali(c);
        curatore.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE);
        curatore.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE.getValue());
        inserisciUtente(curatore);
    }
}
