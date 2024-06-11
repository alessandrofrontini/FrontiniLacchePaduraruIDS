package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface IAzioniContributor {
    boolean inserisciNodo(ClsNodo nodo);
    boolean inserisciNodo(ClsNodo nodo, ClsContestDiContribuzione contest);
    boolean modificaNodo(Long id, ClsNodo nodo);
    boolean eliminaNodo(Long id);
    boolean inserisciItinerario(ClsItinerario itinerario);
    boolean modificaItinerario(ClsItinerario itinerario, Long id);
    boolean eliminaItinerario(Long id);
    @JsonIgnore
    List<ClsNodo> getNodiPossessore();
    boolean deleteNodo(Long idNodo);
    @JsonIgnore
    List<ClsRDCNodo> getRDCNodiById(Long idRDC);
    boolean deleteRDCById(Long idRDC);
    boolean putRDCNodo(ClsRDCNodo rdc);
    boolean postRDCNodo(ClsRDCNodo rdc);
    boolean postRDCItinerario(ClsRdcItinerario rdc);
    @JsonIgnore
    List<ClsContestDiContribuzione> getAllContest();
    @JsonIgnore
    List<ClsItinerario> getItinerariPossessore();
}
