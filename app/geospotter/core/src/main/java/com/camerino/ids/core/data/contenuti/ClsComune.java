package com.camerino.ids.core.data.contenuti;

import com.camerino.ids.core.data.utenti.ClsCuratore;

import java.util.ArrayList;

/**
 * Contiene i dati di un comune
 */
public class ClsComune extends ClsContenuto
{
    String id;
    int abitanti;
    double superficie;
    ArrayList<String> curatoriAssociati;
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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

    public ArrayList<String> getCuratoriAssociati() {
        return curatoriAssociati;
    }

    public void setCuratoriAssociati(ArrayList<String> curatoriAssociati) {
        this.curatoriAssociati = curatoriAssociati;
    }
    //endregion

    public  String visualizzaComune()
    {

        String dummy = "-<-<-<-<-<-<-< DETTAGLIO COMUNE " + this.getId() + "-<-<-<-<-<-<-<";

        dummy += "\nID: " + this.getId() + "\n";
        dummy += "Nome: " + this.getNome() + "\n";
        dummy += "Descrizione: " + this.getDescrizione() + "\n";
        dummy += "Abitanti: " + this.getAbitanti() + "\n";
        dummy += "Posizione: " + this.getPosizione().getX() + " - " +this.getPosizione().getY() + "\n";
        dummy += "Superficie: " + this.getSuperficie() + "\n";
        dummy += "-<-<-<-<-<-<-< FINE DETTAGLIO COMUNE " + this.getId() + "-<-<-<-<-<-<-<";

        return dummy;
    }

}
