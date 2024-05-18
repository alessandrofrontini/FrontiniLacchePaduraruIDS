package com.camerino.ids.core.data.azioni;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import jakarta.persistence.Entity;

@Entity
public class ClsRdcItinerario extends AbsDefaultAction<ClsItinerario> {
    public ClsRdcItinerario(ClsItinerario oldData, ClsItinerario newData) {
        super(oldData, newData);
    }

    public ClsRdcItinerario() {
        super();
    }
}
