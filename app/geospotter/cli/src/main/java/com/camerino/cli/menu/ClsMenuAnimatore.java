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
    @Override
    public void menu() {
        boolean exit = false;
        ClsMenuContributorAuth menuca = new ClsMenuContributorAuth(user);
            while (!exit) {
                menuca.menu();
                println("12) Crea Contest aperto");
                println("13) Crea Contest su invito");
                println("14) Valida contenuti del contest");
                if (user.getClass().equals(ClsAnimatore.class)) {
                    println("0) Esci");
                    print(">> ");
                    switch (in.nextLine()) {
                        case "12" -> menuCreaContestAperto();
                        case "13" -> menuCreaContestChiuso();
                        case "14" -> menuValidaContenutiContest();
                        case "0" -> exit = true;
                    }
                }
                else exit = true;
        }
    }

    private void menuCreaContestAperto(){
        user.creaContest(Input.creaContest());
    }
    private void menuCreaContestChiuso(){
        user.creaContestChiuso(Input.creaContest(), Input.invitaUtenti(filtraUtentiContest()));
    }
    private ArrayList<ClsContributor> filtraUtentiContest(){
        ArrayList<ClsContributor> contributors = new ArrayList<>();
        for(ClsTuristaAutenticato t:MockLocator.getMockTuristi().get(null)){
            if(t.getRuoloUtente() == ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR){
                contributors.add((ClsContributor) t);
            }
        }
        return contributors;
    }
    private void menuValidaContenutiContest(){
        println("ID dei miei contest:");
        for(ClsContestDiContribuzione c:user.visualizzaContestPossessore()){
            println(c.getId());
        }
        print("Seleziona l'id: ");
        String input = in.nextLine();
        if(input != null){
            println("richieste:");
            ArrayList<ClsRichiestaAzioneDiContribuzione> richieste = MockLocator.getMockRCD().get(null);
            int i = 0;
            for(ClsRichiestaAzioneDiContribuzione r:richieste){
                if(r.getDatiNodo().getIdContest() == input){
                    println(i + " - n. " + r.getId());
                    i++;
                }
            }
            input = in.nextLine();
            if(input != null){
                println("richiesta n. " + input);
                ClsRichiestaAzioneDiContribuzione richiesta = richieste.get(Integer.parseInt(input));
                richiesta.getDatiNodo().visualizzaNodo();
                println("Validare?Y/N");
                input = in.nextLine();
                if(input == "Y")
                    user.validaContenutoContest(richiesta, true);
                else
                    user.validaContenutoContest(richiesta, true);
            }
            else println("Errore.");
        }
        else println("Errore.");
    }
}