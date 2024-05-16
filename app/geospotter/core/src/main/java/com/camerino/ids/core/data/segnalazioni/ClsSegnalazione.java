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

    public String getIdContenuto() {
        return idContenuto;
    }

    public void setIdContenuto(String idContenuto) {
        this.idContenuto = idContenuto;
    }

    public String getIdCuratore() {
        return idCuratore;
    }

    public void setIdCuratore(String idCuratore) {
        this.idCuratore = idCuratore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


    //endregion

    public String visualizzaSegnalazione()
    {
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "ID Contenuto: " + this.getIdContenuto() + "\n";
        dummy += "ID Curatore: " + this.getIdCuratore() + "\n";
        dummy += "Descrizione: " + this.getDescrizione() + "\n";

        return dummy;
    }
}
