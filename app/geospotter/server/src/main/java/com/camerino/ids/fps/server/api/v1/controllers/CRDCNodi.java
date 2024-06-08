package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRDCNodi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CRDCNodi {
    public final static String mapping = BaseUrl.baseUrl + "/rdcnodi";
    SRDCNodi sRDCnodi;

    @Autowired
    public CRDCNodi(SRDCNodi sRDCnodi) {
        this.sRDCnodi = sRDCnodi;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsRDCNodo>> getRDC(
            @RequestParam(value = "idRDC", required = false) Long idRDC,
            @RequestParam(value = "owner", required = false) Long owner
    ) {
        if (idRDC != null)
            return ResponseEntity.ok(sRDCnodi.getRDCNodiById(idRDC));
        return ResponseEntity.ok(sRDCnodi.getAllRDCNodi());
    }

    @GetMapping(mapping+"/accetta")
    public ResponseEntity<Boolean> RDC(
            @RequestParam(value = "idValidazione") Long idRDC
    ) {
        return ResponseEntity.ok(sRDCnodi.accettaRDCNodo(idRDC));
    }

    @GetMapping(mapping+"/rifiuta")
    public ResponseEntity<Boolean> rifiutaRDC(
            @RequestParam(value = "idValidazione", required = false) Long idRDC
    ) {
        return ResponseEntity.ok(sRDCnodi.rifutaRDCNodi(idRDC));
    }

    @DeleteMapping(mapping)
    public ResponseEntity<Boolean> deleteRDC(
            @RequestParam(value = "idRDCNodi") Long idRDC
    ) {
        return ResponseEntity.ok(sRDCnodi.deleteRDCNodiById(idRDC));
    }

    @PutMapping(mapping)
    public ResponseEntity<Boolean> putRDC(
            @RequestBody ClsRDCNodo rdc
    ) {
        return ResponseEntity.ok(sRDCnodi.putRDCNodi(rdc));
    }

    @PostMapping(mapping)
    public ResponseEntity<Boolean> postRDC(
            @RequestBody ClsRDCNodo rdc
    ) {
        return ResponseEntity.ok(sRDCnodi.postRDCNodi(rdc));
    }
}
