package com.camerino.cli.menu;

import com.camerino.cli.loggers.ClsConsoleLogger;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
import static com.camerino.cli.menu.Input.checkValore;
public class ClsMenuTuristaAutenticato implements IMenu {

    private ClsTuristaAutenticato user;
    Scanner in = new Scanner(System.in);

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
        if(!user.getAllNodi().isEmpty()) {
            ClsRecensione recensione = Input.inserisciRecensione();
            if (recensione != null) {
                recensione.setIdCreatore(user.getId());
                user.inserisciRecensione(recensione);
                user.setPunteggio(user.getPunteggio()+1);
            } else ClsConsoleLogger.println("Errore. Riprova.");
        }
        else{
            println("Nella piattaforma non sono presenti nodi. Impossibile aggiungere una recensione.");
            in.nextLine();
        }
    }

    /**
     * Il metodo inizialmente stampa a video tutte le recensioni dell'utente loggato, successivamente chiede in input la recensione da modificare
     * tramite l'ID, e dopo aver controllato la correttezza dell'ID inserito procede all'input delle modifiche alla recensione. Infine la recensione viene salvata.
     * Se l'utente non ha ancora inserito recensioni verrà stampato un messaggio e le modifiche non saranno consentite.
     */
    public void menuModificaRecensione() {
        if(!user.getRecensioniPosessore().isEmpty()) {
            for (ClsRecensione r : user.getRecensioniPosessore()) {
                println(r.visualizzaRecensione());
            }
            print("Inserisci l'id della recensione da modificare: ");
            String input = in.nextLine();
            if(checkValore(input, (ArrayList<String>) user.getRecensioniPosessore().stream().map(recensione -> recensione.getId().toString()).collect(Collectors.toList()))) {
                ClsRecensione old = user.getRecensioniPosessore().stream().filter(r -> r.getId() == Long.parseLong(input)).toList().get(0);
                if (old == null) {
                    println("Recensione non trovata.");
                    return;
                }
                ClsRecensione newrec = Input.modificaRecensione(old);
                user.modificaRecensione(old.getId(), newrec);
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
        if(!user.getRecensioniPosessore().isEmpty()) {
            for (ClsRecensione r : user.getRecensioniPosessore()) {
                println(r.visualizzaRecensione());
            }
            print("inserisci l'id della recensione da eliminare: ");
            String input = in.nextLine();
            if(checkValore(input, (ArrayList<String>) user.getRecensioniPosessore().stream().map(recensione -> recensione.getId().toString()).collect(Collectors.toList()))) {
                user.eliminaRecensione(Long.valueOf(input));
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
        if(!user.getAllNodi().isEmpty()) {
            print("inserisci l'id del nodo a cui vuoi aggiungere una foto:");
            String contenuto = in.nextLine();
            if (checkValore(contenuto, (ArrayList<String>) MockLocator.getMockNodi().get(null).stream().map(nodo -> nodo.getId().toString()).collect(Collectors.toList()))) {
                boolean exit = false;
                while (!exit) {
                    println("1) Inserisci Foto");
                    println("0) Esci");
                    switch (in.nextLine()) {
                        case "1" -> inserisciFotoContenuto(contenuto);
                        case "0" -> exit = true;
                    }
                }
            } else println("Il nodo non esiste. Riprova");
        } else {
            println("Nella piattaforma non sono presenti nodi. Impossibile aggiungere una recensione.");
            in.nextLine();
        }
    }

    /**
     * Il metodo effettua l'input di una foto, l'associazione al nodo passato in input e l'inerimento della foto, fatto su richiesta o no, in base al tipo di utente.
     * @param idContenuto
     */
    public void inserisciFotoContenuto(String idContenuto){
        if(!user.getAllNodi().isEmpty()) {
            ClsImmagine immagine = new ClsImmagine();
            immagine.setIdNodoAssociato(Long.valueOf(idContenuto));
            immagine.setIdCreatore(user.getId());
            println("Inserisci l'URL dell'immagine");
            immagine.setURL(in.nextLine());
            ClsRDCImmagine rdcImmagine = new ClsRDCImmagine(null, immagine);
            user.postRDCImmagine(rdcImmagine);
        } else {
            println("Nella piattaforma non sono presenti nodi. Impossibile aggiungere una recensione.");
            in.nextLine();
        }
    }
}
