package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Questo ruolo accetta o respinge le richieste fatte dai vari utenti.
 * Ha anche la possibilità di regolare le autorizzazioni (ruoli) associati ai
 * vari utenti, per governare il loro comportamento.
 * Non è possibile diventare Curatore tramite sistema a punteggi.
 */
@Entity
public class ClsCuratore extends ClsAnimatore implements IAzioniCuratore {
    Long idComuneAssociato;

    //region Constructors
    public ClsCuratore(ClsAnimatore usr) {
        this.credenziali = usr.credenziali;
        this.id = usr.id;

        this.iperNodi = usr.iperNodi;
        this.iperItinerari = usr.iperItinerari;
        this.iperComuni = usr.iperComuni;
        this.iperRecensioni = usr.iperRecensioni;
        this.iperSegnalazioni = usr.iperSegnalazioni;
        this.iperUtenti = usr.iperUtenti;
        this.iperRDCImmagini = usr.iperRDCImmagini;
        this.iperRDCNodi = usr.iperRDCNodi;
        this.iperContest = usr.iperContest;
        this.iperRDCItinerari = usr.iperRDCItinerari;
    }

    public ClsCuratore() {
        this.punteggio = Integer.MAX_VALUE;
        this.ruoloUtente = eRUOLI_UTENTE.CURATORE;
    }
    //endregion

    public List<ClsSegnalazione> _getAllSegnalazioni() {
        return iperSegnalazioni.get(null);
    }

    public List<ClsRDCNodo> _getAllRDCNodi() {
        return iperRDCNodi.get(null);
    }

    @Override
    public boolean putRDCImmagine(ClsRDCImmagine rdci) {
        return iperRDCImmagini.update(null, rdci);
    }

    public List<ClsRdcItinerario> _getAllRDCItinerari() {
        return iperRDCItinerari.get(null);
    }

    public List<ClsRdcItinerario> getRDCItinerarioById(Long idRDCItinerario) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDCItinerario", idRDCItinerario);
        return iperRDCItinerari.get(filters);
    }

    public boolean deleteRDCItinerario(Long idRDCItinerario) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDCItinerario", idRDCItinerario);
        return iperRDCItinerari.delete(filters);
    }

    public boolean putRDCItinerario(ClsRdcItinerario rdc) {
        return iperRDCItinerari.update(null, rdc);
    }

    @Override
    public List<ClsRDCNodo> getAllRDCNodi() {
        return this.iperRDCNodi.get(null);
    }

    @Override
    public List<ClsRDCImmagine> getAllRDCImmagini() {
        return this.iperRDCImmagini.get(null);
    }

    @Override
    public boolean accettaRichiestaNodo(Long idValidazione) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idValidazione", idValidazione);
        filters.put("accetta", true);
        iperRDCNodi.get(filters);
        return true;
    }

    @Override
    public boolean rifiutaRichiestaNodo(Long idValidazione) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idValidazione", idValidazione);
        filters.put("accetta", false);
        iperRDCNodi.get(filters);
        return true;
    }

    @Override
    public boolean accettaRichiestaItinerario(Long idValidazione) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idValidazione", idValidazione);
        filters.put("accetta", true);
        iperRDCItinerari.get(filters);
        return true;
    }

    @Override
    public boolean rifiutaRichiestaImmagine(String idValidazione) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idValidazione", idValidazione);
        filters.put("accetta", false);
        iperRDCImmagini.get(filters);
        return true;
    }

    @Override
    public boolean accettaRichiestaImmagine(Long idValidazione) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idValidazione", idValidazione);
        filters.put("accetta", true);
        iperRDCImmagini.get(filters);
        return true;
    }

    @Override
    public boolean rifiutaRichiestaItinerario(Long idValidazione) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idValidazione", idValidazione);
        filters.put("accetta", false);
        iperRDCItinerari.get(filters);
        return true;
    }
}
