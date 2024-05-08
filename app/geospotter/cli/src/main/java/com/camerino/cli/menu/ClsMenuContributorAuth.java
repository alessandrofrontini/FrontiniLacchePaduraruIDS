package com.camerino.cli.menu;

import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.cli.mock.MockNodi;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.actions.ClsCommonActions.*;
import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;

public class ClsMenuContributorAuth implements IMenu{
    private ClsContributorAutorizzato user;
    Scanner in = new Scanner(System.in);
    public ClsMenuContributorAuth(ClsContributorAutorizzato c){user = c;}
    @Override
    public void menu() {
        ClsMenuTuristaAutenticato menuta = new ClsMenuTuristaAutenticato(user);
        menuta.menu();
        boolean exit = false;
        while (!exit) {
            println("5) Aggiungi Nodo");
            println("6) Modifica Nodo");
            println("7) Elimina Nodo");
            println("8) Inserisci Itinerario");
            println("9) Modifica Itinerario");
            println("10) Modifica Itinerario");
            println("11) I miei contest");
            if (user.getClass().equals(ClsContributorAutorizzato.class)) {
                println("0) Esci");
                print(">> ");
                switch (in.nextLine()) {//Gli altri case sono placeholder
                    case "5" -> aggiungiNodo(user);
                    case "6" -> menuModificaNodo();
                    case "7" -> menuEliminaNodo();
                    case "8" -> aggiungiItinerario(user);
                    case "9" -> aggiungiNodo(user);
                    case "10" -> aggiungiNodo(user);
                    case "11" -> exit = false; //I MIEI CONTEST
                    case "0" -> exit = true;
                }
            }
        }
    }

    private void menuModificaNodo() {
        print("Inserisci id del nodo da modificare: ");
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", in.nextLine());
        //TODO: aggiungere get nodo a user o usare le mock?
        ClsNodo old = MockLocator.getMockNodi().get(null).get(0);
        if(old==null){
            println("Nessun Nodo Trovato");
            return;
        }
        ClsNodo nodo = Input.modificaNodo(old);
        if(nodo==null) return;
        user.modificaNodo(nodo.getId(), nodo);
    }

    private void menuEliminaNodo(){
        print("Inserisci id del nodo da eliminare: ");
        String idNodo = in.nextLine();
        if(idNodo != null){
            user.eliminaNodo(idNodo);
            println("Nodo eliminato");
        }
        else println("Errore");
    }

}
