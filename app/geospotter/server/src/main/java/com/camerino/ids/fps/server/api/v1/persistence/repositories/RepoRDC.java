package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRDC extends JpaRepository<ClsRichiestaAzioneDiContribuzione, String> {
}
