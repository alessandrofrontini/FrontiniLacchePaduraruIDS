package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Contiene i dati richiesti per recuperare un immagine
 */
@Entity
public class ClsImmagine extends ClsInformazione
{
    @Column(name = "idNodoAssImm")
    String idNodoAssociato;
    String URL;

    //region Getter e setter
    public String getIdNodoAssociato() {
        return idNodoAssociato;
    }

    public void setIdNodoAssociato(String idCOntenutoAssociato) {
        this.idNodoAssociato = idCOntenutoAssociato;
    }

    public String getURL() {
        return URL;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }
    //region Getter e setter (ClsInformazione)
    public String getId()
    {
        return super.getId() ;
    }
    public void setId(String id) {
        super.setId(id);
    }
    public String getUsernameCreatore() {return super.getIdCreatore();}
    //endregion
    //endregion

    public String visualizzaImmagine()
    {
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "URL: " + this.getURL() + "\n";
        dummy += "Username Creatore: " + this.getIdCreatore() + "\n";
        dummy += "ID Contenuto Associato: " + this.idNodoAssociato + "\n";

        dummy += "";

        return dummy;
    }
}
