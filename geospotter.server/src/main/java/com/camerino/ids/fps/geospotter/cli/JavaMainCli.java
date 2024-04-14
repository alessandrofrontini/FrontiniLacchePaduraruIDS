package com.camerino.ids.fps.geospotter.cli;

import com.camerino.ids.fps.geospotter.cli.menu.ClsMenuContributorAuth;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.utenti.IContributable;
import com.camerino.ids.fps.geospotter.server.data.utils.Credenziali;
import com.camerino.ids.fps.geospotter.server.data.utils.Posizione;

import java.util.Scanner;

import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.println;

public class JavaMainCli {//Gli scanner i double li vuole con la , e non punto .  . Comportamenot overridabile
    enum USER_TYPE{TOURIST, AUTH_TURIST, CONTRIBUTOR, AUTH_CONTRIBUTOR, ANIMATORE, CURATORE, GESTORE}
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        USER_TYPE uType = null;
        Credenziali credenziali = new Credenziali();
        Posizione pos = new Posizione();
        while (true) {
            print_header();
            while (uType==null) {
                System.out.println("(lasciare l'username vuoto per non autenticarsi)");
                System.out.print("Username:");
                credenziali.setUsername(in.nextLine());
                System.out.print("Password:");
                credenziali.setPassword(in.nextLine());
                uType = login(credenziali);
            }
            main_menu(uType);
            uType = null;
        }
    }
    private static void main_tourist() {
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        while (!exit){
            print_header_tourist();
            println("Cosa vuoi fare?");
            println("1) Vedere Lista comuni");
            println("2) Vedere Nodi di un comune");
            println("0) Uscire");
            print(">> ");
            switch (in.nextLine()){
                case "1" -> println("===LISTA COMUNI");
                case "2" -> println("===INSERISCI COMUNE\n====LISTA NODI");
                case "0" -> exit = true;
            }
        }
    }
    private static void main_auth_tourist() {
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        while (!exit){
            print_header_auth_tourist();
            println("Cosa vuoi fare?");
        }
    }
    private static void aggiungi_nodo(IContributable user){
        boolean ok = false;
        Scanner in = new Scanner(System.in);
        ClsNodo nodo = new ClsNodo();
        Posizione pos = new Posizione();
        while (ok){
            print("Inserisci nome: ");
            nodo.setNome(in.nextLine());
            print("Inserisci tipo:\n 1)Commerciale\n2)Culturale\n3)Culinario\n>> ");
            switch (in.nextLine()){
                case "1" -> nodo.setTipologiaNodo(ClsNodo.TipologiaNodo.COMMERCIALE);
                case "2" -> nodo.setTipologiaNodo(ClsNodo.TipologiaNodo.CULTURALE);
                case "3" -> nodo.setTipologiaNodo(ClsNodo.TipologiaNodo.CULINARIO);
            }
            print("Inserisci coordinata X del nodo: ");
            pos.setX(in.nextDouble());
            print("Inserisci coordinata Y del nodo: ");
            pos.setY(in.nextDouble());
            nodo.setPosizione(pos);
            print("Inserisci id del comune di appartenenza: ");
            nodo.setIdComune(in.nextLine());
            //TODO: aggiungere inserimento "aTempo" e durata
            if(user.inserisciNodo(nodo))
                println("Nodo Inserito con successo");
            else
                println("Errore inserimento nodo");
            print("Press any key to continue...");
            in.nextLine();
        }
    }
    private static void main_gestore() {
    }

    private static void main_curatore() {
    }

    private static void main_animatore() {
    }

    private static void main_contributor() {
    }
    private static USER_TYPE login(Credenziali credenziali) {
        switch (credenziali.getUsername().toLowerCase()){
            case "" -> {
                return USER_TYPE.TOURIST;
            }
            case "curatore" -> {
                return USER_TYPE.CURATORE;
            }
            case "gestore" -> {
                return USER_TYPE.GESTORE;
            }
            case "animatore" -> {
                return USER_TYPE.ANIMATORE;
            }
            case "contributor" -> {
                return USER_TYPE.CONTRIBUTOR;
            }
            case "contributor autorizzato" -> {
                return USER_TYPE.AUTH_CONTRIBUTOR;
            }
            default -> {
                return null;
            }
        }
    }
    private static void main_menu(USER_TYPE uType) {
        switch (uType){
            case TOURIST -> main_tourist();
            case AUTH_TURIST -> main_auth_tourist();
            case CONTRIBUTOR -> main_contributor();
            case AUTH_CONTRIBUTOR -> new ClsMenuContributorAuth().menu();
            case ANIMATORE -> main_animatore();
            case CURATORE -> main_curatore();
            case GESTORE -> main_gestore();
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

    private static void print_header_tourist() {
        System.out.println("""
                 _       __     __                               \s
                | |     / /__  / /________  ____ ___  ___        \s
                | | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\       \s
                | |/ |/ /  __/ / /__/ /_/ / / / / / /  __/       \s
                |__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/      __\s
                                /_  __/___  __  _______(_)____/ /_
                                 / / / __ \\/ / / / ___/ / ___/ __/
                                / / / /_/ / /_/ / /  / (__  ) /_ \s
                               /_/  \\____/\\__,_/_/  /_/____/\\__/ \s
                                                                 \s
                """);
    }

    private static void print_header_auth_tourist() {
        System.out.println("""
                 _       __     __                               \s
                | |     / /__  / /________  ____ ___  ___        \s
                | | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\       \s
                | |/ |/ /  __/ / /__/ /_/ / / / / / /  __/       \s
                |__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/      __\s
                
                """);
    }
}
