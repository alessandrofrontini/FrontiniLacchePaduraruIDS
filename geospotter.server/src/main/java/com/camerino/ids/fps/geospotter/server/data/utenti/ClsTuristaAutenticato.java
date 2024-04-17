package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsRecensione;
import com.camerino.ids.fps.geospotter.server.data.utils.Credenziali;

public class ClsTuristaAutenticato extends ClsTurista implements ILoggedUserAction{
    public enum RUOLO_UTENTE {
        TURISTA_AUTENTICATO(49),
        CONTRIBUTOR(599),
        CONTRIBUTOR_AUTORIZZATO(999),
        ANIMATORE(1000),
        CURATORE(Integer.MAX_VALUE), GESTORE_DELLA_PIATTAFORMA(Integer.MAX_VALUE);

        private Integer value;

        public Integer getValue(){
            return value;
        }

        RUOLO_UTENTE(Integer value){
            this.value = value;
        }
    }
    String id;
    Credenziali credenziali;
    int punteggio;
    RUOLO_UTENTE ruoloUtente;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RUOLO_UTENTE getRuoloUtente() {
        return ruoloUtente;
    }

    public void setRuoloUtente(RUOLO_UTENTE ruoloUtente) {
        this.ruoloUtente = ruoloUtente;
    }

    public Credenziali getCredenziali() {
        return credenziali;
    }

    public void setCredenziali(Credenziali credenziali) {
        this.credenziali = credenziali;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    @Override
    public boolean inserisciRecensione() {
        return false;
    }

    @Override
    public boolean eliminaRecensione(String id) {
        return false;
    }

    @Override
    public boolean modificaRecensione() {
        return false;
    }

    @Override
    public boolean inserisciImmagine() {
        return false;
    }

    @Override
    public ClsRecensione[] visualizzaRecensioniPosessore() {
        return null;
    }

}
