package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.ClsGDP;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsComuneVisual;
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
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneEliminazioneComuni implements Initializable {
    //region Elementi FXML
    @FXML
    TableView<ClsComuneVisual> elencoComuni;

    @FXML
    TableColumn<ClsComuneVisual, String> id;

    @FXML
    TableColumn<ClsComuneVisual, String> nome;

    @FXML
    TableColumn<ClsComuneVisual, String> posizione;

    @FXML
    TableColumn<ClsComuneVisual, String> abitanti;

    @FXML
    TableColumn<ClsComuneVisual, String> superficie;

    @FXML
    TableColumn<ClsComuneVisual, String> curatori;

    @FXML
    ComboBox sceltaComune;
    //endregion

    Utils u = new Utils();
    List<ClsComune> comuni;

    //    List<ClsCuratore> Curatori = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.comuni = Controller_SezioneLogin.UTENTE.getAllComuni();

        setComuni(comuni);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < comuni.size(); i++) {
            items.add(comuni.get(i).getId().toString());
        }

        this.sceltaComune.setItems(items);
        //endregion

        //region setting up colonne tabella
        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        nome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        posizione.setCellValueFactory(
                new PropertyValueFactory<>("posizione"));

        abitanti.setCellValueFactory(
                new PropertyValueFactory<>("abitanti"));

        superficie.setCellValueFactory(
                new PropertyValueFactory<>("superficie"));

        curatori.setCellValueFactory(
                new PropertyValueFactory<>("curatori"));
        //endregion

    }

    public void eliminaComune(MouseEvent mouseEvent) {
        Long IDDaEliminare = Long.valueOf(u.getValueFromCombobox(this.sceltaComune));

        if (IDDaEliminare != null && this.controllaConformitaID(IDDaEliminare)) {
            ((ClsGDP) Controller_SezioneLogin.UTENTE).eliminaComune(IDDaEliminare);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Corretto");
            alert.setContentText(IDDaEliminare.toString());
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Controlla le informazioni");
            alert.show();
        }
    }

    private void setComuni(List<ClsComune> comuni) {
        for (int i = 0; i < comuni.size(); i++) {
            ClsComuneVisual c = u.convertFromClsComune(comuni.get(i));

            elencoComuni.getItems().add(c);
        }
    }

    private boolean controllaConformitaID(Long id) {/*
        boolean flag = false;

        for(int i = 0; i<comuni.size();i++)
        {
            if(comuni.get(i).getId() == id)
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
