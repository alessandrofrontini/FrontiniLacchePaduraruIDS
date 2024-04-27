package com.camerino.ids.fps.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller_SezioneModificaNodi
{



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
