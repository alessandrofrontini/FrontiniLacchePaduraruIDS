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
    String usernameCreatore;

    //region Getters and setters

    public String getId() {
        return Objects.toString(id);
    }

    public void setId(String id) {
        this.id = Long.valueOf(id);
    }

    public String getUsernameCreatore() {
        return usernameCreatore;
    }

    public void setUsernameCreatore(String usernameCreatore) {
        this.usernameCreatore = usernameCreatore;
    }

    //endregion

}
