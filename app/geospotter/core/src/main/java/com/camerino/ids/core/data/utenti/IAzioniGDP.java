package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface IAzioniGDP {
    boolean inserisciComune(ClsComune comune);
    boolean modificaComune(ClsComune comune, Long id);
    boolean eliminaComune(Long id);
    @JsonIgnore
    List<ClsCuratore> getAllCuratori();
    @JsonIgnore
    List<ClsTuristaAutenticato> getUtentiByRuolo(ClsTuristaAutenticato.eRUOLI_UTENTE ruolo);
    @JsonIgnore
    List<ClsCuratore> getFreeCuratori();
}
