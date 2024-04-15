package com.camerino.ids.fps.geospotter.cli.actions;

import com.camerino.ids.fps.geospotter.cli.menu.Input;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.utenti.IContributable;

public class ClsCommonActions {
    public static boolean aggiungiNodo(IContributable user){
        ClsNodo nodo = Input.richiediNodo();
        if(nodo != null)
            return user.inserisciNodo(nodo);
        return false;
    }

    public static boolean eliminaNodo(IContributable user, String id){
        return user.eliminaNodo(id);
    }
}
