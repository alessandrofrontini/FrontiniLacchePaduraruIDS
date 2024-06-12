package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoItinerari;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SItinerari {
    HttpServletRequest request;
    @Deprecated
    RepoItinerari repoItinerari;

    @Autowired
    public SItinerari(HttpServletRequest request,
                      RepoItinerari repoItinerari) {
        this.request = request;
        this.repoItinerari = repoItinerari;
    }

    public List<ClsItinerario> getAllItinerari() {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.getAllItinerari();
    }

    public List<ClsItinerario> getItinerarioById(Long idItinerario) {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.getItinerarioById(idItinerario);
    }

    public boolean postItinerario(ClsItinerario itinerario) {
        List<ClsItinerario> itinerari = repoItinerari.findAllOfficial();
        long numItUguali = itinerari.stream()
                .filter(it->it.equals(itinerario) && !Objects.equals(it.getId(), itinerario.getId()))
                .count();
        if (numItUguali != 0)
            return false;

        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.inserisciItinerario(itinerario);
    }

    public boolean putItinerario(ClsItinerario itinerario) {
        List<ClsItinerario> itinerari = repoItinerari.findAllOfficial();
        long numItUguali = itinerari.stream().
                filter(it->it.equals(itinerario) && !Objects.equals(it.getId(), itinerario.getId()))
                .count();
        if (numItUguali != 0)
            return false;

        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.modificaItinerario(itinerario, itinerario.getId());
    }

    public boolean deleteItinerario(Long idItinerario) {
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.eliminaItinerario(idItinerario);
    }
}
