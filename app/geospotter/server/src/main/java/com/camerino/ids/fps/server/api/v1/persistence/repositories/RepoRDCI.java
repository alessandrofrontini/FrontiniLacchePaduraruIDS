package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
@Deprecated
public interface RepoRDCI extends JpaRepository<ClsRichiestaAzioneDiContribuzioneItinerario, String> {

    @Query("select r from ClsRichiestaAzioneDiContribuzioneItinerario r where r.usernameCreatore = ?1")
    List<ClsRichiestaAzioneDiContribuzioneItinerario> getRDCIByUser(String idUser);

    @Modifying
    @Query("update ClsRichiestaAzioneDiContribuzioneItinerario r set r = ?1 where r.id= ?2")
    void updateRDCIById(ClsRichiestaAzioneDiContribuzioneItinerario object, String idRDCI);
}
