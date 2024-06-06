package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato.eRUOLO_UTENTE;
import com.camerino.ids.core.data.utils.Credenziali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepoUtenti extends JpaRepository<ClsTuristaAutenticato, String>
{
    @Query("select usr from ClsTuristaAutenticato usr where usr.credenziali=?1")
    Optional<ClsTuristaAutenticato> login(Credenziali credenziali);

    @Query("select usr from ClsTuristaAutenticato  usr where usr.ruoloUtente=?1")
    List<ClsTuristaAutenticato> findByRuolo(eRUOLO_UTENTE ruolo);
//    @Query(value = "Select c from ClsTuristaAutenticato c WHERE c.ruoloString == ")
//    List<ClsTuristaAutenticato>getUtentiByRuolo(String ruolo);
}
