package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRDCItinerari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CRDCItinerari {
    public final static String mapping = BaseUrl.baseUrl + "/rdcitinerari";
    SRDCItinerari sRDCItinerari;

    @Autowired
    public CRDCItinerari(SRDCItinerari sRDCItinerari) {
        this.sRDCItinerari = sRDCItinerari;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsRdcItinerario>> getRDC(
            @RequestParam(value = "idRDCItinerario", required = false) Long idRDCItinerario,
            @RequestParam(value = "idUtente", required = false) Long idUtente
    ) {
        if (idRDCItinerario != null)
            return ResponseEntity.ok(sRDCItinerari.getRDCItinerarioById(idRDCItinerario));
        if(idUtente != null)
            return ResponseEntity.ok(sRDCItinerari.getAllRDCItinerariCur());
        return ResponseEntity.ok(sRDCItinerari.getAllRDCItinerari());
    }

    @DeleteMapping(mapping)
    public ResponseEntity<Boolean> deleteRDC(
            @RequestParam(value = "idRDCItinerario") Long idRDCI
    ) {
        return ResponseEntity.ok(sRDCItinerari.deleteRDCItinerarioById(idRDCI));
    }

    @PutMapping(mapping)
    public ResponseEntity<Boolean> putRDCI(
            @RequestBody ClsRdcItinerario rdc
    ) {
        return ResponseEntity.ok(sRDCItinerari.putRDCItinerario(rdc));
    }

    @PostMapping(mapping)
    public ResponseEntity<Boolean> postRDC(
            @RequestBody ClsRdcItinerario rdc
    ) {
        return ResponseEntity.ok(sRDCItinerari.postRDCItinerario(rdc));
    }

    @GetMapping(mapping+"/accetta")
    public ResponseEntity<Boolean> RDC(
            @RequestParam(value = "idValidazione") Long idRDC
    ) {
        return ResponseEntity.ok(sRDCItinerari.accettaRDCNodo(idRDC));
    }

    @GetMapping(mapping+"/rifiuta")
    public ResponseEntity<Boolean> rifiutaRDC(
            @RequestParam(value = "idValidazione", required = false) Long idRDC
    ) {
        return ResponseEntity.ok(sRDCItinerari.rifutaRDCNodi(idRDC));
    }
}
