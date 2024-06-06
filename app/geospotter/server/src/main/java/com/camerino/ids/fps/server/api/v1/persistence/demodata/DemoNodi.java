package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoNodi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;

@Configuration
@Order(2)
public class DemoNodi {
    @Bean
    public CommandLineRunner aggiungiNodi(RepoNodi repo) {
        ArrayList<ClsNodo> nodi = new ArrayList<>();

        ClsNodo nodo1 = new ClsNodo();
        nodo1.setNome("Centro Camerino");
        nodo1.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
        nodo1.setIdComune("Camerino");
        nodo1.setaTempo(true);
        nodo1.setDescrizione("Ciao");
        nodo1.setPosizione(new Posizione(12,23));
        nodo1.setIdCreatore("1");
        nodi.add(nodo1);

        ClsNodo nodo2 = new ClsNodo();
        nodo2.setNome("Porchettaro");
        nodo2.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
        nodo2.setIdComune("Camerino");
        nodo2.setaTempo(true);
        nodo2.setDescrizione("Ciao");
        nodo2.setPosizione(new Posizione(12,23));
        nodi.add(nodo2);

        ClsNodo nodo3 = new ClsNodo();
        nodo3.setNome("Museo Civico");
        nodo3.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
        nodo3.setIdComune("Macerata");
        nodo3.setaTempo(true);
        nodo3.setDescrizione("Museo con reperti storici");
        nodo3.setPosizione(new Posizione(15, 30));
        nodi.add(nodo3);

        ClsNodo nodo4 = new ClsNodo();
        nodo4.setNome("Teatro Romano");
        nodo4.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
        nodo4.setIdComune("Fermo");
        nodo4.setaTempo(true);
        nodo4.setDescrizione("Antico teatro romano");
        nodo4.setPosizione(new Posizione(14, 28));
        nodi.add(nodo4);

        ClsNodo nodo5 = new ClsNodo();
        nodo5.setNome("Parco Naturale");
        nodo5.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULINARIO);
        nodo5.setIdComune("Ascoli Piceno");
        nodo5.setaTempo(false);
        nodo5.setDescrizione("Parco con flora e fauna locali");
        nodo5.setPosizione(new Posizione(13, 25));
        nodi.add(nodo5);

        return args -> repo.saveAll(nodi);
    }
}
