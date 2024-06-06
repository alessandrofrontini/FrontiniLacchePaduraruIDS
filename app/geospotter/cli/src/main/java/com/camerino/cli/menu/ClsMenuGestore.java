package com.camerino.cli.menu;

import com.camerino.cli.actions.ClsCommonActions;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.utenti.ClsGestoreDellaPiattaforma;

import java.util.Scanner;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;

public class ClsMenuGestore implements IMenu {
    ClsGestoreDellaPiattaforma user;
    Scanner in = new Scanner(System.in);

    public ClsMenuGestore(ClsGestoreDellaPiattaforma gdp) {
        this.user = gdp;
        gdp.setIperComuni(MockLocator.getMockComuni());
    }

    @Override
    public void menu() {//TODO: implementare
        boolean exit = false;

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
                case "1" -> ClsCommonActions.aggiungiComune(this.user);
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
