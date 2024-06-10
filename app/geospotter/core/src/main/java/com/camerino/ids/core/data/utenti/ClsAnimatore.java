package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * Il suo ruolo è quello di aggiungere contest sulla piattaforma.
 * I contenuti del contest sono moderati da questo ruolo,
 * <p>
 * Per diventare animatore bisogna raggiungere più di 1000 punti.
 */
@Entity
public class ClsAnimatore extends ClsContributorAutorizzato implements IAzioniAnimatore {
    //region Constructors
    public ClsAnimatore() {
    }

    public ClsAnimatore(ClsContributorAutorizzato usr) {
        this.credenziali = usr.credenziali;
        this.id = usr.id;

        this.iperNodi = usr.iperNodi;
        this.iperItinerari = usr.iperItinerari;
        this.iperComuni = usr.iperComuni;
        this.iperRecensioni = usr.iperRecensioni;
        this.iperSegnalazioni = usr.iperSegnalazioni;

        this.iperUtenti = usr.iperUtenti;
        this.iperRDCImmagini = usr.iperRDCImmagini;
        this.iperRDCNodi = usr.iperRDCNodi;
        this.iperContest = usr.iperContest;
        this.iperRDCItinerari = usr.iperRDCItinerari;
    }
    //endregion

    public List<ClsRDCImmagine> _getAllRDCImmagini() {
        return this.iperRDCImmagini.get(null);
    }

    public boolean putRDCImmagine(ClsRDCImmagine rdci) {
        return false;
    }

    @Override
    public boolean inserisciContest(ClsContestDiContribuzione contest) {
        contest.setIdCreatore(this.id);
        contest.setAperto(true);
        this.iperContest.insert(contest);
        return true;
    }
}
