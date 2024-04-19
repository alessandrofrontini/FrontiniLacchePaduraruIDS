package com.camerino.ids.core.data.punteggio;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;

/**
 * TODO: commentare
 * TODO: come dovrebbe essere usata?
 */
public interface IPunteggioManager {
    boolean guadagnaPunteggio(ClsRichiestaAzioneDiContribuzione.eAzioneDiContribuzione eAzioneDiContribuzione, String idUtente);
    boolean saliDiLivello(String idUtente);
}
