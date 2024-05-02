package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuCuratore implements IMenu{
    private ClsCuratore user;
    private ClsComune comune;
    Scanner in = new Scanner(System.in);
    public ClsMenuCuratore(){}
    @Override
    public void menu() {
        boolean exit = false;
        user = new ClsCuratore(MockLocator.getMockRecensioni(),MockLocator.getMockSegnalazioni(),MockLocator.getMockImmagini(),MockLocator.getMockRCD(),MockLocator.getMockRCDI(),MockLocator.getMockNodi(),MockLocator.getMockItinerari(),MockLocator.getMockContest(), comune, MockLocator.getMockTuristi());
        user.setId("1");
        user.setPunteggio(1000); //punteggio da non prendere seriamente
        user.setCredenziali(new Credenziali());
        user.getCredenziali().setUsername("Curatore");
        user.getCredenziali().setPassword("password");

        while (!exit) {
            println("1) Visualizza richieste");
            println("2) Visualizza segnalazioni");
            println("3) Uprank utente");
            println("4) Downrank utente");
            println("5) Reset Rank utente");
            println("6) Registra nuovo utente");
            println("7) Modifica utente");
            println("8) Elimina utente");
            println("9) Pubblica contenuto sui social");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()){
                case "1": menuVisualizzaRichieste();
                case "2": menuVisualizzaSegnalazioni();
                case "3": menuUprank();
                case "4": menuDownRank();
                case "5": menuResetRank();
                case "6": menuRegistraUtente();
                case "7": menuModificaUtente();
                case "8": menuEliminaUtente();
                case "9": menuPubblicaSocial();
                case "0": exit = true;
            }
        }

    }
    private void menuVisualizzaRichieste(){
        for(ClsRichiestaAzioneDiContribuzione r:user.getRichieste()){
            println("richiesta n. " + r.getId() + ", tipologia: nodo");
        }
        for(ClsRichiestaAzioneDiContribuzioneItinerario r: user.getRichiesteItinerari()){
            println("richiesta n. " + r.getId() + ", tipologia: itinerario");
        }
        sottomenuRichieste();
    }
    private void sottomenuRichieste() {
        println("seleziona l'ID della richiesta da validare");
        String idr = in.nextLine();
        if(idr != null) {
            HashMap<String, Object> filtro = new HashMap<>();
            filtro.put("id", idr);
            println("Richiesta " + idr);
            if(MockLocator.getMockRCD().get(filtro).isEmpty()){
                if(MockLocator.getMockRCDI().get(filtro).isEmpty()){
                    println("Errore");
                    return;
                }
                else {
                    ClsRichiestaAzioneDiContribuzioneItinerario rit = MockLocator.getMockRCDI().get(filtro).get(0);
                    println("Tipo richiesta -> " + rit.geteAzioniDiContribuzione());
                    println("Tappe itinerario:");
                    for(ClsNodo n: rit.getDatiItinerarioVecchio().getTappe()){
                        println(n.toString());
                    }
                    println("Validare? Y/N");
                    if(in.nextLine() == "Y"){
                        user.validaRichiestaItinerario(rit, true);
                    }
                    else user.validaRichiestaItinerario(rit, false);
                }
            }
            else{
                ClsRichiestaAzioneDiContribuzione r = MockLocator.getMockRCD().get(filtro).get(0);
                println("Tipo richiesta -> " + r.geteAzioneDiContribuzione());
                println("Validare? Y/N");
                if(in.nextLine() == "Y"){
                    user.validaRichiesta(r, true);
                }
                else user.validaRichiesta(r, false);
            }
        }
    }

    private void menuVisualizzaSegnalazioni(){
        for(ClsSegnalazione seg:MockLocator.getMockSegnalazioni().get(null)){
            if(seg.getIdCuratore() == null){
                println("Segnalazione n. " + seg.getId());
            }
        }
        println("Inserisci l'ID della segnalazione:");
        String ids = in.nextLine();
        if(ids != null){
            HashMap<String, Object> filtro = new HashMap<>();
            filtro.put("id", ids);
            if(!MockLocator.getMockSegnalazioni().get(filtro).isEmpty()){
                for(ClsSegnalazione segnalazione:MockLocator.getMockSegnalazioni().get(filtro)){
                    println("Descrizione -> " + segnalazione.getDescrizione());
                }
                println("Validare? Y/N");
                if(in.nextLine() == "Y")
                    user.validaSegnalazione(MockLocator.getMockSegnalazioni().get(filtro).get(0), true);
                else
                    user.validaSegnalazione(MockLocator.getMockSegnalazioni().get(filtro).get(0), false);
            }
        }
    }
    private void menuUprank(){
        stampaUtenti();
        String idu = in.nextLine();
        if(idu != null){
            user.upRank(idu);
        }
        else println("Errore");
    }
    private void menuDownRank(){
        stampaUtenti();
        String idu = in.nextLine();
        if(idu != null){
            user.downRank(idu);
        }
        else println("Errore");
    }
    private void menuResetRank(){
        stampaUtenti();
        String idu = in.nextLine();
        if(idu != null){
            user.resetRank(idu);
        }
        else println("Errore");
    }
    private void menuRegistraUtente(){
        user.registraUtente(Input.registraUtente());
    }
    private void menuModificaUtente(){
        stampaUtenti();
        String idu = in.nextLine();
        Input.modificaUtente(idu);
    }
    private void menuEliminaUtente(){
        stampaUtenti();
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", in.nextLine());
        MockLocator.getMockTuristi().delete(filtro);
    }
    private void menuPubblicaSocial(){

    }
    private void stampaUtenti(){
       for(ClsTuristaAutenticato t:MockLocator.getMockTuristi().get(null)){
           println("Utente " + t.getId());
       }
       print("Seleziona un utente");
    }
}