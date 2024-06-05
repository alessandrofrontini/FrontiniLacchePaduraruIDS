package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * TODO: commentare
 */
@Entity
public class ClsRecensione extends ClsInformazione{
    //Ho errore referring to multiple physical column names: [idcontenuto_associato], [id_contenuto_associato]
    //perchè esiste anche in ClsImmagine
    @Column(name = "idNodo2")
    String idNodo;
    double valutazione;
    Long oggetto;
    Long contenuto;

    //region Getter e setter
    public String getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(String idContenutoAssociato) {
        this.idNodo = idContenutoAssociato;
    }

    public double getValutazione() {
        return valutazione;
    }

    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    public Long getOggetto() {
        return oggetto;
    }

    public void setOggetto(Long oggetto) {
        this.oggetto = oggetto;
    }

    public Long getContenuto() {
        return contenuto;
    }

    public void setContenuto(Long contenuto) {
        this.contenuto = contenuto;
    }
    //endregion

    public String visualizzaRecensione()
    {
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "Username Creatore: " + this.getIdCreatore() + "\n";
        dummy += "Valutazione: " + this.getValutazione() + "\n";
        dummy += "Oggetto: " + this.getOggetto() + "\n";
        dummy += "Contenuto: " + this.getContenuto()+ "\n";
        dummy += "Contenuto Associato: " + this.getIdNodo()+ "\n";


        return dummy;
    }
}
