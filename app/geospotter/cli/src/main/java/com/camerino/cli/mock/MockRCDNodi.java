package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.*;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;

public class MockRCDNodi implements IPersistenceModel<ClsRDCNodo> {
    ArrayList<ClsRDCNodo> rcdi = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    @Override
    public ArrayList<ClsRDCNodo> get(HashMap<String, Object> filters) {
        if(filters!=null) {
            ArrayList<ClsRDCNodo> tmp = new ArrayList<>();
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

    @Override
    public boolean update(HashMap<String, Object> filters, ClsRDCNodo object) {
        if(filters.containsKey("id"))
            return updateRCDI(filters.get("id").toString(), object);
        return false;
    }

    @Override
    public boolean insert(ClsRDCNodo object) {
        idCounter++;
        object.setIdRichiesta(""+idCounter);
        return rcdi.add(object);
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        ClsRDCNodo tmp = findById(filters.get("id").toString());
        return rcdi.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(String id, ClsRDCNodo object) {
        ClsRDCNodo tmp = findById(id);
        int index = rcdi.indexOf(tmp);
        if(index<0)
            return false;
        rcdi.set(index, object);
        return true;
    }

    private ClsRDCNodo findById(String id) {
        List<ClsRDCNodo> tmp =
                rcdi.stream().filter(n->n.getIdRichiesta().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    private List<ClsRDCNodo> findLibere() {
        List<ClsRDCNodo> tmp =
                rcdi.stream().filter(n-> Objects.equals(n.getResponsabile(), null)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    public void leggiRCD(){
        File f = new File("CLIsave/rcd.csv");
        if(f.exists()) {
            try {
                FileReader input = new FileReader(f);
                StringBuilder tutti = new StringBuilder();
                int c;
                while ((c = input.read()) != -1) {
                    tutti.append((char) c);
                }
                if(tutti.length()>1){
                String rcdstutti = String.valueOf(tutti);
                String[] rcds = rcdstutti.split("\r\n");
                if (!Objects.equals(rcds[0], "")) {
                    for (String s : rcds) {
                        String[] dati = s.split(",");
                        ClsRDCNodo rcd = leggiRichiestaNodo(dati);
                        rcdi.add(rcd);
                    }
                    maxID();
                }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private ClsRDCNodo leggiRichiestaNodo(String [] dati) throws ParseException {
        ClsNodo n = new ClsNodo();
        ClsRDCNodo r = new ClsRDCNodo();
        n.setId(dati[3]);
        n.setIdComune(dati[4]);
        n.setaTempo(Boolean.parseBoolean(dati[5]));
        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        n.setDataFine(tmp.parse(dati[6]));
        n.setTipologiaNodo(ClsNodo.eTologiaNodo.valueOf(dati[7]));
        n.setUsernameCreatore(dati[8]);
        n.setNome(dati[9]);
        n.setPosizione(new Posizione(Double.parseDouble(dati[10]), Double.parseDouble(dati[11])));
        if(!Objects.equals(dati[0], "MODIFICA_NODO"))
            r = new ClsRDCNodo(null, n);
        else{
            HashMap<String, Object> filtro2 = new HashMap<>();
            filtro2.put("id", dati[14]);
            r = new ClsRDCNodo(MockLocator.getMockNodi().get(filtro2).get(0), n);
        }
        r.setTipo(EAzioniDiContribuzione.valueOf(dati[0]));
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("username", dati[1]);
        r.setCreatore(MockLocator.getMockTuristi().get(filtro).get(0));
        r.setIdRichiesta(dati[2]);
        if(!Objects.equals(dati[12], "null")) {
            filtro.replace("username", dati[1], dati[12]);
            r.setResponsabile((ClsCuratore) MockLocator.getMockTuristi().get(filtro).get(0));
        }
        else r.setResponsabile(null);
        r.setStato(EStatusRDC.valueOf(dati[13]));
        return r;
    }

    public void scriviRCD(){
        try{
            FileWriter output = new FileWriter("CLIsave/rcd.csv");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsRDCNodo r:rcdi){
                daScrivere.append(r.getTipo() + "," + r.getCreatore().getCredenziali().getUsername() + "," + r.getIdRichiesta() + ",");
                SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                ClsNodo n = r.getNewData();
                daScrivere.append(n.getId() + "," + n.getIdComune() + "," + n.isaTempo() + "," + d.format(n.getDataFine()) + "," + n.getTipologiaNodo() + "," + n.getUsernameCreatore() + "," + n.getNome() + "," + n.getPosizione().getY() + "," + n.getPosizione().getX() + "," + (r.getResponsabile()!=null? r.getResponsabile().getCredenziali().getUsername() : "null") + "," + r.getStato());
                if(r.getTipo()==EAzioniDiContribuzione.MODIFICA_NODO){
                    ClsNodo nodoold = r.getOldData();
                    daScrivere.append("," + nodoold.getId());
                }
                daScrivere.append("\r\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void maxID(){
        for(ClsRDCNodo rc:rcdi){
            if(this.idCounter<Long.parseLong(rc.getIdRichiesta()))
                this.idCounter = Long.parseLong(rc.getIdRichiesta());
        }
    }
    //endregion
}
