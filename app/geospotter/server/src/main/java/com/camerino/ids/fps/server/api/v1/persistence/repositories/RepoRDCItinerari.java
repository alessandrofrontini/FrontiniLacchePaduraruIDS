package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoRDCItinerari extends JpaRepository<ClsRdcItinerario, Long> {
    @Query(value = "SELECT * FROM CLS_RDC_ITINERARIO WHERE id_contest_appartenenza_id IS NULL AND\n" +
            "(new_data_id in (SELECT DISTINCT CLS_ITINERARIO_ID FROM CLS_ITINERARIO_TAPPE \n" +
            "JOIN\n" +
            "CLS_NODO\n" +
            "on TAPPE_ID=id AND ID_COMUNE_ASSOCIATO = (SELECT id_comune_associato FROM cls_turista_autenticato WHERE id=?1))\n" +
            "OR\n" +
            "old_data_id in (SELECT DISTINCT CLS_ITINERARIO_ID FROM CLS_ITINERARIO_TAPPE \n" +
            "JOIN\n" +
            "CLS_NODO\n" +
            "on TAPPE_ID=id AND ID_COMUNE_ASSOCIATO = (SELECT id_comune_associato FROM cls_turista_autenticato WHERE id=?1))\n" +
            ");\n", nativeQuery = true)
    List<ClsRdcItinerario> findRDCItinerariByUtenteCur(Long idUtente);
}
