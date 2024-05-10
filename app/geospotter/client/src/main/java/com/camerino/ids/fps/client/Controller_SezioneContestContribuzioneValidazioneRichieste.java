package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_SezioneContestContribuzioneValidazioneRichieste implements Initializable
{
    //region Elementi FXML
    @FXML
    TableView<ClsRichiestaAzioneDiContribuzioneVisual> elencoRichieste;

    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneVisual,String> idColonna;

    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneVisual,String> idContestColonna;
    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneVisual,String> azioneColonna;
    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneVisual,String> tipoContenutoColonna;
    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneVisual,String> idRichiedenteColonna;

    @FXML
    ComboBox sceltaAzione;
    //endregion

    Utils u = new Utils();
    ArrayList<ClsRichiestaAzioneDiContribuzione> richieste;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.richieste = new ArrayList<ClsRichiestaAzioneDiContribuzione>();

        //region Creazione richieste dummy
        ClsRichiestaAzioneDiContribuzione c1 = new ClsRichiestaAzioneDiContribuzione();
        c1.setId("1");
        c1.setIdContest("1");
        c1.seteAzioneDiContribuzione(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        c1.setDatiNodo(null);
        c1.setUsernameCreatoreRichiesta("Username 1");
        ClsImmagine i1 = new ClsImmagine();
        i1.setId("1");
        c1.setDatiImmagine(i1);
        richieste.add(c1);

        ClsRichiestaAzioneDiContribuzione c2 = new ClsRichiestaAzioneDiContribuzione();
        c2.setId("2");
        c2.setIdContest("2");
        c2.seteAzioneDiContribuzione(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        c2.setDatiNodo(null);
        c2.setUsernameCreatoreRichiesta("Username 2");
        ClsImmagine i2 = new ClsImmagine();
        i2.setId("2");
        c2.setDatiImmagine(i2);
        richieste.add(c2);

        ClsRichiestaAzioneDiContribuzione c3 = new ClsRichiestaAzioneDiContribuzione();
        c3.setId("3");
        c3.setIdContest("3");
        c3.seteAzioneDiContribuzione(EAzioniDiContribuzione.INSERISCI_NODO);
        c3.setDatiImmagine(null);
        c3.setUsernameCreatoreRichiesta("Username 2");
        ClsNodo n1 = new ClsNodo();
        n1.setId("1");
        c3.setDatiNodo(n1);

        //endregion

        setRichieste(richieste);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<richieste.size();i++)
        {
            items.add(richieste.get(i).getId());
        }

        this.sceltaAzione.setItems(items);
        //endregion

        //region setting up colonne tabella itinerari
        idColonna.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        idRichiedenteColonna.setCellValueFactory(
                new PropertyValueFactory<>("usernameCreatore"));

        idContestColonna.setCellValueFactory(
                new PropertyValueFactory<>("idContest"));

        azioneColonna.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        tipoContenutoColonna.setCellValueFactory(
                new PropertyValueFactory<>("nodo"));

        //endregion

    }

    private void setRichieste (ArrayList<ClsRichiestaAzioneDiContribuzione> richieste)
    {
        for(int i = 0; i< richieste.size(); i++)
        {
            ClsRichiestaAzioneDiContribuzioneVisual c = u.convertFromRichiestaAzioneContribuzione(richieste.get(i));

            elencoRichieste.getItems().add(c);
        }
    }
}
