package com.camerino.ids.fps.client.visual;

public class ClsComuneVisual {

    public Long id;
    public String nome;
    public String descrizione;
    public Double superficie;
    public String posizione;
    public String curatori;
    public Integer abitanti;

    public Integer getAbitanti() {
        return abitanti;
    }

    public void setAbitanti(Integer abitanti) {
        this.abitanti = abitanti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    public String getCuratori() {
        return curatori;
    }

    public void setCuratori(String curatori) {
        this.curatori = curatori;
    }
}
