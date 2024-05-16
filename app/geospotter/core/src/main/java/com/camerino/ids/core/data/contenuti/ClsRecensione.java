package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Entity;

/**
 * TODO: commentare
 */
@Entity
public class ClsRecensione extends ClsInformazione{
    String idContenutoAssociato;
    double valutazione;
    String oggetto;
    String contenuto;

    //region Getter e setter

    public String getIdContenutoAssociato() {
        return idContenutoAssociato;
    }

    public void setIdContenutoAssociato(String idContenutoAssociato) {
        this.idContenutoAssociato = idContenutoAssociato;
    }

    public double getValutazione() {
        return valutazione;
    }

    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }
    //endregion

    public String visualizzaRecensione()
    {
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "Username Creatore: " + this.getUsernameCreatore() + "\n";
        dummy += "Valutazione: " + this.getValutazione() + "\n";
        dummy += "Oggetto: " + this.getOggetto() + "\n";
        dummy += "Contenuto: " + this.getContenuto()+ "\n";
        dummy += "Contenuto Associato: " + this.getIdContenutoAssociato()+ "\n";


        return dummy;
    }
}
