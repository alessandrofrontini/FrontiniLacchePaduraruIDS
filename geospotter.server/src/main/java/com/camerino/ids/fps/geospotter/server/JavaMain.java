package com.camerino.ids.fps.geospotter.server;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.utenti.ClsContributor;

public class JavaMain {
    public static void main(String[] args) {
        test_aggiungi_nodo();
    }

    public static void test_aggiungi_nodo(){
        ClsContributor contributor = new ClsContributor();
        ClsNodo nodo = new ClsNodo();
        nodo.setTipologiaNodo(ClsNodo.TipologiaNodo.COMMERCIALE);
        contributor.inserisciNodo(nodo);
    }
}
