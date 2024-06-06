package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;

/**
 * TODO: commentare
 */
@Deprecated
public interface IContributable {
    boolean inserisciNodo(ClsNodo nodo);

    boolean modificaNodo(Long id, ClsNodo nodo);

    boolean eliminaNodo(Long id);

    boolean inserisciItinerario(ClsItinerario irinerario);

    boolean modificaItinerario(ClsItinerario itinerario, Long id);

    boolean eliminaItinerario(Long id);

    boolean visualizzaNodiPosessore();
}
