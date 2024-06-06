package com.camerino.ids.core.data.azioni;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.UuidGenerator;

@Deprecated
@Entity
public class ClsRichiestaAzioneDiContribuzioneItinerario {
    @Id
    @GeneratedValue
    Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClsItinerario getDatiItinerario() {
        return datiItinerario;
    }

    public void setDatiItinerario(ClsItinerario datiItinerario) {
        this.datiItinerario = datiItinerario;
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
