package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SSegnalazioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CSegnalazioni {
    private final SSegnalazioni sSegnalazioni;
    private final String mapping = BaseUrl.baseUrl+"/segnalazioni";

    @Autowired
    public CSegnalazioni(SSegnalazioni sSegnalazioni) {
        this.sSegnalazioni = sSegnalazioni;
    }

    @GetMapping(mapping)
    public ResponseEntity<ArrayList<ClsSegnalazione>> getSegnalazioni() {
        return ResponseEntity.ok(sSegnalazioni.getAll());
    }

    @PostMapping(mapping)
    public ResponseEntity<Boolean> postSegnalazioni(
            @RequestBody ClsSegnalazione segnalazione) {
        //System.out.println("Segnalazione ricevuta "+segnalazione.visualizzaSegnalazione());

        return ResponseEntity.ok(sSegnalazioni.creaSegnalazione(segnalazione));
    }

    @PutMapping(mapping)
    public ResponseEntity<String> putSegnalazioni(
            @RequestBody ClsSegnalazione segnalazione) {
        return ResponseEntity.status(501).build();
    }

    @DeleteMapping(mapping)
    public ResponseEntity<Boolean> deleteSegnalazioni(
            @RequestParam("idSegnalazione") String idSegnalazione) {
        return ResponseEntity.ok(sSegnalazioni.deleteSgnalazione(idSegnalazione));
    }
}
