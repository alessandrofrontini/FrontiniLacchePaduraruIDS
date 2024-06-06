package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoUtenti;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(1)
public class DemoUtenti
{
    @Bean
    public CommandLineRunner aggiungiUtenti(RepoUtenti repo)
    {
        //Stessi utenti lato client, copy-paste
        List<ClsTuristaAutenticato> utenti = new ArrayList<>();

        //region contributor
        ClsContributor con = new ClsContributor();

        Credenziali c1 = new Credenziali();
        c1.setUsername("c"); // Username univoco per ogni oggetto
        c1.setPassword("c"); // Password univoca per ogni oggetto

        con.setCredenziali(c1);

        con.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR);
        con.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR.getValue() - 5);
        utenti.add(con);
        //endregion

        //region contributor auth

        ClsContributorAutorizzato conAuth = new ClsContributorAutorizzato();

        Credenziali c2 = new Credenziali();
        c2.setUsername("ca"); // Username univoco per ogni oggetto
        c2.setPassword("ca"); // Password univoca per ogni oggetto

        conAuth.setCredenziali(c2);

        conAuth.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO);
        conAuth.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue() - 150);

        utenti.add(conAuth);

        //endregion

        //region animatore

        ClsAnimatore anim = new ClsAnimatore();

        Credenziali c3 = new Credenziali();
        c3.setUsername("a"); // Username univoco per ogni oggetto
        c3.setPassword("a"); // Password univoca per ogni oggetto

        anim.setCredenziali(c3);

        anim.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE);
        anim.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE.getValue() - 380);

        utenti.add(anim);

        //endregion

        //region curatore

        ClsCuratore cur = new ClsCuratore();

        Credenziali c4 = new Credenziali();
        c4.setUsername("cur"); // Username univoco per ogni oggetto
        c4.setPassword("cur"); // Password univoca per ogni oggetto

        cur.setCredenziali(c4);

        cur.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CURATORE);
        cur.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CURATORE.getValue());

        utenti.add(cur);

        //endregion

        //region gestore

        ClsGestoreDellaPiattaforma gdp = new ClsGestoreDellaPiattaforma();

        Credenziali c8 = new Credenziali();
        c8.setUsername("gdp"); // Username univoco per ogni oggetto
        c8.setPassword("gdp"); // Password univoca per ogni oggetto

        gdp.setCredenziali(c8);

        gdp.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.GESTORE_DELLA_PIATTAFORMA);
        gdp.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.GESTORE_DELLA_PIATTAFORMA.getValue());

        utenti.add(gdp);

        //endregion

        return args -> repo.saveAll(utenti);
    }

}
