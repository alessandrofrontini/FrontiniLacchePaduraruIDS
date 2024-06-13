package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoRDCItinerari extends JpaRepository<ClsRdcItinerario, Long> {
    @Query("select it from ClsRdcItinerario it")
    List<ClsRdcItinerario> findRDCItinerariByUtenteCur(Long idUtente);
}
