package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.ClsGestoreDellaPiattaforma;
import com.camerino.ids.core.data.utenti.ClsTurista;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SComuni {
    HttpServletRequest request;

    @Autowired
    public SComuni(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<ClsComune> getAllComuni() {
        return ((ClsTurista)this.request.getServletContext().getAttribute("user")).getAllComuni();
    }

    public ArrayList<ClsComune> getComuneById(String idComune) {
        return ((ClsTurista)this.request.getServletContext().getAttribute("user")).getComuneById(idComune);
    }

    public boolean deleteComuneById(String idComune) {
        return ((ClsGestoreDellaPiattaforma)this.request.getServletContext().getAttribute("user")).eliminaComune(idComune);
    }
}
