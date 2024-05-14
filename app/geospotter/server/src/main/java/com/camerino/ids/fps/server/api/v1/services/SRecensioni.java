package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SRecensioni {
    HttpServletRequest request;
    @Autowired
    public SRecensioni(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<ClsRecensione> getRecensioniNodo(String idNodo) {
        return ((ClsTurista)request.getServletContext().getAttribute("user")).getRecensioniNodo(idNodo);
    }

    public boolean deleteRecensioneById(String idRecensione) {
        return false;
    }

    public boolean postRecensione(ClsRecensione recensione) {
        return ((ClsTuristaAutenticato)request.getServletContext().getAttribute("user")).pubblicaRecensione(recensione);
    }
}
