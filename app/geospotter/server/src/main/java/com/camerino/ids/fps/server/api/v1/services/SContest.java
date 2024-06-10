package com.camerino.ids.fps.server.api.v1.services;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.utenti.ClsAnimatore;
import com.camerino.ids.core.data.utenti.ClsContributor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SContest {
    HttpServletRequest request;

    @Autowired
    public SContest(HttpServletRequest request) {
        this.request = request;
    }

    public List<ClsContestDiContribuzione> getAllContest() {
        return ((ClsContributor)request.getServletContext().getAttribute("user")).getAllContest();
    }

    public Boolean postContest(ClsContestDiContribuzione contest) {
        return ((ClsAnimatore)request.getServletContext().getAttribute("user")).inserisciContest(contest);
    }
}
