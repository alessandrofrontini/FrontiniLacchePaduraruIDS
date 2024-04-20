package com.camerino.ids.fps.geospotter.server.persistence.mock;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsComune;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsRecensione;
import com.camerino.ids.fps.geospotter.server.data.utils.Posizione;

import java.util.ArrayList;

public class MockRecensioni
{
    private static MockRecensioni instance = null;
    private ArrayList<ClsRecensione> recensioni = new ArrayList<ClsRecensione>();
    //TODO: add to vpp
    private long idCounter = 0;

    private MockRecensioni()
    {
        this.generaRecensioni();
        this.idCounter = recensioni.size();
    }

    //region getInstance MockRecensioni
    public static MockRecensioni getInstance()
    {
        if(instance==null)
            instance = new MockRecensioni();
        return instance;
    }
    //endregion

    //region metodi
    //endregion
    private void generaRecensioni()
    {
        ClsRecensione recensione1 = new ClsRecensione();
        recensione1.setId("1");
        recensione1.setUsernameCreatore("");
        recensione1.setIdContenutoAssociato("1"); //Comune
        recensione1.setOggetto("Oggetto - Recensione 1");
        recensione1.setContenuto("Contenuto - Recensione 1");
        recensione1.setValutazione(4);
        this.recensioni.add(recensione1);

        ClsRecensione recensione2 = new ClsRecensione();
        recensione2.setId("2");
        recensione2.setUsernameCreatore("");
        recensione2.setIdContenutoAssociato("2"); //Nodo
        recensione2.setOggetto("Oggetto - Recensione 2");
        recensione2.setContenuto("Contenuto - Recensione 2");
        recensione2.setValutazione(4);
        this.recensioni.add(recensione2);

        ClsRecensione recensione3 = new ClsRecensione();
        recensione3.setId("3");
        recensione3.setUsernameCreatore("");
        recensione3.setIdContenutoAssociato("3"); //Comune
        recensione3.setOggetto("Oggetto - Recensione 3");
        recensione3.setContenuto("Contenuto - Recensione 3");
        recensione3.setValutazione(0);
        this.recensioni.add(recensione3);

        ClsRecensione recensione4 = new ClsRecensione();
        recensione4.setId("4");
        recensione4.setUsernameCreatore("");
        recensione4.setIdContenutoAssociato("4"); //Nodo
        recensione4.setOggetto("Oggetto - Recensione 4");
        recensione4.setContenuto("Contenuto - Recensione 4");
        recensione4.setValutazione(0);
        this.recensioni.add(recensione4);

    }

    public String visualizzaRecensioni()
    {
        String dummy = "-_-_-_-_-_-_-_-_-_ELENCO RECENSIONI-_-_-_-_-_-_-_-_-_";

        if(this.recensioni.size() > 0)
        {

            for(int i = 0; i<this.recensioni.size();i++)
            {
                dummy += "\n\nID: " + this.recensioni.get(i).getId() + "\n";
                dummy += "Contenuto: " + this.recensioni.get(i).getIdContenutoAssociato()+ "\n";
                dummy += "Username Creatore: " + this.recensioni.get(i).getUsernameCreatore() + "\n";
                dummy += "Oggetto: " + this.recensioni.get(i).getOggetto() + "\n";
                dummy += "Contenuto: " + this.recensioni.get(i).getContenuto() + "\n";
                dummy += "Valutazione: " + this.recensioni.get(i).getValutazione() + "\n";
            }
            dummy += "-_-_-_-_-_-_-_-_-_ELENCO RECENSIONI-_-_-_-_-_-_-_-_-_S";
            return dummy;
        }
        else
        {
            return "Nessuna recensione caricata";
        }
    }
}
