package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepoUtenti extends JpaRepository<ClsTuristaAutenticato, String>
{
//    @Query(value = "Select c from ClsTuristaAutenticato c WHERE c.ruoloString == ")
//    ArrayList<ClsTuristaAutenticato>getUtentiByRuolo(String ruolo);
}
