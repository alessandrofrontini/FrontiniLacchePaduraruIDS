package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoRDCImmagini extends JpaRepository<ClsRDCImmagine, Long> {
    //Ritorna le RDC che fanno parte dei contest dell'animatore richiedente
@Query(value = "SELECT * FROM clsrdcimmagine WHERE id_contest_appartenenza_id IS NOT NULL AND id_contest_appartenenza_id IN (SELECT id FROM cls_contest_di_contribuzione WHERE id_creatore=?1)"
, nativeQuery = true)
    List<ClsRDCImmagine> findRDCImmaginiByUtente(Long idUtente);
    //Ritorna le RDC che non fanno parte di un contest e che sono del comune del curatore richiedente
@Query(value =
        "select * from CLSRDCIMMAGINE where id_contest_appartenenza_id IS NULL\n" +
                " AND (new_data_id in (select id from cls_immagine where id_nodo_ass_imm in (select id from cls_nodo where id_comune_associato in (select id_comune_associato from cls_turista_autenticato where id=?1))) \n" +
                "OR     old_data_id   in (select id from cls_immagine where id_nodo_ass_imm in (select id from cls_nodo where id_comune_associato in (select id_comune_associato from cls_turista_autenticato where id=?1))));",
nativeQuery = true)
    List<ClsRDCImmagine> findRDCImmaginiByUtenteCur(Long idUtente);
}
