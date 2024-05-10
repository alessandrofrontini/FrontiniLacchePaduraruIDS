package com.camerino.ids.fps.server.api.v1.filters;

import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.fps.server.api.v1.persistence.crud.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Questo fitlro controllerà se il JWT è presente e valido.
 * Se non c'è un JWT il tipo di utente sarà Turista,
 * se c'è ma non è valido, sarà rediretto alla login.
 */
@Component
@Order(1)
public class FiltLogin implements Filter {
    IperNodi iperNodi;
    IperComuni iperComuni;
    IperItinerari iperItinerari;
    IperRDC iperRDC;
    IperRDCI iperRDCI;

    @Autowired
    public FiltLogin(
            IperNodi iperNodi,
            IperComuni iperComuni,
            IperItinerari iperItinerari,
            IperRDC iperRDC,
            IperRDCI iperRDCI) {
        this.iperNodi = iperNodi;
        this.iperComuni = iperComuni;
        this.iperItinerari = iperItinerari;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest.getServletContext().getAttribute("user") == null) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            servletRequest.getServletContext().setAttribute("user", AuthClient(request.getHeader("Authorization")));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private ClsTurista AuthClient(String authorization) {
        if(authorization == null) {
            return CreaTurista();
        }
        switch (authorization) {
            default:
                throw new RuntimeException("No user");
        }
    }

//TODO: possibile nuovo pattern
    private ClsTurista CreaTurista() {
        ClsTurista user = new ClsTurista();
        user.setpNodi(this.iperNodi);
        user.setMockComuni(this.iperComuni);
        user.setpItinerari(this.iperItinerari);
        return user;
    }

    private ClsTuristaAutenticato CreaTuristaAut() {
        ClsTuristaAutenticato user = new ClsTuristaAutenticato();
        user.setpNodi(this.iperNodi);
        user.setMockComuni(this.iperComuni);
        user.setpItinerari(this.iperItinerari);
        return user;
    }

    private ClsContributor CreaContributor() {
        ClsContributor user = new ClsContributor();
        user.setpNodi(this.iperNodi);
        user.setMockComuni(this.iperComuni);
        user.setpItinerari(this.iperItinerari);
        user.setpRDC(this.iperRDC);
        user.setpRDCI(this.iperRDCI);
        return user;
    }

    private ClsContributorAutorizzato CreaContributorAut() {
        ClsContributorAutorizzato user = new ClsContributorAutorizzato();
        user.setpNodi(this.iperNodi);
        user.setMockComuni(this.iperComuni);
        user.setpItinerari(this.iperItinerari);
        user.setpRDC(this.iperRDC);
        user.setpRDCI(this.iperRDCI);
        return user;
    }

    private ClsCuratore CreaCuratore() {
        ClsCuratore user = new ClsCuratore();
        user.setpNodi(this.iperNodi);
        user.setMockComuni(this.iperComuni);
        user.setpItinerari(this.iperItinerari);
        user.setpRDC(this.iperRDC);
        user.setpRDCI(this.iperRDCI);
        return user;
    }

    private ClsAnimatore CreaAnimatore() {
        ClsAnimatore user = new ClsAnimatore();
        user.setpNodi(this.iperNodi);
        user.setMockComuni(this.iperComuni);
        user.setpItinerari(this.iperItinerari);
        user.setpRDC(this.iperRDC);
        user.setpRDCI(this.iperRDCI);
        return user;
    }

    private ClsGestoreDellaPiattaforma CreaGDP() {
        ClsGestoreDellaPiattaforma user = new ClsGestoreDellaPiattaforma();
        user.setpNodi(this.iperNodi);
        user.setMockComuni(this.iperComuni);
        user.setpItinerari(this.iperItinerari);
        user.setpRDC(this.iperRDC);
        user.setpRDCI(this.iperRDCI);
        return user;
    }
}
