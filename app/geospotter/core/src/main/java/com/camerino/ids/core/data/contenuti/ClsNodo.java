package com.camerino.ids.core.data.contenuti;

/**
 * TODO: commentare
 */
public class ClsNodo extends ClsContenuto{

    //region Tipologia nodo (enumerazione)
    public enum TipologiaNodo { COMMERCIALE, CULTURALE, CULINARIO }
    //endregion

    private String idComune;
    private TipologiaNodo tipologiaNodo;
    private boolean aTempo;

    //region Getter e setter
    //region Getter e setter (ClsContenuto)
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
    //endregion

    public String visualizzaNodo()
    {
        String dummy = "-<-<-<-<-<-<-< DETTAGLIO NODO "+this.getId()+ "-<-<-<-<-<-<-<\n\n";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "ID Comune: " + this.getIdComune() + "\n";
        dummy += "Username Creatore: " + this.getUsernameCreatore() + "\n";
        dummy += "Nome: " + this.getNome() + "\n";
        dummy += "Tipologia: " + this.getDescrizione() + "\n";
        dummy += "Posizione: " + this.getPosizione().getX() + ("(X) - ") + this.getPosizione().getY() + ("(Y)") + "\n\n";

        dummy += "-<-<-<-<-<-<-< FINE DETTAGLIO NODO "+this.getId()+ "-<-<-<-<-<-<-<\n\n";

        return dummy;
    }
}
