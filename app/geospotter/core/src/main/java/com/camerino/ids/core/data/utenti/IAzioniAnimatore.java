package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface IAzioniAnimatore {
    boolean inserisciContest(ClsContestDiContribuzione contest);
    @JsonIgnore
    List<ClsRDCImmagine> GetRDCImmaginePosessore();
    @JsonIgnore
    List<ClsRDCNodo> GetRDCNodoPosessore();
    boolean accettaRichiestaNodo(Long idValidazione);

    boolean rifiutaRichiestaNodo(Long idValidazione);

    boolean accettaRichiestaItinerario(Long idValidazione);

    boolean rifiutaRichiestaImmagine(Long idValidazione);

    boolean accettaRichiestaImmagine(Long idValidazione);

    boolean rifiutaRichiestaItinerario(Long idValidazione);
}
