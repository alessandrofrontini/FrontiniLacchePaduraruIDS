package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsComune;

@Deprecated
public interface ITownHallAdministrator {
    boolean inserisciComune(ClsComune comune);
    boolean modificaComune(ClsComune comune, Long id);
    boolean eliminaComune(Long id);
    ClsComune[] visualizzaComuni();
}
