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

public class Controller_SezioneLogin
{
    @FXML
    Button sezioneRegistrazioneBTNRegistrazione,sezioneRegistrazioneBTNVaiAllaHome,sezioneRegistrazioneBTNVaiAlLogin;

    @FXML
    TextField sezioneRegistrazioneTextBoxUsername, sezioneRegistrazioneTextBoxPassword;

    public void logga ()
    {
        String username = getNameFromTextField(sezioneRegistrazioneTextBoxUsername);
        String password = getNameFromTextField(sezioneRegistrazioneTextBoxPassword);

        //Controllo server
        if(password.equals(password))
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("ToString");
            alert.setHeaderText(username + ":" + password);
            alert.show();
        }

        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Reinserisci password");
            alert.show();
        }

    }

    //region Navigazione

    public void navigateToSezioneVisualizzazione(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazione.fxml", mouseEvent);
    }

    public void navigateToSezioneRegistrazione(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneRegistrazione.fxml", mouseEvent);
    }

    //region Navigazione - Metodi privati
    /**
     * Metodo di comodo che utile alla navigazione tra le scene
     * @param nomeScena scena che si vuole cambiare
     */
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
    //endregion

    //endregion

    //region Utilities
    /**
     * Metodo di comodo che elimina il contenuto di una textArea
     * @param t textArea
     */
    private void clearTextFromTextField(TextField t)
    {
        t.clear();
    }

    /**
     * Metodo di comodo che estrae il contenuto da una textField
     * @param tf textField
     * @return Contenuto
     */
    private String getNameFromTextField (TextField tf)
    {
        String name = tf.getText();
        tf.clear();
        return name;
    }
    //endregion





}
