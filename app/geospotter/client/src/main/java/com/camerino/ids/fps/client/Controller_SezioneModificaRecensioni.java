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
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneModificaRecensioni implements Initializable {

    //region Elementi FXML
    @FXML
    TableView<ClsRecensione> sezioneEliminazioneRecensioniTableView;

    @FXML
    TableColumn<ClsRecensione, String> sezioneEliminazioneRecensioniTableColumnID;

    @FXML
    TableColumn<ClsRecensione, String> sezioneEliminazioneRecensioniTableColumnIDContenutoAssociato;

    @FXML
    TableColumn<ClsRecensione, String> sezioneEliminazioneRecensioniTableColumnOggetto;

    @FXML
    TableColumn<ClsRecensione, Double> sezioneEliminazioneRecensioniTableColumnValutazione;

    @FXML
    TableColumn<ClsRecensione, String> getSezioneEliminazioneRecensioniTableColumnContenuto;

    @FXML
    ComboBox sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID;

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
    ComboBox sezioneEliminazioneNodiComboBoxSceltaNodoID;

    @FXML
    TextField oggetto, contenuto, valutazione;
    //endregion

    List<ClsRecensione> recensioni;
    List<ClsNodo> nodi;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //recensioni = ((ClsTuristaAutenticato)Controller_SezioneLogin.UTENTE).getAllRecensioni();
        recensioni = ((ClsTuristaAutenticato) Controller_SezioneLogin.UTENTE).getRecensioniPosessore();

        setRecensioni(recensioni);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < recensioni.size(); i++) {
            items.add(recensioni.get(i).getId().toString());
        }

        this.sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID.setItems(items);
        //endregion

        //region setting up colonne tabella
        sezioneEliminazioneRecensioniTableColumnID.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        sezioneEliminazioneRecensioniTableColumnIDContenutoAssociato.setCellValueFactory(
                new PropertyValueFactory<>("idNodoAssociato"));

        sezioneEliminazioneRecensioniTableColumnOggetto.setCellValueFactory(
                new PropertyValueFactory<>("oggetto"));

        sezioneEliminazioneRecensioniTableColumnValutazione.setCellValueFactory(
                new PropertyValueFactory<>("valutazione"));

        getSezioneEliminazioneRecensioniTableColumnContenuto.setCellValueFactory(
                new PropertyValueFactory<>("contenuto"));

        //endregion

        //--

        nodi = Controller_SezioneLogin.UTENTE.getAllNodi();

        setNodi(nodi);

        //region combobox
        ObservableList<String> itemes = FXCollections.observableArrayList();

        for (int i = 0; i < nodi.size(); i++) {
            itemes.add(nodi.get(i).getId().toString());
        }

        this.sezioneEliminazioneNodiComboBoxSceltaNodoID.setItems(itemes);
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

    public void modificaRecensione(MouseEvent mouseEvent) {
        ClsRecensione r = new ClsRecensione();
        String idNodo = u.getValueFromCombobox(this.sezioneEliminazioneNodiComboBoxSceltaNodoID) + "";
        String idRec = u.getValueFromCombobox(this.sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID) + "";

        if(!Objects.equals(idNodo, "") && !Objects.equals(idRec, ""))
        {

            String oggettoo = u.getValueFromTextField(this.oggetto);
            String contenutoo = u.getValueFromTextField(this.contenuto);
            String valutazionee = u.getValueFromTextField(this.valutazione);


            if (!(Objects.equals(oggettoo, "")) && !(oggettoo == null) && !(Objects.equals(contenutoo, "")) && !(contenutoo == null) && valutazionee != null && !valutazionee.equals("")) {


                r.setId(Long.valueOf(idRec));
                r.setIdCreatore(((ClsTuristaAutenticato) Controller_SezioneLogin.UTENTE).getId());
                r.setIdNodoAssociato(Long.valueOf(idNodo));
                r.setOggetto(oggettoo);
                r.setContenuto(contenutoo);
                r.setValutazione(Double.parseDouble(valutazionee));

                ((ClsTuristaAutenticato) Controller_SezioneLogin.UTENTE).modificaRecensione(Long.valueOf(idNodo), r);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("OK");
                alert.setContentText(r.visualizzaRecensione());
                alert.show();
            }
            else
            {
                u.alertError();
            }
        }
        else
        {
            u.alertError();
        }

    }

    private void setRecensioni(List<ClsRecensione> recensioni) {
        for (int i = 0; i < recensioni.size(); i++) {
            sezioneEliminazioneRecensioniTableView.getItems().add(recensioni.get(i));
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

    public void visualizzaInformazioniAttualiRecensione(MouseEvent mouseEvent) {
        String tmp = u.getValueFromCombobox(this.sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID);

        if (!(Objects.equals(tmp, null) || tmp.isEmpty())) {
            for (int i = 0; i < recensioni.size(); i++) {
                if (Objects.equals(recensioni.get(i).getId(), Long.valueOf(tmp))) {
                    this.sezioneEliminazioneNodiComboBoxSceltaNodoID.setValue(recensioni.get(i).getId().toString());
                    this.oggetto.setText(recensioni.get(i).getOggetto());
                    this.contenuto.setText(recensioni.get(i).getContenuto());
                    this.valutazione.setText(recensioni.get(i).getValutazione().toString());
                }
            }
        }
    }


}
