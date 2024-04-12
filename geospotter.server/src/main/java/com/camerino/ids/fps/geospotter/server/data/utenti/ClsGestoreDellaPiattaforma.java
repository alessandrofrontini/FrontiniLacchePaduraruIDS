package com.camerino.ids.fps.geospotter.server.data.utenti;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsComune;

public class ClsGestoreDellaPiattaforma extends ClsAnimatore implements ITownHallAdministrator{
    @Override
    public boolean aggiungiComune(ClsComune comune){
        return false;
    }
    @Override
    public boolean modificaComune(ClsComune comune, String id){
        return  false;
    }
    @Override
    public boolean eliminaComune(String id){
        return false;
    }
    @Override
    public ClsComune[] visualizzaComuni(){
        return null;
    }
}
