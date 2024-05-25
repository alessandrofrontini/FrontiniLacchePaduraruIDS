package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRDCNodi extends JpaRepository<ClsRDCNodo, String> {
}
