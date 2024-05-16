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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameCreatore() {
        return usernameCreatore;
    }

    public void setUsernameCreatore(String usernameCreatore) {
        this.usernameCreatore = usernameCreatore;
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

    public boolean isAperto() {
        return isAperto;
    }

    public void setAperto(boolean aperto) {
        isAperto = aperto;
    }

    public String visualizzaContest ()
    {

            String dummy = "";

            dummy += "\n\nID: " + this.getId() + "\n";
            dummy += "Durata: " + this.getDurata().toString() + "\n";
            dummy += "Comune: " + this.getLocation().getId() + "\n";
            dummy += "Username Creatore: " + this.getUsernameCreatore() + "\n";
            dummy += "Su invito: " + this.isAperto  + "\n";

            return dummy;

    }
}
