package com.camerino.ids.fps.client.controllers.contest;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.fps.client.Utils;
import com.camerino.ids.fps.client.visual.ClsContestDiContribuzioneVisual;
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
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneContestContribuzionePartecipazione implements Initializable
{

    //region Elementi FXML
    @FXML
    TableView<ClsContestDiContribuzioneVisual> elencoContest;

    @FXML
    TableColumn<ClsContestDiContribuzioneVisual,String> idColonna = new TableColumn<>("ID");
    @FXML
    TableColumn <ClsContestDiContribuzioneVisual,String> creatoreColonna = new TableColumn<>("CREATORE");
    @FXML
    TableColumn <ClsContestDiContribuzioneVisual,String> durataColonna = new TableColumn<>("DURATA");
    @FXML
    TableColumn <ClsContestDiContribuzioneVisual,String> locationColonna = new TableColumn<>("LOCATION");
    @FXML
    TableColumn <ClsContestDiContribuzioneVisual,String> apertoColonna = new TableColumn<>("APERTO");

    @FXML
    ComboBox sceltaContestContribuzione;
    //endregion

    ArrayList<ClsContestDiContribuzione> contests;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        contests = new ArrayList<ClsContestDiContribuzione>();

        //region Creazione Contest dummy
        ClsContestDiContribuzione c1 = new ClsContestDiContribuzione();
        c1.setId("1");
        c1.setDurata(new Date("01/01/2024"));
        c1.setUsernameCreatore("test1");
        ClsComune com1 = new ClsComune();
        com1.setNome("Comune1");
        c1.setLocation(com1);
        c1.setAperto(true);
        contests.add(c1);

        ClsContestDiContribuzione c2 = new ClsContestDiContribuzione();
        c2.setId("2");
        c2.setDurata(new Date("02/02/2024"));
        c2.setUsernameCreatore("test2");
        ClsComune com2 = new ClsComune();
        com2.setNome("Comune2");
        c2.setLocation(com2);
        c2.setAperto(false);
        contests.add(c2);

        ClsContestDiContribuzione c3 = new ClsContestDiContribuzione();
        c3.setId("3");
        c3.setDurata(new Date("03/03/2024"));
        c3.setUsernameCreatore("test3");
        ClsComune com3 = new ClsComune();
        com3.setNome("Comune3");
        c3.setLocation(com3);
        c3.setAperto(true);
        contests.add(c3);
        //endregion

        setContest(contests);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<contests.size();i++)
        {
            items.add(contests.get(i).getId());
        }

        this.sceltaContestContribuzione.setItems(items);
        //endregion

        //region setting up colonne tabella
        idColonna.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        creatoreColonna.setCellValueFactory(
                new PropertyValueFactory<>("usernameCreatore"));

        durataColonna.setCellValueFactory(
                new PropertyValueFactory<>("durata"));

        locationColonna.setCellValueFactory(
                new PropertyValueFactory<>("locationComune"));

        apertoColonna.setCellValueFactory(
                new PropertyValueFactory<>("isAperto"));
        //endregion
    }

    public void partecipaContest(MouseEvent mouseEvent)
    {
        String IDDaEliminare = u.getValueFromCombobox(sceltaContestContribuzione);

        if(IDDaEliminare != null && controllaConformitaID(IDDaEliminare))
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle(IDDaEliminare);
            alert.setContentText("--"+IDDaEliminare+"--");
            alert.show();
        }
        else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("SELEZIONA UN CONTEST");
            alert.setContentText("--SELEZIONA UN CONTEST--");
            alert.show();
        }
    }

    private void setContest (ArrayList<ClsContestDiContribuzione> contests)
    {
        for(int i = 0; i<contests.size();i++)
        {
            ClsContestDiContribuzioneVisual c = u.convertFromaClsContestDiContribuzione(contests.get(i));

            elencoContest.getItems().add(c);
        }
    }

    private boolean controllaConformitaID (String id)
    {
        boolean flag = false;

        for(int i = 0; i<contests.size();i++)
        {
            if(contests.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;
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

    public void navigateToSezioneVisualizzazione(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazione.fxml",mouseEvent);
    }


}
