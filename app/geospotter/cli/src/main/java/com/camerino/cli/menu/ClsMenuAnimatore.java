package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsAnimatore;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.cli.mock.MockNodi;

import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.actions.ClsCommonActions.*;
        import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuAnimatore implements IMenu{
    private ClsAnimatore user;
    Scanner in = new Scanner(System.in);

    public ClsMenuAnimatore(ClsAnimatore animatore){user = animatore;}
    @Override
    public void menu() {
        //TODO: implementare
        boolean exit = false;
        user = new ClsAnimatore();
        user.setId("1");
        user.setPunteggio(500); //punteggio da non prendere seriamente
        user.setCredenziali(new Credenziali());
        user.getCredenziali().setUsername("Animatore");
        user.getCredenziali().setPassword("password");

        while (!exit) {
            println("1) Crea Contest aperto");
            println("2) Crea Contest su invito");
            println("3) Valida contenuti del contest");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()) {
                case "1" -> menuCreaContestAperto();
                case "2" -> menuCreaContestChiuso();
                case "3" -> menuValidaContenutiContest();
                case "0" -> exit = true;
            }
        }
    }

    private void menuCreaContestAperto(){

    }

    private void menuCreaContestChiuso(){

    }
    private void menuValidaContenutiContest(){

    }
}