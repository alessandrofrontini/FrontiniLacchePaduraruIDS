package com.camerino.ids.fps.geospotter.server.persistence.mock;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsComune;
import com.camerino.ids.fps.geospotter.server.data.utils.Posizione;
import com.camerino.ids.fps.geospotter.server.persistence.IPersistenceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class MockComuni
{
    private static MockComuni instance = null;
    private ArrayList<ClsComune> comuni = new ArrayList<ClsComune>();

    //TODO:add to vpp
    private long idCounter = 0;

    //Costruttore
    private MockComuni()
    {
        generaComuni();
        idCounter = comuni.size();
    }

    //region getInstance MockComuni
    public static MockComuni getInstance()
    {
        if(instance==null)
            instance = new MockComuni();
        return instance;
    }
    //endregion

    //region metodi
    public ArrayList<ClsComune> get(HashMap<String, Object> filters)
    {
        return this.comuni;
    }

    private ClsComune[] filterById(Object id) {
        return comuni.stream().filter(comune->comune.getId().equals(id)).toArray(ClsComune[]::new);
    }

    public boolean update(HashMap<String, Object> filters, ClsComune object) {
        //TODO
        return false;
    }

    public boolean insert(ClsComune c)
    {
        this.comuni.add(c);
        return true;
    }

    public boolean delete(HashMap<String, Object> filters) {//TODO
        return false;
    }
    //endregion

    //ID numeri dispari
    private void generaComuni()
    {
        ClsComune comune1 = new ClsComune();
        comune1.setId("1");
        comune1.setCuratoriAssociati(null);
        comune1.setNome("Lezzano");
        comune1.setAbitanti(20000);
        comune1.setSuperficie(49900.94);
        comune1.setDescrizione("Comune#1 autogenerato per testing.");
        comune1.setPosizione(new Posizione(50,20));
        this.comuni.add(comune1);

        ClsComune comune2 = new ClsComune();
        comune2.setId("3");
        comune2.setCuratoriAssociati(null);
        comune2.setNome("Rombazzo");
        comune2.setAbitanti(65600);
        comune2.setSuperficie(903400.94);
        comune2.setDescrizione("Comune#2 autogenerato per testing.");
        comune2.setPosizione(new Posizione(60,50));
        this.comuni.add(comune2);

        ClsComune comune3 = new ClsComune();
        comune3.setId("5");
        comune3.setCuratoriAssociati(null);
        comune3.setNome("Pililla");
        comune3.setAbitanti(54432);
        comune3.setSuperficie(120344.94);
        comune3.setDescrizione("Comune#3 autogenerato per testing.");
        comune3.setPosizione(new Posizione(102,456));
        this.comuni.add(comune3);
    }

    public String visualizzaComuni()
    {
        String dummy = "-!-!-!-!-!-!-!-!-ELENCO COMUNI-!-!-!-!-!-!-!-!-" +"\n\n";

        if(this.comuni.size() > 0)
        {
            for(int i = 0; i<this.comuni.size();i++)
            {
               dummy += "ID: " + this.comuni.get(i).getId() + "\n";
               dummy += "UsernameCreatore: " + this.comuni.get(i).getUsernameCreatore() + "\n";
               dummy += "Nome: " + this.comuni.get(i).getNome() + "\n";
               dummy += "Descrizione: " + this.comuni.get(i).getDescrizione() + "\n";
               dummy += "Numero di Abitanti: " + this.comuni.get(i).getAbitanti() + "\n";
               dummy += "Superficie: " + this.comuni.get(i).getSuperficie() + "\n";
               dummy += "Posizione: " + this.comuni.get(i).getPosizione().getX() + "(X) - " + this.comuni.get(i).getPosizione().getY() + "(Y)" + "\n\n";
            }
            dummy += "-!-!-!-!-!-!-!-!-ELENCO COMUNI-!-!-!-!-!-!-!-!-";
            return dummy;
        }
        else
        {
            return "Nessun comune caricato";
        }
    }
}
