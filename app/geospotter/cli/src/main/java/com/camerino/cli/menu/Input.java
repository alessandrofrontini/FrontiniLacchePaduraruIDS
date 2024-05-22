package com.camerino.cli.menu;

import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.data.utils.Posizione;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;

/**
 * Questa classe contiene i menu comuni di input dati
 */
public class Input
{
    static Scanner in = new Scanner(System.in);
    //region Input Nodi
    public static ClsNodo inserisciNodo(){
        boolean exit = false;
        boolean fine = false;
        ClsNodo nodo = new ClsNodo();
        while(!fine) {
            Posizione pos = new Posizione();
            print("Inserisci nome: ");
            nodo.setNome(in.nextLine());
            while (!exit) {
                print("Inserisci tipo:\n1)Commerciale\n2)Culturale\n3)Culinario\n>> ");
                switch (in.nextLine()) {
                    case "1":
                        nodo.setTipologiaNodo(ClsNodo.eTologiaNodo.COMMERCIALE);
                        exit = true;
                        break;
                    case "2":
                        nodo.setTipologiaNodo(ClsNodo.eTologiaNodo.CULTURALE);
                        exit = true;
                        break;
                    case "3":
                        nodo.setTipologiaNodo(ClsNodo.eTologiaNodo.CULINARIO);
                        exit = true;
                        break;
                    default:
                        println("Seleziona una voce presente nel menu.");
                        break;
                }
            }
            print("Inserisci coordinata X del nodo: ");
            pos.setX(Double.parseDouble(in.nextLine()));
            print("Inserisci coordinata Y del nodo: ");
            pos.setY(Double.parseDouble(in.nextLine()));
            nodo.setPosizione(pos);
            String input;
            while (exit) {
                print("Inserisci id del comune di appartenenza: ");
                input = in.nextLine();
                if (checkValore(input, (ArrayList<String>) MockLocator.getMockComuni().get(null).stream().map(ClsComune::getId).collect(Collectors.toList()))) {
                    nodo.setIdComune(input);
                    exit = false;
                } else println("Comune non esistente. Riprova.");
            }
            print("Il nodo ha una scadenza temporale? Y/N");
            input = in.nextLine();
            while (!exit) {
                SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
                if ((Objects.equals(input, "y")) || (Objects.equals(input, "Y"))) {
                    nodo.setaTempo(true);
                    print("Inserisci la data di scadenza in formato yyyy-MM-dd. Ad esempio il 13 luglio 2024 sarà scritto 2024-07-13");
                    try {
                        nodo.setDataFine(data.parse(in.nextLine()));
                    } catch (Exception e) {
                        println("Data scritta male. Riprova.");
                    }
                    exit = true;
                } else {
                    nodo.setaTempo(false);
                    try {
                        nodo.setDataFine(data.parse("2099-12-31"));
                        exit = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (checkNodoDuplicato(nodo))
                fine = true;
            else{
                println("Il nodo che vuoi inserire esiste già nella piattaforma. Riprova");
                in.nextLine();
            }
        }
        return nodo;
    }

    public static ClsNodo modificaNodo(ClsNodo vecchio) {
        ClsNodo nodo = new ClsNodo();
        boolean fine = false;
        while(!fine) {
            Posizione pos = vecchio.getPosizione();
            nodo.setId(vecchio.getId());
            Posizione newpos = new Posizione();
            print(String.format("Inserisci nome (old: %s): ", vecchio.getNome()));
            nodo.setNome(in.nextLine());
            boolean exit = false;
            while (!exit) {
                print(String.format("Inserisci tipo:\n1)Commerciale\n2)Culturale\n3)Culinario\n(old: %s)\n>> ", vecchio.getTipologiaNodo()));
                switch (in.nextLine()) {
                    case "1":
                        nodo.setTipologiaNodo(ClsNodo.eTologiaNodo.COMMERCIALE);
                        exit = true;
                        break;
                    case "2":
                        nodo.setTipologiaNodo(ClsNodo.eTologiaNodo.CULTURALE);
                        exit = true;
                        break;
                    case "3":
                        nodo.setTipologiaNodo(ClsNodo.eTologiaNodo.CULINARIO);
                        exit = true;
                        break;
                    default:
                        println("Seleziona una voce presente nel menu.");
                        break;
                }
            }

            print(String.format("Inserisci coordinata X del nodo (old: %s): ", pos.getX()));
            newpos.setX(Double.parseDouble(in.nextLine()));
            print(String.format("Inserisci coordinata Y del nodo (old: %s): ", pos.getY()));
            newpos.setY(Double.parseDouble(in.nextLine()));
            nodo.setPosizione(newpos);
            String input;
            while (exit) {
                print(String.format("Inserisci id del comune di appartenenza (old: %s): ", vecchio.getIdComune()));
                input = in.nextLine();
                if (checkValore(input, (ArrayList<String>) MockLocator.getMockComuni().get(null).stream().map(ClsComune::getId).collect(Collectors.toList()))) {
                    nodo.setIdComune(input);
                    exit = false;
                }
            }
            SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
            if (vecchio.isaTempo()) {
                println("Attualmente il nodo è a tempo, con scandenza il " + vecchio.getDataFine());
                println("Vuoi modificare questa impostazione? Y/N");
                input = in.nextLine();
                if ((Objects.equals(input, "y")) || (Objects.equals(input, "Y"))) {
                    println("Ora il nodo non è più a tempo.");
                    nodo.setaTempo(false);
                    try {
                        nodo.setDataFine(data.parse("2099-12-31"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    nodo.setaTempo(true);
                    nodo.setDataFine(vecchio.getDataFine());
                    println("le impostazioni di scadenza non sono state agiornate.");
                }
            } else {
                println("Il nodo non è a tempo. Vuoi fissare una scadenza? Y/N");
                input = in.nextLine();
                if ((Objects.equals(input, "y")) || (Objects.equals(input, "Y"))) {
                    while (!exit) {
                        print("Inserisci la data di scadenza in formato yyyy-MM-dd. Ad esempio il 13 luglio 2024 sarà scritto 2024-07-13 > ");
                        nodo.setaTempo(true);
                        try {
                            nodo.setDataFine(data.parse(in.nextLine()));
                        } catch (Exception e) {
                            println("Formato data non valido. Riprova");
                        }
                        println("Data impostata.");
                        exit = true;
                    }
                }
                println("La scadenza del nodo non è stata impostata");
                nodo.setaTempo(false);
                try {
                    nodo.setDataFine(data.parse("2099-12-31"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(checkNodoDuplicato(nodo))
                fine = true;
            else{
                println("Il nodo che vuoi inserire esiste già nella piattaforma. Riprova");
                in.nextLine();
            }
        }
        return nodo;
    }

    private static boolean checkNodoDuplicato(ClsNodo nodo){
        for(ClsNodo n:MockLocator.getMockNodi().get(null)){
            if((n.getPosizione().getX()==nodo.getPosizione().getY())&&(n.getPosizione().getY() == nodo.getPosizione().getY())&&(Objects.equals(n.getIdComune(), nodo.getIdComune())))
                return false;
        }
        return true;
    }
    //endregion
    //region Input Comuni
    //non considera l'associazione con il curatore(fatta dopo)
    public static ClsComune inserisciComune(boolean o, ClsComune old)
    {
        ClsComune comune = new ClsComune();
        if(o)
            comune = old;
        Posizione pos = new Posizione();
            print("Inserisci nome" + (o? " (Old: " + old.getNome() + "): ":": "));
            comune.setNome(in.nextLine());
            print("Inserisci coordinata X: " + (o? " (Old: " + old.getPosizione().getX() + "): ":": "));
            pos.setX(Double.parseDouble(in.nextLine()));
            print("Inserisci coordinata Y: "+ (o? " (Old: " + old.getPosizione() .getY()+ "): ":": "));
            pos.setY(Double.parseDouble(in.nextLine()));
            comune.setPosizione(pos);
            print("Inserisci numero abitanti: "+ (o? " (Old: " + old.getAbitanti() + "): ":": "));
            comune.setAbitanti(Integer.parseInt(in.nextLine()));
            print("Inserisci superficie: "+ (o? " (Old: " + old.getSuperficie() + "): ":": "));
            comune.setSuperficie(Double.parseDouble(in.nextLine()));
            print("Inserisci descrizione: "+ (o? " (Old: " + old.getDescrizione() + "): ":": "));
            comune.setDescrizione(in.nextLine());

        return comune;
    }
    //endregion

    public static ClsItinerario richiediItinerario(){
        boolean exit = false;
        ClsItinerario itinerario = new ClsItinerario();
        while(!exit) {
            itinerario.setTappe(new ArrayList<>());
            print("Inserisci nome itinerario: ");
            itinerario.setNome(in.nextLine());
            String idNodo = "";
            print("E' ordinato (s/n)?: ");
            itinerario.setOrdinato(in.nextLine().charAt(0) == 's');
            while (!idNodo.equals("0")) {
                print("Inserisci id del nodo da aggiungere (0 per terminare l'inserimento): ");
                idNodo = in.nextLine();
                if (idNodo.equals("0") && itinerario.getTappe().isEmpty())
                    print("Non puoi creare un itinerario con 0 tappe");
                if (idNodo.equals("0") && itinerario.getTappe().size() == 1)
                    print("Un itinerario deve avere almeno 2 tappe");
                else if (idNodo.equals("0"))
                    break;
                if (checkValore(idNodo, (ArrayList<String>) itinerario.getTappe().stream().map(ClsNodo::getId).collect(Collectors.toList()))) {
                    println("Tappa già presente. seleziona un altro nodo.");
                    in.nextLine();
                } else {
                    HashMap<String, Object> filtro = new HashMap<>();
                    filtro.put("id", idNodo);
                    itinerario.getTappe().add(MockLocator.getMockNodi().get(filtro).get(0));
                }
            }
            if(checkItinerarioDuplicato(itinerario))
                exit = true;
            else{
                println("Questo itinerario è già presente. Riprova");
                in.nextLine();
            }
        }
        return itinerario;
    }

    public static Credenziali richiediCredenziali(){
        boolean ok = false;
        Credenziali credenziali = new Credenziali();
        while (!ok){
            print("Username: ");
            credenziali.setUsername(in.nextLine());
            print("Password: ");
            credenziali.setPassword(in.nextLine());
            ok = true;
        }
        return credenziali;
    }

    public static boolean checkItinerarioDuplicato(ClsItinerario itinerario){
        for(ClsItinerario i:MockLocator.getMockItinerari().get(null)){
            if((!checkTappe(i, itinerario))&&(itinerario.isOrdinato()==i.isOrdinato()))
                return false;
        }
        return true;
    }
    private static boolean checkTappe(ClsItinerario it1, ClsItinerario it2){
        if(it1.getTappe().size() != it2.getTappe().size())
            return true;
        for(int i = 0; i<it1.getTappe().size(); i++){
            if(it1.getTappe().get(i) != it2.getTappe().get(i))
                return true;
        }
        return false;
    }

    public static ClsRecensione inserisciRecensione(){
        ClsRecensione recensione = new ClsRecensione();
        println("Inserisci l'ID del nodo da recensire");
        String input = in.nextLine();
        if(checkValore(input, (ArrayList<String>) MockLocator.getMockNodi().get(null).stream().map(ClsNodo::getId).collect(Collectors.toList()))) {
            recensione.setIdContenutoAssociato(input);
        }
        else return null;
        println("Dai un punteggio da 1 a 5");
        String punteggio = in.nextLine();
        if((Double.parseDouble(punteggio)>=1)&&(Double.parseDouble(punteggio)<=5))
            recensione.setValutazione(Double.parseDouble(punteggio));
        else return null;
        println("Scegli un titolo per la recensione");
        recensione.setOggetto(in.nextLine());
        println("Aggiungi una descrizione");
        recensione.setContenuto(in.nextLine());
        return recensione;
    }
    public static ClsRecensione modificaRecensione(ClsRecensione old){
        println("Scegli una nuova valutazione da 1 a 5");
        old.setValutazione(Double.parseDouble(in.nextLine()));
        println("Scegli una nuova descrizione:");
        old.setContenuto(in.nextLine());
        return old;
    }

    public static ClsTuristaAutenticato menuRegistraUtente(){
        ClsTuristaAutenticato utente;
            println("Inserisci il ruolo:");
            println("0 > Turista Autenticato");
            println("1 > Contributor");
            println("2 > Contributor Autorizzato");
            println("3 > Animatore");
            switch (in.nextLine()) {
                case "0":
                    utente = new ClsTuristaAutenticato(MockLocator.getMockSegnalazioni(), MockLocator.getMockRecensioni(), MockLocator.getMockRCD(), MockLocator.getMockNodi());
                    utente.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);
                    break;
                case "1":
                    utente = new ClsContributor(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari());
                    utente.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);
                    break;
                case "2":
                    utente = new ClsContributorAutorizzato(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockImmagini(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari());
                    utente.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
                    break;
                case "3":
                    utente = new ClsAnimatore(MockLocator.getMockRecensioni(), MockLocator.getMockSegnalazioni(), MockLocator.getMockImmagini(), MockLocator.getMockRCD(), MockLocator.getMockRCDI(), MockLocator.getMockNodi(), MockLocator.getMockItinerari(), MockLocator.getMockContest());
                    utente.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE);
                    break;
                default:
                    println("Scegli una voce dal menu"); in.nextLine();
                    return null;
            }
        utente.setPunteggio(utente.getRuoloUtente().getValue());
        print("Inserisci l'username > ");
        Credenziali credenziali = new Credenziali();
        credenziali.setUsername(in.nextLine());
        print("Inserisci la password > ");
        credenziali.setPassword(in.nextLine());
        utente.setCredenziali(credenziali);
        return utente;
    }

    public static void modificaUtente(String user){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("username", user);
        ClsTuristaAutenticato utente = MockLocator.getMockTuristi().get(filtro).get(0);
        println("ruolo: " + utente.getRuoloUtente() + "\nUsername: " + utente.getCredenziali().getUsername() + "\nPassword: " + utente.getCredenziali().getPassword() + "\n\nScegli un'azione:");
        boolean exit = false;
        while(!exit) {
            println("1 > Cambia username\n2 > Cambia password\n0 > Esci");
            switch (in.nextLine()) {
                case "1":
                    print("Scegli un nuovo username: ");
                    String username = in.nextLine();
                    if (controllaUsernameDuplicato(username)) utente.getCredenziali().setUsername(username);
                    else println("Username già esistente. Riprova."); break;
                case "2":
                    print("Scegli una nuova password: ");
                    utente.getCredenziali().setPassword(in.nextLine()); println("Password aggiornata."); break;
                case "0": exit = true; break;
            }
        }
    }

    private static boolean controllaUsernameDuplicato(String username){
        for(ClsTuristaAutenticato t:MockLocator.getMockTuristi().get(null)){
            if(Objects.equals(t.getCredenziali().getUsername(), username))
                return false;
        }
        return true;
    }
    public static boolean checkValore(String input, ArrayList<String> range){
        return range.contains(input);
    }
}
