package com.camerino.ids.fps.client.visual;

public class ClsNodoVisual
{
    public String ID;
    public Long IDComuneAssociato;
    public String Posizione;
    public Long Nome;
    public String Tipologia;
    public String ATempo;



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Long getIDComuneAssociato() {
        return IDComuneAssociato;
    }

    public void setIDComuneAssociato(Long IDComuneAssociato) {
        this.IDComuneAssociato = IDComuneAssociato;
    }

    public String getPosizione() {
        return Posizione;
    }

    public void setPosizione(String posizione) {
        Posizione = posizione;
    }

    public Long getNome() {
        return Nome;
    }

    public void setNome(Long nome) {
        Nome = nome;
    }

    public String getTipologia() {
        return Tipologia;
    }

    public void setTipologia(String tipologia) {
        Tipologia = tipologia;
    }

    public String getATempo() {
        return ATempo;
    }

    public void setATempo(String ATempo) {
        this.ATempo = ATempo;
    }


}
