package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRDCItinerari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CRDCItinerari {
    SRDCItinerari sRDCItinerari;
    public final static String mapping = BaseUrl.baseUrl+"/rdcitinerari";

    @Autowired
    public CRDCItinerari(SRDCItinerari sRDCItinerari) {
        this.sRDCItinerari = sRDCItinerari;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsRdcItinerario>> getRDC(
            @RequestParam(value = "idRDCItinerario", required = false) String idRDCItinerario
    ) {
        if(idRDCItinerario == null)
            return ResponseEntity.ok(sRDCItinerari.getAllRDCItinerari());
        return ResponseEntity.ok(sRDCItinerari.getRDCItinerarioById(idRDCItinerario));
    }
    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteRDC(
            @RequestParam(value = "idRDCItinerario", required = false) String idRDCI
    ) {
        if(sRDCItinerari.deleteRDCItinerarioById(idRDCI))
            return ResponseEntity.ok("RDC deleted");
        return ResponseEntity.status(500).body("RDC not deleted");
    }
    @PutMapping(mapping)
    public ResponseEntity<String> putRDCI(
            @RequestBody ClsRdcItinerario rdc
    ){
        if(sRDCItinerari.putRDCItinerario(rdc))
            return ResponseEntity.ok("Azione Modificata");
        return ResponseEntity.status(500).body("Azione non modificata");
    }
    @PostMapping(mapping)
    public ResponseEntity<String> postRDC(
            @RequestBody ClsRdcItinerario rdc
    ){
        if(sRDCItinerari.postRDCItinerario(rdc))
            return ResponseEntity.ok("Azione Creata");
        return ResponseEntity.status(500).body("Azione non creata");
    }
}
