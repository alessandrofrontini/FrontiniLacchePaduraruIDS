package com.camerino.cli;

import com.camerino.cli.loggers.ClsConsoleLogger;
import com.camerino.cli.menu.ClsMenuContributorAuth;
import com.camerino.cli.menu.ClsMenuGestore;
import com.camerino.cli.menu.Input;
import com.camerino.cli.mock.*;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsGestoreDellaPiattaforma;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.data.utils.Posizione;

import java.util.HashMap;
import java.util.Scanner;

public class Main {//Gli scanner i double li vuole con la , e non punto .  . Comportamenot overridabile
    public static void main(String[] args)
    {
    //region debug gdp crud
//        System.out.println("\n\n\n\n\n\n\n TEST INSERT \n\n\n\n\n\n\n");
//        MockComuni m = MockLocator.getMockComuni();
//
//        ClsGestoreDellaPiattaforma gdp = new ClsGestoreDellaPiattaforma();
//        gdp.setMockComuni(m);
//        gdp.setId("1");
//
//        Credenziali c = new Credenziali();
//        c.setUsername("GDP");
//        c.setPassword("");
//        gdp.setCredenziali(c);
//        gdp.setpItinerari(null);
//        gdp.setpRDC(null);
//        gdp.setpNodi(null);
//        gdp.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA);
//
//        ClsComune comune = new ClsComune();
//        comune.setId("1111");
//        comune.setUsernameCreatore(gdp.getCredenziali().getUsername());
//        comune.setAbitanti(1000);
//        comune.setSuperficie(1000);
//        comune.setNome("Test");
//        Posizione pos1 = new Posizione();
//        pos1.setX(111);
//        pos1.setY(111);
//        comune.setPosizione(pos1);
//        gdp.inserisciComune(comune);
//
//        listaComuni();
//
//        System.out.println("\n\n\n\n\n\n\n TEST UPDATE \n\n\n\n\n\n\n");
//
//
//        ClsComune comune2 = new ClsComune();
//        comune2.setId("2");
//        comune2.setUsernameCreatore(gdp.getCredenziali().getUsername());
//        comune2.setAbitanti(2000);
//        comune2.setSuperficie(2000);
//        comune2.setNome("Test2");
//
//        Posizione pos = new Posizione();
//        pos.setX(222);
//        pos.setY(222);
//        comune2.setPosizione(pos);
//
//        gdp.modificaComune(comune2, comune.getId());
//
//        listaComuni();
//
//        System.out.println("\n\n\n\n\n\n\n TEST DELETE \n\n\n\n\n\n\n");
//
//        gdp.eliminaComune(comune2.getId());
//        listaComuni();
//
//        System.out.println("\n\n\n\n\n\n\n\n\n\n\n ----- \n\n\n\n\n\n\n\n\n\n\n");
    //endregion

    //region debug ca crud
//        MockNodi mockNodi = MockLocator.getMockNodi();
//        ClsContributorAutorizzato ca = new ClsContributorAutorizzato();
//        ca.setpNodi(mockNodi);
//
//        ca.setId("2");
//
//        Credenziali cred = new Credenziali();
//        cred.setUsername("CA");
//        cred.setPassword("");
//
//        ca.setCredenziali(cred);
//        ca.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
//        ca.setPunteggio(980);
//
//        System.out.println("\n\n\n\n\n\n\n\n\n\n TEST INSERT \n\n\n\n\n\n\n\n\n\n");
//
//        ClsNodo nodo1 = new ClsNodo();
//        nodo1.setId("00");
//        nodo1.setIdComune(comune2.getId());
//        nodo1.setUsernameCreatore(ca.getCredenziali().getUsername());
//        nodo1.setTipologiaNodo(ClsNodo.eTologiaNodo.COMMERCIALE);
//        nodo1.setNome("TEST NODO INSERT");
//        nodo1.setPosizione(pos1);
//        ca.inserisciNodo(nodo1);
//        listaNodi();
//
//        System.out.println("\n\n\n\n\n\n\n\n\n\n TEST UPDATE \n\n\n\n\n\n\n\n\n\n");
//
//        ClsNodo nodo2 = new ClsNodo();
//        nodo2.setId("00");
//        nodo2.setIdComune(comune2.getId());
//        nodo2.setUsernameCreatore(ca.getCredenziali().getUsername());
//        nodo2.setTipologiaNodo(ClsNodo.eTologiaNodo.COMMERCIALE);
//        nodo2.setNome("TEST NODO UPDATE");
//        nodo2.setPosizione(pos1);
//        ca.modificaNodo(nodo1.getId(), nodo2);
//        listaNodi();
//
//        System.out.println("\n\n\n\n\n\n\n\n\n\n TEST DELETE \n\n\n\n\n\n\n\n\n\n");
//        ca.eliminaNodo(nodo2.getId());


    // endregion

    //region ta crud
    ClsTuristaAutenticato ta = new ClsTuristaAutenticato();
    ta.setpRecensioni(MockLocator.getMockRecensioni());
    ta.setpimmagini(MockLocator.getMockImmagini());
    ta.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);

    Credenziali credenz = new Credenziali();
    credenz.setUsername("TA");
    credenz.setPassword("");
    ta.setCredenziali(credenz);
    ta.setPunteggio(50);

        System.out.println("\n\n\n\n\n\n\n\n\n\n TEST INSERT \n\n\n\n\n\n\n\n\n\n");
        ClsRecensione recensione1 = new ClsRecensione();
        recensione1.setId("1");
        recensione1.setUsernameCreatore("TEST USERNAME");
        recensione1.setContenuto("TEST CONTENUTO");
        recensione1.setOggetto("TEST OGGETTO");
        recensione1.setValutazione(0);
        recensione1.setIdContenutoAssociato("1");
        ta.inserisciRecensione(recensione1);
        listaRecensioni();

        System.out.println("\n\n\n\n\n\n\n\n\n\n TEST UPDATE \n\n\n\n\n\n\n\n\n\n");
        ClsRecensione recensione2 = new ClsRecensione();
        recensione2.setId("2");
        recensione2.setUsernameCreatore("TEST USERNAME");
        recensione2.setContenuto("TEST CONTENUTO2");
        recensione2.setOggetto("TEST OGGETTO2");
        recensione2.setValutazione(0);
        recensione2.setIdContenutoAssociato("2");
        ta.modificaRecensione(recensione1.getId(), recensione2);
        listaRecensioni();

        System.out.println("\n\n\n\n\n\n\n\n\n\n TEST DELETE \n\n\n\n\n\n\n\n\n\n");
        ta.eliminaRecensione(recensione2.getId());
        listaRecensioni();


    //endregion


        Scanner in = new Scanner(System.in);
        while (true) {
            print_team();
            print_header();

            //Queste sono le azioni che un turista (non aitenticato) più fare
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
                case "3"-> ClsConsoleLogger.println("Noop");
                case "4"-> ClsConsoleLogger.println("Noop");
                case "5"-> ClsConsoleLogger.println("Noop");
            }
        }
    }

    private static void listaComuni()
    {
        MockComuni mockComuni = MockLocator.getMockComuni();
        for(int i = 0; i < mockComuni.get(null).size(); i++)
        {
            System.out.println(mockComuni.get(null).get(i).visualizzaComune());
        }
    }

    private static void listaNodi()
    {
        MockNodi mockNodi = MockLocator.getMockNodi();
        for(int i = 0; i < mockNodi.get(null).size(); i++)
        {
            System.out.println(mockNodi.get(null).get(i).visualizzaNodo());
        }
    }

    private static void listaRecensioni()
    {
        MockRecensioni mockRecensioni = MockLocator.getMockRecensioni();
        for(int i = 0; i < mockRecensioni.get(null).size(); i++)
        {
            System.out.println(mockRecensioni.get(null).get(i).visualizzaRecensione());
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
            case TURISTA_AUTENTICATO -> ClsConsoleLogger.println("Noop");
            case CONTRIBUTOR -> ClsConsoleLogger.println("Noop");
            case CONTRIBUTOR_AUTORIZZATO -> new ClsMenuContributorAuth((ClsContributorAutorizzato) turista).menu();
            case ANIMATORE -> ClsConsoleLogger.println("Noop");
            case CURATORE -> ClsConsoleLogger.println("Noop");
            case GESTORE_DELLA_PIATTAFORMA -> new ClsMenuGestore((ClsGestoreDellaPiattaforma) turista).menu();
        }
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
