package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.azioni.ClsRdcItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsRDCVisual;
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
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneCuratoreValidazioneRichieste implements Initializable {

    //region Elementi FXML

    //region Tabella Immagini
    @FXML
    TableView<ClsRDCVisual> elencoRichiesteContribuzioneImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> idColonnaImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> azioneDiContribuzioneImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> statoImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> richiedenteImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> responsabileImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> idContestImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> oldDataImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> newDataImmagine;

    @FXML
    ComboBox sceltaAzioneImmagine, selezionaElementoDettaglioImmagine;
    //endregion

    //region Tabella Nodi
    @FXML
    TableView<ClsRDCVisual> elencoRichiesteContribuzioneNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> idColonnaNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> azioneDiContribuzioneNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> statoNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> richiedenteNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> responsabileNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> idContestNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> oldDataNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> newDataNodo;
    @FXML
    ComboBox sceltaAzioneNodo, selezionaElementoDettaglioNodo;
    //endregion

    //region Tabella Itinerari
    @FXML
    TableView<ClsRDCVisual> elencoRichiesteContribuzioneItinerario;
    @FXML
    TableColumn<ClsRDCVisual, String> idColonnaItinerari;
    @FXML
    TableColumn<ClsRDCVisual, String> azioneDiContribuzioneItinerari;
    @FXML
    TableColumn<ClsRDCVisual, String> statoItinerari;
    @FXML
    TableColumn<ClsRDCVisual, String> richiedenteItinerari;
    @FXML
    TableColumn<ClsRDCVisual, String> responsabileItinerari;
    @FXML
    TableColumn<ClsRDCVisual, String> idContestItinerari;
    @FXML
    TableColumn<ClsRDCVisual, String> oldDataItinerari;
    @FXML
    TableColumn<ClsRDCVisual, String> newDataItinerari;

    @FXML
    ComboBox sceltaAzioneItinerario, selezionaElementoDettaglioItinerario;
    //endregion

    //endregion

    List<ClsRDCImmagine> richiesteImmagini;
    List<ClsRDCNodo> richiesteNodo;
    List<ClsRdcItinerario> richiesteItinerario;

    Utils u = new Utils();
    //region dummy arrays per creare immagini nodi e itinerari
    List<ClsNodo> nodi = new ArrayList<>();

    //endregion
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.richiesteImmagini = ((ClsCuratore)Controller_SezioneLogin.UTENTE).getRdcImmaginiPosessoreCur();
        this.richiesteNodo = ((ClsCuratore)Controller_SezioneLogin.UTENTE).getRDCNodiPosessoreCur();
        this.richiesteItinerario = ((ClsCuratore)Controller_SezioneLogin.UTENTE).getRDCItinerarioPosessoreCur();

        setRichiesteImmagini(richiesteImmagini);

        setRichiesteNodi(richiesteNodo);

        setRichiesteItinerari(richiesteItinerario);

        //region Combobox Scelta Immagini
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < richiesteImmagini.size(); i++) {
            items.add(richiesteImmagini.get(i).getIdRichiesta().toString());
        }

        this.sceltaAzioneImmagine.setItems(items);
        //endregion

        //region Combobox Scelta Immagini dettaglio
        ObservableList<String> itme = FXCollections.observableArrayList();

        for (int i = 0; i < richiesteImmagini.size(); i++) {
            itme.add(richiesteImmagini.get(i).getIdRichiesta().toString());
        }

        this.selezionaElementoDettaglioImmagine.setItems(itme);
        //endregion

        //region Combobox Scelta Nodi
        ObservableList<String> it = FXCollections.observableArrayList();

        for (int i = 0; i < richiesteNodo.size(); i++) {
            it.add(richiesteNodo.get(i).getIdRichiesta().toString());
        }

        this.sceltaAzioneNodo.setItems(it);
        //endregion

        //region Combobox Scelta Nodi dettaglio
        ObservableList<String> ite = FXCollections.observableArrayList();

        for (int i = 0; i < richiesteNodo.size(); i++) {
            ite.add(richiesteNodo.get(i).getIdRichiesta().toString());
        }

        this.selezionaElementoDettaglioNodo.setItems(ite);
        //endregion

        //region Combobox Scelta itinerari
        ObservableList<String> iti = FXCollections.observableArrayList();

        for (int i = 0; i < richiesteItinerario.size(); i++) {
            iti.add(richiesteItinerario.get(i).getIdRichiesta().toString());
        }

        this.sceltaAzioneItinerario.setItems(iti);
        //endregion

        //region Combobox Scelta Immagini dettaglio
        ObservableList<String> itin = FXCollections.observableArrayList();

        for (int i = 0; i < richiesteItinerario.size(); i++) {
            itin.add(richiesteItinerario.get(i).getIdRichiesta().toString());
        }

        this.selezionaElementoDettaglioItinerario.setItems(itin);
        //endregion

        //region Tabella Immagini
        idColonnaImmagine.setCellValueFactory(
                new PropertyValueFactory<>("idRichiesta"));

        azioneDiContribuzioneImmagine.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        statoImmagine.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        richiedenteImmagine.setCellValueFactory(
                new PropertyValueFactory<>("richiedente"));

        responsabileImmagine.setCellValueFactory(
                new PropertyValueFactory<>("responsabile"));

        idContestImmagine.setCellValueFactory(
                new PropertyValueFactory<>("idContest"));

        oldDataImmagine.setCellValueFactory(
                new PropertyValueFactory<>("oldData"));

        newDataImmagine.setCellValueFactory(
                new PropertyValueFactory<>("newData"));
        //endregion

        //region Tabella Nodi
        idColonnaNodo.setCellValueFactory(
                new PropertyValueFactory<>("idRichiesta"));

        azioneDiContribuzioneNodo.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        statoNodo.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        richiedenteNodo.setCellValueFactory(
                new PropertyValueFactory<>("richiedente"));

        responsabileNodo.setCellValueFactory(
                new PropertyValueFactory<>("responsabile"));

        idContestNodo.setCellValueFactory(
                new PropertyValueFactory<>("idContest"));

        oldDataNodo.setCellValueFactory(
                new PropertyValueFactory<>("oldData"));

        newDataNodo.setCellValueFactory(
                new PropertyValueFactory<>("newData"));
        //endregion

        //region Tabella Itinerari
        idColonnaItinerari.setCellValueFactory(
                new PropertyValueFactory<>("idRichiesta"));

        azioneDiContribuzioneItinerari.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        statoItinerari.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        richiedenteItinerari.setCellValueFactory(
                new PropertyValueFactory<>("richiedente"));

        responsabileItinerari.setCellValueFactory(
                new PropertyValueFactory<>("responsabile"));

        idContestItinerari.setCellValueFactory(
                new PropertyValueFactory<>("idContest"));

        oldDataItinerari.setCellValueFactory(
                new PropertyValueFactory<>("oldData"));

        newDataItinerari.setCellValueFactory(
                new PropertyValueFactory<>("newData"));
        //endregion
    }

    public void accettaAzioneImmagine(MouseEvent mouseEvent) {
        if(u.getValueFromCombobox(sceltaAzioneImmagine) != "" && u.getValueFromCombobox(sceltaAzioneImmagine) != null)
        {
            Long IDValidazione = Long.valueOf(u.getValueFromCombobox(sceltaAzioneImmagine));
            if (IDValidazione != null && !Objects.equals(IDValidazione, "")) {
                ((ClsCuratore)Controller_SezioneLogin.UTENTE).accettaRichiestaImmagine(IDValidazione);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("OK");
                alert.setContentText("AZIONE (" + IDValidazione + ") Validata");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRORE");
                alert.setContentText("ERRORE");
                alert.show();
            }
        }
        else{
            u.alertError();
        }

    }

    public void rifiutaAzioneImmagine(MouseEvent mouseEvent) {
        if(u.getValueFromCombobox(sceltaAzioneImmagine) != "" && u.getValueFromCombobox(sceltaAzioneImmagine) != null)
        {
            Long IDValidazione = Long.valueOf(u.getValueFromCombobox(sceltaAzioneImmagine));
            if (IDValidazione != null && !Objects.equals(IDValidazione, "")) {
                ((ClsCuratore)Controller_SezioneLogin.UTENTE).rifiutaRichiestaImmagine(IDValidazione);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("OK");
                alert.setContentText("AZIONE (" + IDValidazione + ") NON Validata");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRORE");
                alert.setContentText("ERRORE");
                alert.show();
            }
        }
        else{
            u.alertError();
        }
    }

    public void accettaAzioneNodo(MouseEvent mouseEvent) {
        if(u.getValueFromCombobox(sceltaAzioneNodo) != "" && u.getValueFromCombobox(sceltaAzioneNodo) != null){
            Long IDValidazione = Long.valueOf(u.getValueFromCombobox(sceltaAzioneNodo));
            if (IDValidazione != null && !Objects.equals(IDValidazione, "")) {
                ((ClsCuratore)Controller_SezioneLogin.UTENTE).accettaRichiestaNodo(IDValidazione);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("OK");
                alert.setContentText("AZIONE (" + IDValidazione + ") Validata");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRORE");
                alert.setContentText("ERRORE");
                alert.show();
            }
        }
        else{
            u.alertError();
        }

    }

    public void rifiutaAzioneNodo(MouseEvent mouseEvent) {
        if(u.getValueFromCombobox(sceltaAzioneNodo) != "" && u.getValueFromCombobox(sceltaAzioneNodo) != null)
        {
            Long IDValidazione = Long.valueOf(u.getValueFromCombobox(sceltaAzioneNodo));
            if (IDValidazione != null && !Objects.equals(IDValidazione, "")) {
                ((ClsCuratore)Controller_SezioneLogin.UTENTE).rifiutaRichiestaNodo(IDValidazione);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("OK");
                alert.setContentText("AZIONE (" + IDValidazione + ") NON Validata");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRORE");
                alert.setContentText("ERRORE");
                alert.show();
            }
        }
        else{
            u.alertError();
        }

    }

    public void accettaAzioneItinerario(MouseEvent mouseEvent) {
        if(u.getValueFromCombobox(sceltaAzioneItinerario) != "" && u.getValueFromCombobox(sceltaAzioneItinerario) != null)
        {
            Long IDValidazione = Long.valueOf(u.getValueFromCombobox(sceltaAzioneItinerario));
            if (IDValidazione != null && !Objects.equals(IDValidazione, "")) {
                ((ClsCuratore)Controller_SezioneLogin.UTENTE).accettaRichiestaItinerario(IDValidazione);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("OK");
                alert.setContentText("AZIONE (" + IDValidazione + ") Validata");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRORE");
                alert.setContentText("ERRORE");
                alert.show();
            }
        }
        else{
            u.alertError();
        }

    }

    public void rifiutaAzioneItinerario(MouseEvent mouseEvent) {
        if(u.getValueFromCombobox(sceltaAzioneItinerario) != "" && u.getValueFromCombobox(sceltaAzioneItinerario) != null)
        {
            Long IDValidazione = Long.valueOf(u.getValueFromCombobox(sceltaAzioneItinerario));
            if (IDValidazione != null && !Objects.equals(IDValidazione, "")) {
                ((ClsCuratore)Controller_SezioneLogin.UTENTE).rifiutaRichiestaItinerario(IDValidazione);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("OK");
                alert.setContentText("AZIONE (" + IDValidazione + ") NON Validata");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRORE");
                alert.setContentText("ERRORE");
                alert.show();
            }
        }
        else {
            u.alertError();
        }

    }

    private void setRichiesteImmagini(List<ClsRDCImmagine> richiesteImmagini) {
        for (int i = 0; i < richiesteImmagini.size(); i++) {
            ClsRDCVisual c = u.convertFromRDCImmagine(richiesteImmagini.get(i));
            elencoRichiesteContribuzioneImmagine.getItems().add(c);
        }
    }

    private void setRichiesteNodi(List<ClsRDCNodo> richiesteNodo) {
        for (int i = 0; i < richiesteNodo.size(); i++) {
            ClsRDCVisual c = u.convertFromRDCNodo(richiesteNodo.get(i));
            elencoRichiesteContribuzioneNodo.getItems().add(c);
        }
    }

    private void setRichiesteItinerari(List<ClsRdcItinerario> richiesteItinerario) {
        for (int i = 0; i < richiesteItinerario.size(); i++) {
            ClsRDCVisual c = u.convertFromRDCItinerario(richiesteItinerario.get(i));
            elencoRichiesteContribuzioneItinerario.getItems().add(c);
        }
    }

    public void visualizzaDettaglioImmagine()
    {
        if(u.getValueFromCombobox(selezionaElementoDettaglioImmagine) != "" && u.getValueFromCombobox(selezionaElementoDettaglioImmagine) != null)
        {
            Long IDDaVisualizzare = Long.valueOf(u.getValueFromCombobox(selezionaElementoDettaglioImmagine));

            if (true) {

                for (int i = 0; i < richiesteImmagini.size(); i++) {
                    if (IDDaVisualizzare.equals(this.richiesteImmagini.get(i).getIdRichiesta())) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("OK");
                        alert.setContentText("ID: " + richiesteImmagini.get(i).getIdRichiesta() + "\nCreatore:" + richiesteImmagini.get(i).getCreatore().getId());
                        alert.show();
                    }
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRORE");
                alert.setContentText("Controlla le informazioni e riprova");
                alert.show();
            }
        }
        else {
            u.alertError();
        }

    }

    public void visualizzaDettaglioNodo()
    {
        if(u.getValueFromCombobox(selezionaElementoDettaglioNodo) != "" && u.getValueFromCombobox(selezionaElementoDettaglioNodo) != null)
        {
            Long IDDaVisualizzare = Long.valueOf(u.getValueFromCombobox(selezionaElementoDettaglioNodo));

            if (true) {

                for (int i = 0; i < richiesteNodo.size(); i++) {
                    if (IDDaVisualizzare.equals(this.richiesteNodo.get(i).getIdRichiesta())) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("OK");
                        alert.setContentText("ID: " +richiesteNodo.get(i).getIdRichiesta() + "\nCreatore: " + richiesteNodo.get(i).getCreatore().getId() );
                        alert.show();
                    }
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRORE");
                alert.setContentText("Controlla le informazioni e riprova");
                alert.show();
            }
        }
        else {
            u.alertError();
        }

    }

    public void visualizzaDettaglioItinerario()
    {

        String IDDaVisualizzare = u.getValueFromCombobox(selezionaElementoDettaglioItinerario).toString();

        if(!Objects.equals(IDDaVisualizzare, ""))
        {
                for (int i = 0; i < richiesteItinerario.size(); i++) {
                    if (IDDaVisualizzare.equals(this.richiesteItinerario.get(i).getIdRichiesta().toString())) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("OK");
                        alert.setContentText("ID:" + richiesteItinerario.get(i).getIdRichiesta() +"\nCreatore: " + richiesteItinerario.get(i).getCreatore().getId());
                        alert.show();
                    }
            }
        }
        else{
            u.alertError();
        }

    }

    private boolean controllaConformitaID(Long id) {
        /*boolean flag = false;

        for(int i = 0; i<richieste.size();i++)
        {
            if(richieste.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
    }

    private void SwitchScene(String nomeScena, MouseEvent mouseEvent) {
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

    public void navigateToSezioneVisualizzazione(MouseEvent mouseEvent) {
        this.SwitchScene("SezioneVisualizzazione.fxml", mouseEvent);
    }


}
