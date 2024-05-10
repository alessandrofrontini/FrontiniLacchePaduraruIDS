package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsComune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoComuni extends JpaRepository<ClsComune, String> {
}
