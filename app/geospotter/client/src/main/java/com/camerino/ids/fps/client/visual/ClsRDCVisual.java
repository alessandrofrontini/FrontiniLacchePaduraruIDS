package com.camerino.ids.fps.client.visual;

public class ClsRDCVisual
{
    public Long idRichiesta;
    public String azioneDiContribuzione;
    public String stato;
    public String richiedente;
    public String responsabile;
    public Long idContest;
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

    public Long getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(Long idRichiesta) {
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

    public String getRichiedente() {
        return richiedente;
    }

    public void setRichiedente(String richiedente) {
        this.richiedente = richiedente;
    }

    public String getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(String responsabile) {
        this.responsabile = responsabile;
    }

    public Long getIdContest() {
        return idContest;
    }

    public void setIdContest(Long idContest) {
        this.idContest = idContest;
    }
}
