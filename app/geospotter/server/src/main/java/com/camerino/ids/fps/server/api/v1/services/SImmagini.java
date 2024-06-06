package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.utenti.ClsTurista;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SImmagini {
    HttpServletRequest request;

    @Autowired
    public SImmagini(HttpServletRequest request) {
        this.request = request;
    }

    public List<ClsImmagine> getAllImmagini() {
        return ((ClsTurista)this.request.getServletContext().getAttribute("user")).getAllImmagini();
    }
}
