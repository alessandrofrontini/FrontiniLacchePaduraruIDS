package com.camerino.ids.core.data.punteggio;

import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.HashMap;

public class ClsPunteggioManager implements IPunteggioManager{
    IPersistenceModel<ClsTuristaAutenticato> pUtenti;
    public ClsPunteggioManager(IPersistenceModel<ClsTuristaAutenticato> u){
        pUtenti = u;
    }
    @Override
    public void guadagnaPunteggio(EAzioniDiContribuzione eAzioneDiContribuzione, String idUtente) {
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", idUtente);
        ClsTuristaAutenticato utente = pUtenti.get(filtro).get(0);
        if(utente.getRuoloUtente().getValue()> ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR.getValue()) {
            switch (eAzioneDiContribuzione) {
                case INSERISCI_RECENSIONE:
                case INSERISCI_NODO:
                case MODIFICA_NODO:
                case ELIMINA_NODO:
                case INSERISCI_ITINERARIO:
                case MODIFICA_ITINERARIO:
                case ELIMINA_ITINERARIO:
                case INSERISCI_FOTO_CONTEST:
                case INSERISCI_NODO_CONTEST: utente.setPunteggio(utente.getPunteggio()+1);
                case CREA_CONTEST: utente.setPunteggio(utente.getPunteggio()+10);
            }
        }
        else{
            switch (eAzioneDiContribuzione) {
                case INSERISCI_RECENSIONE: utente.setPunteggio(utente.getPunteggio()+1);
                case INSERISCI_NODO:
                case MODIFICA_NODO:
                case ELIMINA_NODO:
                case INSERISCI_ITINERARIO:
                case MODIFICA_ITINERARIO:
                case ELIMINA_ITINERARIO:
                case INSERISCI_FOTO_CONTEST:
                case INSERISCI_NODO_CONTEST: utente.setPunteggio(utente.getPunteggio()+2);
            }
        }
    }

    @Override
    public boolean saliDiLivello(String idUtente) {
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", idUtente);
        ClsTuristaAutenticato utente = pUtenti.get(filtro).get(0);
        //vedere se il punteggio è sufficiente per l'upgrade, dato il ruolo attuale
        //se non è sufficiente return false
        //se è sufficiente fare l'upgrade del ruolo, castare l'utente nella classe di ruolo successivo e return true
        return false;
    }

    @Override
    public void scendiDiLivello(String idUtente) {
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", idUtente);
        ClsTuristaAutenticato utente = pUtenti.get(filtro).get(0);
        //downgrade di punteggio, ruolo e cast nella classe inferiore
    }

    @Override
    public void resetLivello(String idUtente) {
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("id", idUtente);
        ClsTuristaAutenticato utente = pUtenti.get(filtro).get(0);
        //ruolo resettato a turista autenticato, punteggio minimo
    }
}
