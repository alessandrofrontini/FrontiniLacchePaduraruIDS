package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoUtenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SLogin {
    RepoUtenti repoUtenti;

    @Autowired
    public SLogin(RepoUtenti repoUtenti) {
        this.repoUtenti = repoUtenti;
    }

    public ClsTuristaAutenticato login(Credenziali credenziali) {
        Optional<ClsTuristaAutenticato> user = repoUtenti.login(credenziali);
        return user.orElse(null);
    }
}
