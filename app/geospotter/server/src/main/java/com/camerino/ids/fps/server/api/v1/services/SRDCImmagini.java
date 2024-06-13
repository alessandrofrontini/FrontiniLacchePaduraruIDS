package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsAnimatore;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SRDCImmagini {
    HttpServletRequest request;
    RepoRDCImmagini repoRDC;
    RepoImmagini repoImmagini;
    RepoUtenti repoUtenti;

    @Autowired
    public SRDCImmagini(HttpServletRequest request,
                        RepoImmagini repoNodi,
                        RepoRDCImmagini repoRDC,
                        RepoUtenti repoUtenti) {
        this.request = request;
        this.repoImmagini = repoNodi;
        this.repoRDC = repoRDC;
        this.repoUtenti = repoUtenti;
    }

    public List<ClsRDCImmagine> getAllRDCI() {
        ClsAnimatore user = (ClsAnimatore) request.getServletContext().getAttribute("user");
        return user._getAllRDCImmagini();
    }

    public List<ClsRDCImmagine> getRDCIById(Long idRDCI) {
        ClsTurista user = (ClsTurista) request.getServletContext().getAttribute("user");
        //return user.getRDCImmagineById(idRDCI);
        return null;
    }

    public boolean deleteRDCImmagineById(Long idRDCImmagine) {
        ClsTuristaAutenticato user = (ClsTuristaAutenticato) request.getServletContext().getAttribute("user");
        return user.deleteRDCImmagineById(idRDCImmagine);
        //return false;
    }

    public boolean putRDCI(ClsRDCImmagine rdci) {
        ClsCuratore user = (ClsCuratore) request.getServletContext().getAttribute("user");
        return user.putRDCImmagine(rdci);
    }

    public boolean postRDCImmagine(ClsRDCImmagine rdci) {
        ClsTuristaAutenticato user = (ClsTuristaAutenticato) request.getServletContext().getAttribute("user");
        return user.postRDCImmagine(rdci);
    }

    public Boolean accettaRDCImmagine(Long idRDC) {
        ClsRDCImmagine rdc = repoRDC.findById(idRDC).get();
        ClsImmagine oldData = rdc.getOldData();
        ClsImmagine newData  = rdc.getNewData();
        repoRDC.delete(rdc);
        switch (rdc.getTipo()){
            case INSERISCI_IMMAGINE: {
                //Basta cancellare la RDC
            }break;
        }
        if(rdc.getIdContestAppartenenza()!=null)
            repoUtenti.incrementaPunteggio(rdc.getCreatore().getId(), 2);
        return true;
    }

    public Boolean rifutaRDCImmagine(Long idRDC) {
        ClsRDCImmagine rdc = repoRDC.findById(idRDC).get();
        ClsImmagine newData  = rdc.getNewData();
        ClsImmagine oldData = rdc.getOldData();
        repoRDC.delete(rdc);
        if(newData!=null)
            repoImmagini.delete(newData);
        return true;
    }

    public List<ClsRDCImmagine> GetRDCImmaginePosessore() {
        return ((ClsAnimatore)request.getServletContext().getAttribute("user")).GetRDCImmaginePosessore();
    }

    public List<ClsRDCImmagine> GetRDCImmaginePosessoreCur() {
        return ((ClsCuratore)request.getServletContext().getAttribute("user")).getRdcImmaginiPosessoreCur();
    }
}
