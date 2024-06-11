package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsItinerarioVisual;
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

public class Controller_SezioneVisualizzazioneItinerari implements Initializable {
    //region Elementi FXML
    @FXML
    TableView<ClsItinerarioVisual> sezioneEliminazioneItinerariTableView;

    @FXML
    TableColumn<ClsItinerarioVisual, String> sezioneEliminazioneItinerariTableColumnID = new TableColumn<>("ID");
    @FXML
    TableColumn<ClsItinerarioVisual, String> sezioneEliminazioneItinerariTableColumnNome = new TableColumn<>("Nome");
    @FXML
    TableColumn<ClsItinerarioVisual, String> sezioneInserimentoItinerariTableColumnOrdinato = new TableColumn<>("E' ordinato?");
    @FXML
    TableColumn<ClsItinerarioVisual, String> sezioneInserimentoItinerariTableColumnTappe = new TableColumn<>("Tappe");

    @FXML
    ComboBox selezionaElementoDettaglio, selezionaElementoSegnalazione;

    @FXML
    TextField descrizioneTF;
    //endregion

    List<ClsItinerario> itinerari;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itinerari = Controller_SezioneLogin.UTENTE.getAllItinerari();

        this.setItinerari(itinerari);

        //region setting up colonne tabella
        sezioneEliminazioneItinerariTableColumnID.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        sezioneEliminazioneItinerariTableColumnNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        sezioneInserimentoItinerariTableColumnOrdinato.setCellValueFactory(
                new PropertyValueFactory<>("ordinato"));

        sezioneInserimentoItinerariTableColumnTappe.setCellValueFactory(
                new PropertyValueFactory<>("tappe"));
        //endregion

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < itinerari.size(); i++) {
            items.add(itinerari.get(i).getId().toString());
        }

        this.selezionaElementoDettaglio.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> itemss = FXCollections.observableArrayList();

        for (int i = 0; i < itinerari.size(); i++) {
            itemss.add(itinerari.get(i).getId().toString());
        }

        this.selezionaElementoSegnalazione.setItems(itemss);
        //endregion

    }

    public void visualizzaDettaglio(MouseEvent mouseEvent) {
        Long IDDaVisualizzare = Long.valueOf(u.getValueFromCombobox(selezionaElementoDettaglio));

        if (IDDaVisualizzare != null && !IDDaVisualizzare.equals("") && this.controllaConformitaID(IDDaVisualizzare)) {
            ClsItinerario itinerario = new ClsItinerario();
            for (int i = 0; i < itinerari.size(); i++) {
                if (IDDaVisualizzare.equals(this.itinerari.get(i).getId())) {
                    itinerario.setId(itinerari.get(i).getId());
                    itinerario.setTappe(itinerari.get(i).getTappe());
                    itinerario.setNome(itinerari.get(i).getNome());
                    itinerario.setOrdinato(itinerari.get(i).isOrdinato());
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setContentText(itinerario.visualizzaItinerario());
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }
    }

    public void inserisciSegnalazione(MouseEvent mouseEvent) {
        String descrizioneSegnalazione = u.getValueFromTextField(descrizioneTF);

        if (!Objects.equals(descrizioneSegnalazione, "") && descrizioneSegnalazione != null && u.getValueFromCombobox(selezionaElementoSegnalazione) != null) {
            Long IDDaSegnalare = Long.valueOf(u.getValueFromCombobox(selezionaElementoSegnalazione));
            ClsSegnalazione segnalazione = new ClsSegnalazione();
            segnalazione.setDescrizione(descrizioneSegnalazione);
            segnalazione.setIdContenuto(IDDaSegnalare);

            /*for (int i = 0; i < this.itinerari.size(); i++) {
                if (Objects.equals(itinerari.get(i).getId(), IDDaSegnalare)) {
                    segnalazione.setIdCuratore(itinerari.get(i).getTappe().get(0).getIdComuneAssociato()); //todo: ottenere da spring
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

    private void setItinerari(List<ClsItinerario> itinerari) {
        for (int i = 0; i < itinerari.size(); i++) {
            ClsItinerarioVisual c = u.convertFromClsItinerario(itinerari.get(i));
            sezioneEliminazioneItinerariTableView.getItems().add(c);
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

        for(int i = 0; i<itinerari.size();i++)
        {
            if(itinerari.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
    }


}
