package com.camerino.cli;

import com.camerino.cli.loggers.ClsConsoleLogger;
import com.camerino.cli.menu.*;
import com.camerino.cli.mock.*;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {
        print_header();
        leggiTutto();
        while (true) {
            //Queste sono le azioni che un turista (non autenticato) può fare
            ClsConsoleLogger.println("1)Login");
            ClsConsoleLogger.println("2)Lista Comuni");
            ClsConsoleLogger.println("3)Lista nodi di un comune");
            ClsConsoleLogger.println("4)Mostra Nodo");
            ClsConsoleLogger.println("5)Segnala Nodo");
            ClsConsoleLogger.println("0)Esci");
            switch (in.nextLine()){
                case "0"-> {
                    salvaTutto();
                    return;
                }
                case "1"-> login();
                case "2"-> listaComuni();
                case "3"-> menuListaNodiComune();
                case "4"-> menuMostraNodo();
                case "5"-> menuSegnalaNodo();
            }
        }
    }

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
    }
    private static void leggiTutto(){
        MockLocator.getMockComuni().leggiComuni();
        MockLocator.getMockNodi().leggiNodi();
        MockLocator.getMockSegnalazioni().leggiSegnalazioni();
        MockLocator.getMockRecensioni().leggiRecensioni();
        MockLocator.getMockTuristi().leggiUtenti();
        MockLocator.getMockImmagini().leggiImmagini();
        MockLocator.getMockRCD().leggiRCD();
        MockLocator.getMockItinerari().leggiItinerari();
        MockLocator.getMockRCDI().leggiRCDItinerari();
    }
    private static void listaComuni()
    {
        for(ClsComune comune:MockLocator.getMockComuni().get(null)){
            ClsConsoleLogger.println(comune.visualizzaComune());
        }


    }

    private static void login() {
        Credenziali credenziali = Input.richiediCredenziali();
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("credenziali", credenziali);
        ClsTuristaAutenticato user = MockLocator.getMockTuristi().get(tmp).get(0);
        if (user == null) {
            ClsConsoleLogger.println("Credenziali errate");
            return;
        }
        main_menu(user);
    }

    private static void main_menu(ClsTuristaAutenticato turista) {
        switch (turista.getRuoloUtente()){
            case TURISTA_AUTENTICATO -> new ClsMenuTuristaAutenticato(turista).menu();
            case CONTRIBUTOR -> new ClsMenuContributor((ClsContributor) turista).menu();
            case CONTRIBUTOR_AUTORIZZATO -> new ClsMenuContributorAuth((ClsContributorAutorizzato) turista).menu();
            case ANIMATORE -> new ClsMenuAnimatore((ClsAnimatore) turista).menu();
            case CURATORE -> new ClsMenuCuratore((ClsCuratore) turista).menu();
            case GESTORE_DELLA_PIATTAFORMA -> new ClsMenuGestore((ClsGestoreDellaPiattaforma) turista).menu();
        }
    }

    private static void menuSegnalaNodo(){
        ClsTurista user = new ClsTurista(MockLocator.getMockSegnalazioni());
        ClsSegnalazione segnalazione = new ClsSegnalazione();
        ClsConsoleLogger.println("inserisci l'id del nodo");
        segnalazione.setIdContenuto(in.nextLine());
        ClsConsoleLogger.println("fornisci una descrizione");
        segnalazione.setDescrizione(in.nextLine());
        segnalazione.setIdUtente("turista");
        user.segnalaContenuto(segnalazione);
        ClsConsoleLogger.println("Segnalazione inserita");
    }

    private static void menuListaNodiComune(){
        MockNodi mockNodi = MockLocator.getMockNodi();
        HashMap<String, Object> filtro = new HashMap<>();
        ClsConsoleLogger.println("inserisci l'id del comune");
        filtro.put("idComune", in.nextLine());
        for(ClsNodo nodo: mockNodi.get(filtro))
            ClsConsoleLogger.println(nodo.visualizzaNodo());
    }
    private static void menuMostraNodo(){
        MockNodi mockNodi = MockLocator.getMockNodi();
        HashMap<String, Object> filtro = new HashMap<>();
        ClsConsoleLogger.println("inserisci l'id del nodo");
        filtro.put("id", in.nextLine());
        ClsConsoleLogger.println(mockNodi.get(filtro).get(0).visualizzaNodo());
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
