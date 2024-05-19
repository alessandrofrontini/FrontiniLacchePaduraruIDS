package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuCuratore implements IMenu{
    private ClsCuratore user;
    Scanner in = new Scanner(System.in);
    public ClsMenuCuratore(ClsCuratore c){user = c;}
    @Override
    public void menu() {
        boolean exit = false;
        ClsMenuAnimatore menuAnimatore = new ClsMenuAnimatore(user);
        while (!exit) {
            menuAnimatore.menu();
            println("15) Visualizza richieste");
            println("16) Visualizza segnalazioni");
            println("17) Uprank utente");
            println("18) Downrank utente");
            println("19) Reset Rank utente");
            println("20) Registra nuovo utente");
            println("21) Modifica utente");
            println("22) Elimina utente");
            println("23) Pubblica contenuto sui social");
            if (user.getClass().equals(ClsCuratore.class)) {
                println("0) Esci");
                print(">> ");
                switch (in.nextLine()) {
                    case "15":
                        menuVisualizzaRichieste(); break;
                    case "16":
                        menuVisualizzaSegnalazioni(); break;
                    case "17":
                        menuUprank(); break;
                    case "18":
                        menuDownRank(); break;
                    case "19":
                        menuResetRank(); break;
                    case "20":
                        menuRegistraUtente(); break;
                    case "21":
                        menuModificaUtente(); break;
                    case "22":
                        menuEliminaUtente(); break;
                    case "23":
                        menuPubblicaSocial(); break;
                    case "0":
                        exit = true; break;
                }
            }
            else exit = true;
        }

    }
    private void menuVisualizzaRichieste(){
        println("1) richieste dei nodi\n2) richieste itinerari");
        if(Objects.equals(in.nextLine(), "1")){
            ArrayList<ClsRichiestaAzioneDiContribuzione> richieste = user.getRichieste();
            if(!richieste.isEmpty()) {
                for (ClsRichiestaAzioneDiContribuzione r : richieste) {
                    println("richiesta n. " + r.getId() + ", tipologia: nodo");
                }
                sottomenuRichieste(false);
            }
        }
        else{
            ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> richieste = user.getRichiesteItinerari();
            if(!richieste.isEmpty()) {
                for (ClsRichiestaAzioneDiContribuzioneItinerario r : richieste) {
                    println("richiesta n. " + r.getId() + ", tipologia: itinerario");
                }
                sottomenuRichieste(true);
            }
        }
    }
    private void sottomenuRichieste(boolean itinerario) {
        println("seleziona l'ID della richiesta da validare");
        String idr = in.nextLine();
        if(idr != null) {
            HashMap<String, Object> filtro = new HashMap<>();
            filtro.put("id", idr);
            println("Richiesta " + idr);
            String esito;
            if(itinerario) {
                ClsRichiestaAzioneDiContribuzioneItinerario rit = MockLocator.getMockRCDI().get(filtro).get(0);
                println("Tipo richiesta -> " + rit.geteAzioniDiContribuzione());
                println("Tappe itinerario:");
                for (ClsNodo n : rit.getDatiItinerarioNuovo().getTappe()) {
                    println(n.toString());
                }
                println("Validare? Y/N");
                esito = in.nextLine();
                user.validaRichiestaItinerario(rit, (Objects.equals(esito, "Y")) || (Objects.equals(esito, "y")));
            }
            else {
                ClsRichiestaAzioneDiContribuzione r = MockLocator.getMockRCD().get(filtro).get(0);
                println("Tipo richiesta -> " + r.geteAzioneDiContribuzione());
                println("Validare? Y/N");
                esito = in.nextLine();
                user.validaRichiesta(r, (Objects.equals(esito, "Y")) || (Objects.equals(esito, "y")));
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
        filtro.put("username", in.nextLine());
        MockLocator.getMockTuristi().delete(filtro);
    }
    private void menuPubblicaSocial(){

    }
    private void stampaUtenti(){
       for(ClsTuristaAutenticato t:MockLocator.getMockTuristi().get(null)){
           println("Utente " + t.getCredenziali().getUsername());
       }
       print("Seleziona un utente");
    }
}