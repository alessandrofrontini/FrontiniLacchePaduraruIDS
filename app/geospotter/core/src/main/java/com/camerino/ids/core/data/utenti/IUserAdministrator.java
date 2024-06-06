package com.camerino.ids.core.data.utenti;

@Deprecated
public interface IUserAdministrator {
    boolean uprankUtente(Long id, int punteggio);

    boolean downrankUtente(Long id, int punteggio);

    boolean resetRankUtente(Long id);

    ClsTuristaAutenticato[] visualizzaUtenti();
}
