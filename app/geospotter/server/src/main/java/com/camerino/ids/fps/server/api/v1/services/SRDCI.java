package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SRDCI {
    HttpServletRequest request;
    @Autowired
    public SRDCI(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> getAllRDCI() {
        ClsCuratore user = (ClsCuratore) request.getSession().getAttribute("user");
        return user._getAllRDCI();
    }

    public ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> getRDCIById(String idRDCI) {
        ClsCuratore user = (ClsCuratore) request.getSession().getAttribute("user");
        return user.getRDCIById(idRDCI);
    }

    public boolean deleteRDCIById(String idRDCI) {
        ClsCuratore user = (ClsCuratore) request.getSession().getAttribute("user");
        return user.deleteRDCById(idRDCI);
    }

    public boolean putRDCI(ClsRichiestaAzioneDiContribuzioneItinerario rdci) {
        ClsCuratore user = (ClsCuratore) request.getSession().getAttribute("user");
        return user.putRDCI(rdci);
    }

    public boolean postRDCI(ClsRichiestaAzioneDiContribuzioneItinerario rdci) {
        ClsCuratore user = (ClsCuratore) request.getSession().getAttribute("user");
        return user.postRDCI(rdci);
    }
}
