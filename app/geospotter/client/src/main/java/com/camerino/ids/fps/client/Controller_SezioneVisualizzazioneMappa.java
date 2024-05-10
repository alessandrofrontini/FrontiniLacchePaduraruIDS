package com.camerino.ids.fps.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller_SezioneVisualizzazioneMappa
{


    public void navigateToSezioneVisualizzazioneItinerari (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazioneItinerari.fxml",mouseEvent);
    }


    public void navigateToSezioneVisualizzazioneNodi (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazioneNodi.fxml",mouseEvent);
    }

    public void navigateToSezioneVisualizzazioneComuni (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazioneComuni.fxml",mouseEvent);
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
