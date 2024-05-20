package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.core.data.utils.Credenziali;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MockTuristi implements IPersistenceModel<ClsTuristaAutenticato> {

    private ArrayList<ClsTuristaAutenticato> turisti = new ArrayList<ClsTuristaAutenticato>();
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsTuristaAutenticato> get(HashMap<String, Object> filters) {
       ArrayList<ClsTuristaAutenticato> tmp = new ArrayList<ClsTuristaAutenticato>();
       if(filters!=null) {
           if (filters.containsKey("credenziali")) {
               tmp.add(login((Credenziali) filters.get("credenziali")));
               return tmp;
           }
           if (filters.containsKey("ruoloUtente")) {
               tmp.addAll(findByRuolo(filters.get("ruoloUtente").toString()));
               return tmp;
           }
           if (filters.containsKey("username")) {
               tmp.addAll(findByUsername(filters.get("username").toString()));
               return tmp;
           }
       }
        return this.turisti;
    }

    private ClsTuristaAutenticato login(Credenziali  credenziali){
        List<ClsTuristaAutenticato> tmp = turisti.stream().filter(t->t.getCredenziali().equals(credenziali)).toList();
        if(tmp.isEmpty())
            return null;
        return  tmp.get(0);
    }

    public boolean update(HashMap<String, Object> filters, ClsTuristaAutenticato object) {
        if(filters.containsKey("username"))
            return modificaUtente(filters.get("username").toString(), object);
        return false;
    }

    private boolean modificaUtente(String user, ClsTuristaAutenticato newuser){
        ClsTuristaAutenticato tmp = findByUsername(user).get(0);
        int index = turisti.indexOf(tmp);
        if(index<0)
            return false;
        turisti.set(index, newuser);
        return true;
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
        if(filters.containsKey("username"))
            return elmiminaUtente(filters.get("username").toString());

        return false;
    }

    private boolean elmiminaUtente(String user){
        return turisti.remove(findByUsername(user).get(0));
    }
    //endregion

    private List<ClsTuristaAutenticato> findByRuolo(String ruolo) {
        List<ClsTuristaAutenticato> tmp =
                turisti.stream().filter(n->n.getRuoloUtente().equals(ClsTuristaAutenticato.eRUOLO_UTENTE.valueOf(ruolo))).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    private List<ClsTuristaAutenticato> findByUsername(String user) {
        List<ClsTuristaAutenticato> tmp =
                turisti.stream().filter(n -> n.getCredenziali().getUsername().equals(user)).toList();
        if (tmp.isEmpty())
            return null;
        return tmp;
    }


    public void leggiUtenti(){
        try{
            FileReader input = new FileReader("CLIsave/turisti.txt");
            StringBuilder tutti = new StringBuilder();
            int c;
            while((c= input.read())!=-1) {
                tutti.append((char) c);
            }
            String [] utenti = String.valueOf(tutti).split("\r\n");
            for(String utente:utenti){
                ClsTuristaAutenticato daAggiungere = null;
                String [] dati = utente.split(",");
                switch (dati[1]){
                    case "TURISTA_AUTENTICATO": daAggiungere = new ClsTuristaAutenticato(MockLocator.getMockSegnalazioni(), MockLocator.getMockRecensioni(), MockLocator.getMockRCD(), MockLocator.getMockNodi()); daAggiungere.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO); break;
                    case "CONTRIBUTOR": daAggiungere = new ClsContributor(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari()); daAggiungere.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR); break;
                    case "CONTRIBUTOR_AUTORIZZATO": daAggiungere = new ClsContributorAutorizzato(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockImmagini(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari()); daAggiungere.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO); break;
                    case "ANIMATORE": daAggiungere = new ClsAnimatore(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockImmagini(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari(), MockLocator.getMockContest()); daAggiungere.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE); break;
                    case "CURATORE": {
                        if(!Objects.equals(dati[5], "null")) {
                            HashMap<String, Object> filtro = new HashMap<>();
                            filtro.put("id", dati[5]);
                            daAggiungere = new ClsCuratore(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockImmagini(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari(), MockLocator.getMockContest(), MockLocator.getMockComuni().get(filtro).get(0), MockLocator.getMockTuristi());
                        }
                        else
                            daAggiungere = new ClsCuratore(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockImmagini(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari(), MockLocator.getMockContest(), null, MockLocator.getMockTuristi());
                        daAggiungere.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE); break;
                    }
                    case "GESTORE_DELLA_PIATTAFORMA": daAggiungere = new ClsGestoreDellaPiattaforma(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockImmagini(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari(), MockLocator.getMockContest(), null, MockLocator.getMockTuristi()); daAggiungere.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA); break;
                }
                daAggiungere.setId(dati[0]);
                Credenziali cr = new Credenziali();
                cr.setUsername(dati[2]);
                cr.setPassword(dati[3]);
                daAggiungere.setCredenziali(cr);
                daAggiungere.setPunteggio(Integer.parseInt(dati[4]));
                inserisciUtente(daAggiungere);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void scriviUtenti(){
        try{
            FileWriter output = new FileWriter("CLIsave/turisti.txt");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsTuristaAutenticato c:turisti){
                daScrivere.append(c.getId() + "," + c.getRuoloUtente() + "," + c.getCredenziali().getUsername() + "," + c.getCredenziali().getPassword() + "," + c.getPunteggio());
                if(c.getRuoloUtente().equals(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE)){
                    ClsCuratore cur = (ClsCuratore) c;
                    if(cur.getComuneAssociato()!=null)
                        daScrivere.append("," + cur.getComuneAssociato().getId());
                    else daScrivere.append(",null");
                }
                daScrivere.append("\r\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
