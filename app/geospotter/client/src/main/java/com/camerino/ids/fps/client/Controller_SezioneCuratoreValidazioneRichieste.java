package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneCuratoreValidazioneRichieste implements Initializable
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
    ComboBox sceltaAzione,selezionaElementoDettaglio;
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

        Posizione p1 = new Posizione();
        p1.setY(12);
        p1.setX(21);
        n1.setPosizione(p1);
        c3.setDatiNodo(n1);
        richieste.add(c3);

        ClsRichiestaAzioneDiContribuzione c4 = new ClsRichiestaAzioneDiContribuzione();
        c4.setId("4");
        c4.setIdContest("4");
        c4.seteAzioneDiContribuzione(EAzioniDiContribuzione.INSERISCI_NODO);
        c4.setDatiImmagine(null);
        c4.setUsernameCreatoreRichiesta("Username 4");
        ClsNodo n2 = new ClsNodo();
        Posizione p2 = new Posizione();
        p2.setY(22);
        p2.setX(22);
        n2.setPosizione(p2);
        n2.setId("4");
        c4.setDatiNodo(n2);
        richieste.add(c4);
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

        //region combobox
        ObservableList<String> itemss = FXCollections.observableArrayList();

        for(int i = 0;i<richieste.size();i++)
        {
            itemss.add(richieste.get(i).getId());
        }

        this.selezionaElementoDettaglio.setItems(itemss);
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
                new PropertyValueFactory<>("tipoContenuto"));

        //endregion

    }

    public void accettaAzione(MouseEvent mouseEvent)
    {
        String IDValidazione = u.getValueFromCombobox(sceltaAzione);
        if(IDValidazione != null && !Objects.equals(IDValidazione, ""))
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText("AZIONE (" + IDValidazione + ") Validata");
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    public void rifiutaAzione(MouseEvent mouseEvent)
    {
        String IDValidazione = u.getValueFromCombobox(sceltaAzione);
        if(IDValidazione != null && !Objects.equals(IDValidazione, ""))
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText("AZIONE (" + IDValidazione + ") NON Validata");
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    private void setRichieste (ArrayList<ClsRichiestaAzioneDiContribuzione> richieste)
    {
        for(int i = 0; i< richieste.size(); i++)
        {
            ClsRichiestaAzioneDiContribuzioneVisual c = u.convertFromRichiestaAzioneContribuzione(richieste.get(i));

            elencoRichieste.getItems().add(c);
        }
    }

    public void visualizzaDettaglio()
    {
        String IDDaVisualizzare = u.getValueFromCombobox(selezionaElementoDettaglio);

        if(IDDaVisualizzare != null && !IDDaVisualizzare.equals("") && this.controllaConformitaID(IDDaVisualizzare))
        {
            ClsRichiestaAzioneDiContribuzione richiesta = new ClsRichiestaAzioneDiContribuzione();
            for(int i = 0; i<richieste.size();i++)
            {
                if(IDDaVisualizzare.equals(this.richieste.get(i).getId()))
                {
                    richiesta.setId(richieste.get(i).getId());
                    richiesta.setIdContest(richieste.get(i).getIdContest());
                    richiesta.seteAzioneDiContribuzione(richieste.get(i).geteAzioneDiContribuzione());
                    richiesta.setUsernameCreatoreRichiesta(richieste.get(i).getUsernameCreatoreRichiesta());

                    if(richieste.get(i).getDatiNodo() != null)
                    {
                        richiesta.setDatiNodo(richieste.get(i).getDatiNodo());

                        Alert alert = new Alert (Alert.AlertType.INFORMATION);
                        alert.setTitle("OK");
                        alert.setContentText("Richiesta di azione: \n" + richiesta.visualizzaRichiesta() + "\nNodo:" + richiesta.getDatiNodo().visualizzaNodo());
                        alert.show();
                    }
                    if(richieste.get(i).getDatiImmagine() != null)
                    {
                        richiesta.setDatiImmagine(richieste.get(i).getDatiImmagine());

                        Alert alert = new Alert (Alert.AlertType.INFORMATION);
                        alert.setTitle("OK");
                        alert.setContentText(richiesta.visualizzaRichiesta() + "\nImmagine:" + richiesta.getDatiImmagine().visualizzaImmagine());
                        alert.show();
                    }

                }
            }

        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }

    }
    private boolean controllaConformitaID (String id)
    {
        boolean flag = false;

        for(int i = 0; i<richieste.size();i++)
        {
            if(richieste.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;
    }

    private void SwitchScene (String nomeScena, MouseEvent mouseEvent)
    {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(nomeScena)));

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToSezioneVisualizzazione (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazione.fxml",mouseEvent);
    }

}
