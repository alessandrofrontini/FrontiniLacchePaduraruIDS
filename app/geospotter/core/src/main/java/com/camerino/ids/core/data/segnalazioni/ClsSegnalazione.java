package com.camerino.ids.core.data.segnalazioni;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * TODO: commentare
 */
@Entity
public class ClsSegnalazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id = 0L;
    String idContenuto;
    @Deprecated
    String idCuratore;
    String descrizione;

    //region Getters and Setters
    @Deprecated
    public String getIdCuratore() {
        return idCuratore;
    }
    @Deprecated
    public void setIdCuratore(String idCuratore) {
        this.idCuratore = idCuratore;
    }
    public String getId() {
        return Objects.toString(id);
    }

    public void setId(String id) {
        this.id = Long.valueOf(id);
    }

    public String getIdContenuto() {
        return idContenuto;
    }

    public void setIdContenuto(String idContenuto) {
        this.idContenuto = idContenuto;
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
