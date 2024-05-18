package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SImmagini
{
    HttpServletRequest request;

    @Autowired
    public SImmagini(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<ClsImmagine> getAllImmagini() {
        return ((ClsTuristaAutenticato)this.request.getServletContext().getAttribute("user")).getAllImmagini();
    }

    public ArrayList<ClsImmagine> getImmagineById(String idImmagine) {
        return ((ClsTuristaAutenticato)this.request.getServletContext().getAttribute("user")).getImmagineById(idImmagine);
    }

}
