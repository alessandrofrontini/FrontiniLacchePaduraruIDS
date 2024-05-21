package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SUtenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CUtenti {
    private final SUtenti sUtenti;
    private final String mapping = BaseUrl.baseUrl+"/utenti";

    @Autowired
    public CUtenti(SUtenti sUtenti) {
        this.sUtenti = sUtenti;
    }

    @GetMapping(value = mapping, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ClsTuristaAutenticato>> getUtenti(
            @RequestParam(value = "ruolo", required = false) String ruolo
    ){
        ArrayList<ClsTuristaAutenticato> body;
//        if(ruolo != null)
//        {
//            body = sUtenti.getUtentiPerGestionePunteggio(ruolo);
//            return ResponseEntity.ok(body);
//        }
//
//        else
//        {

            body = sUtenti.getAllUtenti();
            return ResponseEntity.ok(body);
//        }

    }
}
