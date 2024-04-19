package com.camerino.ids.core.persistence;
import java.util.HashMap;

/**TODO: aggiornare la documentazione
 * Questa classe fornisce i metodi CRUD per salvare i dati.
 */
public interface IPersistenceModel<E> {
    /**
     * TODO: padu
     * @param filters
     * @return
     */
    E[] get(HashMap<String,Object> filters);

    /**
     *  TODO: padu
     * @param filters
     * @param object
     * @return
     */
    boolean update(HashMap<String,Object> filters, E object);

    /**
     * TODO: padu
     * @param object
     * @return
     */
    boolean insert(E object);

    /**
     * TODO: padu
     * @param filters
     * @return
     */
    boolean delete(HashMap<String,Object> filters);
}

