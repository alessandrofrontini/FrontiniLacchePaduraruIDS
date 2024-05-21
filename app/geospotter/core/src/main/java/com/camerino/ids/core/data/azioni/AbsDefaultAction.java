package com.camerino.ids.core.data.azioni;

import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
@MappedSuperclass
public class AbsDefaultAction<E> implements IAction<E,EStatusRDC>{
    @Id
    @UuidGenerator
    String idRichiesta;
    EAzioniDiContribuzione tipo;
    EStatusRDC stato = EStatusRDC.NUOVO;

    @ManyToOne
    ClsTuristaAutenticato creatore;
    @ManyToOne
    ClsCuratore responsabile;
    @ManyToOne
    ClsContestDiContribuzione idContest;

    @ManyToOne
    E oldData;
    @ManyToOne
    E newData;

    public AbsDefaultAction(E oldData, E newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    public AbsDefaultAction() {

    }

    @Override
    public E getOldData() {
        return oldData;
    }

    @Override
    public E getNewData() {
        return newData;
    }

    @Override
    public void SetStatus(EStatusRDC newStatus) {
        stato = newStatus;
    }

    //region G&S

    public String getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(String idRichiesta) {
        this.idRichiesta = idRichiesta;
    }

    public EAzioniDiContribuzione getTipo() {
        return tipo;
    }

    public void setTipo(EAzioniDiContribuzione tipo) {
        this.tipo = tipo;
    }

    public EStatusRDC getStato() {
        return stato;
    }

    public void setStato(EStatusRDC stato) {
        this.stato = stato;
    }

    public ClsTuristaAutenticato getCreatore() {
        return creatore;
    }

    public void setCreatore(ClsTuristaAutenticato creatore) {
        this.creatore = creatore;
    }

    public ClsCuratore getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(ClsCuratore responsabile) {
        this.responsabile = responsabile;
    }

    public ClsContestDiContribuzione getIdContest() {
        return idContest;
    }

    public void setIdContest(ClsContestDiContribuzione idContest) {
        this.idContest = idContest;
    }

    //endregion
}
