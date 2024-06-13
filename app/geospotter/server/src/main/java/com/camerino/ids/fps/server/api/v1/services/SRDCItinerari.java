package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class SRDCItinerari {
    HttpServletRequest request;
    RepoRDCItinerari repoRDC;
    RepoItinerari repoItinerari;
    RepoUtenti repoUtenti;

    @Autowired
    public SRDCItinerari(HttpServletRequest request,
                         RepoItinerari repoItinerari,
                         RepoRDCItinerari repoRDC,
                         RepoUtenti repoUtenti) {
        this.request = request;
        this.repoItinerari = repoItinerari;
        this.repoRDC = repoRDC;
        this.repoUtenti = repoUtenti;
    }


    public List<ClsRdcItinerario> getAllRDCItinerari() {
        return ((ClsCuratore) request.getServletContext().getAttribute("user"))._getAllRDCItinerari();
    }

    public List<ClsRdcItinerario> getRDCItinerarioById(Long idRDCItinerario) {
        return ((ClsCuratore) request.getServletContext().getAttribute("user")).getRDCItinerarioById(idRDCItinerario);
    }

    public boolean deleteRDCItinerarioById(Long idRDCItinerario) {
        return ((ClsCuratore) request.getServletContext().getAttribute("user")).deleteRDCItinerario(idRDCItinerario);
    }

    public boolean putRDCItinerario(ClsRdcItinerario rdc) {
        return ((ClsCuratore) request.getServletContext().getAttribute("user")).putRDCItinerario(rdc);
    }

    public boolean postRDCItinerario(ClsRdcItinerario rdc) {
        if(rdc.getTipo()!= EAzioniDiContribuzione.ELIMINA_ITINERARIO) {
            List<ClsItinerario> itinerari = repoItinerari.findAllOfficial();
            Stream<ClsItinerario> stream = itinerari.stream()
                    .filter(it -> it.equals(rdc.getNewData()));

            if (rdc.getTipo() == EAzioniDiContribuzione.MODIFICA_ITINERARIO)
                stream = stream.filter(it -> !Objects.equals(it.getId(), rdc.getOldData().getId()));
            if (stream.count() != 0)
                return false;
        }
        return ((ClsContributor) request.getServletContext().getAttribute("user")).postRDCItinerario(rdc);
    }

    public Boolean rifutaRDCNodi(Long idRDC) {
        ClsRdcItinerario rdc = repoRDC.findById(idRDC).get();
        ClsItinerario newData  = rdc.getNewData();
        ClsItinerario oldData = rdc.getOldData();
        repoRDC.delete(rdc);
        if(newData!=null)
            repoItinerari.delete(newData);
        return true;
    }

    public Boolean deleteRDCNodiById(Long idRDC) {
        repoRDC.deleteById(idRDC);
        return true;
    }

    public Boolean accettaRDCNodo(Long idRDC) {
        ClsRdcItinerario rdc = repoRDC.findById(idRDC).get();
        ClsItinerario oldData = rdc.getOldData();
        ClsItinerario newData  = rdc.getNewData();
        repoRDC.delete(rdc);
        switch (rdc.getTipo()){
            case INSERISCI_NODO: {
                //Basta cancellare la RDC
            }break;
            case MODIFICA_ITINERARIO: {//todo: bug owner nodo non settato
                oldData.setNome(newData.getNome());
                oldData.setOrdinato(newData.isOrdinato());
                oldData.setTappe(newData.getTappe());
                repoItinerari.delete(newData);
            }break;
            case ELIMINA_ITINERARIO: {
                repoItinerari.delete(oldData);
            }break;
        }
        repoUtenti.incrementaPunteggio(rdc.getCreatore().getId(), 2);
        return true;
    }

    public List<ClsRdcItinerario> getAllRDCItinerariCur() {
        ClsCuratore user = (ClsCuratore) request.getServletContext().getAttribute("user");
        return user.getRDCItinerarioPosessoreCur();
    }
}
