package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoComuni;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;

@Configuration
@Order(1)
public class DemoComuni {
    @Bean
    public CommandLineRunner aggiungiComuni(RepoComuni repo) {
        ArrayList<ClsComune> comuni = new ArrayList<>();
        ClsComune comune = new ClsComune();
        comune.setId("SE MI VEDI NON FUNZIONA");
        comune.setNome("Camerino");
        comune.setDescrizione("Comune di Camerino");
        comune.setPosizione(new Posizione(13,24));
        comuni.add(comune);
        return args -> repo.saveAll(comuni);
    }
}
