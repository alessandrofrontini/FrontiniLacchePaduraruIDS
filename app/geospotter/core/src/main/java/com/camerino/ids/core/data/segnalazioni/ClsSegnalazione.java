package com.camerino.ids.core.data.segnalazioni;

import jakarta.persistence.*;
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
    String idCuratore;
    String descrizione;

    //region Getters and Setters

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
