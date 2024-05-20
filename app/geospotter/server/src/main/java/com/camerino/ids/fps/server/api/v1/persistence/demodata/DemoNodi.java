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
        nodo1.setTipologiaNodo(ClsNodo.eTologiaNodo.CULTURALE);
        nodo1.setIdComune("Camerino");
        nodo1.setaTempo(true);
        nodo1.setDescrizione("Ciao");
        nodo1.setPosizione(new Posizione(12,23));
        nodi.add(nodo1);

        ClsNodo nodo2 = new ClsNodo();
        nodo2.setNome("Porchettaro");
        nodo2.setTipologiaNodo(ClsNodo.eTologiaNodo.CULTURALE);
        nodo2.setIdComune("Camerino");
        nodo2.setaTempo(true);
        nodo2.setDescrizione("Ciao");
        nodo2.setPosizione(new Posizione(12,23));
        nodi.add(nodo2);

        return args -> repo.saveAll(nodi);
    }
}
