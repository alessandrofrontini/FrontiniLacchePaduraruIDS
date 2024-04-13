package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.fps.geospotter.server.data.segnalazioni.ISignalable;

public class ClsTurista implements ISignalable {
    @Override
    public boolean segnalaContenuto(ClsSegnalazione segnalazione) {
        return false;
    }
}
