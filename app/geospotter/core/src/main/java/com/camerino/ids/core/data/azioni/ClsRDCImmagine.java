package com.camerino.ids.core.data.azioni;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import jakarta.persistence.Entity;

@Entity
public class ClsRDCImmagine extends AbsDefaultAction<ClsImmagine> {
    public ClsRDCImmagine(ClsImmagine oldData, ClsImmagine newData) {
        super(oldData, newData);
    }

    public ClsRDCImmagine() {

    }
}
