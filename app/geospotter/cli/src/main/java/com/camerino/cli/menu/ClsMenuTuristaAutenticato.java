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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.camerino.cli.actions.ClsCommonActions.*;
import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuTuristaAutenticato implements IMenu {

    private ClsTuristaAutenticato user;
    Scanner in = new Scanner(System.in);
    IPersistenceModel<ClsSegnalazione> pSegnalazioni;
    IPersistenceModel<ClsRecensione> pRecensioni;
    @Deprecated
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
        else ClsConsoleLogger.println("Errore. Riprova.");
    }

    public void menuModificaRecensione() {
        if(user.visualizzaRecensioniPosessore() != null) {
            for (ClsRecensione r : user.visualizzaRecensioniPosessore()) {
                println(r.visualizzaRecensione());
            }
            print("Inserisci l'id della recensione da modificare: ");
            HashMap<String, Object> filtri = new HashMap<>();
            String input = in.nextLine();
            if(checkValore(input, (ArrayList<String>) user.visualizzaRecensioniPosessore().stream().map(ClsRecensione::getId).collect(Collectors.toList()))) {
                filtri.put("id", input);
                ClsRecensione old = MockLocator.getMockRecensioni().get(filtri).get(0);
                if (old == null) {
                    println("Recensione non trovata.");
                    return;
                }
                ClsRecensione newrec = Input.modificaRecensione(old);
                user.modificaRecensione(old, newrec);
            }
            else println("Recensione non esistente. Riprova.");
        }
        else println("Non hai ancora aggiunto recensioni");
    }

    public void menuEliminaRecensione() {
        if(!user.visualizzaRecensioniPosessore().isEmpty()) {
            for (ClsRecensione r : user.visualizzaRecensioniPosessore()) {
                println(r.visualizzaRecensione());
            }
            print("inserisci l'id della recensione da eliminare: ");
            String input = in.nextLine();
            if(checkValore(input, (ArrayList<String>) user.visualizzaRecensioniPosessore().stream().map(ClsRecensione::getId).collect(Collectors.toList()))) {
                ClsCommonActions.eliminaRecensione(user, input);
                println("recensione eliminata.");
            }
            else println("Recensione inesistente");
        } else println("Non hai ancora aggiunto recensioni");
    }

    public void menuInserisciFoto() {
        print("inserisci l'id del nodo a cui vuoi aggiungere una foto:");
        String contenuto = in.nextLine();
        if(checkValore(contenuto, (ArrayList<String>) MockLocator.getMockNodi().get(null).stream().map(ClsNodo::getId).collect(Collectors.toList()))) {
            boolean exit = false;
            while (!exit) {
                println("1) Inserisci Foto");
                println("0) Esci");
                switch (in.nextLine()) {
                    case "1" -> inserisciFotoContenuto(contenuto);
                    case "0" -> exit = true;
                }
            }
        }
        else println("Il nodo non esiste. Riprova");
    }

    public void inserisciFotoContenuto(String idContenuto){
        ClsImmagine immagine = new ClsImmagine();
        immagine.setIdCOntenutoAssociato(idContenuto);
        immagine.setUsernameCreatore(user.getCredenziali().getUsername());
        println("Inserisci l'URL dell'immagine");
        immagine.setURL(in.nextLine());
        user.inserisciImmagine(immagine);
    }

    private static boolean checkValore(String input, ArrayList<String> range){
        return range.contains(input);
    }
}
