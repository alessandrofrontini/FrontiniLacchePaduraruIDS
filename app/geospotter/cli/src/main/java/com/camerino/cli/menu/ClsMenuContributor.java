package com.camerino.cli.menu;
import com.camerino.cli.actions.ClsCommonActions;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.cli.mock.MockNodi;

import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.actions.ClsCommonActions.*;
        import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuContributor implements IMenu{
    private ClsContributor user;
    Scanner in = new Scanner(System.in);
    public ClsMenuContributor(ClsContributor contributor){ user = contributor;}
    @Override
    public void menu() {
//TODO: implementare
        boolean exit = false;
        user = new ClsContributor();
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
        //TODO: implementare (front)
    }

    private void menuInserisciFotoContest(){
        //TODO: implementare (front)
    }

    private void menuVisualizzaContestAperti(){
        //TODO: implementare (front)
    }
}