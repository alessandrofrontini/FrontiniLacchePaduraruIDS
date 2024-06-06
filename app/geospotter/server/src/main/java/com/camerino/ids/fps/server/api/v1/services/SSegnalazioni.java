package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsTurista;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SSegnalazioni {
    HttpServletRequest request;
    @Autowired
    public SSegnalazioni(HttpServletRequest request) {
        this.request = request;
    }

    public List<ClsSegnalazione> getAll() {
        //chi visualizza le segnalazione? Il curatore no?
        ClsCuratore user = (ClsCuratore) request.getServletContext().getAttribute("user");
        return user._getAllSegnalazioni();
    }

    public boolean creaSegnalazione(ClsSegnalazione segnalazione) {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.segnalaContenuto(segnalazione);
    }

    public boolean deleteSgnalazione(String idSegnalazione) {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return false;
    }
}
