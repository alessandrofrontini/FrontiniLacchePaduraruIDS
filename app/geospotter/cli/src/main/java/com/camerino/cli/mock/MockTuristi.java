package com.camerino.cli.mock;

import com.camerino.cli.menu.Input;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.persistence.IPersistenceModel;

import javax.script.ScriptEngine;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import static com.camerino.cli.CliUtils.getResourceAsFile;

public class MockTuristi implements IPersistenceModel<ClsTuristaAutenticato> {

    private ArrayList<ClsTuristaAutenticato> turisti = new ArrayList<ClsTuristaAutenticato>();
    private long idCounter = 0;

    //region CRUD metodi
    @Override
    public ArrayList<ClsTuristaAutenticato> get(Map<String, Object> filters) {
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
           if (filters.containsKey("idUtente")) {
               tmp.addAll(findByID(Long.valueOf(filters.get("idUtente").toString())));
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

    public boolean update(Map<String, Object> filters, ClsTuristaAutenticato object) {
        if(filters.containsKey("id"))
            return modificaUtente(Long.valueOf(filters.get("id").toString()), object);
        return false;
    }

    private boolean modificaUtente(Long user, ClsTuristaAutenticato newuser){
        ClsTuristaAutenticato tmp = findByID(user).get(0);
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
        utente.setId(idCounter);
        return turisti.add(utente);
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        if(filters.containsKey("username"))
            return elmiminaUtente(Long.valueOf(filters.get("username").toString()));

        return false;
    }

    private boolean elmiminaUtente(Long user){
        return turisti.remove(findByID(user).get(0));
    }
    //endregion

    private List<ClsTuristaAutenticato> findByRuolo(String ruolo) {
        List<ClsTuristaAutenticato> tmp =
                turisti.stream().filter(n->n.getRuoloUtente().equals(ClsTuristaAutenticato.eRUOLI_UTENTE.valueOf(ruolo))).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    private List<ClsTuristaAutenticato> findByID(Long idUser) {
        List<ClsTuristaAutenticato> tmp =
                turisti.stream().filter(n -> n.getId().equals(idUser)).toList();
        if (tmp.isEmpty())
            return null;
        return tmp;
    }


    public void leggiUtenti(){
        File f = getResourceAsFile("CLIsave/turisti.csv");
        if(f.exists()) {
            try {
                FileReader input = new FileReader(f);
                StringBuilder tutti = new StringBuilder();
                int c;
                while ((c = input.read()) != -1) {
                    tutti.append((char) c);
                }
                String[] utenti = Input.removeCarriageReturn(String.valueOf(tutti).split("\n"));
                for (String utente : utenti) {
                    ClsTuristaAutenticato daAggiungere = null;
                    String[] dati = utente.split(",");
                    switch (dati[1]) {
                        case "TURISTA_AUTENTICATO":
                            daAggiungere = CreaTuristaAut();
                            break;
                        case "CONTRIBUTOR":{
                            daAggiungere = CreaContributor();
                            break;
                        }
                        case "CONTRIBUTOR_AUTORIZZATO":
                            daAggiungere = CreaContributorAut();
                            break;
                        case "ANIMATORE":
                            daAggiungere = CreaAnimatore();
                            break;
                        case "CURATORE": {
                            daAggiungere = CreaCuratore();
                            break;
                        }
                        case "GESTORE_DELLA_PIATTAFORMA":
                            daAggiungere = CreaGDP();
                            break;
                    }
                    daAggiungere.setId(Long.valueOf(dati[0]));
                    Credenziali cr = new Credenziali();
                    cr.setUsername(dati[2]);
                    cr.setPassword(dati[3]);
                    daAggiungere.setCredenziali(cr);
                    daAggiungere.setPunteggio(Integer.parseInt(dati[4]));
                    if(daAggiungere.getClass()==ClsCuratore.class)
                        ((ClsCuratore) daAggiungere).setIdComuneAssociato(Long.parseLong(dati[5]));
                    inserisciUtente(daAggiungere);
                }
                maxID();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ClsTuristaAutenticato CreaTuristaAut() {
        ClsTuristaAutenticato user = new ClsTuristaAutenticato(MockLocator.getMockSegnalazioni(), MockLocator.getMockRecensioni(), MockLocator.getMockImmagini(), MockLocator.getMockTuristi());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO);
        user.setIperComuni(MockLocator.getMockComuni());
        user.setIperRDCImmagini(MockLocator.getMockRCDImmagini());
        user.setIperSegnalazioni(MockLocator.getMockSegnalazioni());
        return user;
    }

    public ClsContributor CreaContributor() {
        ClsContributor user = new ClsContributor(CreaTuristaAut());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR);
        user._setIperRDCNodi(MockLocator.getMockRCD());
        user._setIperRDCItinerari(MockLocator.getMockRCDI());
        user.setIperNodi(MockLocator.getMockNodi());
        user.setIperItinerari(MockLocator.getMockItinerari());
        return user;
    }

    public ClsContributorAutorizzato CreaContributorAut() {
        ClsContributorAutorizzato user = new ClsContributorAutorizzato(CreaContributor());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO);
        return user;
    }

    public ClsAnimatore CreaAnimatore() {
        ClsAnimatore user = new ClsAnimatore(CreaContributorAut());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE);
        return user;
    }

    private ClsCuratore CreaCuratore() {
        ClsCuratore user = new ClsCuratore(CreaAnimatore());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CURATORE);
        return user;
    }

    private ClsGDP CreaGDP() {
        ClsGDP user = new ClsGDP(CreaCuratore());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.GESTORE_DELLA_PIATTAFORMA);
        return user;
    }



    private void maxID(){
        for(ClsTuristaAutenticato i:turisti){
            if(i.getId()>this.idCounter)
                this.idCounter = i.getId();
        }
    }

    public void scriviUtenti(){
        try{
            FileWriter output = new FileWriter(getResourceAsFile("CLIsave/turisti.csv"));
            StringBuilder daScrivere = new StringBuilder();
            for(ClsTuristaAutenticato c:turisti){
                daScrivere.append(c.getId() + "," + c.getRuoloUtente() + "," + c.getCredenziali().getUsername() + "," + c.getCredenziali().getPassword() + "," + c.getPunteggio());
                if(c.getRuoloUtente().equals(ClsTuristaAutenticato.eRUOLI_UTENTE.CURATORE)){
                    ClsCuratore cur = (ClsCuratore) c;
                    if(cur.getIdComuneAssociato()!=null)
                        daScrivere.append("," + cur.getIdComuneAssociato());
                    else daScrivere.append(",0");
                }
                daScrivere.append("\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
