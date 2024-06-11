package com.camerino.ids.core.data.segnalazioni;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * TODO: commentare
 */
@Entity
public class ClsSegnalazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id = 0L;
    Long idContenuto;
    String descrizione;

    //region Getters and Setters

    public Long getId() {
        return (id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdContenuto() {
        return idContenuto;
    }

    public void setIdContenuto(Long idContenuto) {
        this.idContenuto = idContenuto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


    //endregion

    public String visualizzaSegnalazione() {
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "ID Contenuto: " + this.getIdContenuto() + "\n";
        dummy += "Descrizione: " + this.getDescrizione() + "\n";

        return dummy;
    }
}
