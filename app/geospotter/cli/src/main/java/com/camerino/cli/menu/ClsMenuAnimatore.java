package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.cli.mock.MockTuristi;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsAnimatore;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.cli.mock.MockNodi;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.actions.ClsCommonActions.*;
        import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuAnimatore implements IMenu{
    private ClsAnimatore user;
    Scanner in = new Scanner(System.in);
    public ClsMenuAnimatore(ClsAnimatore a){user = a;}
    private ClsMenuContributorAuth menuca;
    public ClsMenuContributorAuth getMenuca(){return menuca;}
    @Override
    public void menu() {
        boolean exit = false;
            menuca = new ClsMenuContributorAuth(user);
            while (!exit) {
                menuca.menu();
                println("12) Crea Contest aperto");
                println("13) Crea Contest su invito");
                println("14) Valida contenuti del contest");
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
                        case "13" :
                        case "14" : menuContest(); break;
                        case "0" : exit = true;
                    }
                }
                else exit = true;
        }
    }
    public void menuContest(){
        println("Questa è una funzionalità presente su Geospotter Desktop.");
    }

}