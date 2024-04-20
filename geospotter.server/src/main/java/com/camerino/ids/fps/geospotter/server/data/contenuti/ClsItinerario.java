package com.camerino.ids.fps.geospotter.server.data.contenuti;

import java.util.ArrayList;

/**
 * TODO: commentare
 */
public class ClsItinerario {
    //TODO: idCreatore invece di username(?)
    String usernameCreatore;
    ArrayList<ClsNodo> tappe = new ArrayList<>();
    boolean ordinato;
    String id;
    //TODO: aggiungere titolo itinerario sulla documentazione
    String nome;

    //region Getter e setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameCreatore() {
        return usernameCreatore;
    }

    public void setUsernameCreatore(String usernameCreatore) {
        this.usernameCreatore = usernameCreatore;
    }

    public ArrayList<ClsNodo> getTappe() {
        return tappe;
    }

    public void setTappe(ArrayList<ClsNodo> tappe) {
        this.tappe = tappe;
    }

    public boolean isOrdinato() {
        return ordinato;
    }

    public void setOrdinato(boolean ordinato) {
        this.ordinato = ordinato;
    }
    //endregion

    public String visualizzaItinerario()
    {
        String dummy = "-<-<-<-<-<-<-< DETTAGLIO ITINERARIO "+this.getId()+ "-<-<-<-<-<-<-<\n\n";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "isOrdered: " + this.ordinato + "\n";
        dummy += "Username Creatore: " + this.getUsernameCreatore() + "\n";
        dummy += "Nome: " + this.getNome() + "\n";
        dummy += "TAPPE: " + this.tappe + "\n" + this.visualizzaTappe(this.tappe);


        dummy += "-<-<-<-<-<-<-< FINE DETTAGLIO ITINERARIO "+this.getId()+ "-<-<-<-<-<-<-<\n\n";

        return dummy;
    }

    private String visualizzaTappe(ArrayList<ClsNodo> tappe)
    {
        String dummy = "";

            for(int i = 0; i < tappe.size(); i++)
            {
                 dummy += "\t" + i + ")" + tappe.get(i).getNome() + " - (" + tappe.get(i).getIdComune() + ")\n\n";
            }

        return  dummy;
    }
}
