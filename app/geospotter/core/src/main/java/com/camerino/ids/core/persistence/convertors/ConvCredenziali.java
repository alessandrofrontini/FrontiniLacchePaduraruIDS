package com.camerino.ids.core.persistence.convertors;

import com.camerino.ids.core.data.utils.Credenziali;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ConvCredenziali implements AttributeConverter<Credenziali, String> {

    public static final String SEPARATOR = " ";

    @Override
    public String convertToDatabaseColumn(Credenziali credenziali) {
        return credenziali.getUsername() + SEPARATOR + credenziali.getPassword();
    }

    @Override
    public Credenziali convertToEntityAttribute(String s) {
        String[] cred = s.split(SEPARATOR);
        Credenziali credenziali = new Credenziali();
        credenziali.setPassword(cred[0]);
        credenziali.setPassword(cred[1]);
        return credenziali;
    }
}
