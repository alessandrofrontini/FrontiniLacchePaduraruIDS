package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsGestoreDellaPiattaforma;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.data.utils.Posizione;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneEliminazioneComuni implements Initializable

{
    //region Elementi FXML
    @FXML
    TableView<ClsComuneVisual> elencoComuni;

    @FXML
    TableColumn<ClsComuneVisual,String> id;

    @FXML
    TableColumn<ClsComuneVisual,String> nome;

    @FXML
    TableColumn<ClsComuneVisual,String> posizione;

    @FXML
    TableColumn<ClsComuneVisual,String> abitanti;

    @FXML
    TableColumn<ClsComuneVisual,String> superficie;

    @FXML
    TableColumn<ClsComuneVisual,String> curatori;

    @FXML
    ComboBox sceltaComune;
    //endregion

    Utils u = new Utils();
    ArrayList<ClsComune> comuni;
    ArrayList<ClsCuratore> Curatori = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.comuni = Controller_SezioneLogin.UTENTE.getAllComuni();

        setComuni(comuni);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<Curatori.size();i++)
        {
            items.add(Curatori.get(i).getId());
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

    public void eliminaComune(MouseEvent mouseEvent)
    {
        String IDDaEliminare = u.getValueFromCombobox(this.sceltaComune);

        if(IDDaEliminare != null && this.controllaConformitaID(IDDaEliminare))
        {
            ((ClsGestoreDellaPiattaforma)Controller_SezioneLogin.UTENTE).eliminaComune(IDDaEliminare);
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("Corretto");
            alert.setContentText(IDDaEliminare);
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Controlla le informazioni");
            alert.show();
        }
    }

    private void setComuni (ArrayList<ClsComune> comuni)
    {
        for(int i = 0; i<comuni.size();i++)
        {
            ClsComuneVisual c = u.convertFromClsComune(comuni.get(i));

            elencoComuni.getItems().add(c);
        }
    }

    private boolean controllaConformitaID (String id)
    {/*
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