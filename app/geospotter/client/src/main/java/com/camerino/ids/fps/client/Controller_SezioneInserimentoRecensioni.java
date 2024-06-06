package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
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
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneInserimentoRecensioni implements Initializable
{
    //region Elementi FXML
    @FXML
    TableView<ClsNodoVisual> sezioneEliminazioneNodiTableView;

    @FXML
    TableColumn<ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnID = new TableColumn<>("ID");
    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnIDComuneAssociato = new TableColumn<>("Comune Associato");
    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnNome = new TableColumn<>("Nome");
    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnPosizione = new TableColumn<>("Posizione");
    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnTipologia = new TableColumn<>("Tipologia");
    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnATempo = new TableColumn<>("E' Temporizzato?");

    @FXML
    Button  sezioneEliminazioneNodiButtonHomePage, sezioneInserimentoRecensioniConferma;

    @FXML
    ComboBox sezioneEliminazioneNodiComboBoxSceltaNodoID;

    @FXML
    TextField oggetto, contenuto, valutazione;
    //endregion

    List<ClsNodo> nodi;
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

    public void inserisciRecensione(MouseEvent mouseEvent)
    {

        ClsRecensione r = new ClsRecensione();

        String id = u.getValueFromCombobox(this.sezioneEliminazioneNodiComboBoxSceltaNodoID);
        String oggetto = u.getValueFromTextField(this.oggetto);
        String contenuto = u.getValueFromTextField(this.contenuto);
        String valutazione = u.getValueFromTextField(this.valutazione);

        if((Objects.isNull(id) || id.isEmpty()) || (Objects.isNull(oggetto) || oggetto.isEmpty()) || (Objects.isNull(contenuto) || contenuto.isEmpty()) || (Objects.isNull(valutazione) || valutazione.isEmpty()))
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Inserisci tutte le informazioni necessarie");
            alert.show();
        }
        else
        {
            r.setIdCreatore(Controller_SezioneLogin.utente.getUsername());
            r.setIdNodoAssociato(id);
            r.setOggetto(oggetto);
            r.setContenuto(contenuto);
            r.setValutazione(Double.parseDouble(valutazione));

            ((ClsTuristaAutenticato)Controller_SezioneLogin.UTENTE).inserisciRecensione(r);
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText(r.visualizzaRecensione());
            alert.show();
        }
    }

    private void setNodi (List<ClsNodo> nodi)
    {
        for(int i = 0; i<nodi.size();i++)
        {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            sezioneEliminazioneNodiTableView.getItems().add(c);
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
