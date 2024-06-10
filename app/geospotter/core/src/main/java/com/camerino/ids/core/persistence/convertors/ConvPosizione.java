package com.camerino.ids.core.persistence.convertors;

import com.camerino.ids.core.data.utils.Posizione;
import jakarta.persistence.AttributeConverter;

/**
 * Converte la class Posizione in una stringa formato "x y".
 */
public class ConvPosizione implements AttributeConverter<Posizione, String> {
    public static final String delimitatore = " ";

    @Override
    public String convertToDatabaseColumn(Posizione posizione) {
        if (posizione==null) return null;
        return posizione.getX() + delimitatore + posizione.getY();
    }

    @Override
    public Posizione convertToEntityAttribute(String s) {
        String[] coords = s.split(delimitatore);
        return new Posizione(Double.parseDouble((coords[0])), Double.parseDouble(coords[1]));
    }
}
