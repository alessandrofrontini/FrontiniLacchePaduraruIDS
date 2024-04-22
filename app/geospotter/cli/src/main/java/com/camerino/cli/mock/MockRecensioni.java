package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO: CHECKS
public class MockRecensioni implements IPersistenceModel<ClsRecensione>
{
    private ArrayList<ClsRecensione> recensioni = new ArrayList<ClsRecensione>();

    private long idCounter = 0;

    public MockRecensioni () {
        //generaRecensioni();
    }

    //region CRUD metodi
    @Override
    public ArrayList<ClsRecensione> get(HashMap<String, Object> filters) {

        ArrayList<ClsRecensione> tmp = new ArrayList<ClsRecensione>();

        if(filters != null)
        {
            if(filters.containsKey("id"))
            {
                tmp.add(getRecensioneById(filters.get("id").toString()));
                return tmp;
            }
        }

        return this.recensioni;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRecensione object ) {

        if(object.getUsernameCreatore() == getRecensioneById(filters.get("id").toString()).getUsernameCreatore())
        {
            if(filters.containsKey("id"))
            {
                return modificaRecensione(filters.get("id").toString(), object);
            }
        }

        return false;
    }

    @Override
    public boolean insert(ClsRecensione object) {
        if(!this.recensioni.contains(object))
        {
            return aggiungiRecensione(object);
        }
        return false;

    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        //TODO: come faccio il controllo sullo username?
            if(filters.containsKey("id"))
                return eliminaRecensione(filters.get("id").toString());
        return false;
    }
    //endregion

    //region ------------------------------ Metodi Privati (CRUD) ------------------------------------------
    private ClsRecensione getRecensioneById(String id) {
        List<ClsRecensione> tmp =  recensioni.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private boolean modificaRecensione(String id, ClsRecensione recensione){
        ClsRecensione tmp = getRecensioneById(id);
        int index = recensioni.indexOf(tmp);
        if(index<0)
            return false;
        recensioni.set(index, recensione);
        return true;
    }
    private boolean aggiungiRecensione(ClsRecensione recensione){
        idCounter++;
        //nodo.setId(""+idCounter);
        return recensioni.add(recensione);
    }
    private boolean eliminaRecensione(String id){
        return recensioni.remove(getRecensioneById(id));
    }
    //endregion

    //region ------------------------------ Metodi Privati (UTILS) ------------------------------------------
    private void generaRecensioni() {
        ClsRecensione recensione1 = new ClsRecensione();
        recensione1.setId("1");
        recensione1.setUsernameCreatore("TEST USERNAME");
        recensione1.setContenuto("TEST CONTENUTO");
        recensione1.setOggetto("TEST OGGETTO");
        recensione1.setValutazione(0);
        recensione1.setIdContenutoAssociato("1");
        this.recensioni.add(recensione1);

        ClsRecensione recensione2 = new ClsRecensione();
        recensione2.setId("2");
        recensione2.setUsernameCreatore("TEST USERNAME2");
        recensione2.setContenuto("TEST CONTENUTO2");
        recensione2.setOggetto("TEST OGGETTO2");
        recensione2.setValutazione(0);
        recensione2.setIdContenutoAssociato("2");
        this.recensioni.add(recensione2);

        ClsRecensione recensione3 = new ClsRecensione();
        recensione3.setId("3");
        recensione3.setUsernameCreatore("TEST USERNAME3");
        recensione3.setContenuto("TEST CONTENUTO3");
        recensione3.setOggetto("TEST OGGETTO3");
        recensione3.setValutazione(0);
        recensione3.setIdContenutoAssociato("3");
        this.recensioni.add(recensione3);

    }
    //endregion



}
