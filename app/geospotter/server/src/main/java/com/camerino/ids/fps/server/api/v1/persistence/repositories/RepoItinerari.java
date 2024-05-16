package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RepoItinerari extends JpaRepository<ClsItinerario, String> {
    @Modifying
    @Query("update ClsItinerario i set i = ?1 WHERE i.id = ?2")
    void updateItinerarioById(ClsItinerario object, String idItinerario);
}
