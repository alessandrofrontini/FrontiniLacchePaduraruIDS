package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MockRCDImmagini implements IPersistenceModel<ClsRDCImmagine> {
    ArrayList<ClsRDCImmagine> rcdi = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    @Override
    public ArrayList<ClsRDCImmagine> get(HashMap<String, Object> filters) {
        if(filters!=null) {
            ArrayList<ClsRDCImmagine> tmp = new ArrayList<>();
            if (filters.containsKey("id")) {
                tmp.add(findById(filters.get("id").toString()));
                return tmp;
            }
            if (filters.containsKey("usernameCuratore")) {
                if (findLibere() != null) {
                    tmp.addAll(findLibere());
                    return tmp;
                } else return null;
            }
        }
        return rcdi;
    }


    public boolean update(HashMap<String, Object> filters, ClsRDCImmagine object) {
        if(filters.containsKey("id"))
            return updateRCDI(filters.get("id").toString(), object);
        return false;
    }


    public boolean insert(ClsRDCImmagine object) {
        idCounter++;
        object.setIdRichiesta(""+idCounter);
        return rcdi.add(object);
    }


    public boolean delete(HashMap<String, Object> filters) {
        ClsRDCImmagine tmp = findById(filters.get("id").toString());
        return rcdi.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(String id, ClsRDCImmagine object) {
        ClsRDCImmagine tmp = findById(id);
        int index = rcdi.indexOf(tmp);
        if(index<0)
            return false;
        rcdi.set(index, object);
        return true;
    }

    private ClsRDCImmagine findById(String id) {
        List<ClsRDCImmagine> tmp =
                rcdi.stream().filter(n->n.getIdRichiesta().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    private List<ClsRDCImmagine> findLibere() {
        List<ClsRDCImmagine> tmp =
                rcdi.stream().filter(n-> Objects.equals(n.getResponsabile(), null)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }

    public void LeggiRCDImmagini(){
        File f = new File("CLIsave/rcdimmagini.csv");
        if(f.exists()) {
            try {
                FileReader input = new FileReader(f);
                StringBuilder tutti = new StringBuilder();
                int c;
                while ((c = input.read()) != -1) {
                    tutti.append((char) c);
                }
                if (tutti.length() > 1) {
                    String rcdstutti = String.valueOf(tutti);
                    String[] rcds = rcdstutti.split("\r\n");
                    if (!Objects.equals(rcds[0], "")) {
                        for (String s : rcds) {
                            String[] dati = s.split(",");
                            ClsImmagine i = new ClsImmagine();
                            i.setId(dati[0]);
                            i.setIdCOntenutoAssociato(dati[1]);
                            i.setUsernameCreatore(dati[2]);
                            i.setURL(dati[3]);
                            ClsRDCImmagine r = new ClsRDCImmagine(null, i);
                            r.setTipo(EAzioniDiContribuzione.valueOf(dati[4]));
                            r.setIdRichiesta(dati[5]);
                            HashMap<String, Object> filtro = new HashMap<>();
                            filtro.put("username", dati[6]);
                            r.setCreatore(MockLocator.getMockTuristi().get(filtro).get(0));
                            if(!Objects.equals(dati[7], "null")) {
                                filtro.replace("username", dati[6], dati[7]);
                                r.setResponsabile((ClsCuratore) MockLocator.getMockTuristi().get(filtro).get(0));
                            }
                            else r.setResponsabile(null);
                            rcdi.add(r);
                        }
                    }
                }
                maxID();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void ScriviRCDImmagini(){
        try {
            FileWriter output = new FileWriter("CLIsave/rcdimmagini.csv");
            StringBuilder daScrivere = new StringBuilder();
            for (ClsRDCImmagine r : rcdi) {
                ClsImmagine i = r.getNewData();
                daScrivere.append(i.getId() + "," + i.getIdCOntenutoAssociato() + "," + i.getUsernameCreatore() + "," + i.getURL() + "," + r.getTipo() +"," + r.getIdRichiesta() + "," + r.getCreatore().getCredenziali().getUsername() + "," + (r.getResponsabile()!=null? r.getResponsabile().getCredenziali().getUsername() : "null") + "," + r.getStato() + "\r\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void maxID(){
        for(ClsRDCImmagine rc:rcdi){
            if(this.idCounter<Long.parseLong(rc.getIdRichiesta()))
                this.idCounter = Long.parseLong(rc.getIdRichiesta());
        }
    }
}
