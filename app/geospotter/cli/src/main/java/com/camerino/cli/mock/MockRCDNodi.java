package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockRCDNodi implements IPersistenceModel<ClsRichiestaAzioneDiContribuzione> {
    ArrayList<ClsRichiestaAzioneDiContribuzione> rcdi = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    @Override
    public ArrayList<ClsRichiestaAzioneDiContribuzione> get(HashMap<String, Object> filters) {
        ArrayList<ClsRichiestaAzioneDiContribuzione> tmp = new ArrayList<>();
        if(filters.containsKey("id")) {
            tmp.add(findById(filters.get("id").toString()));
            return tmp;
        }
        return rcdi;
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRichiestaAzioneDiContribuzione object) {
        if(filters.containsKey("id"))
            return updateRCDI(filters.get("id").toString(), object);
        return false;
    }

    @Override
    public boolean insert(ClsRichiestaAzioneDiContribuzione object) {
        idCounter++;
        object.setId(""+idCounter);
        return rcdi.add(object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        ClsRichiestaAzioneDiContribuzione tmp = findById(filters.get("id").toString());
        return rcdi.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(String id, ClsRichiestaAzioneDiContribuzione object) {
        ClsRichiestaAzioneDiContribuzione tmp = findById(id);
        int index = rcdi.indexOf(tmp);
        if(index<0)
            return false;
        rcdi.set(index, object);
        return true;
    }

    private ClsRichiestaAzioneDiContribuzione findById(String id) {
        List<ClsRichiestaAzioneDiContribuzione> tmp =
                rcdi.stream().filter(n->n.getId().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }
    public void leggiRCD(){
        try{
            FileReader input = new FileReader("rcd.txt");
            StringBuilder tutti = new StringBuilder();
            int c;
            while((c= input.read())!=-1) {
                tutti.append((char) c);
            }
            String rcdstutti = String.valueOf(tutti);
            String [] rcds = rcdstutti.split("\r\n");
            for(String s:rcds){
                String [] dati = s.split(",");
                ClsRichiestaAzioneDiContribuzione rcd = new ClsRichiestaAzioneDiContribuzione();
                rcd.setId(dati[0]);
                rcd.setUsernameCreatoreRichiesta(dati[1]);
                rcd.seteAzioneDiContribuzione(EAzioniDiContribuzione.valueOf(dati[2]));
                HashMap<String, Object> filtro = new HashMap<>();
                filtro.put("id", dati[3]);
                rcd.setDatiNodo(MockLocator.getMockNodi().get(filtro).get(0));
                filtro.replace("id", dati[3], dati[4]);
                rcd.setDatiImmagine(MockLocator.getMockImmagini().get(filtro).get(0));
                insert(rcd);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    //endregion
}
