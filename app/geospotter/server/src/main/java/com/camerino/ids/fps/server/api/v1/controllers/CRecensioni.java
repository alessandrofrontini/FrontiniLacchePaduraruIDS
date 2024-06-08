package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRecensioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CRecensioni {
    private final SRecensioni sRecensioni;
    private final String mapping = BaseUrl.baseUrl + "/recensioni";

    @Autowired
    public CRecensioni(SRecensioni sRecensioni) {
        this.sRecensioni = sRecensioni;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsRecensione>> getRecensioni(
            @RequestParam(value = "idNodo", required = false) Long idNodo,
            @RequestParam(value = "owner", required = false) Long owner
    ) {
        //return ResponseEntity.ok(sRecensioni.getRecensioniNodo(idNodo));
        if (owner != null)
            return ResponseEntity.ok(sRecensioni.getRecensioniPossesore());
        return ResponseEntity.ok(sRecensioni.getAllRecensioni());
    }

    @PostMapping(mapping)
    public ResponseEntity<Boolean> postRecensioni(
            @RequestBody ClsRecensione recensione
    ) {
        return ResponseEntity.ok(sRecensioni.postRecensione(recensione));
    }

    @PutMapping(mapping)
    public ResponseEntity<Boolean> putRecensioni(
            @RequestBody ClsRecensione recensione
    ) {
        return ResponseEntity.ok(sRecensioni.putRecensione(recensione));
    }

    @DeleteMapping(mapping)
    public ResponseEntity<Boolean> deleteRecensioni(
            @RequestParam(value = "idRecensione") Long idRecensione
    ) {
        if (!sRecensioni.deleteRecensioneById(idRecensione))
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(null);
    }
}
