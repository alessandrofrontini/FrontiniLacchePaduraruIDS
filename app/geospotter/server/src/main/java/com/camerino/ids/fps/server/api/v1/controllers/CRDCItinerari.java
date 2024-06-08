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
            @RequestParam(value = "idRDCItinerario", required = false) Long idRDCItinerario
    ) {
        if (idRDCItinerario == null)
            return ResponseEntity.ok(sRDCItinerari.getAllRDCItinerari());
        return ResponseEntity.ok(sRDCItinerari.getRDCItinerarioById(idRDCItinerario));
    }

    @DeleteMapping(mapping)
    public ResponseEntity<Boolean> deleteRDC(
            @RequestParam(value = "idRDCItinerario", required = false) Long idRDCI
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
}
