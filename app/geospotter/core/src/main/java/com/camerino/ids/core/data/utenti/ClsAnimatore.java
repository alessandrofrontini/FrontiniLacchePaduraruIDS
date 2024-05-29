package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import jakarta.persistence.Entity;

import javax.swing.plaf.InsetsUIResource;
import java.util.ArrayList;

/**
 * Il suo ruolo è quello di aggiungere contest sulla piattaforma.
 * I contenuti del contest sono moderati da questo ruolo,
 *
 * Per diventare animatore bisogna raggiungere più di 1000 punti.
 */
@Entity
public class ClsAnimatore extends ClsContributorAutorizzato{
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

        this.iperUtenti = usr.iperUtenti;
        this.iperRDCImmagini = usr.iperRDCImmagini;
        this.iperRDCNodi = usr.iperRDCNodi;
    }

    public ArrayList<ClsRDCImmagine> _getAllRDCImmagini() {
        return this.iperRDCImmagini.get(null);
    }

    public boolean putRDCImmagine(ClsRDCImmagine rdci) {
        return false;
    }
}
