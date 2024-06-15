package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoUtenti;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SRecensioni {
    HttpServletRequest request;
    RepoUtenti repoUtenti;

    @Autowired
    public SRecensioni(HttpServletRequest request,
                       RepoUtenti repoUtenti) {
        this.request = request;
        this.repoUtenti = repoUtenti;
    }

    public List<ClsRecensione> getRecensioniNodo(Long idNodo) {
        return ((ClsTurista) request.getServletContext().getAttribute("user")).getRecensioniNodo(idNodo);
    }

    public boolean deleteRecensioneById(Long idRecensione) {
        return ((ClsTuristaAutenticato) request.getServletContext().getAttribute("user")).eliminaRecensione(idRecensione);
    }

    public boolean postRecensione(ClsRecensione recensione) {
        ClsTuristaAutenticato usr = (ClsTuristaAutenticato) request.getServletContext().getAttribute("user");
        if(usr.getPunteggio()<Integer.MAX_VALUE-2)
            repoUtenti.incrementaPunteggio(usr.getId(), 1);
        return (usr).pubblicaRecensione(recensione);
    }

    public List<ClsRecensione> getAllRecensioni() {
        return ((ClsTurista) request.getServletContext().getAttribute("user")).getAllRecensioni();
    }

    public boolean putRecensione(ClsRecensione recensione) {
        return ((ClsTuristaAutenticato) request.getServletContext().getAttribute("user")).modificaRecensione(recensione.getId(), recensione);
    }

    public List<ClsRecensione> getRecensioniPossesore() {
        return ((ClsTuristaAutenticato) request.getServletContext().getAttribute("user")).getRecensioniPosessore();
    }
}
