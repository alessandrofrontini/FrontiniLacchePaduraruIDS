package com.camerino.cli.menu;

import com.camerino.cli.mock.MockLocator;
import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsGDP;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;

import java.util.*;
import java.util.stream.Collectors;

import static com.camerino.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.cli.loggers.ClsConsoleLogger.println;
import static com.camerino.cli.menu.Input.checkValore;

public class ClsMenuGestore implements IMenu {
    ClsGDP user;
    Scanner in = new Scanner(System.in);

    public ClsMenuGestore (ClsGDP gdp)
    {
        this.user = gdp;
        gdp.setIperComuni(MockLocator.getMockComuni());
    }
    private ClsMenuCuratore menuc;
    public ClsMenuCuratore getMenuc(){return menuc;}

    /**
     * Il metodo fornisce un menu contenente tutte le azioni effettuabili dal Gestore della Piattaforma.
     * Siccome il Gestore della Piattaforma è il ruolo più alto in Geospotter, si ha il completo accesso a tutte le funzionalità dei ruoli sottostanti
     */
    @Override
    public void menu() {
        menuc = new ClsMenuCuratore(user);
        boolean exit = false;
        while (!exit)
        {
            menuc.menu();
            println("16) Aggiungi Comune");
            println("17) Modifica Comune");
            println("18) Elimina Comune");
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
                case "13": menuc.getMenuAnimatore().menuContest(); break;
                case "14": menuc.menuVisualizzaRichieste(); break;
                case "15": menuc.menuVisualizzaSegnalazioni(); break;
                case "16": menuAggiungiComune(); break;
                case "17": menuModificaComune(); break;
                case "18": menuEliminaComune(); break;
                case "0": exit = true; break;
            }
        }
    }

    /**
     * Il metoodo richiede in input un nuovo Comune e il curatore da associare, infine effettua il salvataggio.
     */
    private void menuAggiungiComune(){
        ClsComune comune = Input.inserisciComune(false, null);
        comune.setIdCreatore(user.getId());
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
                if(comune.getCuratoriAssociati()==null){
                    List<ClsCuratore> curatori = new ArrayList<>();
                    curatori.add(curatoriDisponibili.get(Integer.parseInt(idc)));
                    comune.setCuratoriAssociati(curatori);
                } else
                    comune.getCuratoriAssociati().add(curatoriDisponibili.get(Integer.parseInt(idc)));
                ClsCuratore c = (ClsCuratore) user.getAllUtenti().stream().filter(utente -> Objects.equals(utente.getId(), comune.getCuratoriAssociati().get(comune.getCuratoriAssociati().size()-1).getId())).toList().get(0);
                user.inserisciComune(comune);
                c.setIdComuneAssociato(comune.getId());
                exit = true;
            }
            else{
                println("Utente non presente. Riprova.");
                in.nextLine();
            }
        }

    }

    /**
     * Il metodo, dopo aver stampato a video l'elenco di tutti i comuni, richiede al gestore della piattaforma l'ID del comune da modificare.
     * Dopo un controllo sull'input viene stampato un sottomenu per le modifiche effettuabili al comune.
     * Di un comune è possibile modificare:
     * - le informazioni (nome, superficie, ecc.)
     * - il numero dei curatori
     */
    private void menuModificaComune(){
        for(ClsComune c:user.getAllComuni()){
            println("Comune (" + c.getId() + "), nome: " + c.getNome());
        }
        boolean exit = false;
        while(!exit) {
            println("Seleziona un id tra quelli sopra");
            String input = in.nextLine();
             if (checkValore(input, (ArrayList<String>) MockLocator.getMockComuni().get(null).stream()
                    .map(comune -> comune.getId().toString())
                    .collect(Collectors.toList()))){
                println("1> Modifica le informazioni del comune\n2> Aggiungi un curatore al comune");
                switch (in.nextLine()) {
                    case "1": {
                        Input.inserisciComune(true, user.getComuneById(Long.parseLong(input)).get(0));
                    }
                    break;
                    case "2": {
                        for (ClsCuratore c : curatoriLiberi(getCuratori())) {
                            println("> " + c.getId());
                        }
                        boolean exit2 = false;
                        while(!exit2) {
                            println("Inserisci l'id del nuovo curatore");
                            String idCuratore = in.nextLine();
                            if (checkValore(input, (ArrayList<String>) MockLocator.getMockTuristi().get(null).stream()
                                    .map(turista -> turista.getId().toString())
                                    .collect(Collectors.toList()))) {
                                ClsCuratore c = (ClsCuratore) user.getAllUtenti().stream().filter(utente -> utente.getId() == Long.parseLong(idCuratore)).toList().get(0);
                                user.getComuneById(Long.parseLong(input)).get(0).getCuratoriAssociati().add(c);
                                c.setIdComuneAssociato(user.getComuneById(Long.parseLong(input)).get(0).getId());
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

    /**
     * Il metodo, dopo aver stampato a video l'elenco dei comuni presenti nella Piattaforma, richiede in input al Gestore l'ID del comune da eliminare.
     * Dopo un controllo sull'input si procede all'eliminazione a cascata di tutto ciò che è legato al comune, infine all'eliminazione del comune stesso.
     * Si eliminano:
     * - tutte le tappe di tutti gli itinerari rappresentate da un nodo all'interno del comune (se l'itinerario rimane con solo una tappa viene anch'esso eliminato)
     * - tutte le immagini, recensioni e segnalazioni connesse ad un nodo all'interno del comune
     * - tutte le richieste di contribuzione che trattano nodi all'interno di quel comune
     * - tutti i nodi del comune
     * - il comune stesso.
     */
    private void menuEliminaComune(){ //test
        for(ClsComune c:user.getAllComuni()){
            println(c.getId() + "> " + c.getNome());
        }
        boolean exit = false;
        while(!exit) {
            println("Seleziona l'id del comune da eliminare");
            String input = in.nextLine();
            if (checkValore(input, (ArrayList<String>) MockLocator.getMockComuni().get(null).stream().map(comune -> comune.getId().toString()).collect(Collectors.toList()))) {
                List<ClsTuristaAutenticato> utenti = user.getAllUtenti().stream().filter(utente -> utente.getRuoloUtente() == ClsTuristaAutenticato.eRUOLI_UTENTE.CURATORE).toList();
                ArrayList<ClsNodo> nodi = MockLocator.getMockNodi().get(null);
                ArrayList<ClsImmagine> immagini = MockLocator.getMockImmagini().get(null);
                ArrayList<ClsRecensione> recensioni = MockLocator.getMockRecensioni().get(null);
                ArrayList<ClsSegnalazione> segnalazioni = MockLocator.getMockSegnalazioni().get(null);
                ArrayList<ClsRDCNodo> rcd = MockLocator.getMockRCD().get(null);
                ArrayList<ClsRdcItinerario> rcdi = MockLocator.getMockRCDI().get(null);
                ArrayList<ClsRDCImmagine> rcdimmagini = MockLocator.getMockRCDImmagini().get(null);
                ArrayList<ClsItinerario> itinerari = MockLocator.getMockItinerari().get(null);
                rcd.removeIf(r -> (Objects.equals(r.getNewData().getIdComuneAssociato(), Long.parseLong(input))) || (Objects.equals(r.getOldData().getIdComuneAssociato(), Long.parseLong(input))));
                Iterator<ClsNodo> nodoIterator = nodi.iterator();
                while (nodoIterator.hasNext()) {
                    ClsNodo nodo = nodoIterator.next();
                    if (Objects.equals(nodo.getIdComuneAssociato(), Long.parseLong(input))) {
                        recensioni.removeIf(recensione -> Objects.equals(recensione.getIdNodoAssociato(), nodo.getId()));
                        segnalazioni.removeIf(segnalazione -> Objects.equals(segnalazione.getIdContenuto(), nodo.getId()));
                        immagini.removeIf(immagine -> Objects.equals(immagine.getIdNodoAssociato(), nodo.getId()));


                        Iterator<ClsRdcItinerario> rdcItinerarioIterator = rcdi.iterator();
                       while(rdcItinerarioIterator.hasNext()) {
                            ClsRdcItinerario r = rdcItinerarioIterator.next();
                            if((r.getNewData().getTappe().contains(nodo))||(r.getOldData().getTappe().contains(nodo)))
                                rdcItinerarioIterator.remove();
                        }

                        Iterator<ClsRDCImmagine> rcdimmaginiiterator= rcdimmagini.iterator();
                        while(rcdimmaginiiterator.hasNext()){
                            ClsRDCImmagine richiesta = rcdimmaginiiterator.next();
                            ClsImmagine i = richiesta.getNewData();
                            if(Objects.equals(i.getIdNodoAssociato(), nodo.getId())){
                                rcdimmaginiiterator.remove();
                            }
                        }

                        Iterator<ClsItinerario> itinerarioIterator = itinerari.iterator();
                        while (itinerarioIterator.hasNext()) {
                            ClsItinerario i = itinerarioIterator.next();
                            i.getTappe().removeIf(tappa -> Objects.equals(tappa.getId(), nodo.getId()));
                            if(i.getTappe().size()<2)
                                itinerarioIterator.remove();
                        }
                        nodoIterator.remove();

                    }
                }
                for(ClsTuristaAutenticato turista:utenti){
                    ClsCuratore c = (ClsCuratore) turista;
                    if(Objects.equals(c.getIdComuneAssociato(), Long.parseLong(input)))
                        c.setIdComuneAssociato(0L);
                }
                user.eliminaComune(Long.parseLong(input));
                 exit = true;
            }
            }
        }

    /**
     * Il metodo estrae la lista di tutti i curatori presenti nella Piattaforma.
     * @return l'insieme dei curatori
     */
    private ArrayList<ClsCuratore> getCuratori(){
        ArrayList<ClsCuratore> curatori = new ArrayList<>();
        HashMap<String, Object> filtro = new HashMap<>();
        for(ClsTuristaAutenticato t:user.getAllUtenti().stream().filter(utente -> utente.getRuoloUtente() == ClsTuristaAutenticato.eRUOLI_UTENTE.CURATORE).toList()){
            curatori.add((ClsCuratore) t);
        }
        return curatori;
    }

    /**
     * Il metodo restituisce l'elenco dei curatori liberi.
     * @param cur l'elenco di tutti i curatori presenti nella Piattaforma.
     * @return l'elenco di tutti i curatori liberi
     */
    private ArrayList<ClsCuratore> curatoriLiberi(ArrayList<ClsCuratore> cur){
        cur.removeIf(c -> c.getIdComuneAssociato() != 0);
        return cur;
    }

}
