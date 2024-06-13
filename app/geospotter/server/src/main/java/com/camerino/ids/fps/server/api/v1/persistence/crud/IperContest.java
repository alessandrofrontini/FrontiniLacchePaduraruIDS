package com.camerino.ids.fps.server.api.v1.persistence.crud;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoComuni;
import com.camerino.ids.fps.server.api.v1.persistence.repositories.RepoContest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class IperContest implements IPersistenceModel<ClsContestDiContribuzione> {

    RepoContest repoContest;

    @Autowired
    public IperContest(final RepoContest repoContest) {
        this.repoContest = repoContest;
    }

    @Override
    public List<ClsContestDiContribuzione> get(Map<String, Object> filters) {
        return new ArrayList<>(repoContest.findAll());
    }

    @Override
    public boolean update(Map<String, Object> filters, ClsContestDiContribuzione object) {
        return false;
    }

    @Override
    public boolean insert(ClsContestDiContribuzione object) {
        repoContest.save(object);
        return true;
    }

    @Override
    public boolean delete(Map<String, Object> filters) {
        return false;
    }
}
