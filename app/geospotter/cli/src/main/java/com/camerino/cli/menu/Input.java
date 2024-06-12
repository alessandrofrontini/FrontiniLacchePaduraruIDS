package com.camerino.cli.menu;

import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsAnimatore;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.data.utils.Posizione;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
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
        nodo.setIdCreatore(0L);
        while(!fine) {
            Posizione pos = new Posizione();
            print("Inserisci nome: ");
            nodo.setNome(in.nextLine());
            while (!exit) {
                print("Inserisci tipo:\n1)Commerciale\n2)Culturale\n3)Culinario\n>> ");
                switch (in.nextLine()) {
                    case "1":
                        nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.COMMERCIALE);
                        exit = true;
                        break;
                    case "2":
                        nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
                        exit = true;
                        break;
                    case "3":
                        nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULINARIO);
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
                if (checkValore(input, (ArrayList<String>) MockLocator.getMockComuni().get(null).stream().map(comune -> comune.getId().toString()).collect(Collectors.toList()))) {
                    nodo.setIdComuneAssociato(Long.valueOf(input));
                    exit = false;
                } else println("Comune non esistente. Riprova.");
            }
            print("Il nodo ha una scadenza temporale? Y/N");
            input = in.nextLine();
            while (!exit) {
                if ((Objects.equals(input, "y")) || (Objects.equals(input, "Y"))) {
                    nodo.setaTempo(true);
                    print("Inserisci la data di scadenza in formato yyyy-MM-dd. Ad esempio il 13 luglio 2024 sarà scritto 2024-07-13");
                    nodo.setDataFine(in.nextLine());
                    exit = true;
                } else {
                    nodo.setaTempo(false);
                    nodo.setDataFine("2099-12-31");
                    exit = true;
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
        nodo.setIdCreatore(vecchio.getIdCreatore());
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
                        nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.COMMERCIALE);
                        exit = true;
                        break;
                    case "2":
                        nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
                        exit = true;
                        break;
                    case "3":
                        nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULINARIO);
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
                print(String.format("Inserisci id del comune di appartenenza (old: %s): ", vecchio.getIdComuneAssociato()));
                input = in.nextLine();
                if (checkValore(input, (ArrayList<String>) MockLocator.getMockComuni().get(null).stream().map(comune -> comune.getId().toString()).collect(Collectors.toList()))) {
                    nodo.setIdComuneAssociato(Long.valueOf(input));
                    exit = false;
                }
            }
            if (vecchio.isaTempo()) {
                println("Attualmente il nodo è a tempo, con scandenza il " + vecchio.getDataFine());
                println("Vuoi modificare questa impostazione? Y/N");
                input = in.nextLine();
                if ((Objects.equals(input, "y")) || (Objects.equals(input, "Y"))) {
                    println("Ora il nodo non è più a tempo.");
                    nodo.setaTempo(false);
                    nodo.setDataFine("2099-12-31");
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
                        nodo.setDataFine(in.nextLine());
                        println("Data impostata.");
                        exit = true;
                    }
                }
                println("La scadenza del nodo non è stata impostata");
                nodo.setaTempo(false);
                nodo.setDataFine("2099-12-31");
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
            if((Objects.equals(n.getPosizione().getX(), nodo.getPosizione().getX()))&&(Objects.equals(n.getPosizione().getY(), nodo.getPosizione().getY()))&&(Objects.equals(n.getIdComuneAssociato(), nodo.getIdComuneAssociato())))
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
                if (checkValore(idNodo, (ArrayList<String>) itinerario.getTappe().stream().map(nodo -> nodo.getId().toString()).collect(Collectors.toList()))) {
                    println("Tappa già presente. seleziona un altro nodo.");
                    in.nextLine();
                } else {
                    if ((checkValore(idNodo, (ArrayList<String>) MockLocator.getMockNodi().get(null).stream().map(nodo -> nodo.getId().toString()).collect(Collectors.toList())))){
                        HashMap<String, Object> filtro = new HashMap<>();
                        filtro.put("idNodo", idNodo);
                        itinerario.getTappe().add(MockLocator.getMockNodi().get(filtro).get(0));
                    }
                    else println("Nodo inesistente. Riprova.");
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
        if(checkValore(input, (ArrayList<String>) MockLocator.getMockNodi().get(null).stream().map(nodo -> nodo.getId().toString()).collect(Collectors.toList()))) {
            recensione.setIdNodoAssociato(Long.valueOf(input));
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

    public static boolean checkValore(String input, ArrayList<String> range){
        return range.contains(input);
    }
}
