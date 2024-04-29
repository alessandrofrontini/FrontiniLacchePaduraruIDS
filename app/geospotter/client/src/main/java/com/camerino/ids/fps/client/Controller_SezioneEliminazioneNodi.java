package com.camerino.ids.fps.client;

<<<<<<< HEAD
<<<<<<< Updated upstream
<<<<<<< HEAD
public class Controller_SezioneEliminazioneNodi {
=======
=======
>>>>>>> parent of dec0096 (.)

import javafx.beans.property.StringProperty;
=======
>>>>>>> Stashed changes
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
<<<<<<< Updated upstream
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
=======
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
>>>>>>> Stashed changes
=======
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
>>>>>>> parent of dec0096 (.)
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneEliminazioneNodi implements Initializable
{
=======
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneEliminazioneNodi implements Initializable {
>>>>>>> Stashed changes
    @FXML
    Button sezioneEliminazioneNodiBTNConferma,sezioneEliminazioneNodiBTNhome;

    @FXML
    TableView sezioneEliminazioneNodiElenco;

<<<<<<< HEAD
<<<<<<< Updated upstream
    public void eliminaNodo()
    {

=======
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

=======
>>>>>>> parent of dec0096 (.)
    public void eliminaNodo()
    {

<<<<<<< HEAD
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle(azione);
        alert.setContentText(azione);
        alert.show();
>>>>>>> Stashed changes
=======
>>>>>>> parent of dec0096 (.)
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
>>>>>>> parent of dec0096 (.)
        ArrayList<String> test = new ArrayList<String>();

        test.add("NODO 1");
        test.add("NODO 2");

        for(int i = 0; i<test.size();i++)
        {

        }

<<<<<<< HEAD
=======
        //metti tutto qua dentro, si precarica
>>>>>>> Stashed changes
=======
>>>>>>> parent of dec0096 (.)
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
<<<<<<< HEAD
<<<<<<< Updated upstream
>>>>>>> parent of dec0096 (.)
=======

    //region utilities
    private String getValueFromCombobox (ComboBox c)
    {
        return (String) c.getValue();
    }
    //endregion
>>>>>>> Stashed changes
=======
>>>>>>> parent of dec0096 (.)
}
