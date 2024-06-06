package com.camerino.ids.core.data.punteggio;

import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;

/**
 * TODO: commentare
 * TODO: come dovrebbe essere usata?
 */
@Deprecated()
public interface IPunteggioManager {
    boolean guadagnaPunteggio(EAzioniDiContribuzione eAzioneDiContribuzione, Long idUtente);
    boolean saliDiLivello(Long idUtente);
}
