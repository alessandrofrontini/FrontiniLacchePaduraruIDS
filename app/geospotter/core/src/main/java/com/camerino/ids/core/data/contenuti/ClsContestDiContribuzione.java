package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

/**
 * Contiene i dati di un contest di contribuzione
 */
@Entity
public class ClsContestDiContribuzione {
    @Id
    @UuidGenerator
    String id;
    String usernameCreatore;
    Date durata;
    @OneToOne
    @JoinColumn(name="")//TODO finire associazione
    ClsComune location;
    boolean isAperto;
}
