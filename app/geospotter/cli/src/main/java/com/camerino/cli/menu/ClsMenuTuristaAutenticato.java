package com.camerino.cli.menu;
import com.camerino.cli.actions.ClsCommonActions;
import com.camerino.cli.loggers.ClsConsoleLogger;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.cli.mock.MockNodi;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.actions.ClsCommonActions.*;
import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuTuristaAutenticato implements IMenu {

    private ClsTuristaAutenticato user;
    Scanner in = new Scanner(System.in);
    IPersistenceModel<ClsSegnalazione> pSegnalazioni;
    IPersistenceModel<ClsRecensione> pRecensioni;
    IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pImmagini;
    public ClsMenuTuristaAutenticato(ClsTuristaAutenticato t){
        user = t;
    }
    @Override
    public void menu() {
        boolean exit = false;
        while (!exit) {
            println("1) Inserisci recensione");
            println("2) Modifica Recensione");
            println("3) Elimina Recensione");
            println("4) Inserisci Foto");
            if (user.getClass().equals(ClsTuristaAutenticato.class)) {
                println("0) Esci");
                print(">> ");
                switch (in.nextLine()) {
                    case "1" -> menuInserisciRecensione();
                    case "2" -> menuModificaRecensione();
                    case "3" -> menuEliminaRecensione();
                    case "4" -> menuInserisciFoto();
                    case "0" -> exit = true;
                }
            }
            else exit = true;
        }
    }

    public void menuInserisciRecensione() {
        ClsRecensione recensione = Input.inserisciRecensione();
        if(recensione != null) {
            recensione.setUsernameCreatore(user.getCredenziali().getUsername());
            user.inserisciRecensione(recensione);
        }
        else ClsConsoleLogger.println("Errore.");
    }

    public void menuModificaRecensione() {
        for(ClsRecensione r:user.visualizzaRecensioniPosessore()){
            println(r.visualizzaRecensione());
        }
        print("Inserisci l'id della recensione da modificare: ");
        HashMap<String, Object> filtri = new HashMap<>();
        filtri.put("id", in.nextLine());
        ClsRecensione old = MockLocator.getMockRecensioni().get(filtri).get(0);
         if(old == null){
             println("Recensione non trovata.");
             return;
          }
          ClsRecensione newrec = Input.modificaRecensione(old);
          user.modificaRecensione(old, newrec);
    }

    public void menuEliminaRecensione() {
        print("inserisci l'id della recensione da eliminare: ");
        ClsCommonActions.eliminaRecensione(user, in.nextLine());
        println("recensione eliminata.");
    }

    public void menuInserisciFoto() {
        print("inserisci l'id del contenuto a cui vuoi aggiungere una foto:");
        String contenuto = in.nextLine();
        boolean exit = false;
        while (!exit) {
            println("1) Inserisci Foto");
            println("0) Esci");
            switch (in.nextLine()) {
                case "1" -> inserisciFotoContenuto(contenuto);
                case "2" -> exit = true;
            }
        }
    }

    public void inserisciFotoContenuto(String idContenuto){
        ClsImmagine immagine = new ClsImmagine();
        immagine.setIdCOntenutoAssociato(idContenuto);
        immagine.setUsernameCreatore(user.getCredenziali().getUsername());
        println("Inserisci l'URL dell'immagine");
        immagine.setURL(in.nextLine());
        user.inserisciImmagine(immagine);
    }
}
