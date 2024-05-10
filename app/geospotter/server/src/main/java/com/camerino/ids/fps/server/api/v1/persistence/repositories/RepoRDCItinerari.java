package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRDCItinerari extends JpaRepository<ClsRichiestaAzioneDiContribuzioneItinerario, String> {
}
