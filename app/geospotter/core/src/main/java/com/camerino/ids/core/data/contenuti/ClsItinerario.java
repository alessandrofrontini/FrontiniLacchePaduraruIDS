package com.camerino.ids.core.data.contenuti;

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
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "isOrdered: " + this.ordinato + "\n";
        dummy += "Username Creatore: " + this.getUsernameCreatore() + "\n";
        dummy += "Nome: " + this.getNome() + "\n";
        dummy += "TAPPE: " + "\n" + this.visualizzaTappe(this.tappe);

        return dummy;
    }

    /**
     * Metodo di utility per la visualizzazione delle tappe di un Itinerario
     * @param tappe elenco tappe
     * @return
     */
    private String visualizzaTappe(ArrayList<ClsNodo> tappe)
    {
        String dummy = "";

        for(int i = 0; i < tappe.size(); i++)
        {
            dummy += "\t" + i + ")" + tappe.get(i).getNome() + "\n\n";
        }

        return  dummy;
    }
}
