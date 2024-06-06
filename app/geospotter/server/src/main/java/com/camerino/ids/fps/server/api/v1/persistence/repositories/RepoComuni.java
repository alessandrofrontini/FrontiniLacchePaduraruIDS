package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsComune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RepoComuni extends JpaRepository<ClsComune, String> {
    @Modifying
    @Query("update ClsComune c set c = ?1 WHERE c.id = ?2")
    void updateComuneById(ClsComune object, Long idComune);
}
