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
import static com.camerino.cli.menu.Input.checkValore;

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
    @Deprecated
    IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pImmagini;
    public ClsMenuTuristaAutenticato(ClsTuristaAutenticato t){
        user = t;
    }
    /**
     * Il metodo stampa a video le azioni effettuabili dal Turista Autenticato.
     * Siccome il menù viene utilizzato dal Turista Autenticato e da tutti i ruoli superiori, viene effettuato un controllo sull'utente:
     * se si tratta di un Turista Autenticato allora il menu si ferma qua, altrimenti proseguirà con i successivi menu.
     */
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

    /**
     * Il metodo stampa a video le azioni effettuabili dal Turista Autenticato.
     * Siccome il menù viene utilizzato dal Turista Autenticato e da tutti i ruoli superiori, viene effettuato un controllo sull'utente:
     * se si tratta di un Turista Autenticato allora il menu si ferma qua, altrimenti proseguirà con i successivi menu.
     */

    public void menuInserisciRecensione() {
        ClsRecensione recensione = Input.inserisciRecensione();
        if(recensione != null) {
            recensione.setUsernameCreatore(user.getCredenziali().getUsername());
            user.inserisciRecensione(recensione);
        }
        else ClsConsoleLogger.println("Errore. Riprova.");
    }

    /**
     * Il metodo inizialmente stampa a video tutte le recensioni dell'utente loggato, successivamente chiede in input la recensione da modificare
     * tramite l'ID, e dopo aver controllato la correttezza dell'ID inserito procede all'input delle modifiche alla recensione. Infine la recensione viene salvata.
     * Se l'utente non ha ancora inserito recensioni verrà stampato un messaggio e le modifiche non saranno consentite.
     */
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

    /**
     * Il metodo inizialmente stampa a video tutte le recensioni dell'utente loggato, successivamente chiede in input la recensione da eliminare.
     * Dopo il controllo sull'ID inserito dall'utente, il metodo elimina la recensione selezionata e comunica l'azione all'utente.
     */
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

    /**
     * Il metodo inizialmente chiede all'utente l'ID del nodo a cui inserire una foto, e dopo un controllo in input si procede con l'inserimento delle foto,
     * che può essere multiplo.
     * Se il nodo inserito non è presente nella piattaforma viene comunicato all'utente.
     */
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

    /**
     * Il metodo effettua l'input di una foto, l'associazione al nodo passato in input e l'inerimento della foto, fatto su richiesta o no, in base al tipo di utente.
     * @param idContenuto
     */
    public void inserisciFotoContenuto(String idContenuto){
        ClsImmagine immagine = new ClsImmagine();
        immagine.setIdCOntenutoAssociato(idContenuto);
        immagine.setUsernameCreatore(user.getCredenziali().getUsername());
        println("Inserisci l'URL dell'immagine");
        immagine.setURL(in.nextLine());
        user.inserisciImmagine(immagine);
    }
}
