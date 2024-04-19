package com.camerino.cli.menu;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;

import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.cli.mock.MockNodi;

import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.actions.ClsCommonActions.*;
import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;

public class ClsMenuContributorAuth implements IMenu{
    private ClsContributorAutorizzato user;
    Scanner in = new Scanner(System.in);
    public ClsMenuContributorAuth(ClsContributorAutorizzato user) {
        this.user = user;
    }
    @Override
    public void menu() {
        boolean exit = false;
        user = new ClsContributorAutorizzato();
        user.setId("1");
        user.setPunteggio(666);
        user.setCredenziali(new Credenziali());
        user.getCredenziali().setUsername("contributor autorizzato");
        user.getCredenziali().setPassword("password");
        while (!exit) {
            println("1) Aggiungi Nodo");
            println("2) Modifica Nodo");
            println("3) Elimina Nodo");
            println("4) Inserisci Itinerario");
            println("5) Modifica Itinerario");
            println("6) Modifica Itinerario");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()){//Gli altri case sono placeholder
                case "1" -> aggiungiNodo(user);
                case "2" -> menuModificaNodo();
                case "3" -> menuEliminaNodo();
                case "4" -> aggiungiItinerario(user);
                case "5" -> aggiungiNodo(user);
                case "6" -> aggiungiNodo(user);
                case "0" -> exit = true;
            }
        }
    }

    private void menuModificaNodo() {
        print("Inserisci id del nodo da modificare: ");
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("id", in.nextLine());
        //TODO: aggiungere get nodo a user o usare le mock?
        ClsNodo old = MockNodi.getInstance().get(tmp)[0];
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
        eliminaNodo(user, in.nextLine());
        println("Nodo eliminato");
    }

}
