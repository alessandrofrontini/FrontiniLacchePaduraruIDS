package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockNodi implements IPersistenceModel<ClsNodo> {
    private static MockNodi instance = null;
    public static MockNodi getInstance(){
        if(instance==null)
            instance = new MockNodi();
        return instance;
    }

    private ArrayList<ClsNodo> nodi = new ArrayList<ClsNodo>();
    private long idCounter = 0;

    private MockNodi(){
        idCounter = nodi.size();
    }

    @Override
    public ClsNodo[] get(HashMap<String, Object> filters) {
        if(filters.containsKey("id"))
            return new ClsNodo[]{getNodoById(filters.get("id").toString())};
        return new ClsNodo[0];
    }

    private ClsNodo getNodoById(String id){
        List<ClsNodo> tmp =  nodi.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsNodo object) {
        if(filters.containsKey("id"))
            return modificaNodo(filters.get("id").toString(), object);
        return false;
    }

    private boolean modificaNodo(String id, ClsNodo nodo){
        ClsNodo tmp = getNodoById(nodo.getId());
        int index = nodi.indexOf(tmp);
        if(index<0)
            return false;
        nodi.set(index, nodo);
        return true;
    }

    @Override
    public boolean insert(ClsNodo object) {
        return aggiungiNodo(object);
    }

    private boolean aggiungiNodo(ClsNodo nodo){
        idCounter++;
        nodo.setId(""+idCounter);
        return nodi.add(nodo);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        if(filters.containsKey("id"))
            return eliminaNodo(filters.get("id").toString());

        return false;
    }

    private boolean eliminaNodo(String id){
        return nodi.remove(getNodoById(id));
    }
}
