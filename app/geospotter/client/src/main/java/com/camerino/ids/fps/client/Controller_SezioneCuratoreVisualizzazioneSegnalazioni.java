package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.fps.client.utils.Utils;
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
import java.util.List;
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
    List<ClsSegnalazione> segnalazioni;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        segnalazioni = Controller_SezioneLogin.UTENTE.GetAllSegnalazioni();

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
    private void setSegnalazioni (List<ClsSegnalazione> segnalazioni)
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
