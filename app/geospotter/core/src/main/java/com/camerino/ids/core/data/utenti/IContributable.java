package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;

import java.util.ArrayList;

/**
 * TODO: commentare
 */
public interface IContributable {
    boolean inserisciNodo(ClsNodo nodo);
    boolean modificaNodo(ClsNodo oldnodo, ClsNodo nodo);
    boolean eliminaNodo(ClsNodo nodo);
    boolean inserisciItinerario(ClsItinerario itinerario);
    boolean modificaItinerario(ClsItinerario itinerarionuovo, ClsItinerario itinerariovecchio);
    boolean eliminaItinerario(ClsItinerario itinerario);
    ArrayList<ClsNodo> visualizzaNodiPosessore();
}
