package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoNodi extends JpaRepository<ClsNodo, String> {
    @Query(value = "select n from ClsNodo n WHERE n.idComune=?1")
    List<ClsNodo> findNodiByComune(String idComune);
    @Modifying
    @Query("update ClsNodo n set n = ?1 where n.idComune= ?2")
    void updateNodoById(ClsNodo object, String idComune);
}
