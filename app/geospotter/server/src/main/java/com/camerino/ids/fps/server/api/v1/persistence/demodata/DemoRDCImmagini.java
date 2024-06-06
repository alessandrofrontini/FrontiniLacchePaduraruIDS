package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDCImmagini;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(3)
public class DemoRDCImmagini {
    @Bean
    public CommandLineRunner aggiungiRDCImmagini(RepoRDCImmagini repo) {
        ClsTuristaAutenticato creatore1 = new ClsTuristaAutenticato();
        creatore1.setId(1L);
        List<ClsRDCImmagine> immagini = new ArrayList<>();
        ClsRDCImmagine rdcImmmagine1 = new ClsRDCImmagine();
        rdcImmmagine1.setTipo(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        rdcImmmagine1.setCreatore(creatore1);
        //immagini.add(rdcImmmagine1);
        return args -> repo.saveAll(immagini);
    }
}
