package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SRDCNodi {
    HttpServletRequest request;

    @Autowired
    public SRDCNodi(HttpServletRequest request) {
        this.request = request;
    }

    public List<ClsRDCNodo> getAllRDCNodi() {
        ClsCuratore user = (ClsCuratore) request.getServletContext().getAttribute("user");
        return user._getAllRDCNodi();
    }

    public List<ClsRDCNodo> getRDCNodiById(Long idRDCNodo) {
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.getRDCNodiById(idRDCNodo);
    }

    public boolean deleteRDCNodiById(Long idRDCNodo) {
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.deleteRDCById(idRDCNodo);
    }

    public boolean putRDCNodi(ClsRDCNodo rdc) {
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.putRDCNodo(rdc);
    }

    public boolean postRDCNodi(ClsRDCNodo rdc) {
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.postRDCNodo(rdc);
    }
}

