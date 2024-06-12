package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * TODO: commentare
 */
@Entity
public class ClsRecensione extends ClsInformazione {
    //Ho errore referring to multiple physical column names: [idcontenuto_associato], [id_contenuto_associato]
    //perchè esiste anche in ClsImmagine
    @Column(name = "idNodoAssRec")
    Long idNodoAssociato;
    Double valutazione;
    String oggetto;
    String contenuto;

    //region Getter e setter
    public Long getIdNodoAssociato() {
        return idNodoAssociato;
    }

    public void setIdNodoAssociato(Long idNodoAssociato) {
        this.idNodoAssociato = idNodoAssociato;
    }

    public Double getValutazione() {
        return valutazione;
    }

    public void setValutazione(Double valutazione) {
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

    public String visualizzaRecensione() {
        String dummy = "";

        dummy += "Creatore: " + this.getIdCreatore() + "\n";
        dummy += "Valutazione: " + this.getValutazione() + "\n";
        dummy += "Oggetto: " + this.getOggetto() + "\n";
        dummy += "Contenuto: " + this.getContenuto() + "\n";
        dummy += "Associazione: " + this.getIdNodoAssociato() + "\n";


        return dummy;
    }
}
