package com.camerino.ids.core.data.azioni;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

/**
 * TODO: commentare
 */
@Entity
public class ClsRichiestaAzioneDiContribuzione {

    @Id
    @UuidGenerator
    String id;
    String usernameCreatoreRichiesta;

    EAzioniDiContribuzione eAzioneDiContribuzione;
    @ManyToOne
    ClsNodo datiNodo;
    @ManyToOne
    ClsImmagine datiImmagine;
    String idContest;

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

    public String visualizzaRichiesta()
    {
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "ID Contest: " + this.getIdContest() + "\n";
        dummy += "Username Creatore: " + this.getUsernameCreatoreRichiesta() + "\n";
        dummy += "Azione: " + this.geteAzioneDiContribuzione() + "\n";

        return dummy;
    }


}
