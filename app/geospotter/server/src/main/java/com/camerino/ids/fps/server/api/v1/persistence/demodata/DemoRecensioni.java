package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRecensioni;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(2)
public class DemoRecensioni {
    @Bean
    public CommandLineRunner aggiungiRecensioni(RepoRecensioni repo) {
        List<ClsRecensione> recensioni = new ArrayList<>();
        ClsRecensione recensione = new ClsRecensione();
        recensione.setContenuto("Contenuto Recensione");
        recensione.setOggetto("Bello, Carino, Avventuroso");
        recensione.setValutazione(2D);
        recensione.setIdNodoAssociato(0L);
        recensione.setIdCreatore(1L);
        recensioni.add(recensione);
        return args -> repo.saveAll(recensioni);
    }
}
