package com.camerino.cli.actions;

import com.camerino.cli.menu.Input;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsGestoreDellaPiattaforma;
import com.camerino.ids.core.data.utenti.IContributable;

public class ClsCommonActions {
    public static boolean aggiungiNodo(IContributable user){
        ClsNodo nodo = Input.inserisciNodo();
        if(nodo != null)
            return user.inserisciNodo(nodo);
        return false;
    }

    public static boolean eliminaNodo(IContributable user, String id){
        return user.eliminaNodo(id);
    }

    public static boolean aggiungiItinerario(IContributable user){
        ClsItinerario itinerario = Input.richiediItinerario();
        if(itinerario!= null)
            return user.inserisciItinerario(itinerario);
        return false;
    }

    public static boolean aggiungiComune(ClsGestoreDellaPiattaforma gdp)
    {
        ClsComune comune = Input.inserisciComune(gdp);
        if(comune!= null)
            return gdp.inserisciComune(comune);
        return false;
    }

}
