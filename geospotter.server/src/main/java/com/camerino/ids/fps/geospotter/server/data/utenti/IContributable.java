package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsItinerario;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;

/**
 * TODO: commentare
 */
public interface IContributable {
    boolean inserisciNodo(ClsNodo nodo);
    boolean modificaNodo(String id, ClsNodo nodo);
    boolean eliminaNodo(String id);
    boolean inserisciItinerario(ClsItinerario irinerario);
    boolean modificaItinerario(ClsItinerario itinerario, String id);
    boolean eliminaItinerario(String id);
    boolean visualizzaNodiPosessore();
}
