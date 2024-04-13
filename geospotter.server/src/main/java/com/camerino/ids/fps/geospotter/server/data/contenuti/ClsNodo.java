package com.camerino.ids.fps.geospotter.server.data.contenuti;

public class ClsNodo {
    public enum TipologiaNodo { COMMERCIALE, CULTURALE, CULINARIO }
    private String id;
    private long idComune;
    private TipologiaNodo tipologiaNodo;
    private boolean aTempo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getIdComune() {
        return idComune;
    }

    public void setIdComune(long idComune) {
        this.idComune = idComune;
    }

    public TipologiaNodo getTipologiaNodo() {
        return tipologiaNodo;
    }

    public void setTipologiaNodo(TipologiaNodo tipologiaNodo) {
        this.tipologiaNodo = tipologiaNodo;
    }

    public boolean isaTempo() {
        return aTempo;
    }

    public void setaTempo(boolean aTempo) {
        this.aTempo = aTempo;
    }
}
