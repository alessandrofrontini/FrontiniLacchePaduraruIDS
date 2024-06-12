package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoNodi;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDCNodi;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoUtenti;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.camerino.ids.fps.server.api.v1.services.SNodi.convPosizione;

@Service
public class SRDCNodi {
    HttpServletRequest request;
    RepoRDCNodi repoRDC;
    RepoNodi repoNodi;
    RepoUtenti repoUtenti;

    @Autowired
    public SRDCNodi(HttpServletRequest request,
                    RepoNodi repoNodi,
                    RepoRDCNodi repoRDC,
                    RepoUtenti repoUtenti) {
        this.request = request;
        this.repoNodi = repoNodi;
        this.repoRDC = repoRDC;
        this.repoUtenti = repoUtenti;
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
        if(rdc.getTipo()!= EAzioniDiContribuzione.ELIMINA_NODO)
            if(repoNodi.countNodiOnSamePosition(convPosizione.convertToDatabaseColumn(rdc.getNewData().getPosizione()))!=0)
                return false;
        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.putRDCNodo(rdc);
    }

    public boolean postRDCNodi(ClsRDCNodo rdc) {

        if(rdc.getTipo()!= EAzioniDiContribuzione.ELIMINA_NODO)
            if(repoNodi.countNodiOnSamePosition(convPosizione.convertToDatabaseColumn(rdc.getNewData().getPosizione()))!=0)
                return false;

        ClsContributor user = (ClsContributor) request.getServletContext().getAttribute("user");
        return user.postRDCNodo(rdc);
    }

    public Boolean accettaRDCNodo(Long idRDC) {
        ClsRDCNodo rdc = repoRDC.findById(idRDC).get();
        ClsNodo oldData = rdc.getOldData();
        ClsNodo newData  = rdc.getNewData();
        repoRDC.delete(rdc);
        switch (rdc.getTipo()){
            case INSERISCI_NODO: {
                //Basta cancellare la RDC
            }break;
            case MODIFICA_NODO: {//todo: bug owner nodo non settato
                oldData.setPosizione(newData.getPosizione());
                oldData.setaTempo(newData.isaTempo());
                oldData.setDescrizione(newData.getDescrizione());
                oldData.setNome(newData.getNome());
                oldData.setDataFine(newData.getDataFine());
                oldData.setDataInizio(newData.getDataInizio());
                oldData.setTipologiaNodo(newData.getTipologiaNodo());
                oldData.setIdComuneAssociato(newData.getIdComuneAssociato());
                oldData.setIdCreatore(newData.getIdCreatore());
                repoNodi.delete(newData);
            }break;
            case ELIMINA_NODO: {
                repoNodi.delete(oldData);
            }break;
        }
        repoUtenti.incrementaPunteggio(rdc.getCreatore().getId(), 2);
        return true;
    }

    public Boolean rifutaRDCNodi(Long idRDC) {
        ClsRDCNodo rdc = repoRDC.findById(idRDC).get();
        ClsNodo newData  = rdc.getNewData();
        ClsNodo oldData = rdc.getOldData();
        repoRDC.delete(rdc);
        if(newData!=null)
            repoNodi.delete(newData);
        return true;
    }
}

