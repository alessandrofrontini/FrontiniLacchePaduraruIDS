package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRecensioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CRecensioni {
    private final SRecensioni sRecensioni;
    private final String mapping = BaseUrl.baseUrl+"/recensioni";

    @Autowired
    public CRecensioni(SRecensioni sRecensioni){
        this.sRecensioni = sRecensioni;
    }

    @GetMapping(mapping)
    public ResponseEntity<ArrayList<ClsRecensione>> getRecensioni(
            @RequestParam(value = "idNodo") String idNodo
    ){
        return ResponseEntity.ok(sRecensioni.getRecensioniNodo(idNodo));
    }

    @PostMapping(mapping)
    public ResponseEntity<String> postRecensioni(
            @RequestBody ClsRecensione recensione
    ){
        return ResponseEntity.status(501).body(null);
    }

    @PutMapping(mapping)
    public ResponseEntity<String> putRecensioni(
            @RequestBody ClsRecensione recensione
    ){
        return ResponseEntity.status(501).body(null);
    }

    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteRecensioni(
            @RequestParam(value = "idRecensione") String idRecensione
    ){
        if(!sRecensioni.deleteRecensioneById(idRecensione))
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(null);
    }
}
