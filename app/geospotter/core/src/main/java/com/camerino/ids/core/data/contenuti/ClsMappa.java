package com.camerino.ids.core.data.contenuti;

import com.camerino.ids.core.persistence.IPersistenceModel;

/**
 * TODO: commentare
 */
@Deprecated()
public class ClsMappa {
    ClsComune[] comuni;
    ClsItinerario[] itinerari;

    //TODO: aggiungi al vpp
    //region Mocks
    IPersistenceModel<ClsNodo> mockNodi;
    IPersistenceModel<ClsComune> mockComuni;
    IPersistenceModel<ClsItinerario> mockItinerari;
    IPersistenceModel<ClsRecensione> mockRecensione;
    IPersistenceModel<ClsImmagine> mockImmagini;
    //endregion

    //region Getter e setter
    public void setComuni(ClsComune[] comuni) {
        this.comuni = comuni;
    }

    public ClsItinerario[] visualizzaItinerari() {
        return itinerari;
    }

    public void setItinerari(ClsItinerario[] itinerari) {
        this.itinerari = itinerari;
    }

    public void setMockNodi(IPersistenceModel<ClsNodo> mockNodi) {
        this.mockNodi = mockNodi;
    }

    public void setMockComuni(IPersistenceModel<ClsComune> mockComuni) {
        this.mockComuni = mockComuni;
    }

    public void setMockItinerari(IPersistenceModel<ClsItinerario> mockItinerari) {
        this.mockItinerari = mockItinerari;
    }

    public void setMockRecensione(IPersistenceModel<ClsRecensione> mockRecensione) {
        this.mockRecensione = mockRecensione;
    }

    public void setMockImmagini(IPersistenceModel<ClsImmagine> mockImmagini) {
        this.mockImmagini = mockImmagini;
    }


    //endregion

    public String visualizzaMappa() {
        String dummy = "\n\n\n -<-<-<-<-<-<-< I NODI -<-<-<-<-<-<-< \n\n\n";

        for (int i = 0; i < this.mockNodi.get(null).size(); i++) {
            dummy += this.mockNodi.get(null).get(i).visualizzaNodo();
        }

        dummy += "\n\n\n-<-<-<-<-<-<-< I COMUNI -<-<-<-<-<-<-<\n\n\n ";

        for (int i = 0; i < this.mockComuni.get(null).size(); i++) {
            dummy += this.mockComuni.get(null).get(i).visualizzaComune();
        }

        dummy += "\n\n\n-<-<-<-<-<-<-< GLI ITINERARI -<-<-<-<-<-<-<\n\n\n ";


        for (int i = 0; i < this.mockItinerari.get(null).size(); i++) {
            dummy += this.mockItinerari.get(null).get(i).visualizzaItinerario();
        }

        return dummy;


    }

}
