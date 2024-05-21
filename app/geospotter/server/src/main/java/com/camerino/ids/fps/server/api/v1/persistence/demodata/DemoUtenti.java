package com.camerino.ids.fps.server.api.v1.persistence.demodata;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoComuni;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoUtenti;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;

@Configuration
@Order(3)
public class DemoUtenti
{
    @Bean
    public CommandLineRunner aggiungiUtenti(RepoUtenti repo) {
        ArrayList<ClsTuristaAutenticato> utenti = new ArrayList<>();

        //region turisti autenticati
        for (int i = 1; i <= 10; i++) {
            ClsTuristaAutenticato t = new ClsTuristaAutenticato();

            Credenziali c = new Credenziali();
            c.setUsername("Username" + i); // Username univoco per ogni oggetto
            c.setPassword("Password" + i); // Password univoca per ogni oggetto

            t.setCredenziali(c);

            t.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);
            t.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO.getValue() - 5 - i);

            utenti.add(t);
        }
        //endregion

        //region contributor
        for (int i = 1; i <= 10; i++) {
            ClsContributor t = new ClsContributor();

            Credenziali c = new Credenziali();
            c.setUsername("Username" + i*2); // Username univoco per ogni oggetto
            c.setPassword("Password" + i*2); // Password univoca per ogni oggetto

            t.setCredenziali(c);

            t.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);
            t.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR.getValue() - 5 - i);

            utenti.add(t);
        }
        //endregion

        //region contributor auth
        for (int i = 1; i <= 10; i++) {
            ClsContributorAutorizzato t = new ClsContributorAutorizzato();

            Credenziali c = new Credenziali();
            c.setUsername("Username" + i*3); // Username univoco per ogni oggetto
            c.setPassword("Password" + i*3); // Password univoca per ogni oggetto

            t.setCredenziali(c);

            t.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
            t.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue() - 5 - i*2);

            utenti.add(t);
        }
        //endregion

        //region animatore
        for (int i = 1; i <= 10; i++) {
            ClsAnimatore t = new ClsAnimatore();

            Credenziali c = new Credenziali();
            c.setUsername("Username" + i*4); // Username univoco per ogni oggetto
            c.setPassword("Password" + i*4); // Password univoca per ogni oggetto

            t.setCredenziali(c);

            t.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE);
            t.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE.getValue() - 5 - i*4);

            utenti.add(t);
        }
        //endregion

        //region curatore
        for (int i = 1; i <= 10; i++) {
            ClsCuratore t = new ClsCuratore();

            Credenziali c = new Credenziali();
            c.setUsername("Username" + i*4); // Username univoco per ogni oggetto
            c.setPassword("Password" + i*4); // Password univoca per ogni oggetto

            t.setCredenziali(c);

            t.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE);
            t.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE.getValue());

            utenti.add(t);
        }
        //endregion

        //region gestore
        for (int i = 1; i <= 10; i++) {
            ClsGestoreDellaPiattaforma t = new ClsGestoreDellaPiattaforma();

            Credenziali c = new Credenziali();
            c.setUsername("Username" + i*4); // Username univoco per ogni oggetto
            c.setPassword("Password" + i*4); // Password univoca per ogni oggetto

            t.setCredenziali(c);

            t.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA);
            t.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA.getValue());

            utenti.add(t);
        }
        //endregion


        return args -> repo.saveAll(utenti);
    }

}
