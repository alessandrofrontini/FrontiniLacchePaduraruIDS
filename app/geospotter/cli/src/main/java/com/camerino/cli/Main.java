package com.camerino.cli;

import com.camerino.cli.loggers.ClsConsoleLogger;
import com.camerino.cli.menu.*;
import com.camerino.cli.mock.*;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    IPersistenceModel<ClsSegnalazione> segnalazioni = null;
    ClsTurista user = new ClsTurista(segnalazioni);
    static Scanner in = new Scanner(System.in);
    //Gli scanner i double li vuole con la , e non punto .  . Comportamenot overridabile
    public static void main(String[] args)
    {
        while (true) {
            print_team();

            print_header();
            //Queste sono le azioni che un turista (non autenticato) può fare
            ClsConsoleLogger.println("1)Login");
            ClsConsoleLogger.println("2)Lista Comuni");
            ClsConsoleLogger.println("3)Lista nodi di un comune");
            ClsConsoleLogger.println("4)Mostra Nodo");
            ClsConsoleLogger.println("5)Segnala Nodo");
            ClsConsoleLogger.println("0)Esci");
            switch (in.nextLine()){
                case "0"-> {
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

    private static void listaComuni()
    {
        MockComuni mockComuni = MockLocator.getMockComuni();
        for(ClsComune comune:mockComuni.get(null)){
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
            case TURISTA_AUTENTICATO -> new ClsMenuTuristaAutenticato().menu();
            case CONTRIBUTOR -> new ClsMenuContributor().menu();
            case CONTRIBUTOR_AUTORIZZATO -> new ClsMenuContributorAuth().menu();
            case ANIMATORE -> new ClsMenuAnimatore().menu();
            case CURATORE -> new ClsMenuCuratore().menu();
            case GESTORE_DELLA_PIATTAFORMA -> new ClsMenuGestore((ClsGestoreDellaPiattaforma) turista).menu();
        }
    }

    private static void menuSegnalaNodo(){
        ClsSegnalazione segnalazione = new ClsSegnalazione();
        ClsConsoleLogger.println("inserisci l'id del nodo");
        segnalazione.setIdContenuto(in.nextLine());
        ClsConsoleLogger.println("fornisci una descrizione");
        segnalazione.setDescrizione(in.nextLine());
        MockLocator.getMockSegnalazioni().insert(segnalazione);
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
        filtro.put("idComune", in.nextLine());
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
    private static void print_team()
    {
        ClsConsoleLogger.print("\n" +
                "   _____ _____        ______      __  __       _______   _______ ______          __  __   _____  _____  ______  _____ ______ _   _ _______    \n" +
                "  / ____|  __ \\ /\\   |  ____/\\   |  \\/  |   /\\|__   __| |__   __|  ____|   /\\   |  \\/  | |  __ \\|  __ \\|  ____|/ ____|  ____| \\ | |__   __|   \n" +
                " | (___ | |__) /  \\  | |__ /  \\  | \\  / |  /  \\  | |       | |  | |__     /  \\  | \\  / | | |__) | |__) | |__  | (___ | |__  |  \\| |  | |      \n" +
                "  \\___ \\|  ___/ /\\ \\ |  __/ /\\ \\ | |\\/| | / /\\ \\ | |       | |  |  __|   / /\\ \\ | |\\/| | |  ___/|  _  /|  __|  \\___ \\|  __| | . ` |  | |      \n" +
                "  ____) | |  / ____ \\| | / ____ \\| |  | |/ ____ \\| |       | |  | |____ / ____ \\| |  | | | |    | | \\ \\| |____ ____) | |____| |\\  |  | |_ _ _ \n" +
                " |_____/|_| /_/    \\_\\_|/_/    \\_\\_|  |_/_/    \\_\\_|       |_|  |______/_/    \\_\\_|  |_| |_|    |_|  \\_\\______|_____/|______|_| \\_|  |_(_|_|_)\n" +
                "                                                                                                                                              \n" +
                "                                                                                                                                              \n");
    }
    //endregion
}
