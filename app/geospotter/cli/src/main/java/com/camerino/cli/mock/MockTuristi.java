package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.core.data.utils.Credenziali;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockTuristi implements IPersistenceModel<ClsTuristaAutenticato> {

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

    public void leggiUtenti(){
        try{

        } catch(Exception e){
            e.printStackTrace();
        }
    }
//    private void creaTuristi() {
//        ClsContributorAutorizzato ca = new ClsContributorAutorizzato(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockImmagini(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari());
//        Credenziali credenzialiCA = new Credenziali();
//        credenzialiCA.setUsername("CA");
//        credenzialiCA.setPassword("");
//        ca.setCredenziali(credenzialiCA);
//        ca.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue());
//        ca.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
//        inserisciUtente(ca);
//
//        ClsGestoreDellaPiattaforma gdp = new ClsGestoreDellaPiattaforma(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockImmagini(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari(), MockLocator.getMockContest(), null, MockLocator.getMockTuristi());
//        Credenziali credenzialiGdP = new Credenziali();
//        credenzialiGdP.setUsername("GDP");
//        credenzialiGdP.setPassword("");
//        gdp.setCredenziali(credenzialiGdP);
//        gdp.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA.getValue());
//        gdp.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA);
//        inserisciUtente(gdp);
//
//        ClsCuratore curatore = new ClsCuratore(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockImmagini(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari(), MockLocator.getMockContest(), null, MockLocator.getMockTuristi());
//        Credenziali c = new Credenziali();
//        c.setUsername("c");
//        c.setPassword("c");
//        curatore.setCredenziali(c);
//        curatore.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE);
//        curatore.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE.getValue());
//        inserisciUtente(curatore);
//
//        ClsTuristaAutenticato ta = new ClsTuristaAutenticato(MockLocator.getMockSegnalazioni(), MockLocator.getMockRecensioni(), MockLocator.getMockRCD());
//        Credenziali c3 = new Credenziali();
//        c3.setUsername("ta");
//        c3.setPassword("");
//        ta.setCredenziali(c3);
//        ta.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);
//        ta.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO.getValue());
//        inserisciUtente(ta);
//    }
}
