package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRDCI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
public class CRDCI {
    SRDCI sRDCI;
    public final static String mapping = BaseUrl.baseUrl+"/rdci";

    @Autowired
    public CRDCI(SRDCI sRDCI) {
        this.sRDCI = sRDCI;
    }

    @GetMapping(mapping)
    public ResponseEntity<ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario>> getRDC(
            @RequestParam(value = "idRDCI", required = false) String idRDCI
    ) {

        if(idRDCI == null)
            return ResponseEntity.ok(sRDCI.getAllRDCI());
        return ResponseEntity.ok(sRDCI.getRDCIById(idRDCI));
    }
    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteRDC(
            @RequestParam(value = "idRDCI", required = false) String idRDCI
    ) {
        if(sRDCI.deleteRDCIById(idRDCI))
            return ResponseEntity.ok("RDC deleted");
        return ResponseEntity.status(500).body("RDC not deleted");
    }
    @PutMapping(mapping)
    public ResponseEntity<String> putRDCI(
            @RequestBody ClsRichiestaAzioneDiContribuzioneItinerario rdci
    ){
        if(sRDCI.putRDCI(rdci))
            return ResponseEntity.ok("Azione Modificata");
        return ResponseEntity.status(500).body("Azione non modificata");
    }
    @PostMapping(mapping)
    public ResponseEntity<String> postRDC(
            @RequestBody ClsRichiestaAzioneDiContribuzioneItinerario rdc
    ){
        if(sRDCI.postRDCI(rdc))
            return ResponseEntity.ok("Azione Creata");
        return ResponseEntity.status(500).body("Azione non creata");
    }
}
