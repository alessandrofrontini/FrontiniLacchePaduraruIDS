package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Objects;

public class Utils
{
    public ClsNodoVisual convertFromClsNodo(ClsNodo nodo){
        ClsNodoVisual c = new ClsNodoVisual();

        c.setID(nodo.getId());
        c.setIDComuneAssociato(nodo.getIdComune());
        c.setNome(nodo.getNome());
        c.setTipologia(nodo.getTipologiaNodoFormatoStringa());
        c.setPosizione(nodo.getPosizione().getX() +"-"+nodo.getPosizione().getY());
        c.setATempo(nodo.isaTempo()+"");

        return c;
    }

    public ClsItinerarioVisual convertFromClsItinerario(ClsItinerario itinerario)
    {
        ClsItinerarioVisual i = new ClsItinerarioVisual();

        i.setId(itinerario.getId());
        i.setOrdinato(itinerario.isOrdinato() + "");
        i.setNome(itinerario.getNome());
        i.setTappe(this.convertTappe(itinerario.getTappe()));

        return i;
    }

    public ClsCuratoreVisual convertFromClsCuratore (ClsCuratore curatore)
    {
        ClsCuratoreVisual c = new ClsCuratoreVisual();

        c.setId(curatore.getId());
        c.setUsername(curatore.getCredenziali().getUsername());

        return c;
    }

    public ClsComuneVisual convertFromClsComune (ClsComune comune)
    {
        ClsComuneVisual c = new ClsComuneVisual();

        c.setId(comune.getId());
        c.setNome(comune.getNome());
        c.setDescrizione(comune.getDescrizione());
        c.setSuperficie(comune.getSuperficie());
        c.setPosizione(comune.getPosizione().getX() + " - " + comune.getPosizione().getY());
        c.setAbitanti(comune.getAbitanti());

        String tmp = "";
        for(int i = 0; i< comune.getCuratoriAssociati().length;i++)
        {
            if(i == comune.getCuratoriAssociati().length-1)
            {
                tmp += comune.getCuratoriAssociati()[i].getId();
            }
            else
            {
                tmp += comune.getCuratoriAssociati()[i].getId() + ", ";
            }

        }

        c.setCuratori(tmp);
        return c;
    }

    public ClsContestDiContribuzioneVisual convertFromaClsContestDiContribuzione (ClsContestDiContribuzione contest)
    {
        ClsContestDiContribuzioneVisual c = new ClsContestDiContribuzioneVisual();

        c.setId(contest.getId());
        c.setUsernameCreatore(contest.getUsernameCreatore());
        c.setLocationComune(contest.getLocation().getNome());
        c.setDurata(contest.getDurata().toString());
        c.setIsAperto(contest.isAperto()+"");

        return c;
    }

    public String getValueFromCombobox (ComboBox c)
    {
        return (String) c.getValue();
    }

    public String getValueFromTextField (TextField t) { return (String)  t.getText(); }

    public boolean getValueFromCheckBox (CheckBox c) { return c.isSelected();}

    private String convertTappe(ArrayList<ClsNodo> tappe)
    {
        String tmp = "";

        for(int i = 0; i<tappe.size();i++)
        {
            if(i == tappe.size()-1)
            {
                tmp += tappe.get(i).getId();
            }
            else{
                tmp += tappe.get(i).getId() + "-";
            }

        }

        return tmp;
    }

    public boolean checkInfoNodo (ClsNodo nodo)
    {
        if(Objects.equals(nodo.getId(), "") ||
                Objects.equals(nodo.getNome(), "") ||
                Objects.equals(nodo.getTipologiaNodoFormatoStringa(), "") ||
                Objects.equals(nodo.getIdComune(), "") ||
                Objects.equals(nodo.getDescrizione(), "") ||
                Objects.equals(nodo.getId(), null) ||
                Objects.equals(nodo.getNome(), null) ||
                Objects.equals(nodo.getTipologiaNodoFormatoStringa(), null) ||
                Objects.equals(nodo.getIdComune(), null) ||
                Objects.equals(nodo.getDescrizione(), null))
        {
            return false;
        }
        else
        {
            return true;
        }

    }

}
