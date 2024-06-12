package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
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

public class Controller_SezioneVisualizzazioneNodi implements Initializable {
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
    Button sezioneEliminazioneNodiButtonHomePage;

    @FXML
    ComboBox selezionaElementoDettaglio, selezionaElementoSegnalazione;

    @FXML
    TextField descrizioneTF;
    //endregion

    List<ClsNodo> nodi;
    Utils u = new Utils();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nodi = Controller_SezioneLogin.UTENTE.getAllNodi();

        setNodi(nodi);

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

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < nodi.size(); i++) {
            items.add(nodi.get(i).getId().toString());
        }

        this.selezionaElementoDettaglio.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> itemss = FXCollections.observableArrayList();

        for (int i = 0; i < nodi.size(); i++) {
            itemss.add(nodi.get(i).getId().toString());
        }

        this.selezionaElementoSegnalazione.setItems(itemss);
        //endregion
    }

    public void visualizzaDettaglio(MouseEvent mouseEvent)
    {
        if(u.getValueFromCombobox(selezionaElementoDettaglio) != "" && u.getValueFromCombobox(selezionaElementoDettaglio) != null)
        {
            Long IDDaVisualizzare = Long.valueOf(u.getValueFromCombobox(selezionaElementoDettaglio));

            if (IDDaVisualizzare != null && !IDDaVisualizzare.equals("") && this.controllaConformitaID(IDDaVisualizzare)) {
                ClsNodo nodo = new ClsNodo();
                for (int i = 0; i < nodi.size(); i++) {
                    if (IDDaVisualizzare.equals(this.nodi.get(i).getId())) {
                        nodo.setId(nodi.get(i).getId());
                        nodo.setNome(nodi.get(i).getNome());
                        nodo.setDescrizione(nodi.get(i).getDescrizione());
                        nodo.setPosizione(nodi.get(i).getPosizione());
                        nodo.setaTempo(nodi.get(i).isaTempo());
                        nodo.setDataInizio(nodi.get(i).getDataInizio());
                        nodo.setDataFine(nodi.get(i).getDataFine());
                        nodo.setIdComuneAssociato(nodi.get(i).getIdComuneAssociato());
                        nodo.setTipologiaNodo(nodi.get(i).getTipologiaNodo());
                        nodo.setIdCreatore(nodi.get(i).getIdCreatore());
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("OK");
                alert.setContentText(nodo.visualizzaNodo());
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRORE");
                alert.setContentText("Controlla le informazioni e riprova");
                alert.show();
            }
        }
        else{
            u.alertError();
        }

    }

    public void inserisciSegnalazione(MouseEvent mouseEvent) {
        String descrizioneSegnalazione = u.getValueFromTextField(descrizioneTF);

        if (!Objects.equals(descrizioneSegnalazione, "") && descrizioneSegnalazione != null && u.getValueFromCombobox(selezionaElementoSegnalazione) != null) {
            Long IDDaSegnalare = Long.valueOf(u.getValueFromCombobox(selezionaElementoSegnalazione));
            ClsSegnalazione segnalazione = new ClsSegnalazione();
            segnalazione.setDescrizione(descrizioneSegnalazione);
            segnalazione.setIdContenuto(IDDaSegnalare);

            /*for (int i = 0; i < this.nodi.size(); i++) {
                if (Objects.equals(nodi.get(i).getId(), IDDaSegnalare)) {
                    segnalazione.setIdCuratore(1L); //todo:aggiungere chiamata per ottenere curatore
                }
            }*/
            Controller_SezioneLogin.UTENTE.segnalaContenuto(segnalazione);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setContentText(segnalazione.visualizzaSegnalazione());
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }
    }

    private void setNodi(List<ClsNodo> nodi) {
        for (int i = 0; i < nodi.size(); i++) {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            sezioneEliminazioneNodiTableView.getItems().add(c);
        }
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

    public void navigateToSezioneVisualizzazioneMappa(MouseEvent mouseEvent) {
        this.SwitchScene("SezioneVisualizzazioneMappa.fxml", mouseEvent);
    }

    private boolean controllaConformitaID(Long id) {
        /*boolean flag = false;

        for(int i = 0; i<nodi.size();i++)
        {
            if(nodi.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
    }


}
