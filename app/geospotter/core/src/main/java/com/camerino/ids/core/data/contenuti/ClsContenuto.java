package com.camerino.ids.core.data.contenuti;

import com.camerino.ids.core.data.utils.Posizione;

/**
 * Contiene i dati di un Contenuto generico nella piattaforma
 */
public class ClsContenuto extends ClsInformazione
{
    Posizione posizione;
    String nome;
    String descrizione;

    //region Getter e setter
    //region Getter e setter (ClsInformazione)
        public String getId()
        {
            return super.getId() ;
        }
        public void setId(String id) {
            super.setId(id);
        }
        public String getUsernameCreatore() {return super.getUsernameCreatore();}
        public void setUsernameCreatore(String usernameCreatore)
        {
            super.setUsernameCreatore(usernameCreatore);
        }
        //endregion
    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    //endregion
}
