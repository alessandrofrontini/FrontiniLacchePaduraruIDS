package com.camerino.cli;

import com.camerino.cli.menu.*;
import com.camerino.cli.mock.MockLocator;
import com.camerino.cli.mock.MockNodi;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.camerino.cli.loggers.ClsConsoleLogger.println;
import static com.camerino.cli.menu.Input.checkValore;

public class Main {
    static Scanner in = new Scanner(System.in);
    static ClsTurista user;
    public static void main(String[] args)
    {
        print_header();
        leggiTutto();
        user = new ClsTurista();
        user.setIperNodi(MockLocator.getMockNodi());
        user.setIperSegnalazioni(MockLocator.getMockSegnalazioni());
        while (true) {
            //Queste sono le azioni che un turista (non autenticato) può fare
            println("1)Login");
            println("2)Registrati");
            println("3)Lista Comuni");
            println("4)Lista nodi di un comune");
            println("5)Mostra Nodo");
            println("6)Segnala Nodo");
            println("0)Esci");
            switch (in.nextLine()){
                case "0"-> {
                    salvaTutto();
                    return;
                }
                case "1"-> login();
                case "2"-> registraUtente();
                case "3"-> listaComuni();
                case "4"-> menuListaNodiComune();
                case "5"-> menuMostraNodo();
                case "6"-> menuSegnalaNodo();
            }
        }
    }
    /**
     * Il metodo scrive su file CSV tutti i dati salvati alla chiusura dell'app.
     */
    private static void salvaTutto(){
        MockLocator.getMockComuni().scriviComuni();
        MockLocator.getMockNodi().scriviNodi();
        MockLocator.getMockSegnalazioni().scriviSegnalazioni();
        MockLocator.getMockRecensioni().scriviRecensioni();
        MockLocator.getMockTuristi().scriviUtenti();
        MockLocator.getMockImmagini().scriviImmagini();
        MockLocator.getMockRCD().scriviRCD();
        MockLocator.getMockItinerari().scriviItinerari();
        MockLocator.getMockRCDI().scriviRCDItinerari();
        MockLocator.getMockRCDImmagini().ScriviRCDImmagini();
    }
    /**
     * Il metodo legge da file tutti i dati salvati e riempie le Mock
     */
    private static void leggiTutto(){
        MockLocator.getMockTuristi().leggiUtenti();
        MockLocator.getMockComuni().leggiComuni();
        MockLocator.getMockNodi().leggiNodi();
        MockLocator.getMockSegnalazioni().leggiSegnalazioni();
        MockLocator.getMockRecensioni().leggiRecensioni();
        MockLocator.getMockImmagini().leggiImmagini();
        MockLocator.getMockRCD().leggiRCD();
        MockLocator.getMockItinerari().leggiItinerari();
        MockLocator.getMockRCDI().leggiRCDItinerari();
        MockLocator.getMockRCDImmagini().LeggiRCDImmagini();
    }
    private static void registraUtente(){
        String username = "", pwd = "";
        boolean exit = false;
        while(!exit) {
            println("Inserisci l'username");
            username = in.nextLine();
            if (checkValore(username, (ArrayList<String>) MockLocator.getMockTuristi().get(null).stream().map(utente -> utente.getCredenziali().getUsername()).collect(Collectors.toList()))) {
                println("Username già in uso. Riprova");
                in.nextLine();
            }
            else exit = true;
        }
        println("Inserisci la password");
        pwd = in.nextLine();
        while(exit) {
            println("Ripeti la password");
            if (!Objects.equals(pwd, in.nextLine())) {
                println("Le password non corrispondono. Riprova.");
                in.nextLine();
            }
            else exit = false;
        }
        ClsTuristaAutenticato utente = new ClsTuristaAutenticato();
        Credenziali c = new Credenziali();
        c.setUsername(username);
        c.setPassword(pwd);
        utente.setCredenziali(c);
        MockLocator.getMockTuristi().insert(utente);
    }

    /**
     * Il metodo stampa a video l'elenco di tutti i comuni
     */
    private static void listaComuni()
    {
        for(ClsComune comune:MockLocator.getMockComuni().get(null)){
            println(comune.visualizzaComune());
        }
    }

    /**
     * Il metodo prende in input le credenziali dell'utente e, se possibile, effettua il login e il reindirizzamento al menu di competenza.
     */
    private static void login() {
        Credenziali credenziali = Input.richiediCredenziali();
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("credenziali", credenziali);
        ClsTuristaAutenticato user = MockLocator.getMockTuristi().get(tmp).get(0);
        if (user == null) {
            println("Credenziali errate");
            return;
        }
        main_menu(user);
    }

    /**
     * Il metodo associa il menu corrispondente al ruolo dell'utente passato come parametro
     * @param turista l'utente che, in base al ruolo, avrà accesso ad un menu diverso
     */
    private static void main_menu(ClsTuristaAutenticato turista) {
        switch (turista.getRuoloUtente()){
            case TURISTA_AUTENTICATO -> new ClsMenuTuristaAutenticato(turista).menu();
            case CONTRIBUTOR -> new ClsMenuContributor((ClsContributor) turista).menu();
            case CONTRIBUTOR_AUTORIZZATO -> new ClsMenuContributorAuth((ClsContributorAutorizzato) turista).menu();
            case ANIMATORE -> new ClsMenuAnimatore((ClsAnimatore) turista).menu();
            case CURATORE -> new ClsMenuCuratore((ClsCuratore) turista).menu();
            case GESTORE_DELLA_PIATTAFORMA -> new ClsMenuGestore((ClsGDP) turista).menu();
        }
    }

    /**
     * Il metodo inserisce una segnalazione al nodo scelto. L'utente prima seleziona un nodo da segnalare, poi fornisce una descrizione della segnalazione
     * e infine la segnalazione viene salvata.
     */
    private static void menuSegnalaNodo(){
        ClsSegnalazione segnalazione = new ClsSegnalazione();
        println("inserisci l'id del nodo");
        String input = in.nextLine();
        if (checkValore(input, (ArrayList<String>) MockLocator.getMockNodi().get(null).stream()
                .map(nodo -> nodo.getId().toString())
                .collect(Collectors.toList())))
         {
            segnalazione.setIdContenuto(Long.valueOf(input));
            println("fornisci una descrizione");
            segnalazione.setDescrizione(in.nextLine());
            user.segnalaContenuto(segnalazione);
            println("Segnalazione inserita");
        }
        else println("Nodo non esistente.");
    }

    /**
     * Il metodo stampa a video tutti i nodi di un comune, preso in input dall'utente tramite l'id.
     */
    private static void menuListaNodiComune(){
        println("inserisci l'id del comune");
        String input = in.nextLine();
        if(checkValore(input, (ArrayList<String>) MockLocator.getMockComuni().get(null).stream().map(comune -> comune.getId().toString()).collect(Collectors.toList()))) {
            for (ClsNodo nodo : user.getNodiByComune(Long.valueOf(input)))
                println(nodo.visualizzaNodo());
        }
        else println("Comune non esistente.");
    }
    /**
     * Il metodo stampa a video le informazioni di un nodo, scelto dall'utente inserendo in input l'ID
     */
    private static void menuMostraNodo(){
        println("inserisci l'id del nodo");
        String input = in.nextLine();
        if(checkValore(input, (ArrayList<String>) MockLocator.getMockNodi().get(null).stream().map(nodo -> nodo.getId().toString()).collect(Collectors.toList()))) {
            println(user.getNodoById(Long.valueOf(input)).get(0).visualizzaNodo());
        }
        else println("nodo non esistente.");
    }

    //region Visualizzazione header e team
    //https://patorjk.com/software/taag/
    private static void print_header() {
        System.out.println("""
                   ______             _____                __   __                            \s
                  / ____/___   ____  / ___/ ____   ____   / /_ / /_ ___   _____               \s
                 / / __ / _ \\ / __ \\ \\__ \\ / __ \\ / __ \\ / __// __// _ \\ / ___/               \s
                / /_/ //  __// /_/ /___/ // /_/ // /_/ // /_ / /_ /  __// /                   \s
                \\____/ \\___/ \\____//____// .___/ \\____/ \\__/ \\__/ \\___//_/                    \s
                                        /_/                                                   \s
                                                       ______                            __   \s
                                                      / ____/____   ____   _____ ____   / /___\s
                                                     / /    / __ \\ / __ \\ / ___// __ \\ / // _ \\
                                                    / /___ / /_/ // / / /(__  )/ /_/ // //  __/
                                                    \\____/ \\____//_/ /_//____/ \\____//_/ \\___/\s
                                                                                              \s
                """);


    }
    //endregion

}
