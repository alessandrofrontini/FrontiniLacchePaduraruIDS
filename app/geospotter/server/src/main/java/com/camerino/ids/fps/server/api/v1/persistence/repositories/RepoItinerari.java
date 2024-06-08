package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoItinerari extends JpaRepository<ClsItinerario, Long> {
}
