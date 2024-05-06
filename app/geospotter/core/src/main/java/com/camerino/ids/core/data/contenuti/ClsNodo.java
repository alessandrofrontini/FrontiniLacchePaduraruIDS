package com.camerino.ids.core.data.contenuti;

import java.util.Date;

/**
 * TODO: commentare
 */
public class ClsNodo extends ClsContenuto{

    //region Tipologia nodo (enumerazione)
    public enum eTologiaNodo { COMMERCIALE, CULTURALE, CULINARIO }
    //endregion

    private String idComune;
    private eTologiaNodo eTologiaNodo;
    private boolean aTempo;
    private Date dataFine;
    private String idContest;

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

    public boolean isaTempo() {
        return aTempo;
    }

    public void setaTempo(boolean aTempo) {
        this.aTempo = aTempo;
    }
    public void setDataFine(Date d){this.dataFine = d;}
    public Date getDataFine(){return this.dataFine;}
    public void setIdContest(String idc){this.idContest = idc;}
    public String getIdContest(){return this.idContest;}
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
