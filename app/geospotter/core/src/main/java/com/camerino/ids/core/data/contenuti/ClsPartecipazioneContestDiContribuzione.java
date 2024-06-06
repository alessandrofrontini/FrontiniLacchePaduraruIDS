package com.camerino.ids.core.data.contenuti;

/**
 * TODO: commentare
 */
@Deprecated()
public class ClsPartecipazioneContestDiContribuzione {
    Long id;
    String usernamePartecipante;
    Long idContest;
    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernamePartecipante() {
        return usernamePartecipante;
    }

    public void setUsernamePartecipante(String usernamePartecipante) {
        this.usernamePartecipante = usernamePartecipante;
    }

    public Long getIdContest() {
        return idContest;
    }

    public void setIdContest(Long idContest) {
        this.idContest = idContest;
    }

    //endregion

    public String visualizzaPartecipazione() {

        String dummy = "";

        dummy += "\n\nID Partecipazione: " + this.getId() + "\n";
        dummy += "ID Contest: " + this.getIdContest() + "\n";
        dummy += "Usermame partecipante: " + this.usernamePartecipante + "\n";

        return dummy;

    }
}
