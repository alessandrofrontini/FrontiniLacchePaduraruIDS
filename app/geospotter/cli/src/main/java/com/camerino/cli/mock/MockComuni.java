package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe che emula molto semplicemente
 */
public class MockComuni implements IPersistenceModel<ClsComune>
{
    ArrayList<ClsComune> comuni = new ArrayList<ClsComune>();
    long id = 0;

    public MockComuni()
    {
        this.generaComuni();
    }

    //region CRUD metodi
    @Override
    public ArrayList<ClsComune> get(HashMap<String, Object> filters) {
//        if(filters.containsKey("id"))
//            return filterById(filters.get("id"));
        return comuni;
    }

    private ArrayList<ClsComune> filterById(Object id) {
        return null; //comuni.stream().filter(comune -> comune.getId().equals(id));
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
//endregion



    private void generaComuni()
    {
        //ID numeri dispari


        ClsComune comune1 = new ClsComune();
        comune1.setId("1");
        comune1.setCuratoriAssociati(null);
        comune1.setNome("Lezzano");
        comune1.setAbitanti(20000);
        comune1.setSuperficie(49900.94);
        comune1.setDescrizione("Comune#1 autogenerato per testing.");
        comune1.setPosizione(new Posizione(50,20));
        this.comuni.add(comune1);

        ClsComune comune2 = new ClsComune();
        comune2.setId("3");
        comune2.setCuratoriAssociati(null);
        comune2.setNome("Rombazzo");
        comune2.setAbitanti(65600);
        comune2.setSuperficie(903400.94);
        comune2.setDescrizione("Comune#2 autogenerato per testing.");
        comune2.setPosizione(new Posizione(60,50));
        this.comuni.add(comune2);

        ClsComune comune3 = new ClsComune();
        comune3.setId("5");
        comune3.setCuratoriAssociati(null);
        comune3.setNome("Pililla");
        comune3.setAbitanti(54432);
        comune3.setSuperficie(120344.94);
        comune3.setDescrizione("Comune#3 autogenerato per testing.");
        comune3.setPosizione(new Posizione(102,456));
        this.comuni.add(comune3);
    }
}
