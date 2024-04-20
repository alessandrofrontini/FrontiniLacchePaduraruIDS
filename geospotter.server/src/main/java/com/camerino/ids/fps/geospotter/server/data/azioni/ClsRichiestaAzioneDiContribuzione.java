package com.camerino.ids.fps.geospotter.server.data.azioni;

<<<<<<< Updated upstream
/**
 * TODO: commentare
 */
public class ClsRichiestaAzioneDiContribuzione {
=======
public class ClsRichiestaAzioneDiContribuzione 
{
>>>>>>> Stashed changes
    public enum AzioneDiContribuzione{
        INSERISCI_RECENSIONE,
        INSERISCI_IMMAGINE,
        INSERISCI_NODO, 
        MODIFICA_NODO, 
        ELIMINA_NODO,
        INSERISCI_ITINERARIO,
        MODIFICA_ITINERARIO,
        ELIMINA_ITINERARIO, 
        INSERISCI_NODO_CONTEST,
        INSERISCI_FOTO_CONTEST
    }
    String id;
    String IdNodo;
    String usernameCreatoreRichiesta;
    AzioneDiContribuzione azioneDiContribuzione;
    boolean isForContestDiContribuzione;

    //ClsContestDiContribuzione contestDiContribuzione;
}
