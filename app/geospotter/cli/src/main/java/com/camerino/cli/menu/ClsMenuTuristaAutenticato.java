package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.cli.mock.MockNodi;

import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.actions.ClsCommonActions.*;
import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
public class ClsMenuTuristaAutenticato implements IMenu {
    private ClsTuristaAutenticato user;
    Scanner in = new Scanner(System.in);

    public ClsMenuTuristaAutenticato(ClsTuristaAutenticato turistaAutenticato) {
        this.user = turistaAutenticato;
    }

    @Override
    public void menu() {
        boolean exit = false;
        user = new ClsTuristaAutenticato();
        user.setId("1");
        user.setPunteggio(100); //punteggio da non prendere seriamente
        user.setCredenziali(new Credenziali());
        user.getCredenziali().setUsername("TuristaAuth");
        user.getCredenziali().setPassword("password");

        while (!exit) {
            println("1) Inserisci recensione");
            println("2) Modifica Recensione");
            println("3) Elimina Recensione");
            println("4) Inserisci Foto");
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
    }

    private void menuInserisciRecensione() {
    }

    private void menuModificaRecensione() {
        //immagina che il turista autenticato trova l'elenco delle sue recensioni (visualizzaRecensioniPossessore)
        //poi seleziona la recensione da modificare
        print("Inserisci l'id della recensione da modificare: ");
        HashMap<String, Object> filtri = new HashMap<>();
        filtri.put("id", in.nextLine());
        /*ClsRecensione old = MockLocator.getMockRecensioni.get(filtri).get(0); Metodo non presente
         * if(old == null){
         *    println("Recensione non trovata.");
         *    return;
         * }
         * ClsRecensione new = Input.modificaRecensione(old);
         * if(new == null) return;
         * user.modificaRecensione(new.getId(), new);
         *
         * */
    }

    private void menuEliminaRecensione() {
        print("inserisci l'id della recensione da eliminare: ");
        //eliminaRecensione(user, in.nextLine()); -> aggiungere su CommonActions
        println("recensione eliminata.");
    }

    private void menuInserisciFoto() {
        print("inserisci l'id del contenuto a cui vuoi aggiungere una foto:");
        String contenuto = in.nextLine();
        boolean exit = false;
        while (!exit) {
            println("1) Inserisci Foto");
            println("0) Esci");
            switch (in.nextLine()) {
                case "1" -> menuInserisciRecensione();
                case "2" -> exit = true;
            }
        }
    }

    private void inserisciFotoContenuto(String idContenuto){
        //input della foto
        //metodo CommonActions che richiede l'inserimento delle foto
        //metodo User che richiede l'inserimento delle foto
    }
}
