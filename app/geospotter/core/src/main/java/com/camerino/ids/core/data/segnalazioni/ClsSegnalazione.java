package com.camerino.ids.core.data.segnalazioni;

/**
 * TODO: commentare
 */
public class ClsSegnalazione {
    private String id;
    private String idContenuto;
    private String idCuratore;
    private String descrizione;
    private String idUtente;


    //region Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdContenuto() {
        return idContenuto;
    }

    public void setIdContenuto(String idContenuto) {
        this.idContenuto = idContenuto;
    }

    public String getIdCuratore() {
        return idCuratore;
    }

    public void setIdCuratore(String idCuratore) {
        this.idCuratore = idCuratore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setIdUtente(String i){this.idUtente = i;}
    public String getIdUtente(){ return this.idUtente;}


    //endregion
}
