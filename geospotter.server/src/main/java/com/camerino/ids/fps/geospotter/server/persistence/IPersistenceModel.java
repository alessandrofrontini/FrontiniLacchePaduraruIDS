package com.camerino.ids.fps.geospotter.server.persistence;

import java.util.Dictionary;
import java.util.HashMap;

//TODO: aggiornare la documentazione
public interface IPersistenceModel<E> {
    E[] get(HashMap<String,Object> filters);
    boolean update(HashMap<String,Object> filters, E object);
    boolean insert(E object);
    boolean delete(HashMap<String,Object> filters);
}
