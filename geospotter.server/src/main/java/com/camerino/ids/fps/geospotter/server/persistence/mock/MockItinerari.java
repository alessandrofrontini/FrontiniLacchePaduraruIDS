package com.camerino.ids.fps.geospotter.server.persistence.mock;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsItinerario;

import java.util.ArrayList;
import java.util.List;

public class MockItinerari {
    private static MockItinerari instance = null;
    public static MockItinerari getInstance(){
        if(instance==null)
            instance = new MockItinerari();
        return instance;
    }

    private ArrayList<ClsItinerario> nodi = new ArrayList<ClsItinerario>();
    private long idCounter = 0;
    private MockNodi mNodi = MockNodi.getInstance();

    public boolean inserisciItinerario(ClsItinerario itinerario){
        idCounter++;
        itinerario.setId(""+idCounter);
        for(int i=0; i<itinerario.getTappe().size(); i++){
            itinerario.getTappe().set(i, mNodi.getNodoById(itinerario.getTappe().get(i).getId()));
        }
        return nodi.add(itinerario);
    }

    public boolean eliminaItinerario(String id){
        return nodi.remove(getItinerarioById(id));
    }

    public boolean modificaItinerario(ClsItinerario itinerario){
        ClsItinerario tmp = getItinerarioById(itinerario.getId());
        int index = nodi.indexOf(tmp);
        if(index<0)
            return false;
        nodi.set(index, itinerario);
        return true;
    }
    public ClsItinerario getItinerarioById(String id){
        List<ClsItinerario> tmp =  nodi.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
}
