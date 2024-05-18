package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SComuni;
import com.camerino.ids.fps.server.api.v1.services.SImmagini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CImmagini
{
    SImmagini sImmagini;
    public final static String mapping = BaseUrl.baseUrl+"/immagini";

    @Autowired
    public CImmagini(SImmagini sImmagini) {
        this.sImmagini = sImmagini;
    }

    @GetMapping(mapping)
    public ResponseEntity<ArrayList<ClsImmagine>> getComuni(
            @RequestParam(value = "idImmagine", required = false) String idComune
    )
    {
        if(idComune == null)
            return ResponseEntity.ok(sImmagini.getAllImmagini());
        return ResponseEntity.ok(sImmagini.getImmagineById(idComune));
    }
    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteComune(
            @RequestParam(value = "idComune", required = false) String idComune
    ) {
        return null;
    }
    @PutMapping(mapping)
    public ResponseEntity<String> putComune(
            @RequestBody ClsNodo nodo
    ){
        return ResponseEntity.status(501).build();
    }
    @PostMapping(mapping)
    public ResponseEntity<String> postComune(
            @RequestBody ClsNodo nodo
    ){
        return ResponseEntity.status(501).build();
    }
}
