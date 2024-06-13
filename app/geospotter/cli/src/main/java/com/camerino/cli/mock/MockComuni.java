package com.camerino.cli.mock;

import com.camerino.cli.loggers.ClsConsoleLogger;
import com.camerino.cli.menu.Input;
import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

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


    public ArrayList<ClsComune> get(Map<String, Object> filters)
    {
        ArrayList<ClsComune> tmp = new ArrayList<ClsComune>();

        if(filters != null)
        {
            if(filters.containsKey("idComune"))
            {
                tmp.add(filterById((Long.parseLong(filters.get("idComune").toString()))));
                return tmp;
            }
        }

        return comuni;
    }

    public boolean update(Map<String, Object> filters, ClsComune object)
    {
        if(filters != null)
        {
            if(filters.containsKey("idComune"))
                return modificaComune(filters.get("idComune").toString(), object);
            return false;
        }
        return false;
    }

    public boolean insert(ClsComune comune)
    {
        this.id++;

        if(!this.comuni.contains(comune))
        {
            comune.setId(this.id);
            return this.comuni.add(comune);
        }

        return false;

    }

    public boolean delete(Map<String, Object> filters)
    {
        ClsComune c = this.filterById(Long.parseLong(filters.get("idComune").toString()));

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

    private ClsComune filterById(Long id)
    {
        return comuni.stream().filter(comune -> comune.getId().equals(id)).toList().get(0);
    }

//endregion


    public void leggiComuni()
    {
        File f = new File("CLIsave/comuni.csv");
        if(f.exists()) {
            try {
                FileReader input = new FileReader(f);
                StringBuilder comuniFile = new StringBuilder();
                int c;
                while ((c = input.read()) != -1) {
                    comuniFile.append((char) c);
                }
                if(comuniFile.length()>1) {
                    String comuniTotal = String.valueOf(comuniFile);
                    String[] comuniACapo = Input.removeCarriageReturn(comuniTotal.split("\n"));
                    for (String comune : comuniACapo) {
                        ClsComune daAggiungere = new ClsComune();
                        String[] dati = comune.split(",");
                        daAggiungere.setId(Long.valueOf(dati[0]));
                        daAggiungere.setNome(dati[1]);
                        daAggiungere.setDescrizione(dati[2]);
                        Posizione p = new Posizione(Double.parseDouble(dati[3]), Double.parseDouble(dati[4]));
                        daAggiungere.setPosizione(p);
                        daAggiungere.setAbitanti(Integer.parseInt(dati[5]));
                        daAggiungere.setSuperficie(Double.parseDouble(dati[6]));
                        List<String> curatori = new ArrayList<>();
                        curatori.addAll(Arrays.asList(dati).subList(7, dati.length));
                        daAggiungere.setCuratoriAssociati(caricaCuratoriDaElenco(curatori));
                        comuni.add(daAggiungere);
                    }
                    maxID();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private List<ClsCuratore> caricaCuratoriDaElenco(List<String> c){
        List<ClsCuratore> curatori = new ArrayList<>();
        for(String s:c){
            HashMap<String, Object> filtro = new HashMap<>();
            filtro.put("idUtente", s);
            curatori.add((ClsCuratore) MockLocator.getMockTuristi().get(filtro).get(0));
        }
        return curatori;
    }
    private void maxID(){
        for(ClsComune rc:comuni){
            if(this.id<rc.getId())
                this.id =rc.getId();
        }
    }
    public void scriviComuni(){
        try {
            FileWriter output = new FileWriter("CLIsave/comuni.csv");
            StringBuilder daScrivere = new StringBuilder("");
            for(ClsComune comune:comuni){
                daScrivere.append(comune.getId() + "," + comune.getNome() + "," + comune.getDescrizione() + "," + comune.getPosizione().getX() + "," + comune.getPosizione().getY() + "," + comune.getAbitanti() + "," + comune.getSuperficie() + ",");
                for(ClsCuratore curatore:comune.getCuratoriAssociati())
                    daScrivere.append(curatore.getId() + ",");
                daScrivere.deleteCharAt(daScrivere.length()-1);
                daScrivere.append("\n");
            }
            output.write(String.valueOf(daScrivere));
            output.close();
        } catch(Exception e){
            ClsConsoleLogger.println("Errore");
        }

    }
}
