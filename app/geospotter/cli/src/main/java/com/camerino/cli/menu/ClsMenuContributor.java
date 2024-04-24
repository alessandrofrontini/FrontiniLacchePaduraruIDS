package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsContributor;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuContributor implements IMenu{
    private ClsContributor user;
    //rec segn imm n it
    IPersistenceModel<ClsRecensione> pRecensioni;
    IPersistenceModel<ClsSegnalazione> pSegnalazioni;
    IPersistenceModel<ClsImmagine> pImmagini;
    IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRCD;
    IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRCDI;
    IPersistenceModel<ClsContestDiContribuzione> pContest;
    IPersistenceModel<ClsNodo> pNodi;
    IPersistenceModel<ClsItinerario> pItinerari;
    Scanner in = new Scanner(System.in);
    public ClsMenuContributor(ClsContributor contributor){ user = contributor;}
    @Override
    public void menu() {
//TODO: implementare
        boolean exit = false;
        user = new ClsContributor(pRecensioni, pSegnalazioni, pImmagini, pRCD, pRCDI, pNodi, pItinerari);
        user.setId("1");
        user.setPunteggio(200); //punteggio da non prendere seriamente
        user.setCredenziali(new Credenziali());
        user.getCredenziali().setUsername("Contributor");
        user.getCredenziali().setPassword("password");

        while (!exit) {
            println("1) Inserisci Nodo");
            println("2) Modifica Nodo");
            println("3) Elimina Nodo");
            println("4) Inserisci Itinerario");
            println("5) Modifica Itinerario");
            println("6) Elimina Itinerario");
            println("7) I miei contest");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()) {
                case "1" -> menuInserisciNodo();
                case "2" -> menuModificaNodo();
                case "3" -> menuEliminaNodo();
                case "4" -> menuInserisciItinerario();
                case "5" -> menuModificaItinerario();
                case "6" -> menuEliminaItinerario();
                case "7" -> sottoMenuContest();
                case "0" -> exit = true;
            }
        }
    }

    private void menuInserisciNodo(){
        user.inserisciNodo(Input.inserisciNodo());
    }

    private void menuModificaNodo(){
        if(user.visualizzaNodiPosessore()) {
            user.visualizzaNodiPosessore();
            println("inserisci l'id del nodo da modificare");
            HashMap<String, Object> tmp = new HashMap<>();
            tmp.put("id", in.nextLine());
            ClsNodo old = MockLocator.getMockNodi().get(null).get(0);
            if (old == null) {
                println("Nessun Nodo Trovato");
                return;
            }
            user.modificaNodo(old.getId(), Input.modificaNodo(old));
        }
    }

    private void menuEliminaNodo() { //da mettere su Input
        if (user.visualizzaNodiPosessore()) {
            user.visualizzaNodiPosessore();
            println("inserisci l'id del nodo da eliminare");
            if (user.eliminaNodo((in.nextLine())))
                println("Nodo eliminato correttamente.");
            else println("Errore");
        }
    }

    private void menuInserisciItinerario(){
        user.inserisciItinerario(Input.richiediItinerario());
    }

    private void menuModificaItinerario(){
        //TODO: inserire "visualizza itinerari possessore" e mettere su Input
        println("inserisci l'id dell'itinerario");
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", in.nextLine());
        ClsItinerario itinerario = MockLocator.getMockItinerari().get(tmp).get(0);
        if(itinerario != null)
            user.modificaItinerario(itinerario, itinerario.getId());
        else println("Errore");
    }

    private void menuEliminaItinerario(){
        println("inserisci l'id dell'itinerario");
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", in.nextLine());
        ClsItinerario itinerario = MockLocator.getMockItinerari().get(tmp).get(0);
        if(itinerario != null)
            user.eliminaItinerario(itinerario.getId());
        else println("Errore");
    }

    private void sottoMenuContest(){
        //TODO: inserire "Visualizza contest possessore"
        println("inserisci l'id del contest");
        boolean exit = false;
        while (!exit) {
            println("1) Inserisci Nodo al contest");
            println("2) Inserisci Foto al contest");
            println("3) Contest aperti");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()) {
                case "1" -> menuInserisciNodoContest();
                case "2" -> menuInserisciFotoContest();
                case "3" -> menuEliminaNodo();
                case "0" -> exit = true;
            }
        }
    }

    private void menuInserisciNodoContest(){
        //TODO: collegare nodo a contest
    }

    private void menuInserisciFotoContest(){
        //TODO: collegare nodo a contest
    }

    private void menuVisualizzaContestAperti(){
        for(ClsContestDiContribuzione c:pContest.get(null)){
            if(c.isAperto()){
                println("Contest - ID -> " + c.getId() + ", Luogo -> " + c.getLocation().getNome() + ", durata -> " + c.getDataFine());
            }
        }
    }
}