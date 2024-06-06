package com.camerino.cli.menu;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsGestoreDellaPiattaforma;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.data.utils.Posizione;

import java.util.Scanner;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;

/**
 * Questa classe contiene i menu comuni di input dati
 */
public class Input {

    //region Input Nodi
    public static ClsNodo inserisciNodo() {
        boolean ok = false;
        Scanner in = new Scanner(System.in);
        ClsNodo nodo = new ClsNodo();
        Posizione pos = new Posizione();
        while (!ok) {
            print("Inserisci nome: ");
            nodo.setNome(in.nextLine());
            print("Inserisci tipo:\n 1)Commerciale\n2)Culturale\n3)Culinario\n>> ");
            switch (in.nextLine()) {
                case "1" -> nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.COMMERCIALE);
                case "2" -> nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
                case "3" -> nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULINARIO);
            }
            print("Inserisci coordinata X del nodo: ");
            pos.setX(in.nextDouble());
            print("Inserisci coordinata Y del nodo: ");
            pos.setY(in.nextDouble());
            nodo.setPosizione(pos);
            print("Inserisci id del comune di appartenenza: ");
            nodo.setIdComuneAssociato(in.nextLine());
            //TODO: aggiungere inserimento "aTempo" e durata
            //TODO: aggiungere eventuali controlli sui dati inseriti
            ok = true;
        }
        return nodo;
    }

    public static ClsNodo modificaNodo(ClsNodo nodo) {
        boolean ok = false;
        Scanner in = new Scanner(System.in);
        Posizione pos = nodo.getPosizione();
        while (!ok) {
            print(String.format("Inserisci nome (old: %s): ", nodo.getNome()));
            nodo.setNome(in.nextLine());
            print(String.format("Inserisci tipo:\n1)Commerciale\n2)Culturale\n3)Culinario\n(old: %s)\n>> ", nodo.getTipologiaNodo()));
            switch (in.nextLine()) {//TODO: nuovo "Input" per selezione tipo nodo
                case "1" -> nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.COMMERCIALE);
                case "2" -> nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
                case "3" -> nodo.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULINARIO);
            }
            print(String.format("Inserisci coordinata X del nodo (old: %s): ", pos.getX()));
            pos.setX(in.nextDouble());
            print(String.format("Inserisci coordinata Y del nodo (old: %s): ", pos.getY()));
            pos.setY(in.nextDouble());
            nodo.setPosizione(pos);
            print(String.format("Inserisci id del comune di appartenenza (old: %s): ", nodo.getIdComuneAssociato()));
            nodo.setIdComuneAssociato(in.nextLine());
            //TODO: aggiungere inserimento "aTempo" e durata
            //TODO: aggiungere eventuali controlli sui dati inseriti
            ok = true;
        }
        return nodo;
    }

    //endregion

    //region Input Comuni
    public static ClsComune inserisciComune(ClsGestoreDellaPiattaforma gdp) {
        boolean ok = false;
        Scanner in = new Scanner(System.in);
        ClsComune comune = new ClsComune();
        Posizione pos = new Posizione();
        while (!ok) {
            print("Inserisci nome: ");
            comune.setNome(in.nextLine());
            print("Inserisci coordinata X del nodo: ");
            pos.setX(in.nextDouble());
            print("Inserisci coordinata Y del nodo: ");
            pos.setY(in.nextDouble());
            comune.setPosizione(pos);
            print("Inserisci numero abitanti: ");
            comune.setAbitanti(in.nextInt());
            print("Inserisci superficie: ");
            comune.setSuperficie(in.nextDouble());
            print("Inserisci descrizione: ");
            comune.setDescrizione(in.nextLine());
            //TODO: aggiungere inserimento "aTempo" e durata
            //TODO: aggiungere eventuali controlli sui dati inseriti
            comune.setCuratoriAssociati(null);
            comune.setIdCreatore(gdp.getCredenziali().getUsername());
            ok = true;
        }
        return comune;
    }
    //endregion


    public static ClsItinerario richiediItinerario() {
        boolean ok = false;
        Scanner in = new Scanner(System.in);
        ClsItinerario itinerario = new ClsItinerario();
        Long idNodo = "0";
        while (!ok) {
            print("Inserisci nome itinerario: ");
            itinerario.setNome(in.nextLine());
            print("E' ordinato (s/n)?: ");
            itinerario.setOrdinato(in.nextLine().charAt(0) == 's');
            while (!idNodo.equals("-1")) {
                print("Inserisci id del nodo da aggiungere (-1 per terminare l'inserimento): ");
                idNodo = in.nextLine();
                if (idNodo.equals("-1") && itinerario.getTappe().size() == 0)
                    print("Non puoi creare un itinerario con 0 tappe");
                else if (idNodo.equals("-1"))
                    break;
                ClsNodo nodo = new ClsNodo();
                nodo.setId(idNodo);
                itinerario.getTappe().add(nodo);//Ci penserà il Mock a poplare le tappe
            }
            ok = true;
        }
        return itinerario;
    }

    public static Credenziali richiediCredenziali() {
        boolean ok = false;
        Scanner in = new Scanner(System.in);
        Credenziali credenziali = new Credenziali();
        while (!ok) {
            print("Username: ");
            credenziali.setUsername(in.nextLine());
            print("Password: ");
            credenziali.setPassword(in.nextLine());
            ok = true;
        }
        return credenziali;
    }
}
