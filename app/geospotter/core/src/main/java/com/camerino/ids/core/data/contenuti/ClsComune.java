package com.camerino.ids.core.data.contenuti;

import com.camerino.ids.core.data.utenti.ClsCuratore;

/**
 * Contiene i dati di un comune
 */
public class ClsComune extends ClsContenuto
{
    String usernameCreatore = "ADMIN";
    int abitanti;
    double superficie;
    ClsCuratore[] curatoriAssociati;

    //region Getter e setter
    public int getAbitanti() {
        return abitanti;
    }

    public void setAbitanti(int abitanti) {
        this.abitanti = abitanti;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public ClsCuratore[] getCuratoriAssociati() {
        return curatoriAssociati;
    }

    public void setCuratoriAssociati(ClsCuratore[] curatoriAssociati) {
        this.curatoriAssociati = curatoriAssociati;
    }
    //endregion

    public  String visualizzaComune()
    {

        String dummy = "-<-<-<-<-<-<-< DETTAGLIO COMUNE -<-<-<-<-<-<-<"+this.getId()+ "-<-<-<-<-<-<-<\n\n";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "\nUsernameCreatore: " + this.getUsernameCreatore() + "\n";
        dummy += "\nNome: " + this.getNome() + "\n";
        dummy += "\nDescrizione: " + this.getDescrizione() + "\n";
        dummy += "\nPosizione: " + this.getPosizione().getX() + " - " +this.getPosizione().getY() + "\n";
        dummy += "Superficie: \n" + this.getSuperficie() + "\n\n";
        dummy += "-<-<-<-<-<-<-< FINE DETTAGLIO COMUNE -<-<-<-<-<-<-<"+this.getId()+ "-<-<-<-<-<-<-<\n\n";

        return dummy;
    }

}