package com.camerino.ids.core.data.contenuti;

import com.camerino.ids.core.data.utils.Posizione;

/**
 * Contiene i dati di un Contenuto generico nella piattaforma
 */
public class ClsContenuto extends ClsInformazione {
    String id;
    Posizione posizione;
    String nome;
    String descrizione;

    //region Getter e setter

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    //endregion
}
