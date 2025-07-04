package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Questo ruolo accetta o respinge le richieste fatte dai vari utenti.
 * Ha anche la possibilità di regolare le autorizzazioni (ruoli) associati ai
 * vari utenti, per governare il loro comportamento.
 * Non è possibile diventare Curatore tramite sistema a punteggi.
 */
@Entity
public class ClsCuratore extends ClsAnimatore implements IAzioniCuratore {
    public Long getIdComuneAssociato() {
        return idComuneAssociato;
    }

    public void setIdComuneAssociato(Long idComuneAssociato) {
        this.idComuneAssociato = idComuneAssociato;
    }

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
    public List<ClsRDCImmagine> getRdcImmaginiPosessoreCur() {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idUtente", this.id);
        return this.iperRDCImmagini.get(filters);
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
    public List<ClsRDCNodo> getRDCNodiPosessoreCur() {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idUtente", this.id);
        return this.iperRDCNodi.get(filters);
    }

    @Override
    public List<ClsRdcItinerario> getRDCItinerarioPosessoreCur() {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idUtente", this.id);
        return this.iperRDCItinerari.get(filters);
    }

    @Override
    public List<ClsRDCImmagine> getRDCImmaginePossessoreCur() {
        List<ClsRDCImmagine> rdcPossessore = new ArrayList<>();
        for(ClsRDCImmagine rdc: getAllRDCImmagini()){
            if (Objects.equals(getNodoById(rdc.getNewData().getIdNodoAssociato()).get(0).getIdComuneAssociato(), idComuneAssociato)){
                rdcPossessore.add(rdc);
            }
        }
        return rdcPossessore;
    }

    @Override
    public List<ClsRDCNodo> getRDCNodoPossessoreCur() {
        List<ClsRDCNodo> rdcPossessore = new ArrayList<>();
        for(ClsRDCNodo rdc: getAllRDCNodi()){
            if(((rdc.getOldData()!=null)&&(Objects.equals(rdc.getOldData().getIdComuneAssociato(), idComuneAssociato)))||(Objects.equals(rdc.getNewData().getIdComuneAssociato(), idComuneAssociato)))
                rdcPossessore.add(rdc);
        }
        return rdcPossessore;
    }

    @Override
    public List<ClsRdcItinerario> getRDCItinerarioPossessoreCur() {
        List<ClsRdcItinerario> rdcIPossessore = new ArrayList<>();
        for(ClsRdcItinerario rdc:_getAllRDCItinerari()){
            if(((rdc.getOldData()!=null)&&(Objects.equals(rdc.getOldData().getTappe().get(0).getIdComuneAssociato(), idComuneAssociato)))||(Objects.equals(rdc.getNewData().getTappe().get(0).getIdComuneAssociato(), idComuneAssociato)))
                rdcIPossessore.add(rdc);
        }
        return rdcIPossessore;
    }
}
