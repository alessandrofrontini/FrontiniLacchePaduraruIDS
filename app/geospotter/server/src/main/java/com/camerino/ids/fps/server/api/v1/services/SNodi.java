package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SNodi {

    HttpServletRequest request;

    @Autowired
    public SNodi(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<ClsNodo> findNodoById(String idNodo) {
        return null;
    }

    public ArrayList<ClsNodo> findNodiByComune(String idComune) {
        return null;
    }

    public ArrayList<ClsNodo> getAllNodi() {
        System.out.println("getAllNodi");
        ClsContributorAutorizzato cont = (ClsContributorAutorizzato) request.getServletContext().getAttribute("user");
        HashMap<String, Object> filters = new HashMap<>();
        return cont.getNodi(filters);
    }
}
