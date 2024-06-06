package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.utenti.ClsGDP;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SUtenti {
    HttpServletRequest request;

    @Autowired
    public SUtenti(HttpServletRequest request) {
        this.request = request;
    }

    public List<ClsTuristaAutenticato> getUtentiPerGestionePunteggio(String ruolo) {
        ClsTuristaAutenticato user = (ClsTuristaAutenticato) request.getServletContext().getAttribute("user");
        return user.getUtentiPerGestionePunteggio(ruolo);
    }

    public List<ClsTuristaAutenticato> getAllUtenti() {
        ClsTuristaAutenticato user = (ClsTuristaAutenticato) request.getServletContext().getAttribute("user");
        return user.getAllUtenti();
    }


    public List<ClsTuristaAutenticato> getUtentiByRuolo(ClsTuristaAutenticato.eRUOLI_UTENTE ruolo) {
        return ((ClsGDP) request.getServletContext().getAttribute("user")).getUtentiByRuolo(ruolo);
    }
}
