package com.camerino.ids.core.data.utenti;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Questo ruolo accetta o respinge le richieste fatte dai vari utenti.
 * Ha anche la possibilità di regolare le autorizzazioni (ruoli) associati ai
 * vari utenti, per governare il loro comportamento.
 * Non è possibile diventare Curatore tramite sistema a punteggi.
 */
@Entity
public class ClsCuratore extends ClsAnimatore{
    //TODO: Ha senso? é una relazione molti a molti dovremmo creare una classe
    // che associa comuni e curatori. Se un utente arriva ad essere curatore in pi comuni
    // creaiamo deve creare un'altro account?
    // (modificato id da String a long)
    String idComuneAssociato;
    public ClsCuratore(){
        this.punteggio = Integer.MAX_VALUE;
    }

    public ArrayList<ClsSegnalazione> getAllSegnalazioni() {
        HashMap<String, Object> filters = new HashMap<>();
        return iperSegnalazioni.get(filters);
    }
}
