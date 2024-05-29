package com.camerino.ids.core.data.contenuti;

import com.camerino.ids.core.data.utenti.ClsCuratore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * Contiene i dati di un comune
 */
@Entity
public class ClsComune extends ClsContenuto
{
    String usernameCreatore = "ADMIN";
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

    public ClsCuratore[] getCuratoriAssociati() {
        return curatoriAssociati;
    }

    public void setCuratoriAssociati(ClsCuratore[] curatoriAssociati) {
        this.curatoriAssociati = curatoriAssociati;
    }
    //endregion

    public  String visualizzaComune()
    {

        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "\nUsernameCreatore: " + this.getIdCreatore() + "\n";
        dummy += "\nNome: " + this.getNome() + "\n";
        dummy += "\nDescrizione: " + this.getDescrizione() + "\n";
        dummy += "\nPosizione: " + this.getPosizione().getX() + " - " +this.getPosizione().getY() + "\n";
        dummy += "\nSuperficie: " + this.getSuperficie() + "\n";
        dummy += "\nCuratori: " + visualizzaCuratoriComune();

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
