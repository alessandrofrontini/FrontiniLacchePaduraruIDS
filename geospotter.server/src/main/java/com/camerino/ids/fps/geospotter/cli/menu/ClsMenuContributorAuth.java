package com.camerino.ids.fps.geospotter.cli.menu;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.fps.geospotter.server.data.utils.Credenziali;

import java.util.Scanner;

import static com.camerino.ids.fps.geospotter.cli.actions.ClsCommonActions.aggiungi_nodo;
import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.println;

public class ClsMenuContributorAuth implements IMenu{
    private ClsContributorAutorizzato user = new ClsContributorAutorizzato();
    @Override
    public void menu() {
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        ClsContributorAutorizzato user = new ClsContributorAutorizzato();
        user.setId("1");
        user.setPunteggio(666);
        user.setCredenziali(new Credenziali());
        user.getCredenziali().setUsername("contributor autorizzato");
        user.getCredenziali().setPassword("password");
        while (!exit) {
            println("1) Aggiungi Nodo");
            println("2) Modifica Nodo");
            println("3) Elimina Nodo");
            println("4) Inserisci Itinerario");
            println("5) Modifica Itinerario");
            println("6) Modifica Itinerario");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()){//Gli altri case sono placeholder
                case "1" -> aggiungi_nodo(user);
                case "2" -> aggiungi_nodo(user);
                case "3" -> aggiungi_nodo(user);
                case "4" -> aggiungi_nodo(user);
                case "5" -> aggiungi_nodo(user);
                case "6" -> aggiungi_nodo(user);
                case "0" -> aggiungi_nodo(user);
            }
        }
    }
}
