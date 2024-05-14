package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Questo ruolo accetta o respinge le richieste fatte dai vari utenti.
 * Ha anche la possibilità di regolare le autorizzazioni (ruoli) associati ai
 * vari utenti, per governare il loro comportamento.
 * Non è possibile diventare Curatore tramite sistema a punteggi.
 */
@Entity
public class ClsCuratore extends ClsAnimatore{
    //TODO: Ha senso? é una relazione molti a molti dovremmo creare una classe
    // che associa comuni e curatori. Se un utente arriva ad essere curatore in pi comuni
    // creaiamo deve creare un'altro account?
    // (modificato id da String a long)
    String idComuneAssociato;
    public ClsCuratore(){
        this.punteggio = Integer.MAX_VALUE;
    }

    public ArrayList<ClsSegnalazione> _getAllSegnalazioni() {
        return iperSegnalazioni.get(null);
    }

    public ArrayList<ClsRichiestaAzioneDiContribuzione> _getAllRDC() {
        return new ArrayList<>(pRDC.get(null));
    }

    public ArrayList<ClsRichiestaAzioneDiContribuzione> getRDCById(String idRDC) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDC", idRDC);
        return pRDC.get(filters);
    }

    public boolean deleteRDCById(String idRDC) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDC", idRDC);
        return pRDC.delete(filters);
    }

    public boolean putRDC(ClsRichiestaAzioneDiContribuzione rdc) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("idRDC", rdc.getId());
        return pRDC.update(filters, rdc);
    }

    public boolean postRDC(ClsRichiestaAzioneDiContribuzione rdc) {
        return pRDC.insert(rdc);
    }

    public ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> _getAllRDCI() {
        return pRDCI.get(null);
    }

    public ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> getRDCIById(String idRDCI) {
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
}
