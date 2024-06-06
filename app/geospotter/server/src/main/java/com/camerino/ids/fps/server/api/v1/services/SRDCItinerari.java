package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SRDCItinerari {
    HttpServletRequest request;
    @Autowired
    public SRDCItinerari(HttpServletRequest request) {
        this.request = request;
    }


    public List<ClsRdcItinerario> getAllRDCItinerari() {
        return ((ClsCuratore)request.getServletContext().getAttribute("user"))._getAllRDCItinerari();
    }

    public List<ClsRdcItinerario> getRDCItinerarioById(String idRDCItinerario) {
        return ((ClsCuratore)request.getServletContext().getAttribute("user")).getRDCItinerarioById(idRDCItinerario);
    }

    public boolean deleteRDCItinerarioById(String idRDCItinerario) {
        return ((ClsCuratore)request.getServletContext().getAttribute("user")).deleteRDCItinerario(idRDCItinerario);
    }

    public boolean putRDCItinerario(ClsRdcItinerario rdc) {
        return ((ClsCuratore)request.getServletContext().getAttribute("user")).putRDCItinerario(rdc);
    }

    public boolean postRDCItinerario(ClsRdcItinerario rdc) {
        return ((ClsContributor)request.getServletContext().getAttribute("user")).postRDCItinerario(rdc);
    }
}
