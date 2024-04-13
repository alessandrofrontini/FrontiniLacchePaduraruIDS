package com.camerino.ids.fps.geospotter.server;

import com.camerino.ids.fps.geospotter.server.data.utils.Credenziali;

import java.util.Scanner;

public class JavaMain {
    enum USER_TYPE{TOURIST, AUTH_TURIST, CONTRIBUTOR, AUTH_CONTRIBUTOR, ANIMATORE, CURATORE, GESTORE}
    public static void print(Object obj){System.out.print(obj);}
    public static void println(Object obj){System.out.println(obj);}
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        USER_TYPE uType = null;
        Credenziali credenziali = new Credenziali();
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
    private static void main_gestore() {
    }

    private static void main_curatore() {
    }

    private static void main_animatore() {
    }

    private static void main_auth_contributor() {
    }

    private static void main_contributor() {
    }

    private static void main_auth_tourist() {
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
            case AUTH_CONTRIBUTOR -> main_auth_contributor();
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
}
