package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SComuni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CComuni {
    SComuni sComnuni;
    public final static String mappin = BaseUrl.baseUrl+"/comuni";

    @Autowired
    public CComuni(SComuni sComnuni) {
        this.sComnuni = sComnuni;
    }

    @GetMapping
    public ResponseEntity<ArrayList<ClsComune>> getComuni(
            @RequestParam(value = "idComune", required = false) String idComune
    ) {
        if(idComune == null)
            return ResponseEntity.ok(sComnuni.getAllComuni());
       return ResponseEntity.ok(sComnuni.getComuneById(idComune));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteComune(
            @RequestParam(value = "idComune", required = false) String idComune
    ) {
       if(sComnuni.deleteComuneById(idComune))
           return ResponseEntity.ok("Comune deleted");
       return ResponseEntity.status(500).body("Comune not deleted");
    }
    @PutMapping
    public ResponseEntity<String> putComune(
            @RequestBody ClsNodo nodo
    ){
        return ResponseEntity.status(501).build();
    }
    @PostMapping
    public ResponseEntity<String> postComune(
            @RequestBody ClsNodo nodo
    ){
        return ResponseEntity.status(501).build();
    }
}
