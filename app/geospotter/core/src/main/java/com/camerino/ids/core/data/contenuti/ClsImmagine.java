package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Contiene i dati richiesti per recuperare un immagine
 */
@Entity
public class ClsImmagine extends ClsInformazione {
    @Column(name = "idNodoAssImm")
    Long idNodoAssociato;
    String URL;



    //region Getter e setter
    public Long getIdNodoAssociato() {
        return idNodoAssociato;
    }

    public void setIdNodoAssociato(Long idNodoAssociato) {
        this.idNodoAssociato = idNodoAssociato;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    //endregion

    public String visualizzaImmagine() {
        String dummy = "";

        dummy += "URL: " + this.getURL() + "\n";
        dummy += "Creatore: " + this.getIdCreatore() + "\n";
        dummy += "ID Associazione: " + this.idNodoAssociato + "\n";

        dummy += "";

        return dummy;
    }
}
