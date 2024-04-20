package com.camerino.ids.fps.geospotter.cli.menu;

import com.camerino.ids.fps.geospotter.server.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.fps.geospotter.server.data.utenti.ClsGestoreDellaPiattaforma;
import com.camerino.ids.fps.geospotter.server.data.utils.Credenziali;

import java.util.Scanner;

import static com.camerino.ids.fps.geospotter.cli.actions.ClsCommonActions.aggiungiItinerario;
import static com.camerino.ids.fps.geospotter.cli.actions.ClsCommonActions.aggiungiNodo;
import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.println;

public class ClsMenuGestore implements IMenu {
    ClsGestoreDellaPiattaforma user;
    Scanner in = new Scanner(System.in);

    @Override
    public void menu() {
        boolean exit = false;
        user = new ClsGestoreDellaPiattaforma();
        user.setId("1");
        user.setPunteggio(666);
        user.setCredenziali(new Credenziali());
        user.getCredenziali().setUsername("contributor autorizzato");
        user.getCredenziali().setPassword("password");
        while (!exit) {
            println("1) Aggiungi Comune");
            println("2) Modifica Comune");
            println("3) Elimina Comune");
            println("4) Inserisci Itinerario");
            println("5) Modifica Itinerario");
            println("6) Modifica Itinerario");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()) {
                case "1" -> print("noop");
                case "2" -> print("noop");
                case "3" -> print("noop");
                case "4" -> print("noop");
                case "5" -> print("noop");
                case "6" -> print("noop");
                case "0" -> print("noop");
            }
        }
    }
}
