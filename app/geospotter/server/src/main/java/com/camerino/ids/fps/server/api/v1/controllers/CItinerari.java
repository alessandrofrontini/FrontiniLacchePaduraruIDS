package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SItinerari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CItinerari {
    SItinerari sItinerari;
    public final static String mapping = BaseUrl.baseUrl+"/itinerari";

    @Autowired
    public CItinerari(SItinerari sItinerari) {
        this.sItinerari = sItinerari;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsItinerario>> getItinerari(
            @RequestParam(name = "idItinerario", required = false)  String idItinerario
    ) {
        if(idItinerario == null)
            return ResponseEntity.ok(sItinerari.getAllItinerari());
        return ResponseEntity.ok(sItinerari.getItinerarioById(idItinerario));
    }

    @PostMapping(mapping)
    public ResponseEntity<String> postItinerari(
            @RequestBody ClsItinerario itinerario
    ){
        if(sItinerari.postItinerario(itinerario))
            return ResponseEntity.ok("OK");
        return ResponseEntity.ok("ERROR");
    }

    @PutMapping(mapping)
    public ResponseEntity<String> putItinerari(
            @RequestBody ClsItinerario itinerario
    ){
        if(sItinerari.putItinerario(itinerario))
            return ResponseEntity.ok("OK");
        return ResponseEntity.ok("ERROR");
    }

    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteItinerari(
            @RequestParam(name = "idItinerario", required = false)  String idItinerario
    ){
        if(sItinerari.deleteItinerario(idItinerario))
            return ResponseEntity.ok("OK");
        return ResponseEntity.ok("ERROR");
    }
}
