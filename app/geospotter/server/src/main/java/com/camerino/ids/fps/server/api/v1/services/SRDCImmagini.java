package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.utenti.ClsAnimatore;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SRDCImmagini {
    HttpServletRequest request;
    @Autowired
    public SRDCImmagini(HttpServletRequest request) {
        this.request = request;
    }

    public List<ClsRDCImmagine> getAllRDCI() {
        ClsAnimatore user = (ClsAnimatore) request.getServletContext().getAttribute("user");
        return user._getAllRDCImmagini();
    }

    public List<ClsRDCImmagine> getRDCIById(String idRDCI) {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        //return user.getRDCImmagineById(idRDCI);
        return null;
    }

    public boolean deleteRDCImmagineById(String idRDCImmagine) {
        ClsTuristaAutenticato user = (ClsTuristaAutenticato) request.getServletContext().getAttribute("user");
        return user.deleteRDCImmagineById(idRDCImmagine);
        //return false;
    }

    public boolean putRDCI(ClsRDCImmagine rdci) {
        ClsCuratore user = (ClsCuratore) request.getServletContext().getAttribute("user");
        return user.putRDCImmagine(rdci);
    }

    public boolean postRDCImmagine(ClsRDCImmagine rdci) {
        ClsTuristaAutenticato user = (ClsTuristaAutenticato) request.getServletContext().getAttribute("user");
        return user.postRDCImmagine(rdci);
    }
}
