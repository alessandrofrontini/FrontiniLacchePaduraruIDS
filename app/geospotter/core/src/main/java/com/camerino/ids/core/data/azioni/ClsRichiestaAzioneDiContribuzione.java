package com.camerino.ids.core.data.azioni;

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

    public String getUsernameCreatoreRichiesta() {
        return usernameCreatoreRichiesta;
    }

    public void setUsernameCreatoreRichiesta(String usernameCreatoreRichiesta) {
        this.usernameCreatoreRichiesta = usernameCreatoreRichiesta;
    }

    public EAzioniDiContribuzione geteAzioneDiContribuzione() {
        return eAzioneDiContribuzione;
    }

    public void seteAzioneDiContribuzione(EAzioniDiContribuzione eAzioneDiContribuzione) {
        this.eAzioneDiContribuzione = eAzioneDiContribuzione;
    }
    //endregion

}
