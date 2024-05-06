package com.camerino.cli.mock;

import com.camerino.cli.loggers.ClsConsoleLogger;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe che emula molto semplicemente
 */
public class MockComuni implements IPersistenceModel<ClsComune>
{
    ArrayList<ClsComune> comuni = new ArrayList<>();
    long id = 0;

    public MockComuni()
    {
    }

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
                return modificaComune(filters.get("id").toString(), object);
            return false;
        }
        return false;
    }

    @Override
    public boolean insert(ClsComune comune)
    {
        this.id++;

        if(!this.comuni.contains(comune))
        {
            comune.setId(""+this.id);
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


    // ------------------------------ Metodi Privati ------------------------------------------
    private boolean modificaComune(String id, ClsComune comune){
        ClsComune tmp = filterById(comune.getId());
        int index = comuni.indexOf(tmp);
        if(index<0)
            return false;
        comuni.set(index, comune);
        return true;
    }

    private ClsComune filterById(Object id)
    {
        return comuni.stream().filter(comune -> comune.getId().equals(id)).toList().get(0);
    }

//endregion


//SPLITTARE PER \r\n, ; e ,
    public void leggiComuni()
    {
        try {
            FileReader input = new FileReader("comuni.txt");
            StringBuilder comuniFile = new StringBuilder();
            int c;
            while((c= input.read())!=-1) {
                comuniFile.append((char) c);
            }
            String comuniTotal = String.valueOf(comuniFile);
            String [] comuniACapo = comuniTotal.split("\r\n");
            for(String comune:comuniACapo){
                ClsComune daAggiungere = new ClsComune();
                String [] dati = comune.split(",");
                daAggiungere.setId(dati[0]);
                daAggiungere.setNome(dati[1]);
                daAggiungere.setDescrizione(dati[2]);
                Posizione p = new Posizione(Double.parseDouble(dati[3]), Double.parseDouble(dati[4]));
                daAggiungere.setPosizione(p);
                daAggiungere.setAbitanti(Integer.parseInt(dati[5]));
                daAggiungere.setSuperficie(Double.parseDouble(dati[6]));
                ArrayList<String> curatori = new ArrayList<>();
                for(int i = 7; i< dati.length; i++){
                    curatori.add(dati[i]);
                }
                daAggiungere.setCuratoriAssociati(curatori);
                insert(daAggiungere);
            }
        } catch (Exception e){
            ClsConsoleLogger.println("File non trovato.");
        }

    }

    public void scriviComuni(){
        try {
            FileWriter output = new FileWriter("comuni.txt");
            StringBuilder daScrivere = new StringBuilder("");
            for(ClsComune comune:comuni){
                daScrivere.append(comune.getId() + "," + comune.getNome() + "," + comune.getDescrizione() + "," + comune.getPosizione().getX() + "," + comune.getPosizione().getY() + "," + comune.getAbitanti() + "," + comune.getSuperficie() + ",");
                for(String curatore:comune.getCuratoriAssociati())
                    daScrivere.append(curatore + ",");
                daScrivere.deleteCharAt(daScrivere.length()-1);
                daScrivere.append("\r\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch(Exception e){
            ClsConsoleLogger.println("Errore");
        }

    }
}
