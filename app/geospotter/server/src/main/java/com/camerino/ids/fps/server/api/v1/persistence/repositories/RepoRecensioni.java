package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoRecensioni extends JpaRepository<ClsRecensione, String> {
    @Query(value = "Select r from ClsRecensione r WHERE r.idNodoAssociato= ?1")
    List<ClsRecensione> findRecensioniByNodo(Long idNodo);

    @Query("SELECT r from ClsRecensione r where r.idCreatore=?1")
    List<ClsRecensione> findRecensioniByUtente(Long idUtente);
}
