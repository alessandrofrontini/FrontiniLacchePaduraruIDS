package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato.eRUOLI_UTENTE;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SUtenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CUtenti {
    private final SUtenti sUtenti;
    private final String mapping = BaseUrl.baseUrl + "/utenti";

    @Autowired
    public CUtenti(SUtenti sUtenti) {
        this.sUtenti = sUtenti;
    }

    @GetMapping(value = mapping)
    public ResponseEntity<List<ClsTuristaAutenticato>> getUtenti(
            @RequestParam(value = "ruolo", required = false) eRUOLI_UTENTE ruolo,
            @RequestParam(value = "freeCurartori", required = false) Boolean freeContr
    ) {
        if (ruolo != null)
            return ResponseEntity.ok(sUtenti.getUtentiByRuolo(ruolo));
        if(freeContr != null)
            return ResponseEntity.ok(sUtenti.getFreeCuratori().stream()
                    .map(cur->(ClsTuristaAutenticato)cur).toList());
        return ResponseEntity.ok(sUtenti.getAllUtenti());
    }
}
