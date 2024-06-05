package com.camerino.ids.fps.client.utils;

import com.camerino.ids.core.data.azioni.*;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.fps.client.visual.*;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Objects;

public class Utils
{
    public ClsNodoVisual convertFromClsNodo(ClsNodo nodo){
        ClsNodoVisual c = new ClsNodoVisual();

        c.setID(nodo.getId());
        c.setIDComuneAssociato(nodo.getIdComune());
        c.setNome(nodo.getNome());
        c.setTipologia(nodo.getTipologiaNodoFormatoStringa());
        c.setPosizione(nodo.getPosizione().getX() + "-" + nodo.getPosizione().getY());
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

    public ClsCuratoreVisual convertFromClsCuratore (ClsTuristaAutenticato curatore)
    {
        ClsCuratoreVisual c = new ClsCuratoreVisual();

        c.setId(curatore.getId());
        c.setUsername("Punteggio: " + curatore.getPunteggio()+""+" \nUsername: "+curatore.getCredenziali().getUsername());


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
        if(comune.getCuratoriAssociati().isEmpty() || comune.getCuratoriAssociati() == null)
        {
            tmp = "non gestito";
        }
        else
        {
            for(int i = 0; i< comune.getCuratoriAssociati().size() ;i++)
            {
                if(i == comune.getCuratoriAssociati().size()-1)
                {
                    tmp += comune.getCuratoriAssociati().get(i).getId();
                }
                else
                {
                    tmp += comune.getCuratoriAssociati().get(i).getId() + "-";
                }
            }
        }

        c.setDescrizione(comune.getDescrizione());
        c.setCuratori(tmp);
        return c;
    }

    public ClsContestDiContribuzioneVisual convertFromaClsContestDiContribuzione (ClsContestDiContribuzione contest)
    {
        ClsContestDiContribuzioneVisual c = new ClsContestDiContribuzioneVisual();

        c.setId(contest.getId());
        c.setUsernameCreatore(contest.getIdCreatore());
        c.setLocationComune(contest.getLocation().getNome());
        c.setDurata(contest.getDurata().toString());
        c.setIsAperto(contest.isAperto()+"");


        return c;
    }

    public ClsUtenteInvitoContestVisual convertFromTuristaAutenticato (ClsTuristaAutenticato turista)
    {
        ClsUtenteInvitoContestVisual u = new ClsUtenteInvitoContestVisual();

        u.setId(turista.getId());
        u.setPunteggio(turista.getPunteggio()+"");
        u.setRuolo(turista.getRuoloUtente()+"");
        u.setUsername(turista.getCredenziali().getUsername());

        return u;
    }

    public ClsRichiestaAzioneDiContribuzioneVisual convertFromRichiestaAzioneContribuzione (ClsRichiestaAzioneDiContribuzione richiesta)
    {
        ClsRichiestaAzioneDiContribuzioneVisual richiestaVisual = new ClsRichiestaAzioneDiContribuzioneVisual();

        richiestaVisual.setId(richiesta.getId());
        richiestaVisual.setIdContest(richiesta.getIdContest());
        richiestaVisual.setAzioneDiContribuzione(richiesta.geteAzioneDiContribuzione().toString());
        richiestaVisual.setUsernameCreatore(richiesta.getUsernameCreatoreRichiesta());

        //Richiesta per nodo
        if(richiesta.getDatiNodo() != null)
        {
            richiestaVisual.setTipoContenuto("NODO");
        }
        else
        {
            richiestaVisual.setTipoContenuto("IMMAGINE");
        }

        return richiestaVisual;
    }

    public ClsUtenteVisual convertFromClsTuristaAutenticato (ClsTuristaAutenticato turista)
    {
        ClsUtenteVisual c = new ClsUtenteVisual();

        c.setId(turista.getId());
        c.setPunteggio(turista.getPunteggio()+"");
        c.setUsername(turista.getCredenziali().getUsername());

        return c;
    }

    public ClsRDCVisual convertFromRDCImmagine (ClsRDCImmagine rdcImmagine)
    {
        ClsRDCVisual tmp = new ClsRDCVisual();

        tmp.setIdRichiesta(rdcImmagine.getIdRichiesta());
        tmp.setAzioneDiContribuzione(rdcImmagine.getTipo().toString());
        tmp.setStato(rdcImmagine.getStato().toString());
        tmp.setRichiedente(rdcImmagine.getCreatore().getId());
        tmp.setResponsabile(rdcImmagine.getResponsabile().getId());
        tmp.setIdContest(rdcImmagine.getIdContest().getId());
        tmp.setOldData("Dettaglio...");
        tmp.setNewData("Dettaglio...");

        return tmp;
    }

    public ClsRDCVisual convertFromRDCNodo (ClsRDCNodo rdcNodo)
    {
        ClsRDCVisual tmp = new ClsRDCVisual();

        tmp.setIdRichiesta(rdcNodo.getIdRichiesta());
        tmp.setAzioneDiContribuzione(rdcNodo.getTipo().toString());
        tmp.setStato(rdcNodo.getStato().toString());
        tmp.setRichiedente(rdcNodo.getCreatore().getId());
        tmp.setResponsabile(rdcNodo.getResponsabile().getId());
        tmp.setIdContest(rdcNodo.getIdContest().getId());
        tmp.setOldData("Dettaglio...");
        tmp.setNewData("Dettaglio...");

        return tmp;
    }

    public ClsRDCVisual convertFromRDCItinerario (ClsRdcItinerario rdcItinerario)
    {
        ClsRDCVisual tmp = new ClsRDCVisual();

        tmp.setIdRichiesta(rdcItinerario.getIdRichiesta());
        tmp.setAzioneDiContribuzione(rdcItinerario.getTipo().toString());
        tmp.setStato(rdcItinerario.getStato().toString());
        tmp.setRichiedente(rdcItinerario.getCreatore().getId());
        tmp.setResponsabile(rdcItinerario.getResponsabile().getId());
        tmp.setIdContest(rdcItinerario.getIdContest().getId());
        tmp.setOldData("Dettaglio...");
        tmp.setNewData("Dettaglio...");

        return tmp;
    }

    public ClsRichiestaAzioneDiContribuzioneItinerarioVisual convertFromRichiestaAzioneContribuzioneItinerario (ClsRichiestaAzioneDiContribuzioneItinerario richiesta)
    {
        ClsRichiestaAzioneDiContribuzioneItinerarioVisual richiestaVisual = new ClsRichiestaAzioneDiContribuzioneItinerarioVisual();

        richiestaVisual.setIdd(richiesta.getId());
        richiestaVisual.setIdItinerario(richiesta.getDatiItinerario().getId());
        richiestaVisual.setUsernameCreatoree(richiesta.getUsernameCreatore());
        richiestaVisual.setAzione(richiesta.geteAzioniDiContribuzione().toString());

        String tappe = "";
        for(int i = 0; i < richiesta.getDatiItinerario().getTappe().size(); i++)
        {
            if(i == richiesta.getDatiItinerario().getTappe().size() - 1)
            {
                tappe += richiesta.getDatiItinerario().getTappe().get(i).getId();

            }
            else
            {
                tappe += richiesta.getDatiItinerario().getTappe().get(i).getId() + "-";
            }

        }
        richiestaVisual.setTappe(tappe);

        return richiestaVisual;
    }

    public String getValueFromCombobox (ComboBox c)
    {
        return (String) c.getValue();
    }

    public Long getValueFromTextField (TextField t) { return (String)  t.getText(); }

    public boolean getValueFromCheckBox (CheckBox c) { return c.isSelected();}

    private String convertTappe(List<ClsNodo> tappe)
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
        if(
                Objects.equals(nodo.getNome(), "") ||
                Objects.equals(nodo.getTipologiaNodoFormatoStringa(), "") ||
                Objects.equals(nodo.getIdComune(), "") ||
                Objects.equals(nodo.getDescrizione(), "") ||
                //Objects.equals(nodo.getId(), null) ||
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
