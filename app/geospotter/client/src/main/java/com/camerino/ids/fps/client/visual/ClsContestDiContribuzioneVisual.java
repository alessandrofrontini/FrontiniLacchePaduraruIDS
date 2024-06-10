package com.camerino.ids.fps.client.visual;

public class ClsContestDiContribuzioneVisual {
    public Long id;
    public Long idCreatore;

    public String durata;

    public String locationComune;

    public String isAperto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCreatore() {
        return idCreatore;
    }

    public void setIdCreatore(Long idCreatore) {
        this.idCreatore = idCreatore;
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
