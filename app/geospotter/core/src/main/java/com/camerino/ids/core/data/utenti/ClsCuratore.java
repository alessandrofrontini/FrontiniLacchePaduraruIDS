package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import jakarta.persistence.Entity;

import java.util.HashMap;
import java.util.List;

/**
 * Questo ruolo accetta o respinge le richieste fatte dai vari utenti.
 * Ha anche la possibilità di regolare le autorizzazioni (ruoli) associati ai
 * vari utenti, per governare il loro comportamento.
 * Non è possibile diventare Curatore tramite sistema a punteggi.
 */
@Entity
public class ClsCuratore extends ClsAnimatore{
    String idComuneAssociato;

    //region Constructors
    public ClsCuratore(ClsAnimatore usr){
        this.pRDC = usr.pRDC;
        this.pRDCI = usr.pRDCI;

        this.credenziali = usr.credenziali;
        this.id = usr.id;

        this.pNodi = usr.pNodi;
        this.pItinerari = usr.pItinerari;
        this.mockComuni = usr.mockComuni;
        this.iperRecensioni = usr.iperRecensioni;
        this.iperSegnalazioni = usr.iperSegnalazioni;
        this.iperUtenti = usr.iperUtenti;
        this.iperRDCImmagini = usr.iperRDCImmagini;
        this.iperRDCNodi = usr.iperRDCNodi;
    }

    public ClsCuratore(){
        this.punteggio = Integer.MAX_VALUE;
        this.ruoloUtente = eRUOLO_UTENTE.CURATORE;
    }
    //endregion

    public List<ClsSegnalazione> _getAllSegnalazioni() {
        return iperSegnalazioni.get(null);
    }
    public List<ClsRDCNodo> _getAllRDCNodi() {
        return iperRDCNodi.get(null);
    }
    public List<ClsRichiestaAzioneDiContribuzioneItinerario> _getAllRDCI() {
        return pRDCI.get(null);
    }
    public List<ClsRichiestaAzioneDiContribuzioneItinerario> getRDCIById(String idRDCI) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDCI", idRDCI);
        return pRDCI.get(filters);
    }

    public boolean putRDCI(ClsRichiestaAzioneDiContribuzioneItinerario rdci) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDCI", rdci.getId());
        return pRDCI.update(filters, rdci);
    }

    public boolean postRDCI(ClsRichiestaAzioneDiContribuzioneItinerario rdci) {
        return pRDCI.insert(rdci);
    }

    @Override
    public boolean putRDCImmagine(ClsRDCImmagine rdci) {
        return iperRDCImmagini.update(null, rdci);
    }

    public List<ClsRdcItinerario> _getAllRDCItinerari() {
        return iperRDCItinerari.get(null);
    }

    public List<ClsRdcItinerario> getRDCItinerarioById(String idRDCItinerario) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDCItinerario", idRDCItinerario);
        return iperRDCItinerari.get(filters);
    }

    public boolean deleteRDCItinerario(String idRDCItinerario) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDCItinerario", idRDCItinerario);
        return iperRDCItinerari.delete(filters);
    }

    public boolean putRDCItinerario(ClsRdcItinerario rdc) {
        return iperRDCItinerari.update(null, rdc);
    }
}
