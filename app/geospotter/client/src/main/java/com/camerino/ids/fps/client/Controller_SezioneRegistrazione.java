package com.camerino.ids.fps.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller_SezioneRegistrazione {

    @FXML
    Button sezioneRegistrazioneBTNConferma, sezioneRegistrazioneBTNVaiAllaHome;

    @FXML
    TextField sezioneRegistrazioneTextBoxRipetiPassword, sezioneRegistrazioneTextBoxPassword, sezioneRegistrazioneTextBoxUsername;

    public void registra() {
        String username = getNameFromTextField(sezioneRegistrazioneTextBoxUsername);
        String password = getNameFromTextField(sezioneRegistrazioneTextBoxPassword);
        String ripetiPassword = getNameFromTextField(sezioneRegistrazioneTextBoxRipetiPassword);

        if (password.equals(ripetiPassword)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ToString");
            alert.setHeaderText(username + ":" + password);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Reinserisci password");
            alert.show();
        }
    }

    //region Navigazione
    public void navigateToSezioneNavigazione(MouseEvent mouseEvent) {
        this.SwitchScene("SezioneVisualizzazione.fxml", mouseEvent);
    }

    public void navigateToSezioneLogIn(MouseEvent mouseEvent) {
        this.SwitchScene("SezioneLogIn.fxml", mouseEvent);
    }

    //region Navigazione - Metodi Privati
    private void SwitchScene(String nomeScena, MouseEvent mouseEvent) {
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

    //region Utilities

    /**
     * Metodo di comodo che elimina il contenuto di una textArea
     *
     * @param t textArea
     */
    private void clearTextFromTextField(TextField t) {
        t.clear();
    }

    /**
     * Metodo di comodo che estrae il contenuto da una textField
     *
     * @param tf textField
     * @return Contenuto
     */
    private String getNameFromTextField(TextField tf) {
        String name = tf.getText();
        tf.clear();
        return name;
    }
    //endregion


}
