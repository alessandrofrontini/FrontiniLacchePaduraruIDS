package com.camerino.ids.core.data.azioni;

import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import jakarta.persistence.*;

@MappedSuperclass
public class AbsDefaultAction<E> implements IAction<E, EStatusRDC> {
    @Id
    @GeneratedValue
    Long idRichiesta = 0L;
    @Enumerated(EnumType.STRING)
    EAzioniDiContribuzione tipo;
    @Enumerated(EnumType.STRING)
    EStatusRDC stato = EStatusRDC.NUOVO;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    ClsTuristaAutenticato creatore;

    @ManyToOne(cascade = CascadeType.ALL)
    ClsContestDiContribuzione idContestAppartenenza;

    @ManyToOne(cascade = CascadeType.ALL)
    E oldData;
    @ManyToOne(cascade = CascadeType.ALL)
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

    public void setOldData(E oldData) {
        this.oldData = oldData;
    }

    @Override
    public E getNewData() {
        return newData;
    }

    public void setNewData(E newData) {
        this.newData = newData;
    }

    @Override
    public void SetStatus(EStatusRDC newStatus) {
        stato = newStatus;
    }

    //region G&S

    public Long getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(Long idRichiesta) {
        this.idRichiesta = Long.valueOf(idRichiesta);
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

    public ClsContestDiContribuzione getIdContestAppartenenza() {
        return idContestAppartenenza;
    }

    public void setIdContestAppartenenza(ClsContestDiContribuzione contest) {
        this.idContestAppartenenza = contest;
    }

    //endregion
}
