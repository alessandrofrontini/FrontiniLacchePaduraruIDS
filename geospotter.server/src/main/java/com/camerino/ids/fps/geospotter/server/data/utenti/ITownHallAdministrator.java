package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsComune;

public interface ITownHallAdministrator {
    boolean aggiungiComune(ClsComune comune);
    boolean modificaComune(ClsComune comune, String id);
    boolean eliminaComune(String id);
    ClsComune[] visualizzaComuni();
}
