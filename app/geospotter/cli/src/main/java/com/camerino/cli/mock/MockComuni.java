package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;

//TODO: CHECKS
public class MockComuni implements IPersistenceModel<ClsComune>
{
    ArrayList<ClsComune> comuni = new ArrayList<ClsComune>();
    long idCounter = 0;

    public MockComuni() {
        //this.generaComuni();
    }

    //region CRUD metodi
    @Override
    public ArrayList<ClsComune> get(HashMap<String, Object> filters)
    {
        ArrayList<ClsComune> tmp = new ArrayList<ClsComune>();

        if(filters != null)
        {
            if(filters.containsKey("id"))
            {
                tmp.add(filterById(filters.get("id")));
                return tmp;
            }
        }

        return comuni;
    }
    @Override
    public boolean update(HashMap<String, Object> filters, ClsComune object)
    {
        if(filters != null)
        {
            if(filters.containsKey("id"))
            {
                return this.modificaComune(filters.get("id").toString(), object);
            }
        }
        return false;
    }
    @Override
    public boolean insert(ClsComune comune)
    {
        this.idCounter++;

        if(!this.comuni.contains(comune))
        {
//            comune.setId(""+this.id);
            return this.comuni.add(comune);
        }

        return false;

    }
    @Override
    public boolean delete(HashMap<String, Object> filters)
    {
        ClsComune c = this.filterById(filters.get("id"));

        if(c != null)
        {
            return this.comuni.remove(this.comuni.remove(c));
        }
        return false;
    }
    //endregion

    //region  ------------------------------ Metodi Privati (CRUD) ------------------------------------------
    private boolean modificaComune(String id, ClsComune comune){
        ClsComune tmp = filterById(id);
        int index = comuni.indexOf(tmp);
        if(index<0)
        {
            return false;
        }
        else
        {
            comuni.set(index, comune);
        }

        return true;
    }
    private ClsComune filterById(Object id)
    {
        return comuni.stream().filter(comune -> comune.getId().equals(id)).toList().get(0);
    }
    //endregion

    //region  ------------------------------ Metodi Privati (UTILS)------------------------------------------
    private void generaComuni() {
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
    //endregion


}
