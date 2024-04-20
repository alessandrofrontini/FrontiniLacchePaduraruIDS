package com.camerino.cli;

import com.camerino.cli.loggers.ClsConsoleLogger;
import com.camerino.cli.menu.ClsMenuContributorAuth;
import com.camerino.cli.menu.Input;
import com.camerino.cli.mock.MockComuni;
import com.camerino.cli.mock.MockLocator;
import com.camerino.cli.mock.MockTuristi;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Credenziali;

import java.util.HashMap;
import java.util.Scanner;

public class Main {//Gli scanner i double li vuole con la , e non punto .  . Comportamenot overridabile
    public static void main(String[] args)
    {
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

    private static void login() {
        Credenziali credenziali = Input.richiediCredenziali();
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("credenziali", credenziali);
        ClsTuristaAutenticato user = MockLocator.getMockTuristi().get(null).get(0);

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
            case GESTORE_DELLA_PIATTAFORMA -> ClsConsoleLogger.println("Noop");
        }
    }
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
}
