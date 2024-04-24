package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.cli.mock.MockNodi;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.actions.ClsCommonActions.*;
        import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuCuratore implements IMenu{
    IPersistenceModel<ClsRecensione> r;
    IPersistenceModel<ClsSegnalazione> s;
    IPersistenceModel<ClsImmagine> i;
    IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRCDNodo;
    IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRCDItinerari;
    IPersistenceModel<ClsNodo> nodi;
    IPersistenceModel<ClsItinerario> itinerari;
    IPersistenceModel<ClsContestDiContribuzione> contest;
    ClsComune c;
    IPersistenceModel<ILoggedUserAction>utenti;
    private ClsCuratore user;
    private ClsComune comune;
    Scanner in = new Scanner(System.in);
    public ClsMenuCuratore (ClsCuratore curatore){ user = curatore;}
    @Override
    public void menu() {
        //TODO: implementare
        boolean exit = false;
        user = new ClsCuratore(r,s,i,pRCDNodo,pRCDItinerari,nodi,itinerari,contest, comune, utenti);
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
        }
        //TODO: continuare (front)
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
            if(pRCDNodo.get(filtro).isEmpty()){
                if(pRCDItinerari.get(filtro).isEmpty()){
                    println("Errore");
                    return;
                }
                else {
                    ClsRichiestaAzioneDiContribuzioneItinerario rit = pRCDItinerari.get(filtro).get(0);
                    println("Tipo richiesta -> " + rit.geteAzioniDiContribuzione());
                    println("Tappe itinerario:");
                    for(ClsNodo n: rit.getDatiItinerario().getTappe()){
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
                ClsRichiestaAzioneDiContribuzione r = pRCDNodo.get(filtro).get(0);
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
        for(ClsSegnalazione seg:s.get(null)){
            if(seg.getIdCuratore() == 0){
                println("Segnalazione n. " + seg.getId());
            }
        }
        println("Inserisci l'ID della segnalazione:");
        String ids = in.nextLine();
        if(ids != null){
            HashMap<String, Object> filtro = new HashMap<>();
            filtro.put("id", ids);
            if(!s.get(filtro).isEmpty()){
                for(ClsSegnalazione segnalazione:s.get(filtro)){
                    println("Descrizione -> " + segnalazione.getDescrizione());
                }
                println("Validare? Y/N");
                if(in.nextLine() == "Y")
                    user.validaSegnalazione(s.get(filtro).get(0), true);
                else
                    user.validaSegnalazione(s.get(filtro).get(0), false);
            }
        }
    }
    private void menuUprank(){

    }
    private void menuDownRank(){

    }
    private void menuResetRank(){

    }
    private void menuRegistraUtente(){

    }
    private void menuModificaUtente(){

    }
    private void menuEliminaUtente(){

    }
    private void menuPubblicaSocial(){

    }
}