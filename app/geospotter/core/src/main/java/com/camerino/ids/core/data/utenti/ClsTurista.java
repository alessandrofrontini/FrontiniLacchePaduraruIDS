package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.segnalazioni.ISignalable;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;

import java.util.HashMap;
import java.util.List;

/**
 * Rappresenta un utente non autenticato.
 */
@MappedSuperclass
public class ClsTurista implements ISignalable {
    transient IPersistenceModel<ClsNodo> iperNodi;
    transient IPersistenceModel<ClsItinerario> iperItinerari;
    transient IPersistenceModel<ClsComune> iperComuni;
    transient IPersistenceModel<ClsRecensione> iperRecensioni;
    transient IPersistenceModel<ClsSegnalazione> iperSegnalazioni;
    transient IPersistenceModel<ClsImmagine> iperImmagini;
    transient IPersistenceModel<ClsTuristaAutenticato> iperUtenti;

    /**
     * Crea una segnalazione per il contenuto segnalato (Nodo, Foto, Recensione, Itinerario)
     * che verrà poi vista da un curatore.
     *
     * @param segnalazione Segnalazione fatta
     * @return True se la creazione della segnalazione ha successo,
     * False altrimenti.a
     */
    @Override
    public boolean segnalaContenuto(ClsSegnalazione segnalazione) {
        return iperSegnalazioni.insert(segnalazione);
    }

    @JsonIgnore
    public List<ClsComune> getAllComuni() {
        return iperComuni.get(null);
    }

    @JsonIgnore
    public List<ClsNodo> getAllNodi() {
        return iperNodi.get(null);
    }

    @JsonIgnore
    public List<ClsNodo> getNodiByComune(Long idComune) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idComune", idComune);
        return iperNodi.get(filters);
    }

    @JsonIgnore
    public List<ClsComune> getComuneById(Long idComune) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idComune", idComune);
        return iperComuni.get(filters);
    }

    @JsonIgnore
    public List<ClsRecensione> getRecensioniNodo(Long idNodo) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idNodo", idNodo);
        return iperRecensioni.get(filters);
    }

//region Getters and Settera

    public void setIperNodi(IPersistenceModel<ClsNodo> iperNodi) {
        this.iperNodi = iperNodi;
    }

    public void setIperItinerari(IPersistenceModel<ClsItinerario> iperItinerari) {
        this.iperItinerari = iperItinerari;
    }

    public void setIperComuni(IPersistenceModel<ClsComune> iperComuni) {
        this.iperComuni = iperComuni;
    }

    public void setIperRecensioni(IPersistenceModel<ClsRecensione> iperRecensioni) {
        this.iperRecensioni = iperRecensioni;
    }

    public void setIperSegnalazioni(IPersistenceModel<ClsSegnalazione> iperSegnalazioni) {
        this.iperSegnalazioni = iperSegnalazioni;
    }

    @JsonIgnore
    public List<ClsItinerario> getAllItinerari() {
        return this.iperItinerari.get(null);
    }

    @JsonIgnore
    public List<ClsItinerario> getItinerarioById() {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idItinerario", iperItinerari.get(filters));
        return this.iperItinerari.get(filters);
    }

    @JsonIgnore
    public List<ClsNodo> getNodoById(Long idNodo) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idNodo", idNodo);
        return this.iperNodi.get(filters);
    }

    public List<ClsSegnalazione> GetAllSegnalazioni() {
        return this.iperSegnalazioni.get(null);
    }

    @JsonIgnore
    public List<ClsRecensione> getAllRecensioni() {
        return this.iperRecensioni.get(null);
    }

    @JsonIgnore
    public List<ClsImmagine> getAllImmagini() {
        return this.iperImmagini.get(null);
    }

    @JsonIgnore
    public IPersistenceModel<ClsImmagine> getpIperImmagini() {
        return iperImmagini;
    }

    public void setpIperImmagini(IPersistenceModel<ClsImmagine> pImmagini) {
        this.iperImmagini = pImmagini;
    }

    public void setIperUtenti(IPersistenceModel<ClsTuristaAutenticato> iperUtenti) {
        this.iperUtenti = iperUtenti;
    }
    //endregion

}
