package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SComuni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CComuni {
    public final static String mapping = BaseUrl.baseUrl + "/comuni";
    SComuni sComnuni;

    @Autowired
    public CComuni(SComuni sComnuni) {
        this.sComnuni = sComnuni;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsComune>> getComuni(
            @RequestParam(value = "idComune", required = false) Long idComune
    ) {

        if (idComune == null)
            return ResponseEntity.ok(sComnuni.getAllComuni());
        return ResponseEntity.ok(sComnuni.getComuneById(idComune));
    }

    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteComune(
            @RequestParam(value = "idComune", required = false) Long idComune
    ) {
        if (sComnuni.deleteComuneById(idComune))
            return ResponseEntity.ok("Comune deleted");
        return ResponseEntity.status(500).body("Comune not deleted");
    }

    @PutMapping(mapping)
    public ResponseEntity<String> putComune(
            @RequestBody ClsComune comune
    ) {
        return ResponseEntity.ok(Boolean.toString(sComnuni.putComune(comune)));
    }

    @PostMapping(mapping)
    public ResponseEntity<String> postComune(
            @RequestBody ClsComune comune
    ) {
        return ResponseEntity.ok(Boolean.toString(sComnuni.postComune(comune)));
    }
}
