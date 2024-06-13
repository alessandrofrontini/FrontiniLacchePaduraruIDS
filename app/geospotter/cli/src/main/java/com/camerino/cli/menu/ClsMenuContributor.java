package com.camerino.cli.menu;

import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
import static com.camerino.cli.menu.Input.checkItinerarioDuplicato;
import static com.camerino.cli.menu.Input.checkValore;

public class ClsMenuContributor implements IMenu{
    private ClsContributor user;
    Scanner in = new Scanner(System.in);
    public ClsMenuContributor(ClsContributor c){user = c;}
    private ClsMenuTuristaAutenticato menuta;
    public ClsMenuTuristaAutenticato getMenuta(){return menuta;}

    /**
     * Il metodo stampa a video le azioni effettuabili dal Turista Autenticato.
     * Siccome il menù viene utilizzato dal Turista Autenticato e da tutti i ruoli superiori, viene effettuato un controllo sull'utente:
     * se si tratta di un Turista Autenticato allora il menu si ferma qua, altrimenti proseguirà con i successivi menu.
     */
    @Override
    public void menu(){
        menuta = new ClsMenuTuristaAutenticato(user);
        boolean exit = false;
        while (!exit) {
            menuta.menu();
            println("5) Inserisci Nodo");
            println("6) Modifica Nodo");
            println("7) Elimina Nodo");
            println("8) Inserisci Itinerario");
            println("9) Modifica Itinerario");
            println("10) Elimina Itinerario");
            println("11) I miei contest");
            if ((user.getClass().equals(ClsContributor.class))||(user.getClass().equals(ClsContributorAutorizzato.class))) {
                println("0) Esci");
                print(">> ");
                switch (in.nextLine()) {
                    case "1" -> menuta.menuInserisciRecensione(); //funziona
                    case "2" -> menuta.menuModificaRecensione(); //funziona
                    case "3" -> menuta.menuEliminaRecensione(); //funziona
                    case "4" -> menuta.menuInserisciFoto(); //funziona
                    case "5" -> menuInserisciNodo(); //funziona
                    case "6" -> menuModificaNodo(); //funziona
                    case "7" -> menuEliminaNodo(); //funziona
                    case "8" -> menuInserisciItinerario(); //funziona
                    case "9" -> menuModificaItinerario(); //funziona
                    case "10" -> menuEliminaItinerario(); //funziona
                    case "11" -> sottoMenuContest(); //noimpl
                    case "0" -> exit = true;
                }
            }
            else exit = true;
        }
    }
    /**
     * Il metodo si occupa di prendere in input un nodo e di inserirlo, tramite richiesta o meno a seconda dell'utente
     */
    public void menuInserisciNodo(){
        if(user.getAllComuni() != null) {
            user.inserisciNodo(Input.inserisciNodo());
            if(user.getClass().equals(ClsContributorAutorizzato.class))
                user.setPunteggio(user.getPunteggio()+1);
        }
        else{
            println("Nella piattaforma non sono presenti Comuni. Impossibile aggiungere un nodo.");
            in.nextLine();
        }
    }

    /**
     * Il metodo inizialmente stampa tutti i nodi di proprietà dell'utente loggato, se presenti.
     * Successivamente viene chiesto di inserire l'id del nodo da inserire, e dopo un controllo sull'input vengono richieste in input
     * le modifiche da effettuare al nodo.
     * Infine il nodo viene modificato, su richiesta o meno a seconda del tipo di utente.
     */
    public void menuModificaNodo(){
        if(!user.getNodiPossessore().isEmpty()) {
            for(ClsNodo n: user.getNodiPossessore())
                println(n.visualizzaNodo());
            println("inserisci l'id del nodo da modificare");
            String input = in.nextLine();
            if (checkValore(input, (ArrayList<String>) user.getNodiPossessore().stream().map(nodo -> nodo.getId().toString()).collect(Collectors.toList()))){
                ClsNodo old = user.getNodoById(Long.parseLong(input)).get(0);
                user.modificaNodo(Long.valueOf(input), Input.modificaNodo(old));
                if(user.getClass().equals(ClsContributorAutorizzato.class))
                    user.setPunteggio(user.getPunteggio()+1);
            }
            else{
                println("Nessun Nodo Trovato");
                in.nextLine();
            }

        }
        else{
            println("Non ci sono nodi presenti nella Piattaforma.");
            in.nextLine();
        }
    }

    /**
     * Il metodo inizialmente stampa l'elenco dei nodi di cui l'utente loggato è possessore, se esistente.
     * Successivamente si chiede all'utente l'id del nodo da eliminare, e dopo aver controllato la correttezza del valore in input
     * si elimina il nodo selezionato, su richiesta o meno in base al tipo di utente.
     */
    public void menuEliminaNodo() {
        if (!user.getNodiPossessore().isEmpty()) {
            for(ClsNodo n: user.getNodiPossessore())
                println(n.visualizzaNodo());
            boolean exit = false;
            while(!exit) {
                println("inserisci l'id del nodo da eliminare");
                String input = in.nextLine();
                if (checkValore(input, (ArrayList<String>) user.getNodiPossessore().stream().map(nodo -> nodo.getId().toString()).collect(Collectors.toList()))) {
                    if (user.eliminaNodo(Long.parseLong(input))) {
                        println("Richiesta di eliminazione inviata.");
                        if(user.getClass().equals(ClsContributorAutorizzato.class))
                            user.setPunteggio(user.getPunteggio()+1);
                        exit = true;
                    }
                } else{
                    println("Nodo non presente. Riprova");
                    in.nextLine();
                }
            }
        } else {
            println("Non ci sono nodi presenti nella Piattaforma");
            in.nextLine();
        }
    }

    /**
     * Il metodo inserisce l'itinerario creato in input dall'utente, su richiesta o meno in base al tipo di utente.
     */

    public void menuInserisciItinerario(){
        if(user.getAllNodi().size()>=2) {
            ClsItinerario itinerario = Input.richiediItinerario();
            if (user.getClass() != ClsContributor.class)
                itinerario.setIdCreatore(user.getId());
            user.inserisciItinerario(itinerario);
            if(user.getClass().equals(ClsContributorAutorizzato.class))
                user.setPunteggio(user.getPunteggio()+1);
        } else {
            println("Nella piattaforma non sono presenti abbastanza nodi. Impossibile aggiungere un itinerario.");
            in.nextLine();
        }
    }

    /**
     * Il metodo inizialmente stampa a video gli itinerari di cui l'utente loggato è possessore, se esistono.
     * Successivamente si richiedono in input le modifiche e il nuovo itinerario viene salvato, tramite richiesta o mano in base
     * al tipo di utente.
     */
    public void menuModificaItinerario(){
        for(ClsItinerario itinerario:user.getItinerariPossessore())
            println(itinerario.visualizzaItinerario());
        boolean exit = false;
        while(!exit) {
            println("inserisci l'id dell'itinerario");
            String input = in.nextLine();
            if (checkValore(input, (ArrayList<String>) user.getItinerariPossessore().stream().map(itinerario -> itinerario.getId().toString()).collect(Collectors.toList()))) {
                ClsItinerario itinerario = user.getItinerarioById(Long.parseLong(input)).get(0);
                if (itinerario != null) {
                    ClsItinerario nuovo = sottomenuModificaItinerario(itinerario);
                    user.modificaItinerario(nuovo, itinerario.getId());
                    if(user.getClass().equals(ClsContributorAutorizzato.class))
                        user.setPunteggio(user.getPunteggio()+1);
                    println("Richiesta di modifica effettuata correttamente.");
                    in.nextLine();
                    exit = true;
                } else{
                    println("Errore nella richiesta. Riprova.");
                    in.nextLine();
                }
            } else {
                println("Itinerario non presente. Riprova");
                in.nextLine();
            }
        }
    }

    /**
     * Il metodo fornisce un sottomenu per la modifica degli itinerari.
     * Di un itinerario è possibile modificare:
     * - il nome
     * - le tappe: è possibile aggiungerne e toglierne
     * - l'ordinamento: l'itinerario può passare da ordinato a non ordinato
     * Non è possibile modificare un itinerario per renderlo uguale ad uno già esistente, pertanto in caso questa situazione si verificasse
     * l'utente viene invitato a modificare nuovamente l'itinerario e le attuali modifiche non vengono salvate.
     * @param itv l'itinerario da modificare, "itv" sta per Itinerario Vecchio
     * @return l'itinerario modificato
     */
    private ClsItinerario sottomenuModificaItinerario(ClsItinerario itv){
        boolean fine = false;
            ClsItinerario nuovo = new ClsItinerario();
            while(!fine) {
            nuovo.setId(itv.getId());
            nuovo.setNome(itv.getNome());
            nuovo.setIdCreatore(itv.getIdCreatore());
            nuovo.setOrdinato(itv.isOrdinato());
            ArrayList<ClsNodo> tappe = new ArrayList<>();
            tappe.addAll(itv.getTappe());
            String input;
            nuovo.setTappe(tappe);
            boolean exit = false;
            boolean exitSezione = false;
            while (!exit) {
                println("1 - aggiungi una tappa");
                println("2 - rimuovi una tappa");
                println("3 - attiva/disattiva l'ordinamento");
                println("0 - Salva ed esci dal menu");
                switch (in.nextLine()) {
                    case "1": {
                        while (!exitSezione) {
                            print("inserisci l'id della nuova tappa");
                            input = in.nextLine();
                            if (checkValore(input, (ArrayList<String>) MockLocator.getMockNodi().get(null).stream().map(nodo -> nodo.getId().toString()).collect(Collectors.toList()))) {
                                if (controllaTappaDuplicataItinerario(nuovo, Long.valueOf(input))) {
                                    nuovo.getTappe().add(user.getNodoById(Long.valueOf(input)).get(0));
                                    exitSezione = true;
                                    break;
                                } else {
                                    println("Errore.");
                                    break;
                                }
                            } else {
                                println("Nodo non esistente. Riprova");
                                in.nextLine();
                            }
                        }
                        break;
                    }
                    case "2": {
                        for (ClsNodo nodo : nuovo.getTappe()) {
                            println(nodo.visualizzaNodo());
                        }
                        while (!exitSezione) {
                            print("Inserisci l'id della tappa da eliminare");
                            String idTappa = in.nextLine();
                            if (!controllaTappaDuplicataItinerario(nuovo, Long.valueOf(idTappa))) {
                                nuovo.getTappe().remove(user.getNodoById(Long.valueOf(idTappa)).get(0));
                                exitSezione = true;
                            } else {
                                println("Errore.");
                            }
                        }
                        break;
                    }
                    case "3": {
                        print("Attualmente l'itinerario è: ");
                        if (nuovo.isOrdinato())
                            print("Ordinato.");
                        else print("Non ordinato.");
                        println("Vuoi modificare l'ordinamento? Y/N");
                        String esito = in.nextLine();
                        if (Objects.equals(esito, "Y") || (Objects.equals(esito, "y"))) {
                            nuovo.setOrdinato(!nuovo.isOrdinato());
                            println("L'ordinamento è stato cambiato");
                        } else println("non sono state apportate modifiche.");
                        break;
                    }
                    case "0": {
                        exit = true; break;
                    }
                }
            }
            if (checkItinerarioDuplicato(nuovo)) {
                fine = true;
            }
            else{
                println("Itinerario già esistente. Riprova");
                in.nextLine();
            }
        }
        return nuovo;
    }

    /**
     * Il metodo controlla che una tappa non sia già presente in un itinerario.
     * @param itinerario l'itinerario su cui effettuare il controllo
     * @param idTappa la tappa da controllare
     * @return true se la tappa non viene trovata, false se l'itinerario contiene già la tappa
     */
    private boolean controllaTappaDuplicataItinerario(ClsItinerario itinerario, Long idTappa){
        for(ClsNodo nodo:itinerario.getTappe()){
            if(Objects.equals(nodo.getId(), idTappa))
                return false;
        }
        return true;
    }

    /**
     * Il metodo si occupa di eliminare un itineraio, tramite richiesta o meno in base al tipo di utente.
     * Inizialmente si chiede all'utente di inserire l'ID dell'itinerario poi, dopo un controllo sull'input, viene effettuata l'eliminazione.
     */
    public void menuEliminaItinerario(){
        if(user.getItinerariPossessore()!=null){
            for(ClsItinerario i:user.getItinerariPossessore()){
                println(i.visualizzaItinerario());
            }
        }
        boolean exit = false;
        while(!exit) {
            println("inserisci l'id dell'itinerario");
            String input = in.nextLine();
            if (checkValore(input, (ArrayList<String>) user.getItinerariPossessore().stream().map(itinerario -> itinerario.getId().toString()).collect(Collectors.toList()))) {
                HashMap<String, Object> tmp = new HashMap<>();
                tmp.put("id", input);
                ClsItinerario itinerario = MockLocator.getMockItinerari().get(tmp).get(0);
                if (itinerario != null) {
                    user.eliminaItinerario(itinerario.getId());
                    if(user.getClass().equals(ClsContributorAutorizzato.class))
                        user.setPunteggio(user.getPunteggio()+1);
                    println("Richiesta di eliminazione effettuata.");
                    exit = true;
                } else println("Errore. Riprova");
                in.nextLine();
            }
        }
    }

    /**
     * Il metodo fornisce un sottomenu per la gestione dei contest. Tuttavia i contest sono una funzionalità presente su Geospotter Desktop.
     */
    public void sottoMenuContest(){
        println("inserisci l'id del contest");
        boolean exit = false;
        while (!exit) {
            println("1) Inserisci Nodo al contest");
            println("2) Inserisci Foto al contest");
            println("3) Contest aperti");
            println("0) Esci");
            print(">> ");
            switch (in.nextLine()) {
                case "1" :
                case "2" :
                case "3" : contest(); break;
                case "0" : exit = true;
            }
        }
    }

    private void contest(){
        println("Questa è una funzionalità di Geospotter Desktop.");
    }

}