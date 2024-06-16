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
    public ResponseEntity<Boolean> deleteComune(
            @RequestParam(value = "idComune") Long idComune
    ) {
        return ResponseEntity.ok(sComnuni.deleteComuneById(idComune));
    }

    @PutMapping(mapping)
    public ResponseEntity<Boolean> putComune(
            @RequestBody ClsComune comune
    ) {
        return ResponseEntity.ok(sComnuni.putComune(comune));
    }

    @PostMapping(mapping)
    public ResponseEntity<Boolean> postComune(
            @RequestBody ClsComune comune
    ) {
        return ResponseEntity.ok(sComnuni.postComune(comune));
    }
}
