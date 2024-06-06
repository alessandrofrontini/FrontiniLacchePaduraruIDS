package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsTurista;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Le azioni del contributor base saranno mascherate con la creazione di un rdc
@Service
public class SNodi {

    HttpServletRequest request;

    @Autowired
    public SNodi(HttpServletRequest request) {
        this.request = request;
    }

    public List<ClsNodo> findNodoById(String idNodo) {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.getNodoById(idNodo);
    }

    public List<ClsNodo> findNodiByComune(String idComune) {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.getNodiByComune(idComune);
    }

    public List<ClsNodo> getAllNodi() {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.getAllNodi();
    }

    public boolean postNodo(ClsNodo nodo) {
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.inserisciNodo(nodo);
    }

    public boolean deleteNodoById(String idNodo) {
        ClsContributor cont = (ClsContributor) request.getServletContext().getAttribute("user");
        return cont.deleteNodo(idNodo);
    }

    public boolean putNodo(ClsNodo nodo) {
        ClsContributor cont = (ClsContributor) request.getServletContext().getAttribute("user");
        return cont.modificaNodo(nodo.getId(), nodo);
    }

    public List<ClsNodo> getNodiPosessore() {
        return (List<ClsNodo>) ((ClsContributor)request.getServletContext().getAttribute("user")).getNodiPossessore();
    }
}
