package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoNodi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;

@Configuration
@Order(2)
public class DemoNodi {
    @Bean
    public CommandLineRunner aggiungiNodi(RepoNodi repo) {
        ArrayList<ClsNodo> nodi = new ArrayList<ClsNodo>();
        ClsNodo nodo = new ClsNodo();
        nodo.setNome("Centro Camerino");
        nodo.setTipologiaNodo(ClsNodo.eTologiaNodo.CULTURALE);
        nodo.setIdComune("Camerino");
        nodo.setaTempo(true);
        nodo.setDescrizione("Ciao");
        nodo.setPosizione(new Posizione(12,23));

        ClsNodo nodo2 = new ClsNodo();
        nodo2.setId("2");
        nodo2.setIdComune("2");
        nodo2.setaTempo(false);
        nodo2.setTipologiaNodo(COMMERCIALE);
        nodo2.setUsernameCreatore("");
        nodo2.setDescrizione("Descrizione - Nodo 2");
        nodo2.setNome("Supermercato");
        nodo2.setPosizione(new Posizione(114, 114));
        nodi.add(nodo2);

        ClsNodo nodo3 = new ClsNodo();
        nodo3.setId("3");
        nodo3.setIdComune("3");
        nodo3.setaTempo(true);
        nodo3.setTipologiaNodo(COMMERCIALE);
        nodo3.setUsernameCreatore("");
        nodo3.setDescrizione("Descrizione - Nodo 3");
        nodo3.setNome("Ristorante");
        nodo3.setPosizione(new Posizione(124, 124));
        nodi.add(nodo3);

        ClsNodo nodo4 = new ClsNodo();
        nodo4.setId("4");
        nodo4.setIdComune("4");
        nodo4.setaTempo(false);
        nodo4.setTipologiaNodo(COMMERCIALE);
        nodo4.setUsernameCreatore("");
        nodo4.setDescrizione("Descrizione - Nodo 4");
        nodo4.setNome("Caffetteria");
        nodo4.setPosizione(new Posizione(134, 134));
        nodi.add(nodo4);

        ClsNodo nodo5 = new ClsNodo();
        nodo5.setId("5");
        nodo5.setIdComune("5");
        nodo5.setaTempo(true);
        nodo5.setTipologiaNodo(COMMERCIALE);
        nodo5.setUsernameCreatore("");
        nodo5.setDescrizione("Descrizione - Nodo 5");
        nodo5.setNome("Libreria");
        nodo5.setPosizione(new Posizione(144, 144));
        nodi.add(nodo5);

        ClsNodo nodo6 = new ClsNodo();
        nodo6.setId("6");
        nodo6.setIdComune("6");
        nodo6.setaTempo(false);
        nodo6.setTipologiaNodo(COMMERCIALE);
        nodo6.setUsernameCreatore("");
        nodo6.setDescrizione("Descrizione - Nodo 6");
        nodo6.setNome("Cinema");
        nodo6.setPosizione(new Posizione(154, 154));
        nodi.add(nodo6);

        ClsNodo nodo7 = new ClsNodo();
        nodo7.setId("7");
        nodo7.setIdComune("7");
        nodo7.setaTempo(true);
        nodo7.setTipologiaNodo(COMMERCIALE);
        nodo7.setUsernameCreatore("");
        nodo7.setDescrizione("Descrizione - Nodo 7");
        nodo7.setNome("Centro Commerciale");
        nodo7.setPosizione(new Posizione(164, 164));
        nodi.add(nodo7);

        ClsNodo nodo8 = new ClsNodo();
        nodo8.setId("8");
        nodo8.setIdComune("8");
        nodo8.setaTempo(false);
        nodo8.setTipologiaNodo(COMMERCIALE);
        nodo8.setUsernameCreatore("");
        nodo8.setDescrizione("Descrizione - Nodo 8");
        nodo8.setNome("Farmacia");
        nodo8.setPosizione(new Posizione(174, 174));
        nodi.add(nodo8);

        ClsNodo nodo9 = new ClsNodo();
        nodo9.setId("9");
        nodo9.setIdComune("9");
        nodo9.setaTempo(true);
        nodo9.setTipologiaNodo(COMMERCIALE);
        nodo9.setUsernameCreatore("");
        nodo9.setDescrizione("Descrizione - Nodo 9");
        nodo9.setNome("Pasticceria");
        nodo9.setPosizione(new Posizione(184, 184));
        nodi.add(nodo9);

        ClsNodo nodo10 = new ClsNodo();
        nodo10.setId("10");
        nodo10.setIdComune("10");
        nodo10.setaTempo(false);
        nodo10.setTipologiaNodo(COMMERCIALE);
        nodo10.setUsernameCreatore("");
        nodo10.setDescrizione("Descrizione - Nodo 10");
        nodo10.setNome("Parrucchiere");
        nodo10.setPosizione(new Posizione(194, 194));
        nodi.add(nodo10);

        nodi.add(nodo);
        return args -> repo.saveAll(nodi);
    }
}
