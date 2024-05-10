package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsRecensione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRecensioni extends JpaRepository<ClsRecensione, String> {
}
