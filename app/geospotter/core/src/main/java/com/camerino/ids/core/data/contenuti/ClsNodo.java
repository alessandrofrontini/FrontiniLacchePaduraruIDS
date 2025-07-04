package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Entity;

import java.util.Objects;

/**
 * Contiene i dati di un nodo
 */
@Entity
public class ClsNodo extends ClsContenuto {

    private Long idComuneAssociato;
    private eTipologiaNodo eTipologiaNodo;
    private Boolean aTempo;
    //dd/mm/yyyy
    private String dataInizio;
    //dd/mm/yyyy
    private String dataFine;

    //region Getter e setter
    public Long getIdComuneAssociato() {
        return idComuneAssociato;
    }

    public void setIdComuneAssociato(Long idComuneAssociato) {
        this.idComuneAssociato = idComuneAssociato;
    }

    public eTipologiaNodo getTipologiaNodo() {
        return eTipologiaNodo;
    }

    public void setTipologiaNodo(eTipologiaNodo eTipologiaNodo) {
        this.eTipologiaNodo = eTipologiaNodo;
    }

    public String getTipologiaNodoFormatoStringa() {
        return this.eTipologiaNodo.toString();
    }

    public void seteTologiaNodoFormatoStringa(String tipologiaNodo) {
        String tmp = tipologiaNodo.toUpperCase();

        switch (tmp) {
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

    public Boolean isaTempo() {
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

    public String visualizzaNodo() {
        String dummy = "";

        dummy += "ID Comune: " + this.getIdComuneAssociato() + "\n";
        dummy += "Creatore: " + this.getIdCreatore() + "\n";
        dummy += "Nome: " + this.getNome() + "\n";
        dummy += "Tipologia: " + this.getDescrizione() + "\n";
        dummy += "Posizione: " + this.getPosizione().getX() + ("(X) - ") + this.getPosizione().getY() + ("(Y)") + "\n\n";

        return dummy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsNodo clsNodo = (ClsNodo) o;
        return Objects.equals(idComuneAssociato, clsNodo.idComuneAssociato) && eTipologiaNodo == clsNodo.eTipologiaNodo && Objects.equals(aTempo, clsNodo.aTempo) && Objects.equals(dataInizio, clsNodo.dataInizio) && Objects.equals(dataFine, clsNodo.dataFine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComuneAssociato, eTipologiaNodo, aTempo, dataInizio, dataFine);
    }

    //region Tipologia nodo (enumerazione)
    public enum eTipologiaNodo {COMMERCIALE, CULTURALE, CULINARIO}
    //endregion
}
