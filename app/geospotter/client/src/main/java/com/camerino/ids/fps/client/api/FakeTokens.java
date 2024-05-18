package com.camerino.ids.fps.client.api;

import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato.eRUOLO_UTENTE;

public class FakeTokens {
        public static final String TK_TURISTA = null;
        public static final String TK_TURISTA_AUTENTICATO = "turista_aut";
        public static final String TK_CONTRIBUTOR = "contr";
        public static final String TK_CONTRIBUTOR_AUTOTRIZZATO = "contr_aut";
        public static final String TK_CURATORE = "curatore";

        public static String getToken(ClsTurista user){
            if(! (user instanceof ClsTuristaAutenticato aut))
                return TK_TURISTA;

            return switch (aut.getRuoloUtente()) {
                case TURISTA_AUTENTICATO -> TK_TURISTA_AUTENTICATO;
                case CONTRIBUTOR -> TK_CONTRIBUTOR;
                case CONTRIBUTOR_AUTORIZZATO -> TK_CONTRIBUTOR_AUTOTRIZZATO;
                case CURATORE -> TK_CURATORE;
                default -> null;
            };
        }

        private FakeTokens(){}
}
