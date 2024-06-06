package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SImmagini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CImmagini {
    public final static String mapping = BaseUrl.baseUrl + "/immagini";
    SImmagini sImmagini;

    @Autowired
    public CImmagini(SImmagini sImmagini) {
        this.sImmagini = sImmagini;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsImmagine>> postComuni(

    ) {
        return ResponseEntity.ok(sImmagini.getAllImmagini());
    }
}
