package com.camerino.ids.fps.client;



import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneEliminazioneNodi implements Initializable
{
    @FXML
    Button sezioneEliminazioneNodiBTNConferma,sezioneEliminazioneNodiBTNhome;

    @FXML
    TableView sezioneEliminazioneNodiElenco;

    @FXML
    ComboBox sezioneEliminazioneNodiComboBox;

    @FXML
    private TableColumn<Object, String> id;
    @FXML
    private TableColumn<Object, String> nome;
    @FXML
    private TableColumn<Object, String> comuneAssociato;
    @FXML
    private TableColumn<Object, String> tipologia;
    @FXML
    private TableColumn<Object, String> temporizzato;
    @FXML
    private TableColumn<Object, String> posizione;

    public void eliminaNodo()
    {
        String azione = this.getValueFromCombobox(sezioneEliminazioneNodiComboBox);

        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle(azione);
        alert.setContentText(azione);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //metti tutto qua dentro, si precarica
    }

    //region Navigazione
    public void navigateToSezioneNavigazione(MouseEvent mouseEvent) {
        this.SwitchScene("SezioneVisualizzazione.fxml",mouseEvent);
    }

    //region Navigazione - Metodi Privati
    private void SwitchScene (String nomeScena, MouseEvent mouseEvent)
    {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(nomeScena)));
            //Utilities grafiche
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //endregion

    //endregion

    //region utilities
    private String getValueFromCombobox (ComboBox c)
    {
        return (String) c.getValue();
    }
    //endregion
}
