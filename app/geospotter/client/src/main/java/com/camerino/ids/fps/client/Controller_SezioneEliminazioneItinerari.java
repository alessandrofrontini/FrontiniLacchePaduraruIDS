package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsItinerarioVisual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneEliminazioneItinerari implements Initializable {
    //region Elementi FXML
    @FXML
    TableView<ClsItinerarioVisual> sezioneEliminazioneItinerariTableView;

    @FXML
    TableColumn<ClsItinerarioVisual, String> sezioneEliminazioneItinerariTableColumnID = new TableColumn<>("ID");
    @FXML
    TableColumn<ClsItinerarioVisual, String> sezioneEliminazioneItinerariTableColumnNome = new TableColumn<>("Nome");
    @FXML
    TableColumn<ClsItinerarioVisual, String> sezioneInserimentoItinerariTableColumnOrdinato = new TableColumn<>("E' ordinato?");
    @FXML
    TableColumn<ClsItinerarioVisual, String> sezioneInserimentoItinerariTableColumnTappe = new TableColumn<>("Tappe");

    @FXML
    ComboBox sezioneEliminazioneItinerariComboBoxSceltaItinerarioID;

    @FXML
    Button sezioneEliminazioneItinerariButtonConferma;
    //endregion

    List<ClsItinerario> itinerari;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itinerari = Controller_SezioneLogin.UTENTE.getAllItinerari();

        this.setItinerari(itinerari);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < itinerari.size(); i++) {
            items.add(itinerari.get(i).getId().toString());
        }

        this.sezioneEliminazioneItinerariComboBoxSceltaItinerarioID.setItems(items);
        //endregion

        //region setting up colonne tabella
        sezioneEliminazioneItinerariTableColumnID.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        sezioneEliminazioneItinerariTableColumnNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        sezioneInserimentoItinerariTableColumnOrdinato.setCellValueFactory(
                new PropertyValueFactory<>("ordinato"));

        sezioneInserimentoItinerariTableColumnTappe.setCellValueFactory(
                new PropertyValueFactory<>("tappe"));
        //endregion

    }

    public void eliminaNodo(MouseEvent mouseEvent) {
        Long IDDaEliminare = Long.valueOf(u.getValueFromCombobox(sezioneEliminazioneItinerariComboBoxSceltaItinerarioID));

        if (IDDaEliminare != null && controllaConformitaID(IDDaEliminare)) {
            ((ClsContributor) Controller_SezioneLogin.UTENTE).eliminaItinerario(IDDaEliminare);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(IDDaEliminare.toString());
            alert.setContentText("--" + IDDaEliminare + "--");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SELEZIONA UN NODO");
            alert.setContentText("--SELEZIONA UN NODO--");
            alert.show();
        }
    }

    private void setItinerari(List<ClsItinerario> itinerari) {
        for (int i = 0; i < itinerari.size(); i++) {
            ClsItinerarioVisual c = u.convertFromClsItinerario(itinerari.get(i));
            sezioneEliminazioneItinerariTableView.getItems().add(c);
        }
    }

    private boolean controllaConformitaID(Long id) {/*
        boolean flag = false;

        for(int i = 0; i<itinerari.size();i++)
        {
            if(Objects.equals(itinerari.get(i).getId(), id))
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
