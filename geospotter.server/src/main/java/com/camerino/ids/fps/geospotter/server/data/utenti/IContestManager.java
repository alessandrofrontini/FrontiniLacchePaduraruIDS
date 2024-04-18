package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsContestDiContribuzione;

import java.util.Date;

/**
 * TODO: commentare
 */
public interface IContestManager {
    //TODO: da aggiornare con la documentazione
    boolean creaContestDiContribuzione(ClsContributor[] invitati, Date inizio, Date fine);
    boolean validaContenutoContest(ClsRichiestaAzioneDiContribuzione richiestaAzioneDiContribuzione);
    ClsRichiestaAzioneDiContribuzione[] visualizzaProdottiContest(ClsContestDiContribuzione contestDiContribuzione);
    ClsContestDiContribuzione visualizzaContestDiContribuzione();
}
