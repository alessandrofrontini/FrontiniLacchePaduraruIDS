package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoRDCNodi extends JpaRepository<ClsRDCNodo, Long> {
    //Ritorna le RDC che fanno parte dei contest dell'animatore richiedente
    @Query(value = "SELECT * FROM clsrdcnodo WHERE id_contest_appartenenza_id IS NOT NULL AND id_contest_appartenenza_id IN (SELECT id FROM cls_contest_di_contribuzione WHERE id_creatore=?1)"
            , nativeQuery = true)
    List<ClsRDCNodo> findRDCNodiByUtente(Long idUtente);
    //Ritorna le RDC che non fanno parte di un contest e che sono del comune del curatore richiedente
    @Query(value = "SELECT * FROM clsrdcnodo WHERE id_contest_appartenenza_id IS NULL AND (new_data_id in ( SELECT id FROM cls_nodo WHERE id_comune_associato in (SELECT id_comune_associato FROM cls_turista_autenticato WHERE id=?1) ) \n" +
            "OR old_data_id  in (SELECT id FROM cls_nodo WHERE id_comune_associato in (SELECT id_comune_associato FROM cls_turista_autenticato WHERE id=?1)));"
            , nativeQuery = true)
    List<ClsRDCNodo> findRDCNodiByUtenteCur(Long idUtente);
}
