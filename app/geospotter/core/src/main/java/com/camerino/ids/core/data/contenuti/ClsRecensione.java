package com.camerino.ids.core.data.contenuti;

/**
 * TODO: commentare
 */
public class ClsRecensione extends ClsInformazione{
    String idContenutoAssociato;
    double valutazione;
    String oggetto;
    String contenuto;
    String idUtente;

    //region Getter e setter

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
    public void setIdUtente(String id){this.idUtente = id;}
    public String getIdUtente(){return this.idUtente;}
    //endregion

    public String visualizzaRecensione()
    {
        String dummy = "-<-<-<-<-<-<-< DETTAGLIO RECENSIONE -<-<-<-<-<-<-<"+this.getId()+ "-<-<-<-<-<-<-<\n\n";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "Valutazione: " + this.getValutazione() + "\n";
        dummy += "Oggetto: " + this.getOggetto() + "\n";
        dummy += "Contenuto: " + this.getContenuto()+ "\n";
        dummy += "Contenuto Associato: " + this.getIdContenutoAssociato()+ "\n";

        dummy += "-<-<-<-<-<-<-< FINE DETTAGLIO RECENSIONE -<-<-<-<-<-<-<"+this.getId()+ "-<-<-<-<-<-<-<\n\n";

        return dummy;
    }
}
