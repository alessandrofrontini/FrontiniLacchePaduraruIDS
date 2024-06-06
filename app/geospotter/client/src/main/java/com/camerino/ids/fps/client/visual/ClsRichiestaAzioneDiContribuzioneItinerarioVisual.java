package com.camerino.ids.fps.client.visual;

public class ClsRichiestaAzioneDiContribuzioneItinerarioVisual
{
    public Long idd;

    public String usernameCreatoree;

    public Long idItinerario;

    public String tappe;

    public String azione;

    public String getAzione() {
        return azione;
    }

    public void setAzione(String azione) {
        this.azione = azione;
    }

    public Long getIdd() {
        return idd;
    }

    public void setIdd(Long idd) {
        this.idd = idd;
    }

    public String getUsernameCreatoree() {
        return usernameCreatoree;
    }

    public void setUsernameCreatoree(String usernameCreatoree) {
        this.usernameCreatoree = usernameCreatoree;
    }

    public Long getIdItinerario() {
        return idItinerario;
    }

    public void setIdItinerario(Long idItinerario) {
        this.idItinerario = idItinerario;
    }

    public String getTappe() {
        return tappe;
    }

    public void setTappe(String tappe) {
        this.tappe = tappe;
    }
}
