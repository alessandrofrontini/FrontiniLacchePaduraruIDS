package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.persistence.convertors.ConvPosizione;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoNodi;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Le azioni del contributor base saranno mascherate con la creazione di un rdc
@Service
public class SNodi {

    HttpServletRequest request;
    RepoNodi repoNodi;
    static ConvPosizione convPosizione = new ConvPosizione();

    @Autowired
    public SNodi(HttpServletRequest request,
                 RepoNodi repoNodi) {
        this.request = request;
        this.repoNodi = repoNodi;
    }

    public List<ClsNodo> findNodoById(Long idNodo) {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.getNodoById(idNodo);
    }

    public List<ClsNodo> findNodiByComune(Long idComune) {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.getNodiByComune(idComune);
    }

    public List<ClsNodo> getAllNodi() {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        return user.getAllNodi();
    }

    public boolean postNodo(ClsNodo nodo) {
        if(repoNodi.countNodiOnSamePosition(convPosizione.convertToDatabaseColumn(nodo.getPosizione()))!=0)
            return false;
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.inserisciNodo(nodo);
    }

    public boolean deleteNodoById(Long idNodo) {
        ClsContributor cont = (ClsContributor) request.getServletContext().getAttribute("user");
        return cont.deleteNodo(idNodo);
    }

    public boolean putNodo(ClsNodo nodo) {
        if(repoNodi.countNodiOnSamePosition(convPosizione.convertToDatabaseColumn(nodo.getPosizione()))!=0)
            return false;
        ClsContributor cont = (ClsContributor) request.getServletContext().getAttribute("user");
        return cont.modificaNodo(nodo.getId(), nodo);
    }

    public List<ClsNodo> getNodiPosessore() {
        return ((ClsContributor) request.getServletContext().getAttribute("user")).getNodiPossessore();
    }
}
