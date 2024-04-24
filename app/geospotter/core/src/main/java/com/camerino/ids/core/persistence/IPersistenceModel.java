package com.camerino.ids.core.persistence;
import java.util.ArrayList;
import java.util.HashMap;

/**TODO: aggiornare la documentazione con questa classe
 * Questa classe definisce quali metodi CRUD definire
 */
public interface IPersistenceModel<E> {
    /**
     * Questo metodo rappresenta la lettura dei dati.
     * @param filters Eventuali filtri da applicare. Questo è un parametro libero. E' a discrezione del programmatore come usarlo.
     * @return Array contente i dati letti.
     */
    ArrayList<E> get(HashMap<String,Object> filters);

    /**
     * Questo metodo rappresenta l'aggiornamento di un dato.
     * @param filters Eventuali filtri da applicare. Questo è un parametro libero. E' a discrezione del programmatore come usarlo.
     * @param object Oggetto con i dati modificati.
     * @return True se l'operazione ha successo,
     *         False altrimenti.
     */
    boolean update(HashMap<String,Object> filters, E object);

    /**
     * Questo metodo rappresenta l'inserimento di un dato.
     * @param object Oggetto da inserire
     * @return True se l'operazione ha successo,
     *         False altrimenti.
     */
    boolean insert(E object);

    /**
     * Questa operazione rappresenta l'eliminazione di una dato.
     * @param filters Eventuali filtri da applicare. Questo è un parametro libero. E' a discrezione del programmatore come usarlo.
     * @return True se l'operazione ha successo,
     *         False altrimenti.
     */
    boolean delete(HashMap<String,Object> filters);

}

