package com.camerino.ids.fps.client.visual;

public class ClsRichiestaAzioneDiContribuzioneVisual {
    public Long id;

    public String usernameCreatore;

    public Long idContest;

    public String azioneDiContribuzione;

    public String tipoContenuto;

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

    public Long getIdContest() {
        return idContest;
    }

    public void setIdContest(Long idContest) {
        this.idContest = idContest;
    }

    public String getAzioneDiContribuzione() {
        return azioneDiContribuzione;
    }

    public void setAzioneDiContribuzione(String azioneDiContribuzione) {
        this.azioneDiContribuzione = azioneDiContribuzione;
    }

    public String getTipoContenuto() {
        return tipoContenuto;
    }

    public void setTipoContenuto(String tipoContenuto) {
        this.tipoContenuto = tipoContenuto;
    }
}
