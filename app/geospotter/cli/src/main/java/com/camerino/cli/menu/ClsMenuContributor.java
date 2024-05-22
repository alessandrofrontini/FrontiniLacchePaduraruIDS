package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsContributor;

import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
import static com.camerino.cli.menu.Input.checkItinerarioDuplicato;
import static com.camerino.cli.menu.Input.checkValore;

public class ClsMenuContributor implements IMenu{
    private ClsContributor user;
    Scanner in = new Scanner(System.in);
    public ClsMenuContributor(ClsContributor c){user = c;}
    private ClsMenuTuristaAutenticato menuta;
    public ClsMenuTuristaAutenticato getMenuta(){return menuta;}
    @Override
    public void menu(){
        menuta = new ClsMenuTuristaAutenticato(user);
        boolean exit = false;
        while (!exit) {
            menuta.menu();
            println("5) Inserisci Nodo");
            println("6) Modifica Nodo");
            println("7) Elimina Nodo");
            println("8) Inserisci Itinerario");
            println("9) Modifica Itinerario");
            println("10) Elimina Itinerario");
            println("11) I miei contest");
            if ((user.getClass().equals(ClsContributor.class))||(user.getClass().equals(ClsContributorAutorizzato.class))) {
                println("0) Esci");
                print(">> ");
                switch (in.nextLine()) {
                    case "1" -> menuta.menuInserisciRecensione(); //funziona
                    case "2" -> menuta.menuModificaRecensione(); //funziona
                    case "3" -> menuta.menuEliminaRecensione(); //funziona
                    case "4" -> menuta.menuInserisciFoto(); //funziona
                    case "5" -> menuInserisciNodo(); //funziona
                    case "6" -> menuModificaNodo(); //funziona
                    case "7" -> menuEliminaNodo(); //funziona
                    case "8" -> menuInserisciItinerario(); //funziona
                    case "9" -> menuModificaItinerario(); //funziona
                    case "10" -> menuEliminaItinerario(); //funziona
                    case "11" -> sottoMenuContest(); //noimpl
                    case "0" -> exit = true;
                }
            }
            else exit = true;
        }
    }

    public void menuInserisciNodo(){
        user.inserisciNodo(Input.inserisciNodo());
    }

    public void menuModificaNodo(){
        if(!user.visualizzaNodiPosessore().isEmpty()) {
            for(ClsNodo n: user.visualizzaNodiPosessore())
                println(n.visualizzaNodo());
            println("inserisci l'id del nodo da modificare");
            HashMap<String, Object> tmp = new HashMap<>();
            tmp.put("id", in.nextLine());
            ClsNodo old = MockLocator.getMockNodi().get(tmp).get(0);
            if (old == null) {
                println("Nessun Nodo Trovato");
                return;
            }
            user.modificaNodo(old.getId(), Input.modificaNodo(old));
        }
    }

    public void menuEliminaNodo() {
        if (!user.visualizzaNodiPosessore().isEmpty()) {
            for(ClsNodo n: user.visualizzaNodiPosessore())
                println(n.visualizzaNodo());
            boolean exit = false;
            while(!exit) {
                println("inserisci l'id del nodo da eliminare");
                String input = in.nextLine();
                if (checkValore(input, (ArrayList<String>) user.visualizzaNodiPosessore().stream().map(ClsNodo::getId).collect(Collectors.toList()))) {
                    if (user.eliminaNodo((input))) {
                        println("Richiesta di eliminazione inviata.");
                        exit = true;
                    }
                } else{
                    println("Nodo non presente. Riprova");
                    in.nextLine();
                }
            }
        }
    }

    public void menuInserisciItinerario(){
        user.inserisciItinerario(Input.richiediItinerario());
    }

    public void menuModificaItinerario(){
        for(ClsItinerario itinerario:user.visualizzaItinerariPossessore())
            println(itinerario.visualizzaItinerario());
        boolean exit = false;
        while(!exit) {
            println("inserisci l'id dell'itinerario");
            String input = in.nextLine();
            if (checkValore(input, (ArrayList<String>) user.visualizzaItinerariPossessore().stream().map(ClsItinerario::getId).collect(Collectors.toList()))) {
                HashMap<String, Object> tmp = new HashMap<>();
                tmp.put("id", input);
                ClsItinerario itinerario = MockLocator.getMockItinerari().get(tmp).get(0);
                if (itinerario != null) {
                    ClsItinerario nuovo = sottomenuModificaItinerario(itinerario);
                    user.modificaItinerario(nuovo, itinerario);
                    println("Richiesta di modifica effettuata correttamente.");
                    in.nextLine();
                    exit = true;
                } else{
                    println("Errore nella richiesta. Riprova.");
                    in.nextLine();
                }
            } else {
                println("Itinerario non presente. Riprova");
                in.nextLine();
            }
        }
    }
    private ClsItinerario sottomenuModificaItinerario(ClsItinerario itv){ //sentire chat gpt per doppia modifica in "modifica"
        boolean fine = false;
            ClsItinerario nuovo = new ClsItinerario();
            while(!fine) {
            nuovo.setId(itv.getId());
            nuovo.setNome(itv.getNome());
            nuovo.setUsernameCreatore(itv.getUsernameCreatore());
            nuovo.setOrdinato(itv.isOrdinato());
            ArrayList<ClsNodo> tappe = new ArrayList<>();
            tappe.addAll(itv.getTappe());
            String input;
            nuovo.setTappe(tappe);
            boolean exit = false;
            while (!exit) {
                println("1 - cambia nome");
                println("2 - aggiungi una tappa");
                println("3 - rimuovi una tappa");
                println("4 - attiva/disattiva l'ordinamento");
                println("0 - Salva ed esci dal menu");
                switch (in.nextLine()) {
                    case "1":
                        print("inserisci il nuovo nome: ");
                        nuovo.setNome(in.nextLine());
                        break;
                    case "2": {
                        while (!exit) {
                            print("inserisci l'id della nuova tappa");
                            input = in.nextLine();
                            if (checkValore(input, (ArrayList<String>) MockLocator.getMockNodi().get(null).stream().map(ClsNodo::getId).collect(Collectors.toList()))) {
                                HashMap<String, Object> filtro = new HashMap<>();
                                filtro.put("id", input);
                                if (controllaTappaDuplicataItinerario(nuovo, input)) {
                                    nuovo.aggiungiTappa(MockLocator.getMockNodi().get(filtro).get(0));
                                    exit = true;
                                    break;
                                } else {
                                    println("Errore.");
                                    break;
                                }
                            } else {
                                println("Nodo non esistente. Riprova");
                                in.nextLine();
                            }
                        }
                        break;
                    }
                    case "3": {
                        for (ClsNodo nodo : nuovo.getTappe()) {
                            println(nodo.visualizzaNodo());
                        }
                        while (!exit) {
                            print("Inserisci l'id della tappa da eliminare");
                            String idTappa = in.nextLine();
                            if (!controllaTappaDuplicataItinerario(nuovo, idTappa)) {
                                HashMap<String, Object> filtro = new HashMap<>();
                                filtro.put("id", idTappa);
                                nuovo.rimuoviTappa(MockLocator.getMockNodi().get(filtro).get(0));
                                exit = true;
                            } else {
                                println("Errore.");
                            }
                        }
                        break;
                    }
                    case "4": {
                        print("Attualmente l'itinerario è: ");
                        if (nuovo.isOrdinato())
                            print("Ordinato.");
                        else print("Non ordinato.");
                        println("Vuoi modificare l'ordinamento? Y/N");
                        String esito = in.nextLine();
                        if (Objects.equals(esito, "Y") || (Objects.equals(esito, "y"))) {
                            nuovo.setOrdinato(!nuovo.isOrdinato());
                            println("L'ordinamento è stato cambiato");
                        } else println("non sono state apportate modifiche.");
                        break;
                    }
                    case "0": {
                        exit = true; break;
                    }
                }
            }
            if (checkItinerarioDuplicato(nuovo)) {
                fine = true;
            }
            else{
                println("Itinerario già esistente. Riprova");
                in.nextLine();
            }
        }
        return nuovo;
    }
    private boolean controllaTappaDuplicataItinerario(ClsItinerario itinerario, String idTappa){
        for(ClsNodo nodo:itinerario.getTappe()){
            if(Objects.equals(nodo.getId(), idTappa))
                return false;
        }
        return true;
    }
    public void menuEliminaItinerario(){
        boolean exit = false;
        while(!exit) {
            println("inserisci l'id dell'itinerario");
            String input = in.nextLine();
            if (checkValore(input, (ArrayList<String>) MockLocator.getMockItinerari().get(null).stream().map(ClsItinerario::getId).collect(Collectors.toList()))) {
                HashMap<String, Object> tmp = new HashMap<>();
                tmp.put("id", in.nextLine());
                ClsItinerario itinerario = MockLocator.getMockItinerari().get(tmp).get(0);
                if (itinerario != null) {
                    user.eliminaItinerario(itinerario.getId());
                    println("Richiesta di eliminazione effettuata.");
                    exit = true;
                } else println("Errore. Riprova");
                in.nextLine();
            }
        }
    }

    public void sottoMenuContest(){
        println("inserisci l'id del contest");
        boolean exit = false;
        while (!exit) {
            println("1) Inserisci Nodo al contest");
            println("2) Inserisci Foto al contest");
            println("3) Contest aperti");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()) {
                case "1" :
                case "2" :
                case "3" : contest(); break;
                case "0" : exit = true;
            }
        }
    }

    private void contest(){
        println("Questa è una funzionalità di Geospotter Desktop.");
    }
}