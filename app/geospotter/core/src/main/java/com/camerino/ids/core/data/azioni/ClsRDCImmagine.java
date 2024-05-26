package com.camerino.ids.core.data.azioni;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;

public class ClsRDCImmagine extends AbsDefaultAction<ClsImmagine> {
    public ClsNodo getNodoAssociato() {
        return nodoAssociato;
    }

    public void setNodoAssociato(ClsNodo nodoAssociato) {
        this.nodoAssociato = nodoAssociato;
    }

    private ClsNodo nodoAssociato;
    public ClsRDCImmagine(ClsImmagine oldData, ClsImmagine newData) {
        super(oldData, newData);
    }

    public ClsRDCImmagine() {

    }
}
