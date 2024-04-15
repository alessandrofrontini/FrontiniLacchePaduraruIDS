package com.camerino.ids.fps.geospotter.server.data.punteggio;

import com.camerino.ids.fps.geospotter.server.data.azioni.ClsRichiestaAzioneDiContribuzione.AzioneDiContribuzione;

public interface IPunteggioManager {
    boolean guadagnaPunteggio(AzioneDiContribuzione azioneDiContribuzione, String idUtente);
    boolean saliDiLivello(String idUtente);
}
