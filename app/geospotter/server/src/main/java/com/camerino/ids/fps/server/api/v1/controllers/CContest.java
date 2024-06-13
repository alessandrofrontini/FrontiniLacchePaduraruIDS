package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SComuni;
import com.camerino.ids.fps.server.api.v1.services.SContest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CContest {
    public final static String mapping = BaseUrl.baseUrl + "/contest";
    SContest sContest;

    @Autowired
    public CContest(SContest sContest) {
        this.sContest = sContest;
    }

    @GetMapping(mapping)
    public ResponseEntity<List<ClsContestDiContribuzione>> getContest() {
        return ResponseEntity.ok(sContest.getAllContest());
    }

    @PostMapping(mapping)
    public ResponseEntity<Boolean> postComune(
            @RequestBody ClsContestDiContribuzione contest
    ) {
        return ResponseEntity.ok(sContest.postContest(contest));
    }
}
