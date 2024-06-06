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
    @Deprecated
    Long idCuratore;
    String descrizione;

    //region Getters and Setters
    @Deprecated
    public Long getIdCuratore() {
        return idCuratore;
    }

    @Deprecated
    public void setIdCuratore(Long idCuratore) {
        this.idCuratore = idCuratore;
    }

    public Long getId() {
        return (id);
    }

    public void setId(Long id) {
        this.id = Long.valueOf(id);
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
        dummy += "ID Curatore: " + this.getIdCuratore() + "\n";
        dummy += "Descrizione: " + this.getDescrizione() + "\n";

        return dummy;
    }
}
