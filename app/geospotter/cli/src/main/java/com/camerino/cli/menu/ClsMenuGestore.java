package com.camerino.cli.menu;

import com.camerino.cli.actions.ClsCommonActions;
import com.camerino.cli.mock.MockComuni;
import com.camerino.cli.mock.MockLocator;
import com.camerino.cli.mock.MockTuristi;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;

public class ClsMenuGestore implements IMenu {
    ClsGestoreDellaPiattaforma user;
    Scanner in = new Scanner(System.in);

    public ClsMenuGestore (ClsGestoreDellaPiattaforma gdp)
    {
        this.user = gdp;
        gdp.setMockComuni(MockLocator.getMockComuni());
    }

    @Override
    public void menu() {//TODO: implementare
        boolean exit = false;

        while (!exit)
        {
            println("1) Aggiungi Comune");
            println("2) Modifica Comune");
            println("3) Elimina Comune");
            println("4) Inserisci Itinerario");
            println("5) Modifica Itinerario");
            println("6) Modifica Itinerario");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()) {
                case "1": menuAggiungiComune();
                case "0": exit = true;
            }
        }
    }

    private void menuAggiungiComune(){
        ClsComune comune = Input.inserisciComune();
        comune.setUsernameCreatore("ADMIN");
        ArrayList<String> curatoriDisponibili = getCuratoriLiberi();
        println("Curatori disponibili (id)");
        for(int i = 0; i<curatoriDisponibili.size(); i++){
            println(i + "> Curatore " + curatoriDisponibili.get(i));
        }
        print("Seleziona una voce dal menu > ");
        String idc = in.nextLine();
        ArrayList<String> curatoriass = new ArrayList<>();
        curatoriass.add(idc);
        comune.setCuratoriAssociati(curatoriass);
        MockLocator.getMockComuni().insert(comune);
    }
    private ArrayList<String> getCuratoriLiberi(){
        ArrayList<String> curatori = new ArrayList<>();
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("ruoloUtente", ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE);
        ArrayList<ClsTuristaAutenticato> a = MockLocator.getMockTuristi().get(filtro);
        for(ClsTuristaAutenticato t:a){
            curatori.add(t.getId());
        }
        return curatori;
    }
}
