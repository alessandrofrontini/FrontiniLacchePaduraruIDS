package com.camerino.cli.menu;
import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.*;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
import static com.camerino.cli.menu.Input.checkValore;

public class ClsMenuCuratore implements IMenu{
    private ClsCuratore user;
    Scanner in = new Scanner(System.in);
    public ClsMenuCuratore(ClsCuratore c){user = c;}
    private ClsMenuAnimatore menuAnimatore;
    public ClsMenuAnimatore getMenuAnimatore(){return menuAnimatore;}

    /**
     * Il Curatore ha accesso a tutti i menu dei ruoli sottostanti, pertanto tutte le azioni di Turista Autenticato, Contributor, Contributor Autorizzato e Animatore sono disponibili.
     * Il metodo fornisce un menu di azioni; se la classe di riferimento è Curatore il menu termina qua, altrimenti poi continua con il menu del Gestore della Piattaforma.
     */

    @Override
    public void menu() {
        boolean exit = false;
        menuAnimatore = new ClsMenuAnimatore(user);
        while (!exit) {
            menuAnimatore.menu();
            println("15) Visualizza richieste");
            println("16) Visualizza segnalazioni");
            println("17) Uprank utente");
            println("18) Downrank utente");
            println("19) Reset Rank utente");
            println("20) Registra nuovo utente");
            println("21) Modifica utente");
            println("22) Elimina utente");
            println("23) Pubblica contenuto sui social");
            if (user.getClass().equals(ClsCuratore.class)) {
                println("0) Esci");
                print(">> ");
                switch (in.nextLine()) {
                    case "1":
                        menuAnimatore.getMenuca().getMenuc().getMenuta().menuInserisciRecensione(); break;
                    case "2":
                        menuAnimatore.getMenuca().getMenuc().getMenuta().menuModificaRecensione(); break;
                    case "3":
                        menuAnimatore.getMenuca().getMenuc().getMenuta().menuEliminaRecensione(); break;
                    case "4":
                        menuAnimatore.getMenuca().getMenuc().getMenuta().menuInserisciFoto(); break;
                    case "5":
                        menuAnimatore.getMenuca().getMenuc().menuInserisciNodo(); break;
                    case "6":
                        menuAnimatore.getMenuca().getMenuc().menuModificaNodo(); break;
                    case "7":
                        menuAnimatore.getMenuca().getMenuc().menuEliminaNodo(); break;
                    case "8":
                        menuAnimatore.getMenuca().getMenuc().menuInserisciItinerario(); break;
                    case "9":
                        menuAnimatore.getMenuca().getMenuc().menuModificaItinerario(); break;
                    case "10":
                        menuAnimatore.getMenuca().getMenuc().menuEliminaItinerario(); break;
                    case "11":
                        menuAnimatore.getMenuca().getMenuc().sottoMenuContest(); break;
                    case "12":
                    case "13":
                    case "14":
                        menuAnimatore.menuContest(); break;
                    case "15":
                        menuVisualizzaRichieste(); break;
                    case "16":
                        menuVisualizzaSegnalazioni(); break;
                    case "17":
                    case "18":
                    case "23":
                    case "19":
                        menuRank(); break;
                    case "20":
                        menuRegistraUtente(); break;
                    case "21":
                        menuModificaUtente(); break;
                    case "22":
                        menuEliminaUtente(); break;
                    case "0":
                        exit = true; break;
                }
            }
            else exit = true;
        }

    }
    /**
     * Il metodo fornisce un sottomenu per gestire le richieste relative ai nodi, alle immagini o agli itinerari.
     * L'utente, scegliendo una voce dal menu, viene indirizzato al menu corretto.
     */

    public void menuVisualizzaRichieste(){
        println("1) richieste dei nodi\n2) richieste itinerari\n3)Richieste Immagini\n0) esci");
        String input = in.nextLine();
        if(Objects.equals(input, "1")){
            ArrayList<ClsRDCNodo> richieste =user.getRichieste();
            if(!richieste.isEmpty()) {
                for (ClsRDCNodo r : richieste) {
                    println("richiesta n. " + r.getIdRichiesta() + ", tipologia: nodo");
                }
                sottomenuRichieste(1);
            }
        }
        else if(Objects.equals(input, "2")){
            ArrayList<ClsRDCItinerario> richieste = user.getRichiesteItinerari();
            if(!richieste.isEmpty()) {
                for (ClsRDCItinerario r : richieste) {
                    println("richiesta n. " + r.getIdRichiesta() + ", tipologia: itinerario");
                }
                sottomenuRichieste(2);
            }
        }
        else{
            ArrayList<ClsRDCImmagine> richieste = user.getRichiesteImmagini();
            if(!richieste.isEmpty()){
                for(ClsRDCImmagine r:richieste){
                    println("richiesta n. " + r.getIdRichiesta() + ", tipologia: immagine");
                }
            }
            sottomenuRichieste(3);
        }
    }
    /**
     * Il metodo mette a disposizione un pannello in cui l'utente, dopo aver visualizzato l'elenco delle richieste, seleziona una richiesta e sceglie se validarla o meno.
     * In caso di validazione la richiesta viene inserita, ma prima vengono automaticamente eliminate tutte le richieste duplicate, quindi tutte le richieste il cui contenuto
     * è uguale a quello della richiesta appena accettata.
     * Successivamente il contenuto associato alla richiesta viene caricato ufficialmente nella piattaforma.
     * Sia che la richiesta venga accettata, sia che la richiesta venga rifiutata, viene associato alla richiesta l'username del curatore.
     * @param tipo valore che indica quale pannello usare: 1 per i nodi, 2 per gli itinerari e 3 per le immagini.
     */
    private void sottomenuRichieste(int tipo) {
        boolean exit = false;
        while(!exit) {
            println("seleziona l'ID della richiesta da validare");
            String idr = in.nextLine();
            if (idr != null) {
                HashMap<String, Object> filtro = new HashMap<>();
                filtro.put("id", idr);
                println("Richiesta " + idr);
                String esito;
                if (tipo==2) {
                    if (checkValore(idr, (ArrayList<String>) MockLocator.getMockRCDI().get(null).stream().map(ClsRDCItinerario::getIdRichiesta).collect(Collectors.toList()))) {
                        ClsRDCItinerario rit = MockLocator.getMockRCDI().get(filtro).get(0);
                        println("Tipo richiesta -> " + rit.getTipo());
                        println("Tappe itinerario:");
                        ClsItinerario itnuovo = rit.getNewData();
                        for (ClsNodo n : itnuovo.getTappe()) {
                            println(n.toString());
                        }
                        println("Validare? Y/N");
                        esito = in.nextLine();
                        scartaRichiesteDuplicateItinerari(rit);
                        user.validaRichiestaItinerario(rit, (Objects.equals(esito, "Y")) || (Objects.equals(esito, "y")));
                        println("Richiesta validata");
                        exit = true;
                    } else {
                        println("Richiesta non esistente. Riprova.");
                        in.nextLine();
                    }
                } else if(tipo==1) {
                    if (checkValore(idr, (ArrayList<String>) MockLocator.getMockRCD().get(null).stream().map(ClsRDCNodo::getIdRichiesta).collect(Collectors.toList()))) {
                        ClsRDCNodo r = MockLocator.getMockRCD().get(filtro).get(0);
                        println("Tipo richiesta -> " + r.getTipo());
                        println("Validare? Y/N");
                        esito = in.nextLine();
                        scartaRichiesteDuplicate(r);
                        user.validaRichiesta(r, (Objects.equals(esito, "Y")) || (Objects.equals(esito, "y")));
                        println("Richiesta validata");
                        exit = true;
                    }
                    else println("Richiesta non esistente. Riprova.");
                    in.nextLine();
                }
                else {
                    if (checkValore(idr, (ArrayList<String>) MockLocator.getMockRCDImmagini().get(null).stream().map(ClsRDCImmagine::getIdRichiesta).collect(Collectors.toList()))) {
                        ClsRDCImmagine r = MockLocator.getMockRCDImmagini().get(filtro).get(0);
                        println("Tipo richiesta -> " + r.getTipo());
                        println("Validare? Y/N");
                        esito = in.nextLine();
                        scartaRichiesteDuplicateImmagini(r);
                        user.validaRichiesta(r, (Objects.equals(esito, "Y")) || (Objects.equals(esito, "y")));
                        exit = true;
                        println("Richiesta validata");
                    }
                    else println("Richiesta non esistente. Riprova.");
                    in.nextLine();
                }

            }
        }
    }

    /**
     * Il metodo elimina dalla Mock le richieste duplicate, qunidi le richieste con lo stesso contenuto, a partire da una richiesta.
     * Se la richiesta è dello stesso tipo di quella di partenza e contiene gli stessi valori, viene rimossa tramite un Iterator.
     * Le richieste devono avere ID diversi da quella passata, altrimenti verrebbe cancellata anche quest'ultima.
     * @param richiesta la richiesta appena validata, che quindi andrà inserita: ogni richiesta con lo stesso contenuto verrà eliminata.
     */
    private void scartaRichiesteDuplicate(ClsRDCNodo richiesta){
        ArrayList<ClsRDCNodo> richieste = MockLocator.getMockRCD().get(null);
        Iterator<ClsRDCNodo> richiestaIterator = richieste.iterator();
        while(richiestaIterator.hasNext()){
            ClsRDCNodo r = richiestaIterator.next();
            ClsNodo nodor = r.getNewData();
            if((r.getTipo() == richiesta.getTipo())&&(!Objects.equals(r.getIdRichiesta(), richiesta.getIdRichiesta()))) {
                switch (r.getTipo()) {
                    case INSERISCI_NODO:
                    case MODIFICA_NODO:
                    case ELIMINA_NODO:{
                        ClsNodo nodorichiesta = richiesta.getNewData();
                        if(((nodor.getPosizione().getX()==nodorichiesta.getPosizione().getY())&&(nodor.getPosizione().getY() == nodorichiesta.getPosizione().getY())&&(Objects.equals(nodor.getIdComune(), nodorichiesta.getIdComune())))){
                            richiestaIterator.remove(); break;
                        }
                    }

                }
            }
        }
    }
    /**
     * Il metodo elimina dalla Mock le richieste duplicate, qunidi le richieste con lo stesso contenuto, a partire da una richiesta.
     * Se la richiesta è dello stesso tipo di quella di partenza e contiene gli stessi valori, viene rimossa tramite un Iterator.
     * Si prende come valore da controllare l'itinerario nuovo, perchè nel caso di richieste di modifica itinerario, quello vecchio sarebbe sempre uguale.
     * Le richieste devono avere ID diversi da quella passata, altrimenti verrebbe cancellata anche quest'ultima.
     * @param richiesta la richiesta appena validata, che quindi andrà inserita: ogni richiesta con lo stesso contenuto verrà eliminata.
     */
    private void scartaRichiesteDuplicateItinerari(ClsRDCItinerario richiesta){
        ClsItinerario itro = richiesta.getOldData();
        ClsItinerario itrn = richiesta.getNewData();
        ArrayList<ClsRDCItinerario> rcdi = MockLocator.getMockRCDI().get(null);
        Iterator<ClsRDCItinerario> rcdIterator = rcdi.iterator();
        while (rcdIterator.hasNext()){
            ClsRDCItinerario r = rcdIterator.next();
            ClsItinerario itold = r.getOldData();
            ClsItinerario itnew = r.getNewData();
            if((r.getTipo() == richiesta.getTipo())&&(!Objects.equals(r.getIdRichiesta(), richiesta.getIdRichiesta()))){
                switch(r.getTipo()){
                    case INSERISCI_ITINERARIO:
                    case ELIMINA_ITINERARIO:{
                        if(itnew==itrn){
                            rcdIterator.remove(); break;
                        }
                    }
                    case MODIFICA_ITINERARIO:{
                        if((itnew==itrn)&&(itold==itro)&&(itnew.isOrdinato() == itrn.isOrdinato())){
                            rcdIterator.remove(); break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Il metodo elimina dalle Mock le richieste di contribuzione relative alle immagini duplicate, cioè tutte le richieste aventi lo stesso URL dell'immagine nello stesso nodo.
     * Tramite un iteratore si scorre la totalità delle richieste di immagini, ed escludendo la richiesta da controllare, ogni richiesta duplicata viene eliminata
     * @param richiesta la richiesta da controllare ma non eliminare
     */
    private void scartaRichiesteDuplicateImmagini(ClsRDCImmagine richiesta){
        ClsImmagine i = richiesta.getNewData();
        ArrayList<ClsRDCImmagine> richiesteImmagini = MockLocator.getMockRCDImmagini().get(null);
        Iterator<ClsRDCImmagine> iteratorrdc = richiesteImmagini.iterator();
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", i.getIdCOntenutoAssociato());
        while(iteratorrdc.hasNext()){
            ClsRDCImmagine r = iteratorrdc.next();
            ClsImmagine immaginer = r.getNewData();
            if((Objects.equals(i.getIdCOntenutoAssociato(), immaginer.getIdCOntenutoAssociato()))&&(Objects.equals(i.getURL(), immaginer.getURL()))&&(!Objects.equals(richiesta.getIdRichiesta(), r.getIdRichiesta()))){
                iteratorrdc.remove();
            }
        }
    }
    /**
     * Il metodo stampa la lista delle segnalazioni presenti nella piattaforma.
     */

    public void menuVisualizzaSegnalazioni(){
        if(user.getSegnalazioni()!=null){
            for(ClsSegnalazione s:user.getSegnalazioni()){
                if(Objects.equals(s.getIdCuratore(), "null")){
                    println("Segnalazione " + s.getId() + "\nNodo associato: " + s.getIdContenuto() + "\nDescrizione: " + s.getDescrizione() + "\n");
                }
            }
        }
        in.nextLine();
    }

    public void menuRank(){
        println("Questa è una funzionalità di Geospotter Desktop.");
    }

    /**
     * Il metodo richiede in input un utente e lo inserisce nella piattaforma.
     */
    public void menuRegistraUtente(){
        ClsTuristaAutenticato t = Input.menuRegistraUtente();
        while(t==null){
            t = Input.menuRegistraUtente();
        }
        user.registraUtente(t);
    }

    /**
     * Il metodo richiede in input l'ID dell'utente che si vuole modificare, successivamente si richiedono in input le modifiche da effettuare
     * all'utente selezionato. Infine l'utente modificato viene salvato nella piattaforma.
     */
    public void menuModificaUtente(){
        stampaUtenti();
        boolean exit = false;
        while(!exit) {
            String idu = in.nextLine();
            if (checkValore(idu, (ArrayList<String>) MockLocator.getMockTuristi().get(null).stream()
                    .map(turista -> turista.getCredenziali().getUsername())
                    .collect(Collectors.toList()))) {
                Input.modificaUtente(idu);
                exit = true;
            } else {
                println("Utente non presente. Riprova");
            }
        }

    }

    /**
     * Il metodo inizialmente stampa tutti gli utenti presenti nella piattaforma, successivamente richiede al curatore l'ID dell'utente da eliminare.
     * Infine l'utente viene eliminato dalla piattaforma, dopo un controllo sull'input.
     */

    public void menuEliminaUtente(){
        stampaUtenti();
        boolean exit = false;
        while(!exit) {
            String input = in.nextLine();
            if (checkValore(input, (ArrayList<String>) MockLocator.getMockTuristi().get(null).stream()
                    .map(turista -> turista.getCredenziali().getUsername())
                    .collect(Collectors.toList()))) {
                HashMap<String, Object> filtro = new HashMap<>();
                filtro.put("username", input);
                MockLocator.getMockTuristi().delete(filtro);
                println("Utente \"" + input + "\" eliminato correttamente");
                exit = true;
                in.nextLine();
            }
            else{
                println("Utente non presente. Riprova");
            }
        }
    }

    /**
     * Il metodo stampa a video l'elenco di tutti gli utenti presenti nella piattaforma.
     */
    private void stampaUtenti(){
       for(ClsTuristaAutenticato t:MockLocator.getMockTuristi().get(null)){
           println("Utente " + t.getCredenziali().getUsername());
       }
       print("Seleziona un utente (username)");
    }
}