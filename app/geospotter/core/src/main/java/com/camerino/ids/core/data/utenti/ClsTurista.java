package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.segnalazioni.ISignalable;
import com.camerino.ids.core.persistence.IPersistenceModel;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.HashMap;
=======
>>>>>>> origin/merge-front-back

/**
 * Rappresenta un utente non autenticato.
 */
public class ClsTurista implements ISignalable {
<<<<<<< HEAD
    transient IPersistenceModel<ClsNodo> pNodi;
    transient IPersistenceModel<ClsItinerario> pItinerari;
    transient IPersistenceModel<ClsComune> mockComuni;
    transient IPersistenceModel<ClsRecensione> iperRecensioni;
    transient IPersistenceModel<ClsSegnalazione> iperSegnalazioni;

    public ClsTurista(){}

=======
    IPersistenceModel<ClsSegnalazione> pSegnalazioni;
    public ClsTurista(){

    }
    public ClsTurista(IPersistenceModel<ClsSegnalazione> segnalazioni){ pSegnalazioni = segnalazioni;}
>>>>>>> origin/merge-front-back
    /**
     * Crea una segnalazione per il contenuto segnalato (Nodo, Foto, Recensione, Itinerario)
     * che verrà poi vista da un curatore.
     * @param segnalazione Segnalazione fatta
     * @return True se la creazione della segnalazione ha successo,
     *         False altrimenti.
     */

    @Override
    public boolean segnalaContenuto(ClsSegnalazione segnalazione) {
<<<<<<< HEAD
        return iperSegnalazioni.insert(segnalazione);
=======
        //Contenuti segnalabili: Nodo, Foto, Itinerario, Recensione (fonte: vpp)
        pSegnalazioni.insert(segnalazione);
        return false;
>>>>>>> origin/merge-front-back
    }


    public ArrayList<ClsComune> getAllComuni() {
        return mockComuni.get(null);
    }
    public ArrayList<ClsNodo> getAllNodi() {
        return pNodi.get(null);
    }

    public ArrayList<ClsNodo> getNodiByComune(String idComune) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idComune", idComune);
        return pNodi.get(filters);
    }

    public ArrayList<ClsComune> getComuneById(String idComune) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idComune", idComune);
        return mockComuni.get(filters);
    }

    public ArrayList<ClsRecensione> getRecensioniNodo(String idNodo) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idNodo", idNodo);
        return iperRecensioni.get(filters);
    }

//region Getters and Settera
    public IPersistenceModel<ClsNodo> getpNodi() {
        return pNodi;
    }

    public void setpNodi(IPersistenceModel<ClsNodo> pNodi) {
        this.pNodi = pNodi;
    }

    public IPersistenceModel<ClsItinerario> getpItinerari() {
        return pItinerari;
    }

    public void setpItinerari(IPersistenceModel<ClsItinerario> pItinerari) {
        this.pItinerari = pItinerari;
    }

    public IPersistenceModel<ClsComune> getMockComuni() {
        return mockComuni;
    }

    public void setMockComuni(IPersistenceModel<ClsComune> mockComuni) {
        this.mockComuni = mockComuni;
    }

    public IPersistenceModel<ClsRecensione> getIperRecensioni() {
        return iperRecensioni;
    }

    public void setIperRecensioni(IPersistenceModel<ClsRecensione> iperRecensioni) {
        this.iperRecensioni = iperRecensioni;
    }

    public IPersistenceModel<ClsSegnalazione> getIperSegnalazioni() {
        return iperSegnalazioni;
    }

    public void setIperSegnalazioni(IPersistenceModel<ClsSegnalazione> iperSegnalazioni) {
        this.iperSegnalazioni = iperSegnalazioni;
    }

    public ArrayList<ClsItinerario> getAllItinerari() {
        return this.pItinerari.get(null);
    }

    public ArrayList<ClsItinerario> getItinerarioById() {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idItinerario", pItinerari.get(filters));
        return this.pItinerari.get(filters);
    }

    public ArrayList<ClsNodo> getNodoById(String idNodo) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idNodo", idNodo);
        return this.pNodi.get(filters);
    }

    //endregion

}
