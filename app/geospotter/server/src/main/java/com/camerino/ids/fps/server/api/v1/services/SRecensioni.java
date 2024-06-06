package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SRecensioni {
    HttpServletRequest request;
    @Autowired
    public SRecensioni(HttpServletRequest request) {
        this.request = request;
    }

    public List<ClsRecensione> getRecensioniNodo(String idNodo) {
        return ((ClsTurista)request.getServletContext().getAttribute("user")).getRecensioniNodo(idNodo);
    }

    public boolean deleteRecensioneById(String idRecensione) {
        return ((ClsTuristaAutenticato)request.getServletContext().getAttribute("user")).eliminaRecensione(idRecensione);
    }

    public boolean postRecensione(ClsRecensione recensione) {
        return ((ClsTuristaAutenticato)request.getServletContext().getAttribute("user")).pubblicaRecensione(recensione);
    }

    public List<ClsRecensione> getAllRecensioni() {
        return ((ClsTurista)request.getServletContext().getAttribute("user")).getAllRecensioni();
    }

    public boolean putRecensione(ClsRecensione recensione) {
        return ((ClsTuristaAutenticato)request.getServletContext().getAttribute("user")).modificaRecensione(recensione.getId(),recensione);
    }

    public List<ClsRecensione> getRecensioniPossesore() {
        return ((ClsTuristaAutenticato)request.getServletContext().getAttribute("user")).getRecensioniPosessore();
    }
}
