package com.camerino.cli.mock;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.COMMERCIALE;
import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.CULTURALE;

public class MockItinerari implements IPersistenceModel<ClsItinerario> {

    private ArrayList<ClsItinerario> itinerari = new ArrayList<ClsItinerario>();
    private long idCounter = 0;
    private MockNodi mNodi;

    public MockItinerari(MockNodi mNodi){
        this.mNodi = mNodi;
    }

    //region CRUD metodi
    @Override
    public boolean update(HashMap<String, Object> filters, ClsItinerario object) {
        if(filters.containsKey("id"))
            return modificaItinerario(filters.get("id").toString(), object);
        return false;
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
    @Override
    public ArrayList<ClsItinerario> get(HashMap<String, Object> filters) {
//        if(filters.containsKey("id"))
//            return new ClsItinerario[]{getItinerarioById(filters.get("id").toString())};

        return this.itinerari;
    }

    //endregion

    //region  ------------------------------ Metodi Privati (CRUD) ------------------------------------------
    private ClsItinerario getItinerarioById(String id){
        List<ClsItinerario> tmp =  itinerari.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    private boolean modificaItinerario(String id, ClsItinerario itinerario){
        ClsItinerario tmp = getItinerarioById(id);
        int index = itinerari.indexOf(tmp);
        if(index<0)
            return false;
        itinerari.set(index, itinerario);
        return true;
    }
    private boolean eliminaItinerario(String id){
        return itinerari.remove(getItinerarioById(id));
    }
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
    //endregion

    //region  ------------------------------ Metodi Privati (UTILS) ------------------------------------------
    private void generaItinerari(){
        ClsItinerario itinerario1 = new ClsItinerario();
        itinerario1.setId("1");
        itinerario1.setNome("TEST - Itinerario1");
        itinerario1.setOrdinato(true);
        itinerario1.setUsernameCreatore("TEST - USERNAME1");

        ArrayList<ClsNodo> tappe1 = new ArrayList<ClsNodo>();
        //region creazione tappe1
        ClsNodo nodo1 = new ClsNodo();
        nodo1.setId("2");
        nodo1.setIdComune("1");
        nodo1.setaTempo(false);
        nodo1.setTipologiaNodo(COMMERCIALE);
        nodo1.setUsernameCreatore("");
        nodo1.setDescrizione("Descrizione - Nodo 1");
        nodo1.setNome("Negozio");
        nodo1.setPosizione(new Posizione(104, 104));

        ClsNodo nodo2 = new ClsNodo();
        nodo2.setId("4");
        nodo2.setIdComune("3");
        nodo2.setaTempo(false);
        nodo2.setTipologiaNodo(CULTURALE);
        nodo2.setUsernameCreatore("");
        nodo2.setDescrizione("Descrizione - Nodo 2");
        nodo2.setNome("Statua");
        nodo2.setPosizione(new Posizione(114, 114));
        //endregion
        itinerario1.setTappe(tappe1);

    }
    //endregion













}
