package com.camerino.ids.core.data.punteggio;

import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;

/**
 * TODO: commentare
 * TODO: come dovrebbe essere usata?
 */
public interface IPunteggioManager {
    void guadagnaPunteggio(EAzioniDiContribuzione eAzioneDiContribuzione, String idUtente);
    boolean saliDiLivello(String idUtente);
    void scendiDiLivello(String idUtente);
    void resetLivello(String idUtente);
}
