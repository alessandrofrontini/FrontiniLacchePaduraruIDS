package com.camerino.ids.core.data.utenti;

import jakarta.persistence.Entity;

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
    }
}
