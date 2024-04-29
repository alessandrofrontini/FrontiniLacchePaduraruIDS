package com.camerino.ids.core.data.contenuti;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * TODO: commentare
 */
@Entity
public class ClsInformazione
{
    @Id
    @UuidGenerator
    String id;
    String usernameCreatore;

    //region Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameCreatore() {
        return usernameCreatore;
    }

    public void setUsernameCreatore(String usernameCreatore) {
        this.usernameCreatore = usernameCreatore;
    }

    //endregion

}
