package com.camerino.cli.mock;

import com.camerino.cli.menu.Input;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.azioni.EStatusRDC;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.camerino.cli.CliUtils.getResourceAsFile;

public class MockRCDNodi implements IPersistenceModel<ClsRDCNodo> {
    ArrayList<ClsRDCNodo> rcdi = new ArrayList<>();
    long idCounter = 0;

    //region Implements IPersistance
    @Override
    public ArrayList<ClsRDCNodo> get(Map<String, Object> filters) {
        if(filters!=null) {
            ArrayList<ClsRDCNodo> tmp = new ArrayList<>();
            if (filters.containsKey("idRDC")) {
                tmp.add(findById(Long.valueOf(filters.get("idRDC").toString())));
                return tmp;
            }
            if (filters.containsKey("usernameCuratore")) {
                if (findLibere() != null) {
                    tmp.addAll(findLibere());
                    return tmp;
                } else return null;
            }
            if(filters.containsKey("idValidazione")){
                ClsRDCNodo rdcNodo = findById(Long.valueOf(filters.get("idValidazione").toString()));
                if((filters.containsKey("accetta"))&&(filters.get("accetta").toString().equals("true"))){
                    switch (rdcNodo.getTipo()){
                        case INSERISCI_NODO:{
                            rdcNodo.getNewData().setIdCreatore(rdcNodo.getCreatore().getId());
                            rdcNodo.setStato(EStatusRDC.ACCETTATO);
                            MockLocator.getMockNodi().insert(rdcNodo.getNewData());
                            break;
                        }
                        case MODIFICA_NODO:{
                            HashMap<String, Object> filtro = new HashMap<>();
                            filtro.put("idNodo", rdcNodo.getOldData().getId());
                            rdcNodo.getNewData().setIdCreatore(rdcNodo.getCreatore().getId());
                            rdcNodo.setStato(EStatusRDC.ACCETTATO);
                            MockLocator.getMockNodi().update(filtro, rdcNodo.getNewData());
                            break;
                        }
                        case ELIMINA_NODO:{
                            HashMap<String, Object> filtro = new HashMap<>();
                            filtro.put("idNodo", rdcNodo.getOldData().getId());
                            rdcNodo.setStato(EStatusRDC.ACCETTATO);
                            MockLocator.getMockNodi().delete(filtro);
                            break;
                        }
                    }
                    rdcNodo.getCreatore().setPunteggio(rdcNodo.getCreatore().getPunteggio()+2);
                }
                else rdcNodo.setStato(EStatusRDC.RIFUTATO);
            }
        }
        return rcdi;
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsRDCNodo object) {
        if(filters.containsKey("id"))
            return updateRCDI(Long.valueOf(filters.get("id").toString()), object);
        return false;
    }

    @Override
    public boolean insert(ClsRDCNodo object) {
        idCounter++;
        object.setIdRichiesta(idCounter);
        return rcdi.add(object);
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        ClsRDCNodo tmp = findById(Long.valueOf(filters.get("id").toString()));
        return rcdi.remove(tmp);
    }
    //endregion

    //region Utility
    private boolean updateRCDI(Long id, ClsRDCNodo object) {
        ClsRDCNodo tmp = findById(id);
        int index = rcdi.indexOf(tmp);
        if(index<0)
            return false;
        rcdi.set(index, object);
        return true;
    }

    private ClsRDCNodo findById(Long id) {
        List<ClsRDCNodo> tmp =
                rcdi.stream().filter(n->n.getIdRichiesta().equals(id)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp.get(0);
    }

    private List<ClsRDCNodo> findLibere() {
        List<ClsRDCNodo> tmp =
                rcdi.stream().filter(n-> Objects.equals(n.getStato(), EStatusRDC.NUOVO)).toList();
        if(tmp.isEmpty())
            return null;
        return tmp;
    }
    public void leggiRCD(){
        File f = getResourceAsFile("CLIsave/rcd.csv");
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
                String[] rcds = Input.removeCarriageReturn(rcdstutti.split("\n"));
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
        n.setId(Long.valueOf(dati[3]));
        n.setIdComuneAssociato(Long.valueOf(dati[4]));
        n.setaTempo(Boolean.parseBoolean(dati[5]));
        n.setDataFine(dati[6]);
        n.setTipologiaNodo(ClsNodo.eTipologiaNodo.valueOf(dati[7]));
        n.setIdCreatore(Long.valueOf(dati[8]));
        n.setNome(dati[9]);
        n.setPosizione(new Posizione(Double.parseDouble(dati[10]), Double.parseDouble(dati[11])));
        if(Objects.equals(dati[0], "INSERISCI_NODO"))
            r = new ClsRDCNodo(null, n);
        else if(Objects.equals(dati[0], "ELIMINA_NODO"))
            r = new ClsRDCNodo(n, null);
        else{
            HashMap<String, Object> filtro2 = new HashMap<>();
            filtro2.put("idNodo", dati[13]);
            r = new ClsRDCNodo(MockLocator.getMockNodi().get(filtro2).get(0), n);
        }
        r.setTipo(EAzioniDiContribuzione.valueOf(dati[0]));
        HashMap<String, Object> filtro = new HashMap<>();
        filtro.put("idUtente", dati[1]);
        r.setCreatore(MockLocator.getMockTuristi().get(filtro).get(0));
        r.setIdRichiesta(Long.valueOf(dati[2]));
        r.setStato(EStatusRDC.valueOf(dati[12]));
        return r;
    }

    public void scriviRCD(){
        try{
            FileWriter output = new FileWriter(getResourceAsFile("CLIsave/rcd.csv"));
            StringBuilder daScrivere = new StringBuilder();
            for(ClsRDCNodo r:rcdi){
                daScrivere.append(r.getTipo() + "," + r.getCreatore().getId() + "," + r.getIdRichiesta() + ",");
                ClsNodo n;
                if(r.getTipo()==EAzioniDiContribuzione.ELIMINA_NODO){
                    n = r.getOldData();
                }
                else{
                    n = r.getNewData();
                }
                daScrivere.append(n.getId() + "," + n.getIdComuneAssociato() + "," + n.isaTempo() + "," + n.getDataFine() + "," + n.getTipologiaNodo() + "," + n.getIdCreatore() + "," + n.getNome() + "," + n.getPosizione().getY() + "," + n.getPosizione().getX() + "," + r.getStato());
                if(r.getTipo()==EAzioniDiContribuzione.MODIFICA_NODO){
                    ClsNodo nodoold = r.getOldData();
                    daScrivere.append("," + nodoold.getId());
                }
                daScrivere.append("\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void maxID(){
        for(ClsRDCNodo rc:rcdi){
            if(this.idCounter<rc.getIdRichiesta())
                this.idCounter = rc.getIdRichiesta();
        }
    }
    //endregion
}
