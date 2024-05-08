package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utils.Posizione;
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

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;
import static java.util.Objects.isNull;

public class Controller_SezioneEliminazioneRecensioni implements Initializable
{

    //region Elementi FXML
    @FXML
    TableView<ClsRecensione> sezioneEliminazioneRecensioniTableView;

    @FXML
    TableColumn<ClsRecensione,String> sezioneEliminazioneRecensioniTableColumnID;

    @FXML
    TableColumn<ClsRecensione,String> sezioneEliminazioneRecensioniTableColumnIDContenutoAssociato;

    @FXML
    TableColumn<ClsRecensione,String> sezioneEliminazioneRecensioniTableColumnOggetto;

    @FXML
    TableColumn<ClsRecensione, Double> sezioneEliminazioneRecensioniTableColumnValutazione;

    @FXML
    TableColumn<ClsRecensione, String> getSezioneEliminazioneRecensioniTableColumnContenuto;

    @FXML
    Button home, conferma;

    @FXML
    ComboBox sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID;
    //endregion

    ArrayList<ClsRecensione> recensioni;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        recensioni = new ArrayList<ClsRecensione>();

        //region Creazione nodi dummy
        ClsRecensione r1 = new ClsRecensione();
        r1.setId("1");
        r1.setUsernameCreatore("test");
        r1.setOggetto("Oggetto1");
        r1.setContenuto("Contenuto1");
        r1.setValutazione(1);
        r1.setIdContenutoAssociato("ContenutoAssociato1");
        recensioni.add(r1);

        ClsRecensione r2 = new ClsRecensione();
        r2.setId("2");
        r2.setUsernameCreatore("test");
        r2.setOggetto("Oggetto2");
        r2.setContenuto("Contenuto2");
        r2.setValutazione(2);
        r2.setIdContenutoAssociato("ContenutoAssociato2");
        recensioni.add(r2);

        ClsRecensione r3 = new ClsRecensione();
        r3.setId("3");
        r3.setUsernameCreatore("test");
        r3.setOggetto("Oggetto3");
        r3.setContenuto("Contenuto3");
        r3.setValutazione(3);
        r3.setIdContenutoAssociato("ContenutoAssociato3");
        recensioni.add(r3);
        //endregion

        setRecensioni(recensioni);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<recensioni.size();i++)
        {
            items.add(recensioni.get(i).getId());
        }

        this.sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID.setItems(items);
        //endregion

        //region setting up colonne tabella
        sezioneEliminazioneRecensioniTableColumnID.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        sezioneEliminazioneRecensioniTableColumnIDContenutoAssociato.setCellValueFactory(
                new PropertyValueFactory<>("idContenutoAssociato"));

        sezioneEliminazioneRecensioniTableColumnOggetto.setCellValueFactory(
                new PropertyValueFactory<>("oggetto"));

        sezioneEliminazioneRecensioniTableColumnValutazione.setCellValueFactory(
                new PropertyValueFactory<>("valutazione"));

        getSezioneEliminazioneRecensioniTableColumnContenuto.setCellValueFactory(
                new PropertyValueFactory<>("contenuto"));

        //endregion
    }

    public void eliminaRecensione(MouseEvent mouseEvent)
    {
        String IDDaEliminare = u.getValueFromCombobox(this.sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID);

        if(IDDaEliminare != null && this.controllaConformitaID(IDDaEliminare))
        {
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

    private void setRecensioni (ArrayList<ClsRecensione> recensioni)
    {
        for(int i = 0; i<recensioni.size();i++)
        {
            sezioneEliminazioneRecensioniTableView.getItems().add(recensioni.get(i));
        }
    }

    private boolean controllaConformitaID (String id)
    {
        boolean flag = false;

        for(int i = 0; i<recensioni.size();i++)
        {
            if(recensioni.get(i).getId() == id)
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

    public void navigateToSezioneVisualizzazione (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazione.fxml",mouseEvent);
    }

}
