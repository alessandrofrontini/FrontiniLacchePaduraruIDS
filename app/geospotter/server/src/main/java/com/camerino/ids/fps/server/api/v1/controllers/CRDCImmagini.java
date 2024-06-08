package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRDCImmagini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CRDCImmagini {
    public final static String mapping = BaseUrl.baseUrl + "/rdcimmagini";
    SRDCImmagini sRDCImmagini;

    @Autowired
    public CRDCImmagini(SRDCImmagini sRDCImmagini) {
        this.sRDCImmagini = sRDCImmagini;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsRDCImmagine>> getRDC(
            @RequestParam(value = "idRDCImmagini", required = false) Long idRDCImmagini
    ) {
        if (idRDCImmagini == null)
            return ResponseEntity.ok(sRDCImmagini.getAllRDCI());
        return ResponseEntity.ok(sRDCImmagini.getRDCIById(idRDCImmagini));
    }

    @DeleteMapping(mapping)
    public ResponseEntity<Boolean> deleteRDC(
            @RequestParam(value = "idRDCImmagini") Long idRDCI
    ) {
        return ResponseEntity.ok(sRDCImmagini.deleteRDCImmagineById(idRDCI));
    }

    @PutMapping(mapping)
    public ResponseEntity<Boolean> putRDCI(
            @RequestBody ClsRDCImmagine rdci
    ) {
        return ResponseEntity.ok(sRDCImmagini.putRDCI(rdci));
    }

    @PostMapping(mapping)
    public ResponseEntity<Boolean> postRDC(
            @RequestBody ClsRDCImmagine rdc
    ) {
        return ResponseEntity.ok(sRDCImmagini.postRDCImmagine(rdc));
    }
}
