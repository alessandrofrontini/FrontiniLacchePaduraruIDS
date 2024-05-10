package com.camerino.ids.core.data.azioni;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;

/**
 * TODO: commentare
 */
public class ClsRichiestaAzioneDiContribuzione {
    String id;
    String usernameCreatoreRichiesta;

    String idContest;
    EAzioniDiContribuzione eAzioneDiContribuzione;
    ClsNodo datiNodo;
    ClsImmagine datiImmagine;

    //region Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClsNodo getDatiNodo() {
        return datiNodo;
    }

    public void setDatiNodo(ClsNodo datiNodo) {
        this.datiNodo = datiNodo;
    }
    public void setDatiImmagine(ClsImmagine i){this.datiImmagine = i;}
    public ClsImmagine getDatiImmagine(){return this.datiImmagine;}

    public String getUsernameCreatoreRichiesta() {
        return usernameCreatoreRichiesta;
    }

    public void setUsernameCreatoreRichiesta(String usernameCreatoreRichiesta) {
        this.usernameCreatoreRichiesta = usernameCreatoreRichiesta;
    }
    public String getIdContest() {
        return idContest;
    }

    public void setIdContest(String idContest) {
        this.idContest = idContest;
    }

    public EAzioniDiContribuzione geteAzioneDiContribuzione() {
        return eAzioneDiContribuzione;
    }

    public void seteAzioneDiContribuzione(EAzioniDiContribuzione eAzioneDiContribuzione) {
        this.eAzioneDiContribuzione = eAzioneDiContribuzione;
    }
  //endregion


}
