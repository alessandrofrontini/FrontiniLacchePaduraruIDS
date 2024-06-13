package com.camerino.ids.core.data.azioni;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import jakarta.persistence.Entity;

@Entity
public class ClsRDCNodo extends AbsDefaultAction<ClsNodo> {
    public ClsRDCNodo(ClsNodo oldData, ClsNodo newData) {
        super(oldData, newData);
    }

    public ClsRDCNodo() {

    }
}
