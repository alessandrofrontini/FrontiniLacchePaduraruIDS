package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class Controller_SezioneCuratoreVisualizzazioneSegnalazioni implements Initializable
{
    //region Elementi FXML
    @FXML
    TableView<ClsSegnalazione> elencoSegnalazioni;

    @FXML
    TableColumn<ClsSegnalazione, String> idColonna;

    @FXML
    TableColumn<ClsSegnalazione, String> idElementoColonna;

    @FXML
    TableColumn<ClsSegnalazione, String> descrizioneColonna;

    //endregion

    Utils u = new Utils();
    ArrayList<ClsSegnalazione> segnalazioni;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        segnalazioni = new ArrayList<>();

        //region Creazione Segnalazioni dummy
        ClsSegnalazione s1 = new ClsSegnalazione();
        s1.setId("1");
        s1.setIdCuratore("1");
        s1.setIdContenuto("1");
        s1.setDescrizione("descrizione1");
        segnalazioni.add(s1);

        ClsSegnalazione s2 = new ClsSegnalazione();
        s2.setId("2");
        s2.setIdCuratore("1");
        s2.setIdContenuto("2");
        s2.setDescrizione("descrizione2");
        segnalazioni.add(s2);

        ClsSegnalazione s3 = new ClsSegnalazione();
        s3.setId("3");
        s3.setIdCuratore("1");
        s3.setIdContenuto("3");
        s3.setDescrizione("descrizione3");
        segnalazioni.add(s3);

        ClsSegnalazione s4 = new ClsSegnalazione();
        s4.setId("4");
        s4.setIdCuratore("4");
        s4.setIdContenuto("4");
        s4.setDescrizione("descrizione4");
        segnalazioni.add(s4);

        ClsSegnalazione s5 = new ClsSegnalazione();
        s5.setId("5");
        s5.setIdCuratore("5");
        s5.setIdContenuto("5");
        s5.setDescrizione("descrizione5");
        segnalazioni.add(s5);

        ClsSegnalazione s6 = new ClsSegnalazione();
        s6.setId("6");
        s6.setIdCuratore("6");
        s6.setIdContenuto("6");
        s6.setDescrizione("descrizione6");
        segnalazioni.add(s6);

        ClsSegnalazione s7 = new ClsSegnalazione();
        s7.setId("7");
        s7.setIdCuratore("7");
        s7.setIdContenuto("7");
        s7.setDescrizione("descrizione7");
        segnalazioni.add(s7);

        ClsSegnalazione s8 = new ClsSegnalazione();
        s8.setId("8");
        s8.setIdCuratore("1");
        s8.setIdContenuto("8");
        s8.setDescrizione("descrizione8");
        segnalazioni.add(s8);

        ClsSegnalazione s9 = new ClsSegnalazione();
        s9.setId("9");
        s9.setIdCuratore("1");
        s9.setIdContenuto("9");
        s9.setDescrizione("descrizione9");
        segnalazioni.add(s9);

        ClsSegnalazione s10 = new ClsSegnalazione();
        s10.setId("10");
        s10.setIdCuratore("1");
        s10.setIdContenuto("10");
        s10.setDescrizione("descrizione10");
        segnalazioni.add(s10);

        //endregion

        setSegnalazioni(segnalazioni);

        //region setting up colonne tabella
        idColonna.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        idElementoColonna.setCellValueFactory(
                new PropertyValueFactory<>("idContenuto"));

        descrizioneColonna.setCellValueFactory(
                new PropertyValueFactory<>("descrizione"));
        //endregion
    }
    private void setSegnalazioni (ArrayList<ClsSegnalazione> segnalazioni)
    {
        for(int i = 0; i<segnalazioni.size();i++)
        {
            elencoSegnalazioni.getItems().add(segnalazioni.get(i));
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
}
