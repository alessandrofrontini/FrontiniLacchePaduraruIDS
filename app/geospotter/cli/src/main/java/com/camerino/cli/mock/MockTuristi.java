package com.camerino.cli.mock;

import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
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

    private void creaTuristi() {
        ClsContributorAutorizzato ca = new ClsContributorAutorizzato();
        Credenziali credenzialiCA = new Credenziali();
        credenzialiCA.setUsername("con aut");
        credenzialiCA.setPassword("");
        ca.setCredenziali(credenzialiCA);
        ca.setPunteggio(ClsTuristaAutenticato.RUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue());
        ca.setRuoloUtente(ClsTuristaAutenticato.RUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
        inserisciUtente(ca);
    }

    @Override
    public ClsTuristaAutenticato[] get(HashMap<String, Object> filters) {
        if (filters.containsKey("credenziali"))
            return new ClsTuristaAutenticato[]{login((Credenziali) filters.get("credenziali"))};
        return new ClsTuristaAutenticato[0];
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
}
