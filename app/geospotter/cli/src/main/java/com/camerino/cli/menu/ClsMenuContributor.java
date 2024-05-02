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
    Scanner in = new Scanner(System.in);
    public ClsMenuContributor(){}
    @Override
    public void menu() {
//TODO: implementare
        boolean exit = false;
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
        if(!user.visualizzaNodiPosessore().isEmpty()) {
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
        if (!user.visualizzaNodiPosessore().isEmpty()) {
            for(ClsNodo nodo:user.visualizzaNodiPosessore()){
                println(nodo.visualizzaNodo());
            }
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
        for(ClsItinerario itinerario:user.visualizzaItinerariPossessore())
            println(itinerario.visualizzaItinerario());
        println("inserisci l'id dell'itinerario");
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", in.nextLine());
        ClsItinerario itinerario = MockLocator.getMockItinerari().get(tmp).get(0);
        if(itinerario != null)
            sottomenuModificaItinerario(itinerario);
        else println("Errore");
    }
    private void sottomenuModificaItinerario(ClsItinerario itv){
        ClsItinerario nuovo = itv;
        boolean exit = false;
        while(!exit){
            println("1 - cambia nome");
            println("2 - aggiungi una tappa");
            println("3 - rimuovi una tappa");
            println("4 - attiva/disattiva l'ordinamento");
            println("0 - Salva ed esci dal menu");
            switch(in.nextLine()){
                case "1": print("inserisci il nuovo nome: "); nuovo.setNome(in.nextLine()); break;
                case "2": {
                        print("inserisci l'id della nuova tappa");
                        String idTappa = in.nextLine();
                        HashMap<String, Object> filtro = new HashMap<>();
                        filtro.put("id", idTappa);
                        if(controllaTappaDuplicataItinerario(itv, idTappa)){
                            nuovo.aggiungiTappa(MockLocator.getMockNodi().get(filtro).get(0));
                            break;
                        }
                        else{
                            println("Errore."); break;
                        }
                }
                case "3":{
                    for(ClsNodo nodo: nuovo.getTappe()){
                        println(nodo.visualizzaNodo());
                    }
                    print("Inserisci l'id della tappa da eliminare");
                    String idTappa = in.nextLine();
                    if(!controllaTappaDuplicataItinerario(nuovo, idTappa)){
                        HashMap<String, Object> filtro = new HashMap<>();
                        filtro.put("id", idTappa);
                        nuovo.rimuoviTappa(MockLocator.getMockNodi().get(filtro).get(0));
                        break;
                    }
                    else{
                        println("Errore.");
                        break;
                    }
                }
                case "4":{
                    print("Attualmente l'itinerario è: ");
                    if(nuovo.isOrdinato())
                        print("Ordinato.");
                    else print("Non ordinato.");
                    println("Vuoi modificare l'ordinamento? Y/N");
                    String esito = in.nextLine();
                    if(esito == "Y"){
                        nuovo.setOrdinato(!nuovo.isOrdinato());
                        println("L'ordinamento è stato cambiato");
                    }
                    else println("non sono state apportate modifiche.");
                    break;
                }
                case "0": {
                    user.modificaItinerario(nuovo, itv);
                    exit = true;
                    break;
                }
            }
        }
    }
    private boolean controllaTappaDuplicataItinerario(ClsItinerario itinerario, String idTappa){
        for(ClsNodo nodo:itinerario.getTappe()){
            if(nodo.getId() == idTappa)
                return false;
        }
        return true;
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

}