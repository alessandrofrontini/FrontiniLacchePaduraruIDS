package com.camerino.ids.fps.geospotter.server.persistence.mock;

import com.camerino.ids.fps.geospotter.server.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.fps.geospotter.server.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.fps.geospotter.server.data.utils.Credenziali;

import java.util.ArrayList;

public class MockTuristi {
    private static MockTuristi instance = null;
    public static MockTuristi getInstance(){
        if(instance==null)
            instance = new MockTuristi();
        return instance;
    }
    private MockTuristi(){
        creaTuristi();
    }

    private ArrayList<ClsTuristaAutenticato> turisti = new ArrayList<ClsTuristaAutenticato>();
    private long idCounter = 0;

    public boolean inserisciUtente(ClsTuristaAutenticato utente){
        idCounter++;
        utente.setId(""+idCounter);
        return turisti.add(utente);
    }

    public ClsTuristaAutenticato login(Credenziali  credenziali){
        var tmp = turisti.stream().filter(t->t.getCredenziali().equals(credenziali)).toList();
        if(tmp.isEmpty())
            return null;
        return  tmp.get(0);
    }

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

}
