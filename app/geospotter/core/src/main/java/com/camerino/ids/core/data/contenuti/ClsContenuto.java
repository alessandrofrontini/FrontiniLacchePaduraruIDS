package com.camerino.ids.core.data.contenuti;

import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.convertors.ConvPosizione;

import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;

/**
 * Contiene i dati di un Contenuto generico nella piattaforma
 */
@MappedSuperclass
public abstract class ClsContenuto extends ClsInformazione {

    @Convert(converter = ConvPosizione.class)
    Posizione posizione;
    Long nome;
    Long descrizione;

    //region Getter e setter
    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
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
    //endregion
}
