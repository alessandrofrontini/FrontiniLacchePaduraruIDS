package com.camerino.ids.fps.client;

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
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneEliminazioneNodi implements Initializable {
    //region Elementi FXML
    @FXML
    TableView<ClsNodoVisual> sezioneEliminazioneNodiTableView;

    @FXML
    TableColumn<ClsNodoVisual, String> sezioneEliminazioneNodiTableColumnID = new TableColumn<>("ID");
    @FXML
    TableColumn<ClsNodoVisual, String> sezioneEliminazioneNodiTableColumnIDComuneAssociato = new TableColumn<>("Comune Associato");
    @FXML
    TableColumn<ClsNodoVisual, String> sezioneEliminazioneNodiTableColumnNome = new TableColumn<>("Nome");
    @FXML
    TableColumn<ClsNodoVisual, String> sezioneEliminazioneNodiTableColumnPosizione = new TableColumn<>("Posizione");
    @FXML
    TableColumn<ClsNodoVisual, String> sezioneEliminazioneNodiTableColumnTipologia = new TableColumn<>("Tipologia");
    @FXML
    TableColumn<ClsNodoVisual, String> sezioneEliminazioneNodiTableColumnATempo = new TableColumn<>("E' Temporizzato?");

    @FXML
    Button sezioneEliminazioneNodiButtonConferma, sezioneEliminazioneNodiButtonHomePage;

    @FXML
    ComboBox sezioneEliminazioneNodiComboBoxSceltaNodoID;

    //endregion

    List<ClsNodo> nodi;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //nodi = Controller_SezioneLogin.UTENTE.getAllNodi();
        nodi = (List<ClsNodo>) ((ClsContributor) Controller_SezioneLogin.UTENTE).getNodiPossessore();

        setNodi(nodi);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < nodi.size(); i++) {
            items.add(nodi.get(i).getId().toString());
        }

        this.sezioneEliminazioneNodiComboBoxSceltaNodoID.setItems(items);
        //endregion

        //region setting up colonne tabella
        sezioneEliminazioneNodiTableColumnID.setCellValueFactory(
                new PropertyValueFactory<>("ID"));

        sezioneEliminazioneNodiTableColumnIDComuneAssociato.setCellValueFactory(
                new PropertyValueFactory<>("IDComuneAssociato"));

        sezioneEliminazioneNodiTableColumnNome.setCellValueFactory(
                new PropertyValueFactory<>("Nome"));

        sezioneEliminazioneNodiTableColumnPosizione.setCellValueFactory(
                new PropertyValueFactory<>("Posizione"));

        sezioneEliminazioneNodiTableColumnTipologia.setCellValueFactory(
                new PropertyValueFactory<>("Tipologia"));

        sezioneEliminazioneNodiTableColumnATempo.setCellValueFactory(
                new PropertyValueFactory<>("ATempo"));
        //endregion
    }

    public void eliminaNodo()
    {
        String IDDaEliminare = u.getValueFromCombobox(this.sezioneEliminazioneNodiComboBoxSceltaNodoID);

        if(!Objects.equals(IDDaEliminare, "") && IDDaEliminare != null)
        {
                ClsContributor user = (ClsContributor) Controller_SezioneLogin.UTENTE;
                if (user.eliminaNodo(Long.valueOf(IDDaEliminare))) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(IDDaEliminare.toString());
                    alert.setContentText("Eliminato!");
                    alert.show();
                } else {
                    u.alertError();
                }

        }
        else {
            u.alertError();
        }

    }

    private void setNodi(List<ClsNodo> nodi) {
        for (int i = 0; i < nodi.size(); i++) {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            sezioneEliminazioneNodiTableView.getItems().add(c);
        }
    }

    private boolean controllaConformitaID(Long id) {
        /*
        boolean flag = false;

        for(int i = 0; i<nodi.size();i++)
        {
            if(Objects.equals(nodi.get(i).getId(), id))
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
    }

    private void SwitchScene(String nomeScena, MouseEvent mouseEvent) {
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

    public void navigateToSezioneVisualizzazione(MouseEvent mouseEvent) {
        this.SwitchScene("SezioneVisualizzazione.fxml", mouseEvent);
    }
}

