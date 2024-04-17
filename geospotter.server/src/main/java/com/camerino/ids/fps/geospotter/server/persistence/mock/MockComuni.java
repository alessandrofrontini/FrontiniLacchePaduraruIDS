package com.camerino.ids.fps.geospotter.server.persistence.mock;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsComune;
import com.camerino.ids.fps.geospotter.server.data.utils.Posizione;
import com.camerino.ids.fps.geospotter.server.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MockComuni implements IPersistenceModel<ClsComune> {
    private static MockComuni instance = new MockComuni();
    private MockComuni(){
        generaComuni();
    }

    public static MockComuni getInstance(){
        return instance;
    }

    ArrayList<ClsComune> comuni = new ArrayList<>();
    long id = 0;

    @Override
    public ClsComune[] get(HashMap<String, Object> filters) {
        if(filters.containsKey("id"))
            return filterById(filters.get("id"));
        return comuni.toArray(new ClsComune[0]);
    }

    private ClsComune[] filterById(Object id) {
        return comuni.stream().filter(comune->comune.getId().equals(id)).toArray(ClsComune[]::new);
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsComune object) {
        //TODO
        return false;
    }

    @Override
    public boolean insert(ClsComune comune) {
        id++;
        comune.setId(""+id);
        return comuni.add(comune);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {//TODO
        return false;
    }

    private void generaComuni() {
        ClsComune comune1 = new ClsComune();
        comune1.setDescrizione("Comune#1 autogenerato per testing.");
        comune1.setNome("Lezzano");
        comune1.setPosizione(new Posizione(50,20));
        insert(comune1);

        ClsComune comune2 = new ClsComune();
        comune2.setDescrizione("Un'altro Comune#2 autogenerato per testing.");
        comune2.setNome("Camerino");
        comune2.setPosizione(new Posizione(20,50));
        insert(comune2);
    }
}
