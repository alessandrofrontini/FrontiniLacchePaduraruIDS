package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SUtenti
{
    HttpServletRequest request;
    @Autowired
    public SUtenti(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<ClsTuristaAutenticato> getUtentiPerGestionePunteggio(String ruolo) {
        ClsTuristaAutenticato user = (ClsTuristaAutenticato) request.getServletContext().getAttribute("user");
        return user.getUtentiPerGestionePunteggio(ruolo);
    }

    public ArrayList<ClsTuristaAutenticato> getAllUtenti() {
        ClsTuristaAutenticato user = (ClsTuristaAutenticato) request.getServletContext().getAttribute("user");
        return user.getAllUtenti();
    }


}
