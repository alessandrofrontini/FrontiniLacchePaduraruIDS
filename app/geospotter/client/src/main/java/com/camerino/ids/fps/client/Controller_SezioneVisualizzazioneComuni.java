package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsCuratore;
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

public class Controller_SezioneVisualizzazioneComuni implements Initializable {

    //region Elementi FXML
    @FXML
    TableView<ClsComuneVisual> elencoComuni;

    @FXML
    TableColumn<ClsComuneVisual, String> id;

    @FXML
    TableColumn<ClsComuneVisual, String> nome;

    @FXML
    TableColumn<ClsComuneVisual, String> posizione;

    @FXML
    TableColumn<ClsComuneVisual, String> abitanti;

    @FXML
    TableColumn<ClsComuneVisual, String> superficie;

    @FXML
    TableColumn<ClsComuneVisual, String> curatori;

    @FXML
    ComboBox selezionaElementoDettaglio, selezionaElementoSegnalazione;

    @FXML
    TextField descrizioneTF;

    //endregion

    Utils u = new Utils();
    List<ClsComune> comuni;
    List<ClsCuratore> Curatori = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.comuni = Controller_SezioneLogin.UTENTE.getAllComuni();


        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < comuni.size(); i++) {
            items.add(comuni.get(i).getId().toString());
        }

        this.selezionaElementoDettaglio.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> itemss = FXCollections.observableArrayList();

        for (int i = 0; i < comuni.size(); i++) {
            itemss.add(comuni.get(i).getId().toString());
        }

        this.selezionaElementoSegnalazione.setItems(itemss);
        //endregion

        setComuni(comuni);

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

    public void inserisciSegnalazione() {
        String descrizioneSegnalazione = u.getValueFromTextField(descrizioneTF);

        if (!Objects.equals(descrizioneSegnalazione, "") && descrizioneSegnalazione != null && u.getValueFromCombobox(selezionaElementoSegnalazione) != null) {
            Long IDDaSegnalare = Long.valueOf(u.getValueFromCombobox(selezionaElementoSegnalazione));
            ClsSegnalazione segnalazione = new ClsSegnalazione();
            segnalazione.setDescrizione(descrizioneSegnalazione);
            segnalazione.setIdContenuto(IDDaSegnalare);
            /*for (int i = 0; i < this.comuni.size(); i++) {
                if (Objects.equals(comuni.get(i).getId(), IDDaSegnalare)) {
                    if(!comuni.get(i).getCuratoriAssociati().isEmpty())
                        segnalazione.setIdCuratore(comuni.get(i).getCuratoriAssociati().get(0).getId());
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

    public void visualizzaDettaglio() {
        Long IDDaVisualizzare = Long.valueOf(u.getValueFromCombobox(selezionaElementoDettaglio));

        if (this.controllaConformitaID(IDDaVisualizzare)) {
            ClsComune c = new ClsComune();
            for (int i = 0; i < comuni.size(); i++) {
                if (IDDaVisualizzare.equals(this.comuni.get(i).getId())) {
                    c.setId(comuni.get(i).getId());
                    c.setNome(comuni.get(i).getNome());
                    c.setCuratoriAssociati(comuni.get(i).getCuratoriAssociati());
                    c.setSuperficie(comuni.get(i).getSuperficie());
                    c.setDescrizione(comuni.get(i).getDescrizione());
                    c.setIdCreatore(comuni.get(i).getIdCreatore());
                    c.setAbitanti(comuni.get(i).getAbitanti());
                    c.setPosizione(comuni.get(i).getPosizione());
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setContentText(c.visualizzaComune());
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }
    }

    private void setComuni(List<ClsComune> comuni) {
        for (int i = 0; i < comuni.size(); i++) {
            ClsComuneVisual c = u.convertFromClsComune(comuni.get(i));

            elencoComuni.getItems().add(c);
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

    private boolean controllaConformitaID(Long id) {/*
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
}
