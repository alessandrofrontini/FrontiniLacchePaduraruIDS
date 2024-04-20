package com.camerino.ids.fps.geospotter.server.data.utenti;

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
    String idComuneAssociato;
    public ClsCuratore(){
        super.setPunteggio(Integer.MAX_VALUE);
    }
}
