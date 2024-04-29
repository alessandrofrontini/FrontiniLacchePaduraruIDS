package com.camerino.ids.core.data.segnalazioni;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * TODO: commentare
 */
@Entity
public class ClsSegnalazione {
    @Id
    long id;
    long idContenuto;
    long idCuratore;
    String descrizione;

    //region Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdContenuto() {
        return idContenuto;
    }

    public void setIdContenuto(long idContenuto) {
        this.idContenuto = idContenuto;
    }

    public long getIdCuratore() {
        return idCuratore;
    }

    public void setIdCuratore(long idCuratore) {
        this.idCuratore = idCuratore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    //endregion
}
