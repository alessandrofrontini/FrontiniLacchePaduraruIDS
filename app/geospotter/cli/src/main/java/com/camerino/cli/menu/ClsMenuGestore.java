package com.camerino.cli.menu;

import com.camerino.cli.actions.ClsCommonActions;
import com.camerino.cli.mock.MockComuni;
import com.camerino.cli.mock.MockLocator;
import com.camerino.cli.mock.MockTuristi;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
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
    public void menu() {
        ClsMenuCuratore menuc = new ClsMenuCuratore(user);
        boolean exit = false;
        while (!exit)
        {
            menuc.menu();
            println("24) Aggiungi Comune");
            println("25) Modifica Comune");
            println("26) Elimina Comune");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()) {
                case "24": menuAggiungiComune(); break;
                case "25": menuModificaComune(); break;
                case "26": menuEliminaComune(); break;
                case "0": exit = true; break;
            }
        }
    }

    private void menuAggiungiComune(){
        ClsComune comune = Input.inserisciComune(false, null);
        comune.setUsernameCreatore("ADMIN");
        ArrayList<ClsCuratore> curatoriDisponibili = curatoriLiberi(getCuratori());
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

    private void menuModificaComune(){
        for(ClsComune c:user.visualizzaComuni()){
            println("Comune (" + c.getId() + "), nome: " + c.getNome());
        }
        println("Seleziona un id tra quelli sopra");
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", in.nextLine());
        println("1> Modifica le informazioni del comune\n2> Aggiungi un curatore al comune\n3> rimuovi un curatore al comune");
        switch (in.nextLine()){
            case "1": {
                Input.inserisciComune(true, MockLocator.getMockComuni().get(filtro).get(0));
            } break;
            case "2":{
                for(ClsCuratore c:curatoriLiberi(getCuratori())){
                    println("> " + c.getCredenziali().getUsername());
                }
                println("Inserisci l'username del nuovo curatore");
                HashMap<String, Object> fuser = new HashMap<>();
                fuser.put("username", in.nextLine());
                ClsCuratore c = (ClsCuratore) MockLocator.getMockTuristi().get(fuser).get(0);
                MockLocator.getMockComuni().get(filtro).get(0).getCuratoriAssociati().add(c.getCredenziali().getUsername());
                c.setComuneAssociato(MockLocator.getMockComuni().get(filtro).get(0));
            }

        }
    }

    private void menuEliminaComune(){
        for(ClsComune c:user.visualizzaComuni()){
            println(c.getId() + "> " + c.getNome());
        }
        println("Seleziona l'id del comune da eliminare");
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", in.nextLine());
        ClsComune comune = MockLocator.getMockComuni().get(filtro).get(0);
        //TESTARE E IN CASO RISCRIVERE
        for(ClsItinerario i:MockLocator.getMockItinerari().get(null)){
            for(ClsNodo n:i.getTappe()) {
                if (Objects.equals(n.getId(), comune.getId())) {
                    i.rimuoviTappa(n);
                }
            }
        }
        ArrayList<String> idNodiCancellare = new ArrayList<>();
        for(ClsNodo n:MockLocator.getMockNodi().get(null)){
            if(Objects.equals(n.getIdComune(), comune.getId())){
                for(ClsImmagine immagine:MockLocator.getMockImmagini().get(null)){
                    if(Objects.equals(immagine.getIdCOntenutoAssociato(), n.getId())){
                        HashMap<String, Object> filtrotmp = new HashMap<>();
                        filtrotmp.put("id", immagine.getId());
                        MockLocator.getMockImmagini().delete(filtrotmp);
                    }
                }
                for(ClsRecensione r:MockLocator.getMockRecensioni().get(null)){
                    if(Objects.equals(r.getIdContenutoAssociato(), n.getId())){
                        HashMap<String, Object> filtrotmp2 = new HashMap<>();
                        filtrotmp2 = new HashMap<>();
                        filtrotmp2.put("id", r.getId());
                        MockLocator.getMockRecensioni().delete(filtrotmp2);
                    }
                }
                idNodiCancellare.add(n.getId());
            }
        }
        for(String id:idNodiCancellare){
            HashMap<String, Object> filtronodo = new HashMap<>();
            filtronodo.put("id", id);
            MockLocator.getMockNodi().delete(filtronodo);
        }
        MockLocator.getMockComuni().delete(filtro);
    }
    private ArrayList<ClsCuratore> getCuratori(){
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
        cur.removeIf(c -> c.getComuneAssociato() != null);
        return cur;
    }

}
