package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utils.Credenziali;
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
        ArrayList<ClsCuratore> Curatori = new ArrayList<>();

        ClsComune comune = new ClsComune();
        comune.setNome("Camerino");
        comune.setDescrizione("Comune di Camerino");
        comune.setPosizione(new Posizione(13,24));
        comuni.add(comune);

        ClsComune com1 = new ClsComune();
        com1.setId("1");
        com1.setNome("Canepona");
        com1.setDescrizione("Una strada, un tabacchi e un autovelox");
        com1.setSuperficie(321);
        Posizione p1 = new Posizione();
        p1.setX(12.1);
        p1.setY(12.3);
        com1.setPosizione(p1);
        com1.setCuratoriAssociati(null);
        comuni.add(com1);

        ClsComune com2 = new ClsComune();
        com2.setId("2");
        com2.setNome("Spello");
        com2.setDescrizione("Spavento");
        com2.setSuperficie(123);
        Posizione p2 = new Posizione();
        p2.setX(14.4);
        p2.setY(18.9);
        com2.setPosizione(p2);
        comuni.add(com2);
        com2.setCuratoriAssociati(null);

        ClsComune com3 = new ClsComune();
        com3.setId("3");
        com3.setNome("Castelraimondo");
        com3.setDescrizione("Capitale");
        com3.setSuperficie(213);
        Posizione p3 = new Posizione();
        p3.setX(32);
        p3.setY(31);
        com3.setPosizione(p3);
        com3.setCuratoriAssociati(null);
        comuni.add(com3);



        return args -> repo.saveAll(comuni);
    }
}
