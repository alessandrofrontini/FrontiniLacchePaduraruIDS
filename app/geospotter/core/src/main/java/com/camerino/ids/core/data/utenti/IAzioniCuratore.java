package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface IAzioniCuratore {
    @JsonIgnore
    List<ClsSegnalazione> _getAllSegnalazioni();
    @JsonIgnore
    List<ClsRDCNodo> _getAllRDCNodi();
    @JsonIgnore
    List<ClsRichiestaAzioneDiContribuzioneItinerario> _getAllRDCI();
    @JsonIgnore
    List<ClsRichiestaAzioneDiContribuzioneItinerario> getRDCIById(Long idRDCI);
    boolean putRDCI(ClsRichiestaAzioneDiContribuzioneItinerario rdci);
    boolean postRDCI(ClsRichiestaAzioneDiContribuzioneItinerario rdci);
    @JsonIgnore
    List<ClsRdcItinerario> _getAllRDCItinerari();
    boolean deleteRDCItinerario(Long idRDCItinerario);
    boolean putRDCItinerario(ClsRdcItinerario rdc);
}
