package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoComuni;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(1)
public class DemoComuni {
    @Bean
    public CommandLineRunner aggiungiComuni(RepoComuni repo) {
        List<ClsComune> comuni = new ArrayList<>();
//        List<ClsCuratore> Curatori = new ArrayList<>();
//
//        ClsCuratore c1 = new ClsCuratore();
//        c1.setId("1");
//        Credenziali cred1 = new Credenziali();
//        cred1.setUsername("test1");
//        cred1.setPassword("test1");
//
//        ClsCuratore c2 = new ClsCuratore();
//        c1.setId("2");
//        Credenziali cred2 = new Credenziali();
//        cred2.setUsername("test2");
//        cred2.setPassword("test2");
//
//        ClsCuratore c3 = new ClsCuratore();
//        c1.setId("3");
//        Credenziali cred3 = new Credenziali();
//        cred3.setUsername("test3");
//        cred3.setPassword("test3");
//
//        ClsCuratore c4 = new ClsCuratore();
//        c4.setId("4");
//        Credenziali cred4 = new Credenziali();
//        cred4.setUsername("test4");
//        cred4.setPassword("test4");

        ClsComune comune = new ClsComune();
        comune.setNome("Camerino");
        comune.setDescrizione("Comune di Camerino");
        comune.setPosizione(new Posizione(13, 24));
//        List<ClsCuratore> cur1 = new ArrayList<>();
//        cur1.add(c1);
//        comune.setCuratoriAssociati(new ArrayList<>(cur1));
        comuni.add(comune);

        ClsComune com1 = new ClsComune();
        com1.setId(1L);
        com1.setNome("Canepona");
        com1.setDescrizione("Una strada, un tabacchi e un autovelox");
        com1.setSuperficie(321);
        Posizione p1 = new Posizione();
        p1.setX(12.1);
        p1.setY(12.3);
        com1.setPosizione(p1);
//        List<ClsCuratore> cur2 = new ArrayList<>();
//        cur2.add(c2);
//        comune.setCuratoriAssociati(new ArrayList<>(cur2));
        comuni.add(com1);

        ClsComune com2 = new ClsComune();
        com2.setId(2L);
        com2.setNome("Spello");
        com2.setDescrizione("Spavento");
        com2.setSuperficie(123);
        Posizione p2 = new Posizione();
        p2.setX(14.4);
        p2.setY(18.9);
        com2.setPosizione(p2);
        comuni.add(com2);
//        List<ClsCuratore> cur3 = new ArrayList<>();
//        cur3.add(c3);
//        comune.setCuratoriAssociati(new ArrayList<>(cur3));

        ClsComune com3 = new ClsComune();
        com3.setId(3L);
        com3.setNome("Castelraimondo");
        com3.setDescrizione("Capitale");
        com3.setSuperficie(213);
        Posizione p3 = new Posizione();
        p3.setX(32);
        p3.setY(31);
        com3.setPosizione(p3);
//        List<ClsCuratore> cur4 = new ArrayList<>();
//        cur4.add(c4);
//        comune.setCuratoriAssociati(new ArrayList<>(cur4));
        comuni.add(com3);


        return args -> repo.saveAll(comuni);
    }
}
