package com.camerino.cli.menu;

import com.camerino.ids.core.data.utenti.ClsAnimatore;

import java.util.Scanner;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuAnimatore implements IMenu{
    private ClsAnimatore user;
    Scanner in = new Scanner(System.in);
    public ClsMenuAnimatore(ClsAnimatore a){user = a;}
    private ClsMenuContributorAuth menuca;
    public ClsMenuContributorAuth getMenuca(){return menuca;}
    /**
     * L'Animatore ha accesso a tutti i menu dei ruoli sottostanti, pertanto tutte le azioni di Turista Autenticato, Contributor e Contributor Autorizzato sono disponibili.
     * Il metodo fornisce un menu di azioni; se la classe di riferimento è Animatore il menu termina qua, altrimenti poi continua con il menu del Curatore.
     */
    @Override
    public void menu() {
        boolean exit = false;
            menuca = new ClsMenuContributorAuth(user);
            while (!exit) {
                menuca.menu();
                println("12) Crea Contest");
                println("13) Valida contenuti del contest");
                if (user.getClass().equals(ClsAnimatore.class)) {
                    println("0) Esci");
                    print(">> ");
                    switch (in.nextLine()) {
                        case "1" : menuca.getMenuc().getMenuta().menuInserisciRecensione(); break;
                        case "2" : menuca.getMenuc().getMenuta().menuModificaRecensione(); break;
                        case "3" : menuca.getMenuc().getMenuta().menuEliminaRecensione(); break;
                        case "4" : menuca.getMenuc().getMenuta().menuInserisciFoto(); break;
                        case "5" : menuca.getMenuc().menuInserisciNodo(); break;
                        case "6" : menuca.getMenuc().menuModificaNodo(); break;
                        case "7" : menuca.getMenuc().menuEliminaNodo(); break;
                        case "8" : menuca.getMenuc().menuInserisciItinerario(); break;
                        case "9" : menuca.getMenuc().menuModificaItinerario(); break;
                        case "10" : menuca.getMenuc().menuEliminaItinerario(); break;
                        case "11" : menuca.getMenuc().sottoMenuContest(); break;
                        case "12" :
                        case "13" : menuContest(); break;
                        case "0" : exit = true;
                    }
                }
                else exit = true;
        }
    }
    /**
     * Il metodo è pronto a fornire un sottomenu per la creazione di contest. Tuttavia i contest sono una funzionalità di Geospotter Desktop.
     */
    public void menuContest(){
        println("Questa è una funzionalità presente su Geospotter Desktop.");
    }

}