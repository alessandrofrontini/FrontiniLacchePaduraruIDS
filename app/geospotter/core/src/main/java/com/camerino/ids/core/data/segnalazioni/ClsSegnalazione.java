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
    Long idCuratore;
    Long descrizione;

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

    public Long getIdCuratore() {
        return idCuratore;
    }

    public void setIdCuratore(Long idCuratore) {
        this.idCuratore = idCuratore;
    }

    public Long getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(Long descrizione) {
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
