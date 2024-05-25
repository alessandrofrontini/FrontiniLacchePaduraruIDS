package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoUtenti;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

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
    public ArrayList<ClsTuristaAutenticato> get(HashMap<String, Object> filters)
    {
        ArrayList<ClsTuristaAutenticato> utenti = new ArrayList<>(repoUtenti.findAll());
        return utenti;
//        if(filters == null)
//            return new ArrayList<ClsTuristaAutenticato>(repoUtenti.findAll());
//        if(filters.containsKey("ruolo"))
//            return new ArrayList<>(repoUtenti.fi)

    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsTuristaAutenticato object) {
        return false;
    }

    @Override
    public boolean insert(ClsTuristaAutenticato object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
}
