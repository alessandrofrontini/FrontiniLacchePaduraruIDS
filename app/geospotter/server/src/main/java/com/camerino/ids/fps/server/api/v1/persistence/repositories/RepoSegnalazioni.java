package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoSegnalazioni extends JpaRepository<ClsSegnalazione, String> {
    @Query("select r from ClsSegnalazione r where r.idContenuto=?1")
    int filterByContenuto(String idContenuto);
}
