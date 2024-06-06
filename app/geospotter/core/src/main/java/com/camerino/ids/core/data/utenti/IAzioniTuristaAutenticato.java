package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface IAzioniTuristaAutenticato {
    boolean inserisciRecensione(ClsRecensione recensione);
    boolean eliminaRecensione(Long id);
    boolean modificaRecensione(Long IDDaModificare, ClsRecensione newrec);
    boolean inserisciImmagine(ClsImmagine immagine);
    boolean postRDCImmagine(ClsRDCImmagine rdc);
    boolean deleteRDCImmagineById(Long idRDCImmagine);
    @JsonIgnore
    List<ClsRecensione> getRecensioniPosessore();
}
