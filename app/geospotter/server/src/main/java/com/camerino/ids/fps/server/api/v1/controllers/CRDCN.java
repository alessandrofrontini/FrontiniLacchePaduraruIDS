package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@Deprecated
public class CRDCN {
    SRDC sRDC;
    public final static String mapping = BaseUrl.baseUrl+"/rdcn";

    @Autowired
    public CRDCN(SRDC sRDC) {
        this.sRDC = sRDC;
    }

    @GetMapping(mapping)
    public ResponseEntity<ArrayList<ClsRichiestaAzioneDiContribuzione>> getRDC(
            @RequestParam(value = "idRDC", required = false) String idRDC
    ) {
        if(idRDC != null)
            return ResponseEntity.ok(sRDC.getRDCById(idRDC));
        return ResponseEntity.ok(sRDC.getAllRDC());
    }
    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteRDC(
            @RequestParam(value = "idRDC", required = false) String idRDC
    ) {
        if(sRDC.deleteRDCById(idRDC))
            return ResponseEntity.ok("RDC deleted");
        return ResponseEntity.status(500).body("RDC not deleted");
    }
    @PutMapping(mapping)
    public ResponseEntity<String> putRDC(
            @RequestBody ClsRichiestaAzioneDiContribuzione rdc
    ){
        if(sRDC.putRDC(rdc))
            return ResponseEntity.ok("Azione Modificata");
        return ResponseEntity.status(500).body("Azione non modificata");
    }
    @PostMapping(mapping)
    public ResponseEntity<String> postRDC(
            @RequestBody ClsRichiestaAzioneDiContribuzione rdc
    ){
        if(sRDC.postRDC(rdc))
            return ResponseEntity.ok("Azione Creata");
        return ResponseEntity.status(500).body("Azione non creata");
    }
}
