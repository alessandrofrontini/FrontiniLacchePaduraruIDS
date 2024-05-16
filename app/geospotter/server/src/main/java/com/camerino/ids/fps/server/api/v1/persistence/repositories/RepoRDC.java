package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoRDC extends JpaRepository<ClsRichiestaAzioneDiContribuzione, String> {

    @Modifying
    @Query("update ClsRichiestaAzioneDiContribuzione r set r = ?1 where r.id= ?2")
    void updateRDCById(ClsRichiestaAzioneDiContribuzione object, String idRDC);

    @Query("select r from ClsRichiestaAzioneDiContribuzione r where r.usernameCreatoreRichiesta = ?1")
    List<ClsRichiestaAzioneDiContribuzione> getRDCByUser(String idUser);
}
