package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

/**
 * Contiene i dati richiesti per recuperare un immagine
 */
@Entity
public class ClsImmagine extends ClsInformazione
{
    @Column(name = "idCon1")
    String idCOntenutoAssociato;
    String URL;

    //region Getter e setter
    public String getIdCOntenutoAssociato() {
        return idCOntenutoAssociato;
    }

    public void setIdCOntenutoAssociato(String idCOntenutoAssociato) {
        this.idCOntenutoAssociato = idCOntenutoAssociato;
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
    public String getUsernameCreatore() {return super.getUsernameCreatore();}
    public void setUsernameCreatore(String usernameCreatore)
    {
        super.setUsernameCreatore(usernameCreatore);
    }
    //endregion
    //endregion

    public String visualizzaImmagine()
    {
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "URL: " + this.getURL() + "\n";
        dummy += "Username Creatore: " + this.getUsernameCreatore() + "\n";
        dummy += "ID Contenuto Associato: " + this.idCOntenutoAssociato + "\n";

        dummy += "";

        return dummy;
    }
}
