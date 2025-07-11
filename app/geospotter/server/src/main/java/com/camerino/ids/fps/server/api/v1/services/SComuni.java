package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.ClsGDP;
import com.camerino.ids.core.data.utenti.ClsTurista;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SComuni {
    HttpServletRequest request;

    @Autowired
    public SComuni(HttpServletRequest request) {
        this.request = request;
    }

    public List<ClsComune> getAllComuni() {
        return ((ClsTurista) this.request.getServletContext().getAttribute("user")).getAllComuni();
    }

    public List<ClsComune> getComuneById(Long idComune) {
        return ((ClsTurista) this.request.getServletContext().getAttribute("user")).getComuneById(idComune);
    }

    public boolean deleteComuneById(Long idComune) {
        return ((ClsGDP) this.request.getServletContext().getAttribute("user")).eliminaComune(idComune);
    }

    public boolean postComune(ClsComune comune) {
        return ((ClsGDP) this.request.getServletContext().getAttribute("user")).inserisciComune(comune);
    }

    public boolean putComune(ClsComune comune) {
        return ((ClsGDP) this.request.getServletContext().getAttribute("user")).modificaComune(comune, comune.getId());
    }
}
