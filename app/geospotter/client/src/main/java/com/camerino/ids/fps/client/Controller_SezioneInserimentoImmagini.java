package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsNodoVisual;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneInserimentoImmagini implements Initializable
{
    //region Elementi FXML
    @FXML
    TableView<ClsNodoVisual> ElencoNodi;

    @FXML
    TableColumn<ClsNodoVisual,String> idColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> idComuneColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> nomeColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> posizioneColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> tipologiaColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> aTempoColonna;

    @FXML
    ComboBox sceltaNodo;

    @FXML
    TextField urlImmagine;
    //endregion

    ArrayList<ClsNodo> nodi;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        nodi = Controller_SezioneLogin.UTENTE.getAllNodi();
        setNodi(nodi);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<nodi.size();i++)
        {
            items.add(nodi.get(i).getId());
        }

        this.sceltaNodo.setItems(items);
        //endregion

        //region setting up colonne tabella
        idColonna.setCellValueFactory(
                new PropertyValueFactory<>("ID"));

        idComuneColonna.setCellValueFactory(
                new PropertyValueFactory<>("IDComuneAssociato"));

        nomeColonna.setCellValueFactory(
                new PropertyValueFactory<>("Nome"));

        posizioneColonna.setCellValueFactory(
                new PropertyValueFactory<>("Posizione"));

        tipologiaColonna.setCellValueFactory(
                new PropertyValueFactory<>("Tipologia"));

        aTempoColonna.setCellValueFactory(
                new PropertyValueFactory<>("ATempo"));
        //endregion
    }

    public void inserisciImmagine(MouseEvent mouseEvent)
    {
        String IDNodoAssociatoImmagine = u.getValueFromCombobox(this.sceltaNodo);
        ClsImmagine i = new ClsImmagine();

        if((!Objects.equals(u.getValueFromCombobox(this.sceltaNodo), null)) && (!Objects.equals(u.getValueFromTextField(urlImmagine), "")) && this.controllaConformitaID(IDNodoAssociatoImmagine))
        {
            i.setIdCreatore(Controller_SezioneLogin.utente.getUsername());
            i.setIdNodo(u.getValueFromCombobox(this.sceltaNodo));
            i.setURL(u.getValueFromTextField(urlImmagine));
            ((ClsContributor)Controller_SezioneLogin.UTENTE).inserisciImmagine(i);
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("Corretto");
            alert.setContentText(i.visualizzaImmagine());
            alert.show();
        }
        else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("SELEZIONA UN NODO");
            alert.setContentText("--SELEZIONA UN NODO--");
            alert.show();
        }
    }

    private void setNodi (ArrayList<ClsNodo> nodi)
    {
        for(int i = 0; i<nodi.size();i++)
        {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            ElencoNodi.getItems().add(c);
        }
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

    private boolean controllaConformitaID (String id)
    {
        /*if(id==null)
            return false;
        else{
            boolean flag = false;

            for(int i = 0; i<nodi.size();i++)
            {
                if(Objects.equals(nodi.get(i).getId(), id))
                {
                    flag = true;
                }
            }
            return flag;
        }*/
        return true;

    }



}
