package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;

import java.util.Date;

@Deprecated
public interface IContestManager {
    //TODO: da aggiornare con la documentazione
    boolean creaContestDiContribuzione(ClsContributor[] invitati, Date inizio, Date fine);
    boolean validaContenutoContest(ClsRichiestaAzioneDiContribuzione richiestaAzioneDiContribuzione);
    ClsRichiestaAzioneDiContribuzione[] visualizzaProdottiContest(ClsContestDiContribuzione contestDiContribuzione);
    ClsContestDiContribuzione visualizzaContestDiContribuzione();
}
