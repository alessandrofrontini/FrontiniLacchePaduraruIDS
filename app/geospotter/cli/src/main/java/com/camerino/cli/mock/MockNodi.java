package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTipologiaNodo.*;

public class MockNodi implements IPersistenceModel<ClsNodo> {

    private List<ClsNodo> nodi = new ArrayList<ClsNodo>();
    private long idCounter = 0;

    //region CRUD metodi
        @Override
        public List<ClsNodo> get(Map<String, Object> filters) {
    //        if(filters.containsKey("id"))
    //            return new ArrayList<ClsNodo>().{getNodoById(filters.get("id").toString())};
            return this.nodi;
        }

        private ClsNodo getNodoById(Long id){
            List<ClsNodo> tmp =  nodi.stream().filter(n->n.getId().equals(id)).toList();
            if(tmp.isEmpty())
                return null;
            return tmp.get(0);
        }

        @Override
        public boolean update(Map<String, Object> filters, ClsNodo object) {
            if(filters.containsKey("id"))
                return modificaNodo(filters.get("id").toString(), object);
            return false;
        }

        private boolean modificaNodo(Long id, ClsNodo nodo){
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
        public boolean delete(Map<String, Object> filters) {
            if(filters.containsKey("id"))
                return eliminaNodo(filters.get("id").toString());

            return false;
        }

        private boolean eliminaNodo(Long id){
            return nodi.remove(getNodoById(id));
        }
        //endregion


    private void generaNodi()
    {
        //ID numeri pari

        ClsNodo nodo1 = new ClsNodo();
        nodo1.setId("2");
        nodo1.setIdComuneAssociato("1");
        nodo1.setaTempo(false);
        nodo1.setTipologiaNodo(COMMERCIALE);
        nodo1.setIdCreatore("");
        nodo1.setDescrizione("Descrizione - Nodo 1");
        nodo1.setNome("Negozio");
        nodo1.setPosizione(new Posizione(104,104));
        nodi.add(nodo1);

        ClsNodo nodo2 = new ClsNodo();
        nodo2.setId("4");
        nodo2.setIdComuneAssociato("3");
        nodo2.setaTempo(false);
        nodo2.setTipologiaNodo(CULTURALE);
        nodo2.setIdCreatore("");
        nodo2.setDescrizione("Descrizione - Nodo 2");
        nodo2.setNome("Statua");
        nodo2.setPosizione(new Posizione(114,114));
        nodi.add(nodo2);

        ClsNodo nodo3 = new ClsNodo();
        nodo3.setId("6");
        nodo3.setIdComuneAssociato("5");
        nodo3.setaTempo(false);
        nodo3.setTipologiaNodo(CULINARIO);
        nodo3.setIdCreatore("");
        nodo3.setDescrizione("Descrizione - Nodo 3");
        nodo3.setNome("Ristorante");
        nodo3.setPosizione(new Posizione(124,124));
        nodi.add(nodo3);
    }
}
