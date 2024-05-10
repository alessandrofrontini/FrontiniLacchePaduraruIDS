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
        ArrayList<ClsNodo> nodi = new ArrayList<ClsNodo>();
        ClsNodo nodo = new ClsNodo();
        nodo.setNome("Centro Camerino");
        nodo.setTipologiaNodo(ClsNodo.eTologiaNodo.CULTURALE);
        nodo.setIdComune("Camerino");
        nodo.setaTempo(true);
        nodo.setDescrizione("Ciao");
        nodo.setPosizione(new Posizione(12,23));
        nodi.add(nodo);
        return args -> repo.saveAll(nodi);
    }
}
