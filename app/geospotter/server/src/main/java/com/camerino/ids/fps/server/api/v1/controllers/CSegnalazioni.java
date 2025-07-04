package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SSegnalazioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CSegnalazioni {
    private final SSegnalazioni sSegnalazioni;
    private final String mapping = BaseUrl.baseUrl + "/segnalazioni";

    @Autowired
    public CSegnalazioni(SSegnalazioni sSegnalazioni) {
        this.sSegnalazioni = sSegnalazioni;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsSegnalazione>> getSegnalazioni() {
        return ResponseEntity.ok(sSegnalazioni.getAll());
    }

    @PostMapping(mapping)
    public ResponseEntity<Boolean> postSegnalazioni(
            @RequestBody ClsSegnalazione segnalazione) {
        //System.out.println("Segnalazione ricevuta "+segnalazione.visualizzaSegnalazione());

        return ResponseEntity.ok(sSegnalazioni.creaSegnalazione(segnalazione));
    }
}
