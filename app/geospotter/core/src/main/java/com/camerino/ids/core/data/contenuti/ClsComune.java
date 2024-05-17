package com.camerino.ids.core.data.contenuti;

import com.camerino.ids.core.data.utenti.ClsCuratore;

import java.util.ArrayList;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * Contiene i dati di un comune
 */
@Entity
public class ClsComune extends ClsContenuto
{
    String id;
    int abitanti;
    double superficie;
    @OneToOne
    @JoinColumn(name="")//TODO: associazione
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

    private String visualizzaCuratoriComune()
    {

        String tmp = "";

        for(int i = 0; i<this.curatoriAssociati.length; i++)
        {
            if(i == curatoriAssociati.length -1)
            {
                tmp += this.curatoriAssociati[i].getCredenziali().getUsername();
            }
            else
            {
                tmp += this.curatoriAssociati[i].getCredenziali().getUsername() +", ";
            }

        }

        return tmp;
    }

}
