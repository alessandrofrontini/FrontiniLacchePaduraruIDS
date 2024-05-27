package com.camerino.ids.fps.server.api.v1.filters;

import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.fps.server.api.v1.persistence.crud.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

/**
 * Questo fitlro controllerà se il JWT è presente e valido.
 * Se non c'è un JWT il tipo di utente sarà Turista,
 * se c'è ma non è valido, sarà rediretto alla login.
 */
@Component
@Order(1)
public class FiltLogin extends OncePerRequestFilter {

    IperRecensioni iperRecensioni;
    IperNodi iperNodi;
    IperComuni iperComuni;
    IperItinerari iperItinerari;
    IperRDCI iperRDCI;
    IperSegnalazioni iperSegnalazioni;
    IperUtenti iperUtenti;
    IperImmagini iperImmagini;
    IperRDCImmagini iperRDCImmagini;


    @Autowired
    public FiltLogin(
            IperNodi iperNodi,
            IperComuni iperComuni,
            IperItinerari iperItinerari,
            IperRDCI iperRDCI,
            IperRecensioni iperRecensioni,
            IperSegnalazioni iperSegnalazioni,
            IperUtenti iperUtenti,
    IperImmagini iperImmagini,
            IperRDCImmagini iperRDCImmagini) {

        this.iperNodi = iperNodi;
        this.iperComuni = iperComuni;
        this.iperItinerari = iperItinerari;
        this.iperRecensioni = iperRecensioni;
        this.iperSegnalazioni = iperSegnalazioni;
        this.iperUtenti = iperUtenti;
        this.iperImmagini = iperImmagini;
        this.iperRDCImmagini = iperRDCImmagini;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.getServletContext().setAttribute("user", AuthClient(request.getHeader("Authorization")));
        filterChain.doFilter(request, response);
    }

    private ClsTurista AuthClient(String authorization) {
        if(authorization == null) {
            return CreaTurista();
        }
        return switch (authorization) {
            case "turista_aut" -> CreaTuristaAut();
            case "contr"-> CreaContributor();
            case "contr_aut" -> CreaContributorAut();
            case "curatore" -> CreaCuratore();
            case "animatore" -> CreaAnimatore();
            case "gdp" -> CreaGDP();
            default -> throw new RuntimeException("No user");
        };
    }

//TODO: possibile nuovo pattern
    private ClsTurista CreaTurista() {
        ClsTurista user = new ClsTurista();
        user.setpNodi(this.iperNodi);
        user.setMockComuni(this.iperComuni);
        user.setpItinerari(this.iperItinerari);
        user.setIperRecensioni(this.iperRecensioni);
        user.setIperSegnalazioni(this.iperSegnalazioni);
        user.setpImmagini(this.iperImmagini);

        return user;
    }

    private ClsTuristaAutenticato CreaTuristaAut() {
        ClsTuristaAutenticato user = new ClsTuristaAutenticato(CreaTurista());
        user.setIperUtenti(this.iperUtenti);
        user.setIperRDCImmagini(this.iperRDCImmagini);
        return user;
    }

    private ClsContributor CreaContributor() {
        ClsContributor user = new ClsContributor(CreaTuristaAut());
        //user.setpRDC(this.iperRDC);
        user.setpRDCI(this.iperRDCI);
        return user;
    }

    private ClsContributorAutorizzato CreaContributorAut() {
        ClsContributorAutorizzato user = new ClsContributorAutorizzato(CreaContributor());
        return user;
    }

    private ClsAnimatore CreaAnimatore() {
        ClsAnimatore user = new ClsAnimatore(CreaContributorAut());
        return user;
    }

    private ClsCuratore CreaCuratore() {
        ClsCuratore user = new ClsCuratore(CreaAnimatore());
        return user;
    }

    private ClsGestoreDellaPiattaforma CreaGDP() {
        ClsGestoreDellaPiattaforma user = new ClsGestoreDellaPiattaforma(CreaCuratore());
        return user;
    }
}
    /*@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest.getServletContext().getAttribute("user") == null) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            servletRequest.getServletContext().setAttribute("user", AuthClient(request.getHeader("Authorization")));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }*/