package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * TODO: commentare
 */
@MappedSuperclass
public abstract class ClsInformazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id = 0L;
    Long idCreatore;

    //region Getters and setters

    public Long getId() {
        return (id);
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

    //endregion

}
