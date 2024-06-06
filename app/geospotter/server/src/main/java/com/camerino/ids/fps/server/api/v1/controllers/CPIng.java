package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.fps.server.api.v1.BaseUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Questo controller serve per pingare il server
 */
@RestController
public class CPIng {
    @GetMapping(BaseUrl.baseUrl + "/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pong");
    }
}
