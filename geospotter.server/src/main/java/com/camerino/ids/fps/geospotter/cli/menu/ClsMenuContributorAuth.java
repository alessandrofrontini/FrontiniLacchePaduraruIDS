package com.camerino.ids.fps.geospotter.cli.menu;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.fps.geospotter.server.data.utils.Credenziali;

import java.util.Scanner;

import static com.camerino.ids.fps.geospotter.cli.actions.ClsCommonActions.aggiungiNodo;
import static com.camerino.ids.fps.geospotter.cli.actions.ClsCommonActions.eliminaNodo;
import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.println;
import static com.camerino.ids.fps.geospotter.cli.menu.Input.richiediNodo;

public class ClsMenuContributorAuth implements IMenu{
    private ClsContributorAutorizzato user = new ClsContributorAutorizzato();
    Scanner in = new Scanner(System.in);
    @Override
    public void menu() {
        boolean exit = false;
        user = new ClsContributorAutorizzato();
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
                case "1" -> aggiungiNodo(user);
                case "2" -> menuModificaNodo();
                case "3" -> menuEliminaNodo();
                case "4" -> aggiungiNodo(user);
                case "5" -> aggiungiNodo(user);
                case "6" -> aggiungiNodo(user);
                case "0" -> aggiungiNodo(user);
            }
        }
    }

    private void menuModificaNodo() {
        ClsNodo nodo = richiediNodo();
        if(nodo==null) return;
        user.modificaNodo(nodo.getId(), nodo);
    }

    private void menuEliminaNodo(){
        print("Inserisci id del nodo da eliminare: ");
        eliminaNodo(user, in.nextLine());
        println("Nodo eliminato");
    }

}
