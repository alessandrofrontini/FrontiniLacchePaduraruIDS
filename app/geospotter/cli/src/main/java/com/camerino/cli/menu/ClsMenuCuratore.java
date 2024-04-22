package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsAnimatore;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.cli.mock.MockNodi;

import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.actions.ClsCommonActions.*;
import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuCuratore implements IMenu{
    private ClsCuratore user;
    Scanner in = new Scanner(System.in);
    public ClsMenuCuratore (ClsCuratore curatore){ user = curatore;}
    @Override
    public void menu() {
        boolean exit = false;
        user = new ClsCuratore();
        user.setId("1");
        user.setPunteggio(1000); //punteggio da non prendere seriamente
        user.setCredenziali(new Credenziali());
        user.getCredenziali().setUsername("Curatore");
        user.getCredenziali().setPassword("password");

        while (!exit) {
            println("1) Visualizza richieste");
            println("2) Uprank utente");
            println("3) Downrank utente");
            println("4) Reset Rank utente");
            println("5) Registra nuovo utente");
            println("6) Modifica utente");
            println("7) Elimina utente");
            println("8) Pubblica contenuto sui social");
            println("0) Esci");
            print(">> ");
            switch(in.nextLine()){
                case "1" -> menuVisualizzaRichieste();
                case "2" -> menuUprankUtente();
                case "3" -> menuDownRankUtente();
                case "4" -> menuResetRankUtente();
                case "5" -> menuRegistraNuovoUtente();
                case "6" -> menuModificaUtente();
                case "7" -> menuEliminaUtente();
                case "8" -> menuPubblicaSocial();
                case "0" -> exit = true;
            }
        }
    }

    private void menuVisualizzaRichieste(){

    }
    private void menuUprankUtente(){

    }
    private void menuDownRankUtente(){

    }
    private void menuResetRankUtente(){

    }
    private void menuRegistraNuovoUtente(){
    
    }
    private void menuModificaUtente(){

    }
    private void menuEliminaUtente(){

    }
    private void menuPubblicaSocial(){

    }
}
