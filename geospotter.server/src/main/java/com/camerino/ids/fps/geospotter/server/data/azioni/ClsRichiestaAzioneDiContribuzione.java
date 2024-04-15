package com.camerino.ids.fps.geospotter.server.data.azioni;

public class ClsRichiestaAzioneDiContribuzione {
    public enum AzioneDiContribuzione{
        INSERISCI_RECENSIONE, INSERISCI_IMMAGINE,
        INSERISCI_NODO, MODIFICA_NODO, ELIMINA_NODO,
        INSERISCI_ITINERARIO, MODIFICA_ITINERARIO,
        ELIMINA_ITINERARIO, INSERISCI_NODO_CONTEST,
        INSERISCI_FOTO_CONTEST
    }
    String id,
    IdoNodo,
    usernameCreatoreRichiesta;
    AzioneDiContribuzione azioneDiContribuzione;
}
