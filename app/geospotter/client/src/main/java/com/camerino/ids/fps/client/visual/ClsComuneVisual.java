package com.camerino.ids.fps.client.visual;

public class ClsComuneVisual
{

    public String id;
    public Long nome;
    public Long descrizione;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNome() {
        return nome;
    }

    public void setNome(Long nome) {
        this.nome = nome;
    }

    public Long getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(Long descrizione) {
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
