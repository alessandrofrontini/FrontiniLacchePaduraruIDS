package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoNodi extends JpaRepository<ClsNodo, Long> {
    @Query(value = "select n from ClsNodo n WHERE n.idComuneAssociato=?1")
    List<ClsNodo> findNodiByComune(Long idComune);

    @Query("SELECT n from ClsNodo n where n.idCreatore=?1")
    List<ClsNodo> findNodoByUtente(Long owner);

    //Trova tutti i nodi che non fanno parte di una RDC
    @Query(value = """
select * from CLS_NODO where id not in (select NEW_DATA_ID from CLSRDcnodo where NEW_DATA_ID  is not null)
""", nativeQuery = true)
    List<ClsNodo> findAllOfficial();
    @Query(value = "SELECT COUNT(*) FROM (SELECT * FROM cls_nodo WHERE id NOT IN (SELECT new_data_id FROM clsrdcnodo) AND posizione=?1);",
    nativeQuery = true)
    int countNodiOnSamePosition(String convertedPosition);
}
