package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

/**
 * TODO: commentare
 */
@MappedSuperclass
public abstract class ClsInformazione
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id = 0L;
    String idCreatore;

    //region Getters and setters

    public String getId() {
        return Objects.toString(id);
    }

    public void setId(String id) {
        this.id = Long.valueOf(id);
    }

    public String getIdCreatore() {
        return idCreatore;
    }

    public void setIdCreatore(String usernameCreatore) {
        this.idCreatore = usernameCreatore;
    }

    //endregion

}
