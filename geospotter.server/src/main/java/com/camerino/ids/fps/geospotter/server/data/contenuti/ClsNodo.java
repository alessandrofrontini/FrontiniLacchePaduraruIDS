package com.camerino.ids.fps.geospotter.server.data.contenuti;

public class ClsNodo {
    enum TipologiaNodo { COMMERCIALE, CULTURALE, CULINARIO }
    long id;
    long idComune;
    TipologiaNodo tipologiaNodo;
    boolean aTempo;
}
