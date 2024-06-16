package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SItinerari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CItinerari {
    public final static String mapping = BaseUrl.baseUrl + "/itinerari";
    SItinerari sItinerari;

    @Autowired
    public CItinerari(SItinerari sItinerari) {
        this.sItinerari = sItinerari;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsItinerario>> getItinerari(
            @RequestParam(name = "idItinerario", required = false) Long idItinerario,
            @RequestParam(name = "owner", required = false) Long owner
    ) {
        if (idItinerario != null)
            return ResponseEntity.ok(sItinerari.getItinerarioById(idItinerario));
        if(owner!=null)
            return ResponseEntity.ok(sItinerari.getItinerariPosessore());
        return ResponseEntity.ok(sItinerari.getAllItinerari());
    }

    @PostMapping(mapping)
    public ResponseEntity<Boolean> postItinerari(
            @RequestBody ClsItinerario itinerario
    ) {
        return ResponseEntity.ok(sItinerari.postItinerario(itinerario));
    }

    @PutMapping(mapping)
    public ResponseEntity<Boolean> putItinerari(
            @RequestBody ClsItinerario itinerario
    ) {
        return ResponseEntity.ok(sItinerari.putItinerario(itinerario));
    }

    @DeleteMapping(mapping)
    public ResponseEntity<Boolean> deleteItinerari(
            @RequestParam(name = "idItinerario", required = false) Long idItinerario
    ) {
        return ResponseEntity.ok(sItinerari.deleteItinerario(idItinerario));
    }
}
