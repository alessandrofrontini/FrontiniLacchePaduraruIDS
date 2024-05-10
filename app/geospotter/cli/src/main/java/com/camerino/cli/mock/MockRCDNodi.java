package com.camerino.cli.mock;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;

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
            FileReader input = new FileReader("CLIsave/rcd.txt");
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
                //nodo
                ClsNodo n = new ClsNodo();
                n.setId(dati[3]);
                n.setIdComune(dati[4]);
                n.setaTempo(Boolean.parseBoolean(dati[5]));
                SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
                n.setDataFine(tmp.parse(dati[6]));
                switch (dati[7]) {
                    case "commerciale":
                        n.setTipologiaNodo(COMMERCIALE);
                        break;
                    case "culturale":
                        n.setTipologiaNodo(CULTURALE);
                        break;
                    case "culinario":
                        n.setTipologiaNodo(CULINARIO);
                        break;
                }
                n.setUsernameCreatore(dati[8]);
                n.setDescrizione(dati[9]);
                n.setNome(dati[10]);
                n.setPosizione(new Posizione(Double.parseDouble(dati[11]), Double.parseDouble(dati[12])));
                rcd.setDatiNodo(n);
                //immagine
                ClsImmagine i = new ClsImmagine();
                i.setId(dati[13]);
                i.setIdCOntenutoAssociato(dati[14]);
                i.setUsernameCreatore(dati[15]);
                i.setURL(dati[16]);
                rcd.setDatiImmagine(i);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void scriviRCD(){
        try{
            FileWriter output = new FileWriter("CLIsave/rcd.txt");
            StringBuilder daScrivere = new StringBuilder();
            for(ClsRichiestaAzioneDiContribuzione r:rcdi){
                daScrivere.append(r.getId() + "," + r.getUsernameCreatoreRichiesta() + "," + r.geteAzioneDiContribuzione() + ",");
                //dump del nodo se è stato inserito (non c'è nessun id)
                if(r.getDatiNodo()!=null){
                    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

                    daScrivere.append(r.getDatiNodo().getId() + "," + r.getDatiNodo().getIdComune() + "," + r.getDatiNodo().isaTempo() + "," + d.format(r.getDatiNodo().getDataFine()) + "," + r.getDatiNodo().getTipologiaNodo() + "," + r.getDatiNodo().getUsernameCreatore() + "," + r.getDatiNodo().getDescrizione() + "," + r.getDatiNodo().getNome() + "," + r.getDatiNodo().getPosizione().getY() + "," + r.getDatiNodo().getPosizione().getX() + ",");
                }
                else daScrivere.append("null,");
                //dump dell'immagine se esiste
                if(r.getDatiImmagine()!=null){
                    daScrivere.append(r.getDatiImmagine().getId() + "," + r.getDatiImmagine().getIdCOntenutoAssociato() + "," + r.getDatiImmagine().getUsernameCreatore() + "," + r.getDatiImmagine().getURL());
                }
                else daScrivere.append("null");
                daScrivere.append("\r\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    //endregion
}
