package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoImmagini extends JpaRepository<ClsImmagine, String> {
}
