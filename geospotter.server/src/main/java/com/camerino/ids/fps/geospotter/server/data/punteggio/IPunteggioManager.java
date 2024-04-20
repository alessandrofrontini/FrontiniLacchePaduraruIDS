package com.camerino.ids.fps.geospotter.server.data.punteggio;

import com.camerino.ids.fps.geospotter.server.data.azioni.ClsRichiestaAzioneDiContribuzione.AzioneDiContribuzione;

/**
 * TODO: commentare
 * TODO: come dovrebbe essere usata?
 */
public interface IPunteggioManager {
    boolean guadagnaPunteggio(AzioneDiContribuzione azioneDiContribuzione, String idUtente);
    boolean saliDiLivello(String idUtente);
}
