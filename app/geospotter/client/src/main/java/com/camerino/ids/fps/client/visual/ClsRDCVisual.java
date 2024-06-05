package com.camerino.ids.fps.client.visual;

public class ClsRDCVisual
{
    public String idRichiesta;
    public String azioneDiContribuzione;
    public String stato;
    public Long richiedente;
    public Long responsabile;
    public String idContest;
    public String oldData;
    public String newData;

    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    public String getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(String idRichiesta) {
        this.idRichiesta = idRichiesta;
    }

    public String getAzioneDiContribuzione() {
        return azioneDiContribuzione;
    }

    public void setAzioneDiContribuzione(String azioneDiContribuzione) {
        this.azioneDiContribuzione = azioneDiContribuzione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Long getRichiedente() {
        return richiedente;
    }

    public void setRichiedente(Long richiedente) {
        this.richiedente = richiedente;
    }

    public Long getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(Long responsabile) {
        this.responsabile = responsabile;
    }

    public String getIdContest() {
        return idContest;
    }

    public void setIdContest(String idContest) {
        this.idContest = idContest;
    }
}
