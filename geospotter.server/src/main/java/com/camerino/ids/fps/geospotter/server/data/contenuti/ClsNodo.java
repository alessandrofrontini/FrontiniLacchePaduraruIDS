package com.camerino.ids.fps.geospotter.server.data.contenuti;

<<<<<<< Updated upstream
/**
 * TODO: commentare
 */
public class ClsNodo extends ClsContenuto{
=======
public class ClsNodo extends ClsContenuto
{
    
>>>>>>> Stashed changes
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

    //TODO: sviluppa
    public String visualizzaNodo()
    {
        return "";
    }
}
