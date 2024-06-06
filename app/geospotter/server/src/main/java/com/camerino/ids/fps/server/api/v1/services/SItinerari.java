package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.core.data.utenti.ClsTurista;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SItinerari {
    HttpServletRequest request;

    @Autowired
    public SItinerari(HttpServletRequest request) {
        this.request = request;
    }

    public List<ClsItinerario> getAllItinerari() {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.getAllItinerari();
    }

    public List<ClsItinerario> getItinerarioById(String idItinerario) {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.getItinerarioById();
    }

    public boolean postItinerario(ClsItinerario itinerario) {
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.inserisciItinerario(itinerario);
    }

    public boolean putItinerario(ClsItinerario itinerario) {
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.modificaItinerario(itinerario, itinerario.getId());
    }

    public boolean deleteItinerario(String idItinerario) {
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.eliminaItinerario(idItinerario);
    }
}
