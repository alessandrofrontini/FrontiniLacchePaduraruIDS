package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoImmagini;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoRDCImmagini;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(2)
public class DemoImmagini {
    /*@Bean
    public CommandLineRunner aggiungiImmagini(RepoImmagini repo) {
        ClsTuristaAutenticato creatore1=new ClsTuristaAutenticato();
        creatore1.setId(1+"");
        List<ClsRDCImmagine> immagini = new ArrayList<>();
        ClsRDCImmagine rdcImmmagine1 = new ClsRDCImmagine();
        rdcImmmagine1.setTipo(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        rdcImmmagine1.setCreatore(creatore1);
        return args -> repo.saveAll(immagini);
    }
     */
}
