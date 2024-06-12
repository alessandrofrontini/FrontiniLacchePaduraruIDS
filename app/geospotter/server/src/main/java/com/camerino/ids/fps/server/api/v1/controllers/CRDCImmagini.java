package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SRDCImmagini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CRDCImmagini {
    public final static String mapping = BaseUrl.baseUrl + "/rdcimmagini";
    SRDCImmagini sRDCImmagini;

    @Autowired
    public CRDCImmagini(SRDCImmagini sRDCImmagini) {
        this.sRDCImmagini = sRDCImmagini;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsRDCImmagine>> getRDC(
            @RequestParam(value = "idRDCImmagini", required = false) Long idRDCImmagini,
            @RequestParam(value = "idUtente", required = false) Long idUtente,
            @RequestParam(value = "onlyContest", required = false) Boolean onlyContest
    ) {
        if(idUtente!=null)
        {
            if (onlyContest!=null)
                return ResponseEntity.ok(sRDCImmagini.GetRDCImmaginePosessore());
            return ResponseEntity.ok(sRDCImmagini.GetRDCImmaginePosessoreCur());
        }
        return ResponseEntity.ok(sRDCImmagini.getRDCIById(idRDCImmagini));
    }

    @DeleteMapping(mapping)
    public ResponseEntity<Boolean> deleteRDC(
            @RequestParam(value = "idRDCImmagini") Long idRDCI
    ) {
        return ResponseEntity.ok(sRDCImmagini.deleteRDCImmagineById(idRDCI));
    }

    @PutMapping(mapping)
    public ResponseEntity<Boolean> putRDCI(
            @RequestBody ClsRDCImmagine rdci
    ) {
        return ResponseEntity.ok(sRDCImmagini.putRDCI(rdci));
    }

    @PostMapping(mapping)
    public ResponseEntity<Boolean> postRDC(
            @RequestBody ClsRDCImmagine rdc
    ) {
        return ResponseEntity.ok(sRDCImmagini.postRDCImmagine(rdc));
    }
    @GetMapping(mapping+"/accetta")
    public ResponseEntity<Boolean> RDC(
            @RequestParam(value = "idValidazione") Long idRDC
    ) {
        return ResponseEntity.ok(sRDCImmagini.accettaRDCImmagine(idRDC));
    }

    @GetMapping(mapping+"/rifiuta")
    public ResponseEntity<Boolean> rifiutaRDC(
            @RequestParam(value = "idValidazione", required = false) Long idRDC
    ) {
        return ResponseEntity.ok(sRDCImmagini.rifutaRDCImmagine(idRDC));
    }
}
