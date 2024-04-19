package com.camerino.ids.core.data.azioni;

/**
 * TODO: commentare
 */
public class ClsRichiestaAzioneDiContribuzione {
    public enum eAzioneDiContribuzione {
        INSERISCI_RECENSIONE, INSERISCI_IMMAGINE,
        INSERISCI_NODO, MODIFICA_NODO, ELIMINA_NODO,
        INSERISCI_ITINERARIO, MODIFICA_ITINERARIO,
        ELIMINA_ITINERARIO, INSERISCI_NODO_CONTEST,
        INSERISCI_FOTO_CONTEST
    }
    String id,
    IdoNodo,
    usernameCreatoreRichiesta;
    eAzioneDiContribuzione eAzioneDiContribuzione;
}
