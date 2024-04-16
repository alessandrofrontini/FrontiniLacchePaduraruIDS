package com.camerino.ids.fps.geospotter.server.persistance.mock;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;

import java.util.ArrayList;
import java.util.List;

public class MockNodi {
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

    public boolean aggiungiNodo(ClsNodo nodo){
        idCounter++;
        nodo.setId(""+idCounter);
        return nodi.add(nodo);
    }

    public boolean eliminaNodo(String id){
        return nodi.remove(getNodoById(id));
    }

    public boolean modificaNodo(ClsNodo nodo){
        ClsNodo tmp = getNodoById(nodo.getId());
        int index = nodi.indexOf(tmp);
        if(index<0)
            return false;
        nodi.set(index, nodo);
        return true;
    }
    public ClsNodo getNodoById(String id){
        List<ClsNodo> tmp =  nodi.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
}
