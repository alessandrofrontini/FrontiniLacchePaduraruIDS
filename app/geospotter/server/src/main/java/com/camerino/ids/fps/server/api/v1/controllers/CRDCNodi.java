package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRDCNodi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CRDCNodi {
    SRDCNodi sRDCnodi;
    public final static String mapping = BaseUrl.baseUrl+"/rdcnodi";

    @Autowired
    public CRDCNodi(SRDCNodi sRDCnodi) {
        this.sRDCnodi = sRDCnodi;
    }

    @GetMapping(mapping)
    public ResponseEntity<ArrayList<ClsRDCNodo>> getRDC(
            @RequestParam(value = "idRDC", required = false) String idRDC
    ) {
        if(idRDC != null)
            return ResponseEntity.ok(sRDCnodi.getRDCNodiById(idRDC));
        return ResponseEntity.ok(sRDCnodi.getAllRDCNodi());
    }
    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteRDC(
            @RequestParam(value = "idRDCNodi", required = false) String idRDC
    ) {
        if(sRDCnodi.deleteRDCNodiById(idRDC))
            return ResponseEntity.ok("RDC deleted");
        return ResponseEntity.status(500).body("RDC not deleted");
    }
    @PutMapping(mapping)
    public ResponseEntity<String> putRDC(
            @RequestBody ClsRDCNodo rdc
    ){
        if(sRDCnodi.putRDCNodi(rdc))
            return ResponseEntity.ok("Azione Modificata");
        return ResponseEntity.status(500).body("Azione non modificata");
    }
    @PostMapping(mapping)
    public ResponseEntity<String> postRDC(
            @RequestBody ClsRDCNodo rdc
    ){
        if(sRDCnodi.postRDCNodi(rdc))
            return ResponseEntity.ok("Azione Creata");
        return ResponseEntity.status(500).body("Azione non creata");
    }
}