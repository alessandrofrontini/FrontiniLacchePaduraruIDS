package com.camerino.ids.core.data.contenuti;

/**
 * TODO: commentare
 */
public class ClsPartecipazioneContestDiContribuzione {
    String id;
    Long usernamePartecipante;
    String idContest;
    //region Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUsernamePartecipante() {
        return usernamePartecipante;
    }

    public void setUsernamePartecipante(Long usernamePartecipante) {
        this.usernamePartecipante = usernamePartecipante;
    }

    public String getIdContest() {
        return idContest;
    }

    public void setIdContest(String idContest) {
        this.idContest = idContest;
    }

    //endregion

    public String visualizzaPartecipazione ()
    {

        String dummy = "";

        dummy += "\n\nID Partecipazione: " + this.getId() + "\n";
        dummy += "ID Contest: " + this.getIdContest() + "\n";
        dummy += "Usermame partecipante: " + this.usernamePartecipante + "\n";

        return dummy;

    }
}
