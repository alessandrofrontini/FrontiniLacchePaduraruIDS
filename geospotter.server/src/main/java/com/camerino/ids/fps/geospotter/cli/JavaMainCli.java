package com.camerino.ids.fps.geospotter.cli;

import com.camerino.ids.fps.geospotter.cli.menu.ClsMenuContributorAuth;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsMappa;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.utenti.ClsAnimatore;
import com.camerino.ids.fps.geospotter.server.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.fps.geospotter.server.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.fps.geospotter.server.data.utils.Credenziali;
import com.camerino.ids.fps.geospotter.server.persistence.mock.MockComuni;
import com.camerino.ids.fps.geospotter.server.persistence.mock.MockNodi;
import com.camerino.ids.fps.geospotter.server.persistence.mock.MockRecensioni;
import com.camerino.ids.fps.geospotter.server.persistence.mock.MockTuristi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.println;
import static com.camerino.ids.fps.geospotter.cli.menu.Input.richiediCredenziali;

public class JavaMainCli {//Gli scanner i double li vuole con la , e non punto .  . Comportamenot overridabile
    private static MockTuristi mUtenti = MockTuristi.getInstance();
    public static void main(String[] args)
    {
        //region a
//        Scanner in = new Scanner(System.in);
//        while (true) {
//            print_header();
//            //Queste sono le azioni che un turista (non aitenticato) più fare
//            println("1)Login");
//            println("2)Lista Comuni");
//            println("3)Lista nodi di un comune");
//            println("4)Mostra Nodo");
//            println("5)Segnala Nodo");
//            println("0)Esci");
//            switch (in.nextLine()){
//                case "0"-> {
//                    return;
//                }
//                case "1"-> login();
//                case "2"-> listaComuni();
//                case "3"-> println("Noop");
//                case "4"-> println("Noop");
//                case "5"-> println("Noop");
//            }
//        }
        //endregion

        //Comuni Nodi e Itinerari
        ClsMappa mappa = new ClsMappa();
        System.out.println(mappa.visualizzaMappa());


        MockRecensioni mockRecensioni = MockRecensioni.getInstance();
        System.out.println(mockRecensioni.visualizzaRecensioni());

    }

    private static void listaComuni() {

    }

    private static void login() {
        Credenziali credenziali = richiediCredenziali();
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("credenziali", credenziali);
        ClsTuristaAutenticato user = mUtenti.get(tmp)[0];
        if (user == null) {
            println("Credenziali errate");
            return;
        }
        main_menu(user);
    }

    private static void main_menu(ClsTuristaAutenticato turista) {
//        switch (turista.getRuoloUtente()){
//            case TURISTA_AUTENTICATO -> println("Noop");
//            case CONTRIBUTOR -> println("Noop");
//            case CONTRIBUTOR_AUTORIZZATO -> new ClsMenuContributorAuth((ClsContributorAutorizzato)turista).menu();
//            case ANIMATORE -> println("Noop");
//            case CURATORE -> println("Noop");
//            case GESTORE_DELLA_PIATTAFORMA -> println("Noop");
//        }


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
}
