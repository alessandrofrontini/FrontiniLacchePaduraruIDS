package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato.eRUOLI_UTENTE;
import com.camerino.ids.core.data.utils.Credenziali;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoUtenti extends JpaRepository<ClsTuristaAutenticato, Long> {
    @Query("select usr from ClsTuristaAutenticato usr where usr.credenziali=?1")
    Optional<ClsTuristaAutenticato> login(Credenziali credenziali);

    @Query("select usr from ClsTuristaAutenticato  usr where usr.ruoloUtente=?1")
    List<ClsTuristaAutenticato> findByRuolo(eRUOLI_UTENTE ruolo);

    @Transactional
    @Modifying
    @Query("update ClsTuristaAutenticato usr set usr.punteggio=usr.punteggio+?1 where usr.id=?1")
    void incrementaPunteggio(long idUtente, int punteggio);

    @Query(value = "SELECT * FROM cls_turista_autenticato WHERE id_comune_associato IS NULL AND dtype='ClsCuratore'"
    ,nativeQuery = true)
    List<ClsTuristaAutenticato> getFreeCuratori();
}
