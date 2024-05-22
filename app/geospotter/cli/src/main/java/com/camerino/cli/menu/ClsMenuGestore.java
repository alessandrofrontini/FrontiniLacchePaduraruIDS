package com.camerino.cli.menu;

import com.camerino.cli.actions.ClsCommonActions;
import com.camerino.cli.mock.MockComuni;
import com.camerino.cli.mock.MockLocator;
import com.camerino.cli.mock.MockTuristi;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
import static com.camerino.cli.menu.Input.checkValore;

public class ClsMenuGestore implements IMenu {
    ClsGestoreDellaPiattaforma user;
    Scanner in = new Scanner(System.in);

    public ClsMenuGestore (ClsGestoreDellaPiattaforma gdp)
    {
        this.user = gdp;
        gdp.setMockComuni(MockLocator.getMockComuni());
    }
    private ClsMenuCuratore menuc;
    public ClsMenuCuratore getMenuc(){return menuc;}

    @Override
    public void menu() {
        menuc = new ClsMenuCuratore(user);
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
                case "1": menuc.getMenuAnimatore().getMenuca().getMenuc().getMenuta().menuInserisciRecensione(); break;
                case "2": menuc.getMenuAnimatore().getMenuca().getMenuc().getMenuta().menuModificaRecensione(); break;
                case "3": menuc.getMenuAnimatore().getMenuca().getMenuc().getMenuta().menuEliminaRecensione(); break;
                case "4": menuc.getMenuAnimatore().getMenuca().getMenuc().getMenuta().menuInserisciFoto(); break;
                case "5": menuc.getMenuAnimatore().getMenuca().getMenuc().menuInserisciNodo(); break;
                case "6": menuc.getMenuAnimatore().getMenuca().getMenuc().menuModificaNodo(); break;
                case "7": menuc.getMenuAnimatore().getMenuca().getMenuc().menuEliminaNodo(); break;
                case "8": menuc.getMenuAnimatore().getMenuca().getMenuc().menuInserisciItinerario(); break;
                case "9": menuc.getMenuAnimatore().getMenuca().getMenuc().menuModificaItinerario(); break;
                case "10": menuc.getMenuAnimatore().getMenuca().getMenuc().menuEliminaItinerario(); break;
                case "11": menuc.getMenuAnimatore().getMenuca().getMenuc().sottoMenuContest(); break;
                case "12":
                case "13":
                case "14": menuc.getMenuAnimatore().menuContest(); break;
                case "15": menuc.menuVisualizzaRichieste(); break;
                case "16": menuc.menuVisualizzaSegnalazioni(); break;
                case "17":
                case "18":
                case "23":
                case "19": menuc.menuRank(); break;
                case "20": menuc.menuRegistraUtente(); break;
                case "21": menuc.menuModificaUtente(); break;
                case "22": menuc.menuEliminaUtente(); break;
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
        boolean exit = false;
        while(!exit) {
            print("Seleziona una voce dal menu > ");
            String idc = in.nextLine();
            if ((Integer.parseInt(idc)<curatoriDisponibili.size())&&(Integer.parseInt(idc)>=0)){
                if (comune.getCuratoriAssociati() == null) {
                    ArrayList<String> curatori = new ArrayList<>();
                    curatori.add((curatoriDisponibili.get(Integer.parseInt(idc)).getCredenziali().getUsername()));
                    comune.setCuratoriAssociati(curatori);
                } else
                    comune.getCuratoriAssociati().add(curatoriDisponibili.get(Integer.parseInt(idc)).getCredenziali().getUsername());
                HashMap<String, Object> filtro = new HashMap<>();
                filtro.put("username", curatoriDisponibili.get(Integer.parseInt(idc)).getCredenziali().getUsername());
                ClsCuratore c = (ClsCuratore)(MockLocator.getMockTuristi().get(filtro).get(0));
                c.setComuneAssociato(comune);
                user.inserisciComune(comune);
                exit = true;
            }
            else{
                println("Utente non presente. Riprova.");
                in.nextLine();
            }
        }

    }

    private void menuModificaComune(){
        for(ClsComune c:user.visualizzaComuni()){
            println("Comune (" + c.getId() + "), nome: " + c.getNome());
        }
        boolean exit = false;
        while(!exit) {
            println("Seleziona un id tra quelli sopra");
            String input = in.nextLine();
             if (checkValore(input, (ArrayList<String>) MockLocator.getMockTuristi().get(null).stream()
                    .map(turista -> turista.getCredenziali().getUsername())
                    .collect(Collectors.toList()))){
                HashMap<String, Object> filtro = new HashMap<>();
                filtro.put("id", input);
                println("1> Modifica le informazioni del comune\n2> Aggiungi un curatore al comune\n3> rimuovi un curatore al comune");
                switch (in.nextLine()) {
                    case "1": {
                        Input.inserisciComune(true, MockLocator.getMockComuni().get(filtro).get(0));
                    }
                    break;
                    case "2": {
                        for (ClsCuratore c : curatoriLiberi(getCuratori())) {
                            println("> " + c.getCredenziali().getUsername());
                        }
                        boolean exit2 = false;
                        while(!exit2) {
                            println("Inserisci l'username del nuovo curatore");
                            input = in.nextLine();
                            if (checkValore(input, (ArrayList<String>) MockLocator.getMockTuristi().get(null).stream()
                                    .map(turista -> turista.getCredenziali().getUsername())
                                    .collect(Collectors.toList()))) {
                                HashMap<String, Object> fuser = new HashMap<>();
                                fuser.put("username", input);
                                ClsCuratore c = (ClsCuratore) MockLocator.getMockTuristi().get(fuser).get(0);
                                MockLocator.getMockComuni().get(filtro).get(0).getCuratoriAssociati().add(c.getCredenziali().getUsername());
                                c.setComuneAssociato(MockLocator.getMockComuni().get(filtro).get(0));
                                exit2 = true;
                            }
                            else{
                                println("Utente non presente. Riprova.");
                                in.nextLine();
                            }
                        }
                    }

                }
                exit = true;
            }
            else{
                println("Comune non esistente. Riprova");
                in.nextLine();
            }
        }
    }

    private void menuEliminaComune(){ //test
        for(ClsComune c:user.visualizzaComuni()){
            println(c.getId() + "> " + c.getNome());
        }
        boolean exit = false;
        while(!exit) {
            println("Seleziona l'id del comune da eliminare");
            String input = in.nextLine();
            if (checkValore(input, (ArrayList<String>) MockLocator.getMockComuni().get(null).stream().map(ClsComune::getId).collect(Collectors.toList()))) {
                HashMap<String, Object> filtro = new HashMap<>();
                filtro.put("id", input);
                HashMap<String, Object> filtroc = new HashMap<>();
                filtroc.put("ruoloUtente", ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE);
                ArrayList<ClsTuristaAutenticato> utenti = MockLocator.getMockTuristi().get(filtroc);
                ArrayList<ClsNodo> nodi = MockLocator.getMockNodi().get(null);
                ArrayList<ClsImmagine> immagini = MockLocator.getMockImmagini().get(null);
                ArrayList<ClsRecensione> recensioni = MockLocator.getMockRecensioni().get(null);
                ArrayList<ClsSegnalazione> segnalazioni = MockLocator.getMockSegnalazioni().get(null);
                ArrayList<ClsRichiestaAzioneDiContribuzione> rcd = MockLocator.getMockRCD().get(null);
                ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> rcdi = MockLocator.getMockRCDI().get(null);
                ArrayList<ClsItinerario> itinerari = MockLocator.getMockItinerari().get(null);

                Iterator<ClsNodo> nodoIterator = nodi.iterator();
                while (nodoIterator.hasNext()) {
                    ClsNodo nodo = nodoIterator.next();
                    if (Objects.equals(nodo.getIdComune(), MockLocator.getMockComuni().get(filtro).get(0).getId())) {
                        recensioni.removeIf(recensione -> Objects.equals(recensione.getIdContenutoAssociato(), nodo.getId()));
                        segnalazioni.removeIf(segnalazione -> Objects.equals(segnalazione.getIdContenuto(), nodo.getId()));
                        immagini.removeIf(immagine -> Objects.equals(immagine.getIdCOntenutoAssociato(), nodo.getId()));
                        rcd.removeIf(r -> Objects.equals(r.getDatiNodo().getId(), nodo.getId()));

                        for (ClsRichiestaAzioneDiContribuzioneItinerario r : rcdi) {
                            Iterator<ClsNodo> tappaIterator = r.getDatiItinerarioNuovo().getTappe().iterator();
                            while (tappaIterator.hasNext()) {
                                ClsNodo tappa = tappaIterator.next();
                                if (Objects.equals(tappa.getId(), nodo.getId())) {
                                    tappaIterator.remove();
                                }
                            }
                        }

                        Iterator<ClsItinerario> itinerarioIterator = itinerari.iterator();
                        while (itinerarioIterator.hasNext()) {
                            ClsItinerario i = itinerarioIterator.next();
                            Iterator<ClsNodo> tappaIterator = i.getTappe().iterator();
                            while (tappaIterator.hasNext()) {
                                ClsNodo tappa = tappaIterator.next();
                                if (Objects.equals(tappa.getId(), nodo.getId())) {
                                    tappaIterator.remove();
                                }
                            }
                            if(i.getTappe().size()<2)
                                itinerarioIterator.remove();
                        }
                        nodoIterator.remove();

                    }
                }
                for(ClsTuristaAutenticato turista:utenti){
                    ClsCuratore c = (ClsCuratore) turista;
                    if(c.getComuneAssociato()==MockLocator.getMockComuni().get(filtro).get(0))
                        c.setComuneAssociato(null);
                }
                MockLocator.getMockComuni().delete(filtro);
                 exit = true;
            }
            }
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
