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
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()) {
                case "1": menuAggiungiComune(); break;
                case "0": exit = true; break;
            }
        }
    }

    private void menuAggiungiComune(){
        ClsComune comune = Input.inserisciComune();
        comune.setUsernameCreatore("ADMIN");
        ArrayList<ClsCuratore> curatoriDisponibili = curatoriLiberi(getCuratoriLiberi());
        println("Curatori disponibili (username)");
        for(int i = 0; i<curatoriDisponibili.size(); i++){
            println(i + "> " + curatoriDisponibili.get(i).getCredenziali().getUsername());
        }
        print("Seleziona una voce dal menu > ");
        String idc = in.nextLine();
        if(comune.getCuratoriAssociati()==null){
            ArrayList<String> curatori = new ArrayList<>();
            curatori.add((curatoriDisponibili.get(Integer.parseInt(idc)).getCredenziali().getUsername()));
            comune.setCuratoriAssociati(curatori);
        }
        else
            comune.getCuratoriAssociati().add(curatoriDisponibili.get(Integer.parseInt(idc)).getCredenziali().getUsername());
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("username", curatoriDisponibili.get(Integer.parseInt(idc)).getCredenziali().getUsername());
        ClsCuratore c = (ClsCuratore)(MockLocator.getMockTuristi().get(filtro).get(0));
        c.setComuneAssociato(comune);
        user.inserisciComune(comune);
    }
    private ArrayList<ClsCuratore> getCuratoriLiberi(){
        ArrayList<ClsCuratore> curatori = new ArrayList<>();
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("ruoloUtente", ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE);
        ArrayList<ClsTuristaAutenticato> a =  MockLocator.getMockTuristi().get(filtro);
        for(ClsTuristaAutenticato t:a){
            curatori.add((ClsCuratore) t);
        }
        return curatori;
    }

    private ArrayList<ClsCuratore> curatoriLiberi(ArrayList<ClsCuratore> cur){
        for(ClsCuratore c:cur){
            if(c.getComuneAssociato() != null)
                cur.remove(c);
        }
        return cur;
    }

}
