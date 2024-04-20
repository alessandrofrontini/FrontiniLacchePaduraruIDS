package com.camerino.ids.fps.geospotter.server.persistence.mock;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsImmagine;

import java.util.ArrayList;

public class MockImmagini
{
    private static MockImmagini instance = null;
    private ArrayList<ClsImmagine> immagini = new ArrayList<>();

    //TODO:add to vpp
    private long idCounter = 0;

    //Costruttore
    private MockImmagini()
    {

    }

    //region getInstance MockImmagini
    public static MockImmagini getInstance(){
        if(instance==null)
            instance = new MockImmagini();
        return instance;
    }
    //endregion

    //region metodi

    //endregion

    private void generaImmagini()
    {
    ClsImmagine immagine1 = new ClsImmagine();
    immagine1.setId("1");
    immagine1.setURL("https://picsum.photos/200");
    immagine1.setIdCOntenutoAssociato("1");//Comune
    immagine1.setUsernameCreatore("");

    ClsImmagine immagine2 = new ClsImmagine();
    immagine2.setId("2");
    immagine2.setURL("https://picsum.photos/200");
    immagine2.setIdCOntenutoAssociato("2");//Nodo
    immagine2.setUsernameCreatore("");

    ClsImmagine immagine3 = new ClsImmagine();
    immagine3.setId("3");
    immagine3.setURL("https://picsum.photos/200");
    immagine3.setIdCOntenutoAssociato("3");
    immagine3.setUsernameCreatore("");

    ClsImmagine immagine4 = new ClsImmagine();
    immagine4.setId("4");
    immagine4.setURL("https://picsum.photos/200");
    immagine4.setIdCOntenutoAssociato(" ");
    immagine4.setUsernameCreatore("");



    }
}
