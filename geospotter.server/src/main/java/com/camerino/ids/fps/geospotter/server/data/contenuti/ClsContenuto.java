package com.camerino.ids.fps.geospotter.server.data.contenuti;

import com.camerino.ids.fps.geospotter.server.data.utils.Posizione;

public class ClsContenuto extends ClsInformazione{
    String id;
    Posizione posizione;
    String nome;
    String descrizione;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
