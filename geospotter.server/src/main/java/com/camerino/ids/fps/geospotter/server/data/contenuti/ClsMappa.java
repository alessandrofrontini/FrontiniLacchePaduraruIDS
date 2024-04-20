package com.camerino.ids.fps.geospotter.server.data.contenuti;

import com.camerino.ids.fps.geospotter.server.persistence.mock.*;

import java.util.HashMap;

/**
 * TODO: commentare
 */
public class ClsMappa
{
    ClsComune[] comuni;
    ClsItinerario[] itinerari;

    //TODO: aggiungi
    MockNodi mockNodi = MockNodi.getInstance();

    MockComuni mockComuni = MockComuni.getInstance();
    MockItinerari mockItinerari = MockItinerari.getInstance();
    MockRecensioni mockRecensioni = MockRecensioni.getInstance();
    MockImmagini mockImmagini = MockImmagini.getInstance();



    public void setComuni(ClsComune[] comuni) {
        this.comuni = comuni;
    }

    public ClsItinerario[] visualizzaItinerari() {
        return itinerari;
    }

    public void setItinerari(ClsItinerario[] itinerari) {
        this.itinerari = itinerari;
    }
    //endregion

    public String visualizzaMappa()
    {
        String dummy = "\n\n\n -<-<-<-<-<-<-< I NODI -<-<-<-<-<-<-< \n\n\n";

        for(int i = 0;i<this.mockNodi.getNodi().size();i++)
        {
            dummy += this.mockNodi.getNodi().get(i).visualizzaNodo();
        }

        dummy += "\n\n\n-<-<-<-<-<-<-< I COMUNI -<-<-<-<-<-<-<\n\n\n ";


        for(int i = 0; i < this.mockComuni.get(null).size(); i++)
        {
            dummy += this.mockComuni.get(null).get(i).visualizzaComune();
        }
    return dummy;

    }

}
