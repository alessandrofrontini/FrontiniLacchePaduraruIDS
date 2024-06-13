package com.camerino.cli.menu;

import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.*;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;

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
            println("14) Visualizza richieste");
            println("15) Visualizza segnalazioni");
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
                        menuAnimatore.menuContest(); break;
                    case "14":
                        menuVisualizzaRichieste(); break;
                    case "15":
                        menuVisualizzaSegnalazioni(); break;
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
        println("1) richieste dei nodi\n2) richieste itinerari\n3) Richieste Immagini\n0) esci");
        String input = in.nextLine();
        if(Objects.equals(input, "1")){
            List<ClsRDCNodo> richieste =user.getRDCNodoPossessoreCur().stream().filter(rdc -> rdc.getStato() == EStatusRDC.NUOVO).toList();
            if(!richieste.isEmpty()) {
                for (ClsRDCNodo r : richieste) {
                    println("richiesta n. " + r.getIdRichiesta() + ", tipologia: nodo");
                }
                sottomenuRichieste(1);
            }
        }
        else if(Objects.equals(input, "2")){
            List<ClsRdcItinerario> richieste = user.getRDCItinerarioPossessoreCur().stream().filter(rdc -> rdc.getStato() == EStatusRDC.NUOVO).toList();
            if(!richieste.isEmpty()) {
                for (ClsRdcItinerario r : richieste) {
                    println("richiesta n. " + r.getIdRichiesta() + ", tipologia: itinerario");
                }
                sottomenuRichieste(2);
            }
        }
        else{
            List<ClsRDCImmagine> richieste = user.getRDCImmaginePossessoreCur().stream().filter(rdc -> rdc.getStato() == EStatusRDC.NUOVO).toList();
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
                println("Richiesta " + idr);
                String esito;
                if (tipo==2) {
                    if (checkValore(idr, (ArrayList<String>) user._getAllRDCItinerari().stream().filter(rdc -> rdc.getStato() == EStatusRDC.NUOVO).map(rcd -> rcd.getIdRichiesta().toString()).collect(Collectors.toList()))) {
                        ClsRdcItinerario rit = user.getRDCItinerarioById(Long.parseLong(idr)).get(0);
                        println("Tipo richiesta -> " + rit.getTipo());
                        println("Tappe itinerario:");
                        ClsItinerario itnuovo;
                        if(rit.getTipo()== EAzioniDiContribuzione.ELIMINA_ITINERARIO)
                            itnuovo = rit.getOldData();
                        else itnuovo = rit.getNewData();
                        for (ClsNodo n : itnuovo.getTappe()) {
                            println("nodo n." + n.getId());
                        }
                        println("Validare? Y/N");
                        esito = in.nextLine();
                        scartaRichiesteDuplicateItinerari(rit);
                        if((Objects.equals(esito, "Y")) || (Objects.equals(esito, "y"))){
                            user.accettaRichiestaItinerario(rit.getIdRichiesta());
                            println("Richiesta validata");
                            exit = true;
                        }
                        else{
                            user.rifiutaRichiestaItinerario(rit.getIdRichiesta());
                            println("Richiesta rifiutata");
                            exit = true;
                        }

                    } else {
                        println("Richiesta non esistente. Riprova.");
                        in.nextLine();
                    }
                } else if(tipo==1) {
                    if (checkValore(idr, (ArrayList<String>) user.getAllRDCNodi().stream().filter(rdc -> rdc.getStato() == EStatusRDC.NUOVO).map(rcd -> rcd.getIdRichiesta().toString()).collect(Collectors.toList()))) {
                        ClsRDCNodo r = user.getRDCNodiById(Long.parseLong(idr)).get(0);
                        println("Tipo richiesta -> " + r.getTipo());
                        println("Validare? Y/N");
                        esito = in.nextLine();
                        scartaRichiesteDuplicate(r);
                        if((Objects.equals(esito, "Y")) || (Objects.equals(esito, "y"))){
                            user.accettaRichiestaNodo(r.getIdRichiesta());
                            println("Richiesta validata");
                            exit = true;
                        }
                        else{
                            user.rifiutaRichiestaNodo(r.getIdRichiesta());
                            println("Richiesta rifiutata");
                        }
                    }
                    else println("Richiesta non esistente. Riprova.");
                    in.nextLine();
                }
                else {
                    if (checkValore(idr, (ArrayList<String>) user.getAllRDCImmagini().stream().filter(rdc -> rdc.getStato() == EStatusRDC.NUOVO).map(rcd -> rcd.getIdRichiesta().toString()).collect(Collectors.toList()))) {
                        ClsRDCImmagine r = user.getAllRDCImmagini().stream().filter(rdc -> rdc.getIdRichiesta() == Long.parseLong(idr)).toList().get(0);
                        println("Tipo richiesta -> " + r.getTipo());
                        println("Validare? Y/N");
                        esito = in.nextLine();
                        scartaRichiesteDuplicateImmagini(r);
                        if((Objects.equals(esito, "Y")) || (Objects.equals(esito, "y"))){
                            user.accettaRichiestaImmagine(Long.parseLong(idr));
                            exit = true;
                            println("Richiesta validata");
                        }
                        else{
                            user.rifiutaRichiestaImmagine(Long.parseLong(idr));
                            exit = true;
                            println("Richiesta rifiutata");
                        }
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
            ClsNodo nodold = r.getOldData();
            if((r.getTipo() == richiesta.getTipo())&&(!Objects.equals(r.getIdRichiesta(), richiesta.getIdRichiesta()))) {
                switch (r.getTipo()) {
                    case INSERISCI_NODO:
                    case MODIFICA_NODO:{
                        ClsNodo nodorichiesta = richiesta.getNewData();
                        if(((Objects.equals(nodor.getPosizione().getX(), nodorichiesta.getPosizione().getY()))&&(Objects.equals(nodor.getPosizione().getY(), nodorichiesta.getPosizione().getY()))&&(Objects.equals(nodor.getIdComuneAssociato(), nodorichiesta.getIdComuneAssociato())))){
                            richiestaIterator.remove(); break;
                        }
                        else break;
                    }
                    case ELIMINA_NODO:{
                        ClsNodo nodorichiesta = richiesta.getOldData();
                        if(((Objects.equals(nodold.getPosizione().getX(), nodorichiesta.getPosizione().getY()))&&(Objects.equals(nodold.getPosizione().getY(), nodorichiesta.getPosizione().getY()))&&(Objects.equals(nodold.getIdComuneAssociato(), nodorichiesta.getIdComuneAssociato())))){
                            richiestaIterator.remove(); break;
                        }
                        else break;
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
    private void scartaRichiesteDuplicateItinerari(ClsRdcItinerario richiesta){
        ClsItinerario itro = richiesta.getOldData();
        ClsItinerario itrn = richiesta.getNewData();
        ArrayList<ClsRdcItinerario> rcdi = MockLocator.getMockRCDI().get(null);
        Iterator<ClsRdcItinerario> rcdIterator = rcdi.iterator();
        while (rcdIterator.hasNext()){
            ClsRdcItinerario r = rcdIterator.next();
            ClsItinerario itold = r.getOldData();
            ClsItinerario itnew = r.getNewData();
            if((r.getTipo() == richiesta.getTipo())&&(!Objects.equals(r.getIdRichiesta(), richiesta.getIdRichiesta()))){
                switch(r.getTipo()){
                    case ELIMINA_ITINERARIO:{
                        if(isUgualeItinerario(itold, itro)){
                            rcdIterator.remove(); break;
                        } else break;
                    }
                    case INSERISCI_ITINERARIO:
                    case MODIFICA_ITINERARIO:{
                        if(isUgualeItinerario(itnew, itrn)){
                            rcdIterator.remove(); break;
                        } else break;
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
        filtro.put("id", i.getIdNodoAssociato());
        while(iteratorrdc.hasNext()){
            ClsRDCImmagine r = iteratorrdc.next();
            ClsImmagine immaginer = r.getNewData();
            if((Objects.equals(i.getIdNodoAssociato(), immaginer.getIdNodoAssociato()))&&(Objects.equals(i.getURL(), immaginer.getURL()))&&(!Objects.equals(richiesta.getIdRichiesta(), r.getIdRichiesta()))){
                iteratorrdc.remove();
            }
        }
    }
    /**
     * Il metodo stampa la lista delle segnalazioni presenti nella piattaforma.
     */

    public void menuVisualizzaSegnalazioni(){
        if(user._getAllSegnalazioni()!=null){
            for(ClsSegnalazione s:user._getAllSegnalazioni()){
                println("Segnalazione " + s.getId() + "\nNodo associato: " + s.getIdContenuto() + "\nDescrizione: " + s.getDescrizione() + "\n");
            }
        }
        else println("Non ci sono segnalazioni.");
        in.nextLine();
    }

    private boolean isUgualeItinerario(ClsItinerario it1, ClsItinerario it2){
        if(it1.isOrdinato() == it2.isOrdinato()){
            for(ClsNodo n: it1.getTappe()){
                if(!it2.getTappe().contains(n))
                    return false;
            }
            return true;
        }
        return false;
    }
}