package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CLogin {
    public final static String mapping = BaseUrl.baseUrl + "/login";
    SLogin sLogin;

    @Autowired
    public CLogin(SLogin sLogin) {
        this.sLogin = sLogin;
    }

    @PostMapping(mapping)
    public ResponseEntity<ClsTuristaAutenticato> login(
            @RequestBody Credenziali credenziali
    ) {
        return ResponseEntity.ok(sLogin.login(credenziali));
    }

}
