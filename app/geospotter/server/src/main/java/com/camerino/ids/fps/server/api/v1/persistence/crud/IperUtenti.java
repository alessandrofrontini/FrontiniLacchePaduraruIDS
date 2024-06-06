package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato.eRUOLI_UTENTE;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoUtenti;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IperUtenti implements IPersistenceModel<ClsTuristaAutenticato>
{
   RepoUtenti repoUtenti;

   @Autowired
   public IperUtenti(final RepoUtenti repoUtenti)
   {
    this.repoUtenti = repoUtenti;
   }


    @Override
    @Transient
    public List<ClsTuristaAutenticato> get(Map<String, Object> filters)
    {
        if(filters == null)
            return new ArrayList<>(repoUtenti.findAll());
        if(filters.containsKey("ruolo"))
            return new ArrayList<>(repoUtenti.findByRuolo((eRUOLI_UTENTE)filters.get("ruolo")));
        return new ArrayList<>(repoUtenti.findAll());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsTuristaAutenticato object) {
        return false;
    }

    @Override
    public boolean insert(ClsTuristaAutenticato object) {
        return false;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return false;
    }
}
