package com.camerino.ids.fps.client.visual;

public class ClsContestDiContribuzioneVisual
{
    public Long id;
    public String usernameCreatore;

    public String durata;

    public String locationComune;

    public String isAperto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernameCreatore() {
        return usernameCreatore;
    }

    public void setUsernameCreatore(String usernameCreatore) {
        this.usernameCreatore = usernameCreatore;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getLocationComune() {
        return locationComune;
    }

    public void setLocationComune(String locationComune) {
        this.locationComune = locationComune;
    }

    public String getIsAperto() {
        return isAperto;
    }

    public void setIsAperto(String isAperto) {
        this.isAperto = isAperto;
    }
}
