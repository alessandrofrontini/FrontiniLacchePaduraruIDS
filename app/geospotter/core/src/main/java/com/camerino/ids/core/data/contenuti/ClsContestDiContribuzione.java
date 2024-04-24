package com.camerino.ids.core.data.contenuti;

import java.util.Date;

/**
 * Contiene i dati di un contest di contribuzione
 */
public class ClsContestDiContribuzione {
    String id;
    String nome;
    String usernameCreatore;
    Date dataFine;
    ClsComune location;
    boolean isAperto;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setNome(String n){ nome = n;}
    public String getNome(){return nome;}
    public void setUsernameCreatore(String user){
        this.usernameCreatore = user;
    }
    public String getUsernameCreatore(){
        return this.usernameCreatore;
    }
    public void setDataFine(Date d){
        this.dataFine = d;
    }
    public Date getDataFine(){
        return this.dataFine;
    }
    public void setLocation(ClsComune comune){
        this.location = comune;
    }
    public ClsComune getLocation(){
        return this.location;
    }
    public void setAperto(boolean a){
        this.isAperto = a;
    }
    public boolean isAperto(){
        return this.isAperto;
    }
}
