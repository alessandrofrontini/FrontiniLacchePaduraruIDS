package com.camerino.ids.fps.client;

<<<<<<< HEAD
public class Controller_SezioneEliminazioneNodi {
=======

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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

    public void eliminaNodo()
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ArrayList<String> test = new ArrayList<String>();

        test.add("NODO 1");
        test.add("NODO 2");

        for(int i = 0; i<test.size();i++)
        {

        }

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
>>>>>>> parent of dec0096 (.)
}
