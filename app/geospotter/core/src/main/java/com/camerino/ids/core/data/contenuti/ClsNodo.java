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
    private Long idComune;
    private eTipologiaNodo eTipologiaNodo;
    private boolean aTempo;

    //dd/mm/yyyy
    private Long dataInizio;
    //dd/mm/yyyy
    private Long dataFine;



    //region Getter e setter
    public Long getIdComune() {
        return idComune;
    }

    public void setIdComune(Long idComune) {
        this.idComune = idComune;
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

    public Long getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Long dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Long getDataFine() {
        return dataFine;
    }

    public void setDataFine(Long dataFine) {
        this.dataFine = dataFine;
    }

    //endregion

    public String visualizzaNodo()
    {
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "ID Comune: " + this.getIdComune() + "\n";
        dummy += "Username Creatore: " + this.getIdCreatore() + "\n";
        dummy += "Nome: " + this.getNome() + "\n";
        dummy += "Tipologia: " + this.getDescrizione() + "\n";
        dummy += "Posizione: " + this.getPosizione().getX() + ("(X) - ") + this.getPosizione().getY() + ("(Y)") + "\n\n";

        return dummy;
    }
}
