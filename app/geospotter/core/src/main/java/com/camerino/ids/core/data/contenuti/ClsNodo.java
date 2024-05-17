package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * TODO: commentare
 */
@Entity
public class ClsNodo extends ClsContenuto{

    //region Tipologia nodo (enumerazione)
    public enum eTologiaNodo { COMMERCIALE, CULTURALE, CULINARIO }
    //endregion
    private String idComune;
    private eTologiaNodo eTologiaNodo;
    private boolean aTempo;

    //dd/mm/yyyy
    private String dataInizio;
    //dd/mm/yyyy
    private String dataFine;



    //region Getter e setter
    public String getIdComune() {
        return idComune;
    }

    public void setIdComune(String idComune) {
        this.idComune = idComune;
    }

    public eTologiaNodo getTipologiaNodo() {
        return eTologiaNodo;
    }

    public void setTipologiaNodo(eTologiaNodo eTologiaNodo) {
        this.eTologiaNodo = eTologiaNodo;
    }

    public String getTipologiaNodoFormatoStringa(){ return this.eTologiaNodo.toString();}

    public void seteTologiaNodoFormatoStringa (String tipologiaNodo){
        String tmp = tipologiaNodo.toUpperCase();

        switch (tmp)
        {
            case "COMMERCIALE":
                this.eTologiaNodo = eTologiaNodo.COMMERCIALE;
                break;

            case "CULINARIO":
                this.eTologiaNodo = eTologiaNodo.CULINARIO;
                break;

            case "CULTURALE":
                this.eTologiaNodo = eTologiaNodo.CULTURALE;
                break;

            default:
                this.eTologiaNodo = null;
                break;
        }
    }

    public boolean isaTempo() {
        return aTempo;
    }

    public void setaTempo(boolean aTempo) {
        this.aTempo = aTempo;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    //endregion

    public String visualizzaNodo()
    {
        String dummy = "-<-<-<-<-<-<-< DETTAGLIO NODO "+this.getId()+ "-<-<-<-<-<-<-<\n";

        dummy += "ID: " + this.getId() + "\n";
        dummy += "ID Comune: " + this.getIdComune() + "\n";
        dummy += "Username Creatore: " + this.getUsernameCreatore() + "\n";
        if(this.isaTempo()){
            dummy += "Nodo a tempo: Si, scadenza: " + this.getDataFine() + "\n";
        }
        else dummy += "Nodo a tempo: No\n";
        dummy += "Nome: " + this.getNome() + "\n";
        dummy += "Tipologia: " + this.getDescrizione() + "\n";
        dummy += "Posizione: " + this.getPosizione().getX() + ("(X) - ") + this.getPosizione().getY() + ("(Y)") + "\n";

        dummy += "-<-<-<-<-<-<-< FINE DETTAGLIO NODO "+this.getId()+ "-<-<-<-<-<-<-<\n";

        return dummy;
    }
}
