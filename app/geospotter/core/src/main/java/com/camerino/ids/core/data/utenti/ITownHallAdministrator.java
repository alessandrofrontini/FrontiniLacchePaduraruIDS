package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsComune;

@Deprecated
public interface ITownHallAdministrator {
    boolean inserisciComune(ClsComune comune);
    boolean modificaComune(ClsComune comune, String id);
    boolean eliminaComune(String id);
    ClsComune[] visualizzaComuni();
}
