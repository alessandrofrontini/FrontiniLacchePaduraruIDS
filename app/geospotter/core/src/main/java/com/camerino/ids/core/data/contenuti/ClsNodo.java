package com.camerino.ids.core.data.contenuti;

/**
 * TODO: commentare
 */
public class ClsNodo extends ClsContenuto{
    public enum TipologiaNodo { COMMERCIALE, CULTURALE, CULINARIO }
    private String idComune;
    private TipologiaNodo tipologiaNodo;
    private boolean aTempo;
    public String getIdComune() {
        return idComune;
    }

    public void setIdComune(String idComune) {
        this.idComune = idComune;
    }

    public TipologiaNodo getTipologiaNodo() {
        return tipologiaNodo;
    }

    public void setTipologiaNodo(TipologiaNodo tipologiaNodo) {
        this.tipologiaNodo = tipologiaNodo;
    }

    public boolean isaTempo() {
        return aTempo;
    }

    public void setaTempo(boolean aTempo) {
        this.aTempo = aTempo;
    }
}
