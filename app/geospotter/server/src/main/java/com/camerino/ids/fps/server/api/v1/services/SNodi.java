package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import java.util.ArrayList;
import java.util.HashMap;

//Le azioni del contributo base saranno mascherate con la creazione di un rdc
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
        ClsContributorAutorizzato cont = (ClsContributorAutorizzato) request.getServletContext().getAttribute("user");
        return cont.getAllNodi();
    }

    public boolean postNodo(ClsNodo nodo) {
        return false;
    }

    public boolean deleteNodoById(String idNodo) {
        ClsContributor cont = (ClsContributor) request.getServletContext().getAttribute("user");
        return cont.deleteNodo(idNodo);
    }

    public boolean putNodo(ClsNodo nodo) {
        ClsContributor cont = (ClsContributor) request.getServletContext().getAttribute("user");
        return cont.modificaNodo(nodo.getId(), nodo);
    }
}
