package com.camerino.ids.fps.geospotter.server.data.utenti;

public class ClsCuratore extends ClsAnimatore{
    //TODO: Ha senso? é una relazione molti a molti dovremmo creare una classe
    // che associa comuni e curatori. Se un utente arriva ad essere curatore in pi comuni
    // creaiamo deve creare un'altro account?
    // (modificato id da String a long)
    long idComuneAssociato;
    public ClsCuratore(){
        this.punteggio = Integer.MAX_VALUE;
    }
}
