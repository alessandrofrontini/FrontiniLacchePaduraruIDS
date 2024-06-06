package com.camerino.ids.core.data.utenti;

@Deprecated
public interface IUserAdministrator {
    boolean uprankUtente(String id, int punteggio);
    boolean downrankUtente(String id, int punteggio);
    boolean resetRankUtente(String id);
    ClsTuristaAutenticato[] visualizzaUtenti();
}
