package com.camerino.ids.core.data.utenti;

import jakarta.persistence.Entity;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Il suo ruolo è quello di aggiungere contest sulla piattaforma.
 * I contenuti del contest sono moderati da questo ruolo,
 *
 * Per diventare animatore bisogna raggiungere più di 1000 punti.
 */
@Entity
public class ClsAnimatore extends ClsContributorAutorizzato{
    IPersistenceModel<ClsContestDiContribuzione> pContest;
    IPersistenceModel<ClsPartecipazioneContestDiContribuzione> pPartecipazioni;
    public ClsAnimatore(IPersistenceModel<ClsRecensione> r, IPersistenceModel<ClsSegnalazione> s, IPersistenceModel<ClsImmagine> i, IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRCDNodo, IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRCDItinerari, IPersistenceModel<ClsNodo> nodi, IPersistenceModel<ClsItinerario> itinerari, IPersistenceModel<ClsContestDiContribuzione> contest)
    {
        super(r, s, i, pRCDNodo, pRCDItinerari, nodi, itinerari);
        pContest = contest;
    }

    public boolean creaContest(ClsContestDiContribuzione contest){
        return pContest.insert(contest);
    }

    /**
     * Il metodo prima crea un contest, successivamente ci inserisce i partecipanti; per ogni partecipante viene registrata una partecipazione.
     * @param contest il contest da creare
     * @param partecipanti i partecipanti al contest
     * @return
     */
    public boolean creaContestChiuso(ClsContestDiContribuzione contest, ArrayList<ClsContributor> partecipanti){
        if(creaContest(contest)){
            for(ClsContributor c: partecipanti){
                ClsPartecipazioneContestDiContribuzione partecipazione = new ClsPartecipazioneContestDiContribuzione();
                partecipazione.setIdContest(contest.getId());
                partecipazione.setUsernamePartecipante(c.getId());
                pPartecipazioni.insert(partecipazione);
            }
            return true;
        }
        return false;
    }

    /**
     * L'animatore accetta o respinge una richiesta di contribuzione per un contest.
     * @param richiesta la richiesta presa in esame
     * @param esito l'esito della validazione
     * @return
     */
    public boolean validaContenutoContest(ClsRichiestaAzioneDiContribuzione richiesta, boolean esito){
        if(esito){
            switch(richiesta.geteAzioneDiContribuzione()){
                case INSERISCI_NODO_CONTEST -> pNodi.insert(richiesta.getDatiNodo());
                case INSERISCI_FOTO_CONTEST -> pImmagini.insert(richiesta.getDatiImmagine());
            }
        }
        return esito;
    }
    public ArrayList<ClsContestDiContribuzione> visualizzaContestPossessore(){
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("usernameCreatore", this.credenziali.getUsername());
        return pContest.get(filtro);
    }

    public ClsAnimatore(){}
    public ClsAnimatore(ClsContributorAutorizzato usr){

        this.pRDC = usr.pRDC;
        this.pRDCI = usr.pRDCI;

        this.credenziali = usr.credenziali;
        this.id = usr.id;

        this.pNodi = usr.pNodi;
        this.pItinerari = usr.pItinerari;
        this.mockComuni = usr.mockComuni;
        this.iperRecensioni = usr.iperRecensioni;
        this.iperSegnalazioni = usr.iperSegnalazioni;
    }
}
