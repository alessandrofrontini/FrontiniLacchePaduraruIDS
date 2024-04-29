package com.camerino.ids.fps.server.api.v1.filters;

import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
import com.camerino.ids.fps.server.api.v1.persistence.crud.IperNodi;
import jakarta.servlet.*;
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

    @Autowired
    public FiltLogin(IperNodi iperNodi) {
        this.iperNodi = iperNodi;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest.getServletContext().getAttribute("user") == null) {
            ClsContributorAutorizzato cont = new ClsContributorAutorizzato();
            cont.setpNodi(iperNodi);
            servletRequest.getServletContext().setAttribute("user", cont);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
