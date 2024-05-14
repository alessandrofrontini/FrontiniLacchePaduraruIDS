package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SRDC {
    HttpServletRequest request;
    @Autowired
    public SRDC(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<ClsRichiestaAzioneDiContribuzione> getAllRDC() {
        ClsCuratore user = (ClsCuratore) request.getSession().getAttribute("user");
        return user._getAllRDC();
    }

    public ArrayList<ClsRichiestaAzioneDiContribuzione> getRDCById(String idRDC) {
        ClsCuratore user = (ClsCuratore) request.getSession().getAttribute("user");
        return user.getRDCById(idRDC);
    }

    public boolean deleteRDCById(String idRDC) {
        ClsCuratore user = (ClsCuratore) request.getSession().getAttribute("user");
        return user.deleteRDCById(idRDC);
    }

    public boolean putRDC(ClsRichiestaAzioneDiContribuzione rdc) {
        ClsCuratore user = (ClsCuratore) request.getSession().getAttribute("user");
        return user.putRDC(rdc);
    }

    public boolean postRDC(ClsRichiestaAzioneDiContribuzione rdc) {
        ClsCuratore user = (ClsCuratore) request.getSession().getAttribute("user");
        return user.postRDC(rdc);
    }
}
