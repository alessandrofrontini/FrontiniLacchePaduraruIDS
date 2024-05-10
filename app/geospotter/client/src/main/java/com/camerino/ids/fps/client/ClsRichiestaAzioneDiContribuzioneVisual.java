package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsImmagine;

public class ClsRichiestaAzioneDiContribuzioneVisual
{
    public String id;

    public String usernameCreatore;

    public String idContest;

    public String azioneDiContribuzione;

    public ClsNodoVisual nodo;

    public ClsImmagine immagine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameCreatore() {
        return usernameCreatore;
    }

    public void setUsernameCreatore(String usernameCreatore) {
        this.usernameCreatore = usernameCreatore;
    }

    public String getIdContest() {
        return idContest;
    }

    public void setIdContest(String idContest) {
        this.idContest = idContest;
    }

    public String getAzioneDiContribuzione() {
        return azioneDiContribuzione;
    }

    public void setAzioneDiContribuzione(String azioneDiContribuzione) {
        this.azioneDiContribuzione = azioneDiContribuzione;
    }

    public ClsNodoVisual getNodo() {
        return nodo;
    }

    public void setNodo(ClsNodoVisual nodo) {
        this.nodo = nodo;
    }

    public ClsImmagine getImmagine() {
        return immagine;
    }

    public void setImmagine(ClsImmagine immagine) {
        this.immagine = immagine;
    }

}
