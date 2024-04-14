package com.camerino.ids.fps.geospotter.cli.actions;

import com.camerino.ids.fps.geospotter.cli.menu.Input;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.utenti.IContributable;

public class ClsCommonActions {
    public static boolean aggiungi_nodo(IContributable user){
        ClsNodo nodo = Input.richiediNodo();
        if(nodo != null)
            return user.inserisciNodo(nodo);
        return false;
    }
}
