package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
import static com.camerino.cli.menu.Input.checkValore;

public class ClsMenuCuratore implements IMenu{
    private ClsCuratore user;
    Scanner in = new Scanner(System.in);
    public ClsMenuCuratore(ClsCuratore c){user = c;}
    private ClsMenuAnimatore menuAnimatore;
    public ClsMenuAnimatore getMenuAnimatore(){return menuAnimatore;}
    @Override
    public void menu() {
        boolean exit = false;
        menuAnimatore = new ClsMenuAnimatore(user);
        while (!exit) {
            menuAnimatore.menu();
            println("15) Visualizza richieste");
            println("16) Visualizza segnalazioni");
            println("17) Uprank utente");
            println("18) Downrank utente");
            println("19) Reset Rank utente");
            println("20) Registra nuovo utente");
            println("21) Modifica utente");
            println("22) Elimina utente");
            println("23) Pubblica contenuto sui social");
            if (user.getClass().equals(ClsCuratore.class)) {
                println("0) Esci");
                print(">> ");
                switch (in.nextLine()) {
                    case "1":
                        menuAnimatore.getMenuca().getMenuc().getMenuta().menuInserisciRecensione(); break;
                    case "2":
                        menuAnimatore.getMenuca().getMenuc().getMenuta().menuModificaRecensione(); break;
                    case "3":
                        menuAnimatore.getMenuca().getMenuc().getMenuta().menuEliminaRecensione(); break;
                    case "4":
                        menuAnimatore.getMenuca().getMenuc().getMenuta().menuInserisciFoto(); break;
                    case "5":
                        menuAnimatore.getMenuca().getMenuc().menuInserisciNodo(); break;
                    case "6":
                        menuAnimatore.getMenuca().getMenuc().menuModificaNodo(); break;
                    case "7":
                        menuAnimatore.getMenuca().getMenuc().menuEliminaNodo(); break;
                    case "8":
                        menuAnimatore.getMenuca().getMenuc().menuInserisciItinerario(); break;
                    case "9":
                        menuAnimatore.getMenuca().getMenuc().menuModificaItinerario(); break;
                    case "10":
                        menuAnimatore.getMenuca().getMenuc().menuEliminaItinerario(); break;
                    case "11":
                        menuAnimatore.getMenuca().getMenuc().sottoMenuContest(); break;
                    case "12":
                    case "13":
                    case "14":
                        menuAnimatore.menuContest(); break;
                    case "15":
                        menuVisualizzaRichieste(); break;
                    case "16":
                        menuVisualizzaSegnalazioni(); break;
                    case "17":
                    case "18":
                    case "23":
                    case "19":
                        menuRank(); break;
                    case "20":
                        menuRegistraUtente(); break;
                    case "21":
                        menuModificaUtente(); break;
                    case "22":
                        menuEliminaUtente(); break;
                    case "0":
                        exit = true; break;
                }
            }
            else exit = true;
        }

    }
    public void menuVisualizzaRichieste(){
        println("1) richieste dei nodi\n2) richieste itinerari\n0) esci");
        if(Objects.equals(in.nextLine(), "1")){
            ArrayList<ClsRichiestaAzioneDiContribuzione> richieste = user.getRichieste();
            if(!richieste.isEmpty()) {
                for (ClsRichiestaAzioneDiContribuzione r : richieste) {
                    println("richiesta n. " + r.getId() + ", tipologia: nodo");
                }
                sottomenuRichieste(false);
            }
        }
        else if(Objects.equals(in.nextLine(), "2")){
            ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> richieste = user.getRichiesteItinerari();
            if(!richieste.isEmpty()) {
                for (ClsRichiestaAzioneDiContribuzioneItinerario r : richieste) {
                    println("richiesta n. " + r.getId() + ", tipologia: itinerario");
                }
                sottomenuRichieste(true);
            }
        }
    }
    private void sottomenuRichieste(boolean itinerario) {
        boolean exit = false;
        while(!exit) {
            println("seleziona l'ID della richiesta da validare");
            String idr = in.nextLine();
            if (idr != null) {
                HashMap<String, Object> filtro = new HashMap<>();
                filtro.put("id", idr);
                println("Richiesta " + idr);
                String esito;
                if (itinerario) {
                    if (checkValore(idr, (ArrayList<String>) MockLocator.getMockRCDI().get(null).stream().map(ClsRichiestaAzioneDiContribuzioneItinerario::getId).collect(Collectors.toList()))) {
                        ClsRichiestaAzioneDiContribuzioneItinerario rit = MockLocator.getMockRCDI().get(filtro).get(0);
                        println("Tipo richiesta -> " + rit.geteAzioniDiContribuzione());
                        println("Tappe itinerario:");
                        for (ClsNodo n : rit.getDatiItinerarioNuovo().getTappe()) {
                            println(n.toString());
                        }
                        println("Validare? Y/N");
                        esito = in.nextLine();
                        user.validaRichiestaItinerario(rit, (Objects.equals(esito, "Y")) || (Objects.equals(esito, "y")));
                        exit = true;
                    } else {
                        println("Richiesta non esistente. Riprova.");
                        in.nextLine();
                    }
                } else {
                    if (checkValore(idr, (ArrayList<String>) MockLocator.getMockRCD().get(null).stream().map(ClsRichiestaAzioneDiContribuzione::getId).collect(Collectors.toList()))) {
                        ClsRichiestaAzioneDiContribuzione r = MockLocator.getMockRCD().get(filtro).get(0);
                        println("Tipo richiesta -> " + r.geteAzioneDiContribuzione());
                        println("Validare? Y/N");
                        esito = in.nextLine();
                        user.validaRichiesta(r, (Objects.equals(esito, "Y")) || (Objects.equals(esito, "y")));
                        exit = true;
                    }
                    else println("Richiesta non esistente. Riprova.");
                    in.nextLine();
                }

            }
        }
    }

    public void menuVisualizzaSegnalazioni(){
        for(ClsSegnalazione seg:MockLocator.getMockSegnalazioni().get(null)){
            if(seg.getIdCuratore() == null){
                println("Segnalazione n. " + seg.getId());
            }
        }
    }
    public void menuRank(){
        println("Questa è una funzionalità di Geospotter Desktop.");
    }
    public void menuRegistraUtente(){
        ClsTuristaAutenticato t = Input.menuRegistraUtente();
        while(t==null){
            t = Input.menuRegistraUtente();
        }
        user.registraUtente(t);
    }
    public void menuModificaUtente(){
        stampaUtenti();
        boolean exit = false;
        while(!exit) {
            String idu = in.nextLine();
            if (checkValore(idu, (ArrayList<String>) MockLocator.getMockTuristi().get(null).stream()
                    .map(turista -> turista.getCredenziali().getUsername())
                    .collect(Collectors.toList()))) {
                Input.modificaUtente(idu);
                exit = true;
            } else {
                println("Utente non presente. Riprova");
            }
        }

    }
    public void menuEliminaUtente(){
        stampaUtenti();
        boolean exit = false;
        while(!exit) {
            String input = in.nextLine();
            if (checkValore(input, (ArrayList<String>) MockLocator.getMockTuristi().get(null).stream()
                    .map(turista -> turista.getCredenziali().getUsername())
                    .collect(Collectors.toList()))) {
                HashMap<String, Object> filtro = new HashMap<>();
                filtro.put("username", input);
                MockLocator.getMockTuristi().delete(filtro);
                println("Utente \"" + input + "\" eliminato correttamente");
                exit = true;
                in.nextLine();
            }
            else{
                println("Utente non presente. Riprova");
            }
        }
    }
    private void stampaUtenti(){
       for(ClsTuristaAutenticato t:MockLocator.getMockTuristi().get(null)){
           println("Utente " + t.getCredenziali().getUsername());
       }
       print("Seleziona un utente (username)");
    }
}