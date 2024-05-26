package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.*;
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
public class ClsAnimatore extends ClsContributorAutorizzato{
    IPersistenceModel<ClsContestDiContribuzione> pContest;
    IPersistenceModel<ClsPartecipazioneContestDiContribuzione> pPartecipazioni;
    public ClsAnimatore(IPersistenceModel<ClsRecensione> r, IPersistenceModel<ClsSegnalazione> s, IPersistenceModel<ClsImmagine> i, IPersistenceModel<ClsRDCImmagine> pRCDImmagini, IPersistenceModel<ClsRDCNodo> pRCDNodo, IPersistenceModel<ClsRDCItinerario> pRCDItinerari, IPersistenceModel<ClsNodo> nodi, IPersistenceModel<ClsItinerario> itinerari, IPersistenceModel<ClsContestDiContribuzione> contest)
    {
        super(r, s, i, pRCDImmagini, pRCDNodo, pRCDItinerari, nodi, itinerari);
        pContest = contest;
    }


}
