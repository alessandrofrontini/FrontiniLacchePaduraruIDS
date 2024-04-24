package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.contenuti.*;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Questo ruolo accetta o respinge le richieste fatte dai vari utenti.
 * Ha anche la possibilità di regolare le autorizzazioni (ruoli) associati ai
 * vari utenti, per governare il loro comportamento.
 * Non è possibile diventare Curatore tramite sistema a punteggi.
 */
public class ClsCuratore extends ClsAnimatore{
    //TODO: Ha senso? é una relazione molti a molti dovremmo creare una classe
    // che associa comuni e curatori. Se un utente arriva ad essere curatore in pi comuni
    // creaiamo deve creare un'altro account?
    // (modificato id da String a long)
    IPersistenceModel<ILoggedUserAction> pUtenti;
    ClsComune comuneAssociato;
    public ClsCuratore(IPersistenceModel<ClsRecensione> r, IPersistenceModel<ClsSegnalazione> s, IPersistenceModel<ClsImmagine> i, IPersistenceModel<ClsRichiestaAzioneDiContribuzione> pRCDNodo, IPersistenceModel<ClsRichiestaAzioneDiContribuzioneItinerario> pRCDItinerari, IPersistenceModel<ClsNodo> nodi, IPersistenceModel<ClsItinerario> itinerari, IPersistenceModel<ClsContestDiContribuzione> contest, ClsComune c, IPersistenceModel<ILoggedUserAction>utenti){
        super(r, s, i, pRCDNodo, pRCDItinerari, nodi, itinerari, contest);
        comuneAssociato = c;
        pUtenti = utenti;
    }

    public boolean registraUtente(ILoggedUserAction utente){
        return pUtenti.insert(utente);
    }
    public boolean modificaInformazioniUtente(HashMap<String, Object> filtri, ILoggedUserAction utente){
        return pUtenti.update(filtri, utente);
    }

    public boolean eliminaUtente(ILoggedUserAction utente){
        //pUtenti.delete(utente); //TODO
        return false;
    }
    public boolean upRank(ILoggedUserAction utente){
        return false; //TODO
    }
    public boolean downRank(ILoggedUserAction utente){
        return false; //TODO
    }
    public boolean resetRank(ILoggedUserAction utente){
        return false; //TODO
    }
    public boolean validaRichiesta(ClsRichiestaAzioneDiContribuzione richiesta, boolean esito){
        if(esito){
            switch (richiesta.geteAzioneDiContribuzione()){
                case INSERISCI_NODO -> pNodi.insert(richiesta.getDatiNodo());
                case INSERISCI_IMMAGINE -> pImmagini.insert(richiesta.getDatiImmagine());
            }
        }
        return esito;
    }
    public boolean validaRichiestaItinerario(ClsRichiestaAzioneDiContribuzioneItinerario richiesta, boolean esito){
        if(esito){
            switch (richiesta.geteAzioniDiContribuzione()){
                case INSERISCI_ITINERARIO -> pItinerari.insert(richiesta.getDatiItinerario());
            }
        }
        return esito;
    }
    public boolean validaSegnalazione(ClsSegnalazione segnalazione, boolean esito){
        if(esito){
            segnalazione.setIdCuratore(Long.parseLong(id));
            //TODO: esito segnalazione? tipo di segnalazione?
        }
        return esito;
    }
    public ArrayList<ClsRichiestaAzioneDiContribuzione> getRichieste(){
        HashMap<String, Object> filtri = new HashMap<>();
        filtri.put("idComune", comuneAssociato.getId());
        return pRDC.get(filtri);
    }
    public ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> getRichiesteItinerari(){
        HashMap<String, Object> filtri = new HashMap<>();
        filtri.put("idComune", comuneAssociato.getId());
        return pRDCI.get(filtri);
    }

    public ArrayList<ClsSegnalazione> getSegnalazioni(){
        HashMap<String, Object> filtri = new HashMap<>();
        filtri.put("idComune", comuneAssociato.getId());
        return pSegnalazioni.get(filtri);
    }

}
