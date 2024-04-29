package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

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
