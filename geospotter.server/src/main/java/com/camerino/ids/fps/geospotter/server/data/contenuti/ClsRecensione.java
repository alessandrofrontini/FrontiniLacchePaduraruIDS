package com.camerino.ids.fps.geospotter.server.data.contenuti;

/**
 * TODO: commentare
 */
public class ClsRecensione extends ClsInformazione
{

    String idContenutoAssociato;
    double valutazione;
    String oggetto;
    String contenuto;

    //region Getter e setter
    //region Getter e setter (ClsInformazione)
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
    public String getIdContenutoAssociato() {
        return idContenutoAssociato;
    }

    public void setIdContenutoAssociato(String idContenutoAssociato) {
        this.idContenutoAssociato = idContenutoAssociato;
    }

    public double getValutazione() {
        return valutazione;
    }

    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }
    //endregion
}
