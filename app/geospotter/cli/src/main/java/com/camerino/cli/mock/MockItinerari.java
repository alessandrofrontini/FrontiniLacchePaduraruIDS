package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockItinerari implements IPersistenceModel<ClsItinerario> {

    private ArrayList<ClsItinerario> itinerari = new ArrayList<ClsItinerario>();
    private long idCounter = 0;
    private MockNodi mNodi;

    public MockItinerari(MockNodi mNodi){
        this.mNodi = mNodi;
    }

    //region CRUD metodi
    public boolean inserisciItinerario(ClsItinerario itinerario){
        idCounter++;
        itinerario.setId(""+idCounter);
//        for(int i=0; i<itinerario.getTappe().size(); i++){
//            HashMap<String, Object> tmp = new HashMap<>();
//            tmp.put("id",itinerario.getTappe().get(i).getId());
//            itinerario.getTappe().set(i, mNodi.get(tmp)[0]);
//        }
        return itinerari.add(itinerario);
    }

    @Override
    public ArrayList<ClsItinerario> get(HashMap<String, Object> filters) {
//        if(filters.containsKey("id"))
//            return new ClsItinerario[]{getItinerarioById(filters.get("id").toString())};

        return this.itinerari;
    }

    private ClsItinerario getItinerarioById(String id){
        List<ClsItinerario> tmp =  itinerari.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsItinerario object) {
        if(filters.containsKey("id"))
            return modificaItinerario(filters.get("id").toString(), object);
        return false;
    }

    private boolean modificaItinerario(String id, ClsItinerario itinerario){
        ClsItinerario tmp = getItinerarioById(id);
        int index = itinerari.indexOf(tmp);
        if(index<0)
            return false;
        itinerari.set(index, itinerario);
        return true;
    }

    @Override
    public boolean insert(ClsItinerario object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        if (filters.containsKey("id"))
            return eliminaItinerario(filters.get("id").toString());
        return false;
    }

    private boolean eliminaItinerario(String id){
        return itinerari.remove(getItinerarioById(id));
    }
    //endregion



}