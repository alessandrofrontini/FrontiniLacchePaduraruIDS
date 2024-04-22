package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;

//TODO: CHECKS
public class MockNodi implements IPersistenceModel<ClsNodo>
{
    private ArrayList<ClsNodo> nodi = new ArrayList<ClsNodo>();
    private long idCounter = 0;

    public MockNodi () {
        //generaNodi();
    }

    //region CRUD metodi
        @Override
        public ArrayList<ClsNodo> get(HashMap<String, Object> filters) {
            ArrayList<ClsNodo> tmp = new ArrayList<ClsNodo>();

            if(filters != null)
            {
                if(filters.containsKey("id"))
                {
                    tmp.add(getNodoById(filters.get("id").toString()));
                    return tmp;
                }
            }
            return this.nodi;
        }
        @Override
        public boolean update(HashMap<String, Object> filters, ClsNodo object) {
            if(filters.containsKey("id"))
                return modificaNodo(filters.get("id").toString(), object);
            return false;
        }
        @Override
        public boolean insert(ClsNodo object) {
            return aggiungiNodo(object);
        }
        @Override
        public boolean delete(HashMap<String, Object> filters) {
            if(filters.containsKey("id"))
                return eliminaNodo(filters.get("id").toString());

            return false;
        }

        //endregion

    //region ------------------------------ Metodi Privati (CRUD) ------------------------------------------
    private ClsNodo getNodoById(String id){
        List<ClsNodo> tmp =  nodi.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private boolean modificaNodo(String id, ClsNodo nodo){
        ClsNodo tmp = getNodoById(id);
        int index = nodi.indexOf(tmp);
        if(index<0)
            return false;
        nodi.set(index, nodo);
        return true;
    }
    private boolean aggiungiNodo(ClsNodo nodo){
        idCounter++;
        //nodo.setId(""+idCounter);
        return nodi.add(nodo);
    }
    private boolean eliminaNodo(String id){
        return nodi.remove(getNodoById(id));
    }
    //endregion

    //region ------------------------------ Metodi Privati (UTILS) ------------------------------------------
    private void generaNodi() {
        //ID numeri pari

        ClsNodo nodo1 = new ClsNodo();
        nodo1.setId("2");
        nodo1.setIdComune("1");
        nodo1.setaTempo(false);
        nodo1.setTipologiaNodo(COMMERCIALE);
        nodo1.setUsernameCreatore("");
        nodo1.setDescrizione("Descrizione - Nodo 1");
        nodo1.setNome("Negozio");
        nodo1.setPosizione(new Posizione(104,104));
        nodi.add(nodo1);

        ClsNodo nodo2 = new ClsNodo();
        nodo2.setId("4");
        nodo2.setIdComune("3");
        nodo2.setaTempo(false);
        nodo2.setTipologiaNodo(CULTURALE);
        nodo2.setUsernameCreatore("");
        nodo2.setDescrizione("Descrizione - Nodo 2");
        nodo2.setNome("Statua");
        nodo2.setPosizione(new Posizione(114,114));
        nodi.add(nodo2);

        ClsNodo nodo3 = new ClsNodo();
        nodo3.setId("6");
        nodo3.setIdComune("5");
        nodo3.setaTempo(false);
        nodo3.setTipologiaNodo(CULINARIO);
        nodo3.setUsernameCreatore("");
        nodo3.setDescrizione("Descrizione - Nodo 3");
        nodo3.setNome("Ristorante");
        nodo3.setPosizione(new Posizione(124,124));
        nodi.add(nodo3);
    }
    //endregion

}
