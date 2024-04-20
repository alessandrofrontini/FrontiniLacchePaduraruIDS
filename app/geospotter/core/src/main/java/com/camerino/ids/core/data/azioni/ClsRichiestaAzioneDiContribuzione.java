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

    //region Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdoNodo() {
        return IdoNodo;
    }

    public void setIdoNodo(String idoNodo) {
        IdoNodo = idoNodo;
    }

    public String getUsernameCreatoreRichiesta() {
        return usernameCreatoreRichiesta;
    }

    public void setUsernameCreatoreRichiesta(String usernameCreatoreRichiesta) {
        this.usernameCreatoreRichiesta = usernameCreatoreRichiesta;
    }

    public ClsRichiestaAzioneDiContribuzione.eAzioneDiContribuzione geteAzioneDiContribuzione() {
        return eAzioneDiContribuzione;
    }

    public void seteAzioneDiContribuzione(ClsRichiestaAzioneDiContribuzione.eAzioneDiContribuzione eAzioneDiContribuzione) {
        this.eAzioneDiContribuzione = eAzioneDiContribuzione;
    }
    //endregion

}
