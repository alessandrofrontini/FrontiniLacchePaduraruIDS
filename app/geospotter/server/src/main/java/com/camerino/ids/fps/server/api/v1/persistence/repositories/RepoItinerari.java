package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoItinerari extends JpaRepository<ClsItinerario, Long> {
    //ToDO: fixare
    @Query(value = """
select * from CLS_ITINERARIO where id not in (select NEW_DATA_ID from CLS_RDC_itinerario where NEW_DATA_ID  is not null)
""", nativeQuery = true)
    List<ClsItinerario> findAllOfficial();
}
