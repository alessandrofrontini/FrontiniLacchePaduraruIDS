package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoItinerari;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(3)
public class DemoItinerari
{
    @Bean
    public CommandLineRunner aggiungiItinerari(RepoItinerari repo) {
        List<ClsNodo> nodi = new ArrayList<>();

        ClsNodo nodo1 = new ClsNodo();
        nodo1.setNome("Centro Camerino");
        nodo1.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
        nodo1.setIdComuneAssociato(1L);
        nodo1.setaTempo(true);
        nodo1.setDescrizione("Ciao");
        nodo1.setPosizione(new Posizione(12, 23));
        nodi.add(nodo1);

        ClsNodo nodo2 = new ClsNodo();
        nodo2.setNome("Porchettaro");
        nodo2.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
        nodo2.setIdComuneAssociato(1L);
        nodo2.setaTempo(true);
        nodo2.setDescrizione("Ciao");
        nodo2.setPosizione(new Posizione(12, 23));
        nodi.add(nodo2);

        List<ClsItinerario> itinerari = new ArrayList<>();

        ClsItinerario itinerario = new ClsItinerario();
        itinerario.setId(1L);
        itinerario.setOrdinato(true);
        itinerario.setIdCreatore(1L);
        itinerario.setNome("Il Cammino");
        itinerario.setTappe(nodi);

        ClsNodo nodo3 = new ClsNodo();
        nodo3.setNome("Museo Civico");
        nodo3.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
        nodo3.setIdComuneAssociato(1L);
        nodo3.setaTempo(true);
        nodo3.setDescrizione("Museo con reperti storici");
        nodo3.setPosizione(new Posizione(15, 30));
        nodi.add(nodo3);

        ClsItinerario itinerario2 = new ClsItinerario();
        itinerario2.setId(2L);
        itinerario2.setOrdinato(false);
        itinerario2.setIdCreatore(1L);
        itinerario2.setNome("Il Passeggio");
        itinerario2.setTappe(nodi);

        ClsNodo nodo4 = new ClsNodo();
        nodo4.setNome("Teatro Romano");
        nodo4.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
        nodo4.setIdComuneAssociato(1L);
        nodo4.setaTempo(true);
        nodo4.setDescrizione("Antico teatro romano");
        nodo4.setPosizione(new Posizione(14, 28));
        nodi.add(nodo4);

        ClsItinerario itinerario3 = new ClsItinerario();
        itinerario3.setId(3L);
        itinerario3.setOrdinato(false);
        itinerario3.setIdCreatore(1L);
        itinerario3.setNome("Il Passetto");
        itinerario3.setTappe(nodi);

        return args -> repo.saveAll(itinerari);
    }

}
