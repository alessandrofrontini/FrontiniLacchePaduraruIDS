package com.camerino.cli.menu;
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
    public ClsMenuTuristaAutenticato(ClsTuristaAutenticato turistaAutenticato) {
        this.user = turistaAutenticato;
    }
    @Override
    public void menu() {
        boolean exit = false;
        user = new ClsTuristaAutenticato(pSegnalazioni, pRecensioni, pImmagini);
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

    private boolean menuInserisciRecensione() {
        ClsRecensione recensione = new ClsRecensione();
        println("Inserisci l'ID del contenuto da recensire");
        String idContenuto = in.nextLine();
        if(idContenuto == null){
            println("Errore.");
            return false;
        }
        recensione.setIdContenutoAssociato(idContenuto);
        println("Dai un punteggio da 1 a 5");
        String punteggio = "+";
        punteggio += in.nextLine();
        if(punteggio == "+"){
            println("Errore.");
            return false;
        }
        recensione.setValutazione(Double.parseDouble(punteggio));
        println("Scegli un titolo per la recensione");
        recensione.setOggetto(in.nextLine());
        println("Aggiungi una descrizione");
        recensione.setContenuto(in.nextLine());
        return pRecensioni.insert(recensione);

    }

    private void menuModificaRecensione() {
        //TODO
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
        //TODO
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
                case "1" -> inserisciFotoContenuto(contenuto);
                case "2" -> exit = true;
            }
        }
    }

    private void inserisciFotoContenuto(String idContenuto){
        ClsImmagine immagine = new ClsImmagine();
        immagine.setIdCOntenutoAssociato(idContenuto);
        immagine.setUsernameCreatore("TuristaAuth"); //TODO: cambiare quando disponibile
        println("Inserisci l'URL dell'immagine");
        immagine.setURL(in.nextLine());
        user.inserisciImmagine(immagine);
        //TODO
        //metodo CommonActions che richiede l'inserimento delle foto
    }
}
