package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface IAzioniTurista {
    @JsonIgnore
    List<ClsItinerario> getAllItinerari();
    @JsonIgnore
    List<ClsItinerario> getItinerarioById();
    @JsonIgnore
    List<ClsNodo> getNodoById(Long idNodo);
    @JsonIgnore
    List<ClsSegnalazione> getAllSegnalazioni();
    @JsonIgnore
    List<ClsRecensione> getAllRecensioni();
    @JsonIgnore
    List<ClsImmagine> getAllImmagini();
    boolean segnalaContenuto(ClsSegnalazione segnalazione);
    @JsonIgnore
    List<ClsComune> getAllComuni();
    @JsonIgnore
    List<ClsNodo> getAllNodi();
    @JsonIgnore
    List<ClsNodo> getNodiByComune(Long idComune);
    @JsonIgnore
    List<ClsComune> getComuneById(Long idComune);
    @JsonIgnore
    List<ClsRecensione> getRecensioniNodo(Long idNodo);
}
