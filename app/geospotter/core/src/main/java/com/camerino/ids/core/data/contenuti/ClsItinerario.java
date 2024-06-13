package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Contiene i dati di un itinerario
 */
@Entity
public class ClsItinerario {
    Long idCreatore;
    @ManyToMany
    List<ClsNodo> tappe = new ArrayList<>();
    boolean ordinato;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id = 0L;
    String nome;

    //region Getter e setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = Long.valueOf(id);
    }

    public Long getIdCreatore() {
        return idCreatore;
    }

    public void setIdCreatore(Long idCreatore) {
        this.idCreatore = idCreatore;
    }

    public List<ClsNodo> getTappe() {
        return tappe;
    }

    public void setTappe(List<ClsNodo> tappe) {
        this.tappe = tappe;
    }

    public boolean isOrdinato() {
        return ordinato;
    }

    public void setOrdinato(boolean ordinato) {
        this.ordinato = ordinato;
    }
    //endregion

    public String visualizzaItinerario() {
        String dummy = "";
        dummy += "ID: " + this.getId() + "\n";
        dummy += "isOrdered: " + this.ordinato + "\n";
        dummy += "Creatore: " + this.getIdCreatore() + "\n";
        dummy += "Nome: " + this.getNome() + "\n";
        dummy += "TAPPE: " + "\n" + this.visualizzaTappe(this.tappe);

        return dummy;
    }

    /**
     * Metodo di utility per la visualizzazione delle tappe di un Itinerario
     *
     * @param tappe elenco tappe
     * @return tappe formato string
     */
    private String visualizzaTappe(List<ClsNodo> tappe) {
        String dummy = "";

        for (int i = 0; i < tappe.size(); i++) {
            dummy += "\t" + i + ")" + tappe.get(i).getNome() + "\n";
        }

        return dummy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsItinerario that = (ClsItinerario) o;
        return ordinato == that.ordinato &&
                Objects.equals(new HashSet<>(tappe),
                        new HashSet<>(that.tappe));
    }

    @Override
    public int hashCode() {
        return Objects.hash(tappe, ordinato);
    }
}
