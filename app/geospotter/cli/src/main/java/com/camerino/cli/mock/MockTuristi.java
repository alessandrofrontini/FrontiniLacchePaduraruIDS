package com.camerino.cli.mock;

import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsGestoreDellaPiattaforma;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.core.data.utils.Credenziali;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockTuristi implements IPersistenceModel<ClsTuristaAutenticato> {
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
            //return new ClsTuristaAutenticato[]{login((Credenziali) filters.get("credenziali"))};
            tmp.add(login((Credenziali) filters.get("credenziali")));
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

    private void creaTuristi() {
        ClsContributorAutorizzato ca = new ClsContributorAutorizzato();
        Credenziali credenzialiCA = new Credenziali();
        credenzialiCA.setUsername("CA");
        credenzialiCA.setPassword("");
        ca.setCredenziali(credenzialiCA);
        ca.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue());
        ca.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
        inserisciUtente(ca);

        ClsGestoreDellaPiattaforma gdp = new ClsGestoreDellaPiattaforma();
        Credenziali credenzialiGdP = new Credenziali();
        credenzialiGdP.setUsername("GDP");
        credenzialiGdP.setPassword("");
        gdp.setCredenziali(credenzialiGdP);
        gdp.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA.getValue());
        gdp.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA);
        inserisciUtente(gdp);
    }
}
