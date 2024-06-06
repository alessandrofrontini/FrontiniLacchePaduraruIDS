package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Entity;

/**
 * TODO: commentare
 */
@Entity
public class ClsNodo extends ClsContenuto{

    //region Tipologia nodo (enumerazione)
    public enum eTipologiaNodo { COMMERCIALE, CULTURALE, CULINARIO }
    //endregion
    private String idComuneAssociato;
    private eTipologiaNodo eTipologiaNodo;
    private boolean aTempo;

    //dd/mm/yyyy
    private String dataInizio;
    //dd/mm/yyyy
    private String dataFine;



    //region Getter e setter
    public String getIdComuneAssociato() {
        return idComuneAssociato;
    }

    public void setIdComuneAssociato(String idComune) {
        this.idComuneAssociato = idComune;
    }

    public eTipologiaNodo getTipologiaNodo() {
        return eTipologiaNodo;
    }

    public void setTipologiaNodo(eTipologiaNodo eTipologiaNodo) {
        this.eTipologiaNodo = eTipologiaNodo;
    }

    public String getTipologiaNodoFormatoStringa(){ return this.eTipologiaNodo.toString();}

    public void seteTologiaNodoFormatoStringa (String tipologiaNodo){
        String tmp = tipologiaNodo.toUpperCase();

        switch (tmp)
        {
            case "COMMERCIALE":
                this.eTipologiaNodo = eTipologiaNodo.COMMERCIALE;
                break;

            case "CULINARIO":
                this.eTipologiaNodo = eTipologiaNodo.CULINARIO;
                break;

            case "CULTURALE":
                this.eTipologiaNodo = eTipologiaNodo.CULTURALE;
                break;

            default:
                this.eTipologiaNodo = null;
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
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "ID Comune: " + this.getIdComuneAssociato() + "\n";
        dummy += "Username Creatore: " + this.getIdCreatore() + "\n";
        dummy += "Nome: " + this.getNome() + "\n";
        dummy += "Tipologia: " + this.getDescrizione() + "\n";
        dummy += "Posizione: " + this.getPosizione().getX() + ("(X) - ") + this.getPosizione().getY() + ("(Y)") + "\n\n";

        return dummy;
    }
}
