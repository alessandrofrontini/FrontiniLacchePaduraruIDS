package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Contiene i dati di un contest di contribuzione
 */
@Entity
public class ClsContestDiContribuzione {
    @Id
    @GeneratedValue
    Long id;
    Long idCreatore;
    Date durata;
    @OneToOne
    @JoinColumn(name = "id")
    ClsComune location;
    Boolean isAperto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCreatore() {
        return idCreatore;
    }

    public void setIdCreatore(Long usernameCreatore) {
        this.idCreatore = usernameCreatore;
    }

    public Date getDurata() {
        return durata;
    }

    public void setDurata(Date durata) {
        this.durata = durata;
    }

    public ClsComune getLocation() {
        return location;
    }

    public void setLocation(ClsComune location) {
        this.location = location;
    }

    public Boolean isAperto() {
        return isAperto;
    }

    public void setAperto(boolean aperto) {
        isAperto = aperto;
    }

    public String visualizzaContest() {

        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "Durata: " + this.getDurata().toString() + "\n";
        dummy += "Comune: " + this.getLocation().getId() + "\n";
        dummy += "Username Creatore: " + this.getIdCreatore() + "\n";
        dummy += "Su invito: " + this.isAperto + "\n";

        return dummy;

    }
}
