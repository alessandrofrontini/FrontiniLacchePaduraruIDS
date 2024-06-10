package com.camerino.ids.fps.server.api.v1.persistence.repositories;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoContest extends JpaRepository<ClsContestDiContribuzione, Long> {
}
