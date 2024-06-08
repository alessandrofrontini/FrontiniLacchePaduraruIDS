package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoNodi extends JpaRepository<ClsNodo, Long> {
    @Query(value = "select n from ClsNodo n WHERE n.idComuneAssociato=?1")
    List<ClsNodo> findNodiByComune(Long idComune);

    @Query("SELECT n from ClsNodo n where n.idCreatore=?1")
    List<ClsNodo> findNodoByUtente(Long owner);
}
