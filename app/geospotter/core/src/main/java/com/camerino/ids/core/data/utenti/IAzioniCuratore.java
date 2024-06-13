package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface IAzioniCuratore {
    @JsonIgnore
    List<ClsSegnalazione> _getAllSegnalazioni();
    @JsonIgnore
    List<ClsRDCNodo> _getAllRDCNodi();
    @JsonIgnore
    List<ClsRdcItinerario> _getAllRDCItinerari();
    boolean deleteRDCItinerario(Long idRDCItinerario);
    boolean putRDCItinerario(ClsRdcItinerario rdc);
    @JsonIgnore
    List<ClsRDCNodo> getAllRDCNodi();
    @JsonIgnore
    List<ClsRDCImmagine> getAllRDCImmagini();
    @JsonIgnore
    List<ClsRDCImmagine> getRdcImmaginiPosessoreCur();
    @JsonIgnore
    List<ClsRDCNodo> getRDCNodiPosessoreCur();
    @JsonIgnore
    List<ClsRdcItinerario> getRDCItinerarioPosessoreCur();
}
