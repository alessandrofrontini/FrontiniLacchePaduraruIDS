package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRDCImmagini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CRDCImmagini {
    SRDCImmagini sRDCImmagini;
    public final static String mapping = BaseUrl.baseUrl+"/rdcimmagini";

    @Autowired
    public CRDCImmagini(SRDCImmagini sRDCImmagini) {
        this.sRDCImmagini = sRDCImmagini;
    }

    @GetMapping(mapping)
    public ResponseEntity<ArrayList<ClsRDCImmagine>> getRDC(
            @RequestParam(value = "idRDCImmagini", required = false) String idRDCImmagini
    ) {
        if(idRDCImmagini == null)
            return ResponseEntity.ok(sRDCImmagini.getAllRDCI());
        return ResponseEntity.ok(sRDCImmagini.getRDCIById(idRDCImmagini));
    }
    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteRDC(
            @RequestParam(value = "idRDCI", required = false) String idRDCI
    ) {
        if(sRDCImmagini.deleteRDCIById(idRDCI))
            return ResponseEntity.ok("RDC deleted");
        return ResponseEntity.status(500).body("RDC not deleted");
    }
    @PutMapping(mapping)
    public ResponseEntity<String> putRDCI(
            @RequestBody ClsRDCImmagine rdci
    ){
        if(sRDCImmagini.putRDCI(rdci))
            return ResponseEntity.ok("Azione Modificata");
        return ResponseEntity.status(500).body("Azione non modificata");
    }
    @PostMapping(mapping)
    public ResponseEntity<String> postRDC(
            @RequestBody ClsRDCImmagine rdc
    ){
        if(sRDCImmagini.postRDCImmagine(rdc))
            return ResponseEntity.ok("Azione Creata");
        return ResponseEntity.status(500).body("Azione non creata");
    }
}
