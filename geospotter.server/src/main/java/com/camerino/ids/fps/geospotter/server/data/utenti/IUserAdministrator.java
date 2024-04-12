package com.camerino.ids.fps.geospotter.server.data.utenti;

public interface IUserAdministrator {
    boolean uprankUtente(String id, int punteggio);
    boolean downrankUtente(String id, int punteggio);
    boolean resetRankUtente(String id);
    ClsTuristaAutenticato[] visualizzaUtenti();
}
