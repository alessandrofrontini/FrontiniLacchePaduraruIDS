package com.camerino.ids.core.data.azioni;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class ClsRichiestaAzioneDiContribuzioneItinerario {
    @Id
    @UuidGenerator
    String id;
    @ManyToOne
    ClsItinerario datiItinerario;
    String usernameCreatore;

    public EAzioniDiContribuzione geteAzioniDiContribuzione() {
        return eAzioniDiContribuzione;
    }

    public void seteAzioniDiContribuzione(EAzioniDiContribuzione eAzioniDiContribuzione) {
        this.eAzioniDiContribuzione = eAzioniDiContribuzione;
    }

    EAzioniDiContribuzione eAzioniDiContribuzione;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClsItinerario getDatiItinerarioVecchio() {
        return datiItinerarioVecchio;
    }

    public void setDatiItinerarioVecchio(ClsItinerario datiItinerarioVecchio) {
        this.datiItinerarioVecchio = datiItinerarioVecchio;
    }
    public ClsItinerario getDatiItinerarioNuovo() {
        return datiItinerarioNuovo;
    }

    public void setDatiItinerarioNuovo(ClsItinerario datiItinerarioNuovo) {
        this.datiItinerarioNuovo = datiItinerarioNuovo;
    }

    public String getUsernameCreatore() {
        return usernameCreatore;
    }

    public void setUsernameCreatore(String usernameCreatore) {
        this.usernameCreatore = usernameCreatore;
    }

    public String visualizzaRichiestaItinerario()
    {
        String dummy = "";

        dummy += "\n\nID: " + this.getId() + "\n";
        dummy += "Username Creatore: " + this.getUsernameCreatore() + "\n";
        dummy += "Azione: " + this.geteAzioniDiContribuzione() + "\n";

        return dummy;
    }
}
