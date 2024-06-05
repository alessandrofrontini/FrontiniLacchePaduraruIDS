package com.camerino.ids.fps.client.visual;

public class ClsContestDiContribuzioneVisual
{
    public String id;
    public Long usernameCreatore;

    public String durata;

    public Long locationComune;

    public String isAperto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUsernameCreatore() {
        return usernameCreatore;
    }

    public void setUsernameCreatore(Long usernameCreatore) {
        this.usernameCreatore = usernameCreatore;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public Long getLocationComune() {
        return locationComune;
    }

    public void setLocationComune(Long locationComune) {
        this.locationComune = locationComune;
    }

    public String getIsAperto() {
        return isAperto;
    }

    public void setIsAperto(String isAperto) {
        this.isAperto = isAperto;
    }
}
