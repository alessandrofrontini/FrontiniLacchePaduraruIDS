package com.camerino.ids.core.data.segnalazioni;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * TODO: commentare
 */
@Entity
public class ClsSegnalazione {
    @Id
    String id;
    long idContenuto;
    long idCuratore;
    String descrizione;

    //region Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
