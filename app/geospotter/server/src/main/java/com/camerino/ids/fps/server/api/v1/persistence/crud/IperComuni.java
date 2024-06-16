package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoComuni;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoUtenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class IperComuni implements IPersistenceModel<ClsComune> {

    RepoComuni repoComuni;
    RepoUtenti repoUtenti;

    @Autowired
    public IperComuni(final RepoComuni repoComuni,
                      RepoUtenti repoUtenti) {
        this.repoComuni = repoComuni;
        this.repoUtenti = repoUtenti;
    }

    @Override
    public List<ClsComune> get(Map<String, Object> filters) {
        if (filters == null)
            return new ArrayList<>(repoComuni.findAll());

        if (filters.containsKey("idComune")) {
            List<Long> ids = new ArrayList<>();
            ids.add((Long) filters.get("idComune"));
            return new ArrayList<>(repoComuni.findAllById(ids));
        }

        return new ArrayList<>(repoComuni.findAll());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsComune object) {
        List<ClsCuratore> curatoriVecchi = repoComuni.findById(object.getId()).get().getCuratoriAssociati();
        List<ClsCuratore> curatoriAggiornati = object.getCuratoriAssociati().stream()
                .map(cur->(ClsCuratore)repoUtenti.findById(cur.getId()).get()).toList();

        for (ClsCuratore curatore : curatoriAggiornati)
            curatore.setIdComuneAssociato(object.getId());

        for(ClsCuratore curatore : curatoriVecchi){
            boolean found = false;
            for (ClsCuratore aggiornati : curatoriAggiornati){
                if(Objects.equals(aggiornati.getId(), curatore.getId()))
                    found=true;
            }
            if(!found) {
                curatore.setIdComuneAssociato(null);
                repoUtenti.save(curatore);
            }
        }
        repoUtenti.saveAll(curatoriAggiornati);
        repoComuni.save(object);
        return true;
    }

    @Override
    public boolean insert(ClsComune object) {
        object = repoComuni.save(object);
        List<ClsCuratore> curatori = new ArrayList<>();
        for (ClsCuratore curatore : object.getCuratoriAssociati()) {
            ClsCuratore tmp = (ClsCuratore) repoUtenti.findById(curatore.getId()).get();
            tmp.setIdComuneAssociato(object.getId());
            curatori.add(tmp);
        }
        object.setCuratoriAssociati(curatori);
        repoUtenti.saveAll(curatori);
        return true;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        if (filters == null)
            return false;
        if (!filters.containsKey("idComune"))
            return false;
        ClsComune comune = repoComuni.findById((Long) filters.get("idComune")).get();
        for (ClsCuratore curatore : comune.getCuratoriAssociati())
            curatore.setIdComuneAssociato(null);
        repoUtenti.saveAll(comune.getCuratoriAssociati());
        repoComuni.deleteById((Long) filters.get("idComune"));
        return true;
    }
}
