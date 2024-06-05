package com.camerino.ids.core.data.contenuti;

import jakarta.persistence.*;
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
    Long idCreatore;

    //region Getters and setters

    public String getId() {
        return Objects.toString(id);
    }

    public void setId(String id) {
        this.id = Long.valueOf(id);
    }

    public Long getIdCreatore() {
        return idCreatore;
    }

    public void setIdCreatore(Long usernameCreatore) {
        this.idCreatore = usernameCreatore;
    }

    //endregion

}
