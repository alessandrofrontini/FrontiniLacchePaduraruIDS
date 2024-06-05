package com.camerino.ids.core.data.contenuti;

import com.camerino.ids.core.data.utenti.ClsCuratore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

/**
 * Contiene i dati di un comune
 */
@Entity
public class ClsComune extends ClsContenuto
{
    Integer abitanti;
    Double superficie;
    @OneToMany(mappedBy = "id")
    List<ClsCuratore> curatoriAssociati;

    //region Getter e setter
    public Integer getAbitanti() {
        return abitanti;
    }

    public void setAbitanti(Integer abitanti) {
        this.abitanti = abitanti;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public List<ClsCuratore> getCuratoriAssociati() {
        return curatoriAssociati;
    }

    public void setCuratoriAssociati(List<ClsCuratore> curatoriAssociati) {
        this.curatoriAssociati = curatoriAssociati;
    }
    //endregion

    public  String visualizzaComune()
    {

        String dummy = "";

        dummy += "\nID: " + this.getId() ;
        dummy += "\nUsernameCreatore: " + this.getIdCreatore();
        dummy += "\nNome: " + this.getNome();
        dummy += "\nDescrizione: " + this.getDescrizione();
        dummy += "\nPosizione: " + this.getPosizione().getX() + " - " +this.getPosizione().getY();
        dummy += "\nSuperficie: " + this.getSuperficie();
        dummy += "\nCuratori: " + visualizzaCuratoriComune();

        return dummy;
    }

    private String visualizzaCuratoriComune()
    {

        String tmp = "";

        for(int i = 0; i< this.curatoriAssociati.size(); i++)
        {
            if(i == curatoriAssociati.size() -1)
            {
                tmp += this.curatoriAssociati.get(i).getId();
            }
            else
            {
                tmp += this.curatoriAssociati.get(i).getId();
            }

        }

        return tmp;
    }

}
