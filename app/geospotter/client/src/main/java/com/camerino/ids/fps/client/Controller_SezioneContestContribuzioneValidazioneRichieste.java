package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.azioni.ClsRDCImmagine;
import com.camerino.ids.core.data.azioni.ClsRDCNodo;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsRDCVisual;
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
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneContestContribuzioneValidazioneRichieste implements Initializable {
    //region Elementi FXML

    //region Tabella Immagini
    @FXML
    TableView<ClsRDCVisual> elencoRichiesteContribuzioneImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> idColonnaImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> azioneDiContribuzioneImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> statoImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> richiedenteImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> responsabileImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> idContestImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> oldDataImmagine;
    @FXML
    TableColumn<ClsRDCVisual, String> newDataImmagine;

    @FXML
    ComboBox sceltaAzioneImmagine, selezionaElementoDettaglioImmagine;
    //endregion

    //region Tabella Nodi
    @FXML
    TableView<ClsRDCVisual> elencoRichiesteContribuzioneNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> idColonnaNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> azioneDiContribuzioneNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> statoNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> richiedenteNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> responsabileNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> idContestNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> oldDataNodo;
    @FXML
    TableColumn<ClsRDCVisual, String> newDataNodo;
    @FXML
    ComboBox sceltaAzioneNodo, selezionaElementoDettaglioNodo;
    //endregion

    //endregion

    List<ClsRDCImmagine> richiesteImmagini;
    List<ClsRDCNodo> richiesteNodo;

    Utils u = new Utils();

    //region dummy arrays per creare immagini nodi e itinerari
    List<ClsNodo> nodi = new ArrayList<ClsNodo>();
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.richiesteImmagini = new ArrayList<ClsRDCImmagine>();
        this.richiesteNodo = new ArrayList<ClsRDCNodo>();
/*
        //region Creazione RDCImmagine
        ClsRDCImmagine img1 = new ClsRDCImmagine();
        img1.setIdRichiesta("1");
        img1.setTipo(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        img1.setStato(EStatusRDC.ASSEGNATO);

        ClsTuristaAutenticato utente = new ClsTuristaAutenticato();
        utente.setId("1");
        img1.setCreatore(utente);

        ClsCuratore c1 = new ClsCuratore();
        c1.setId("1");
        //img1.setResponsabile(c1);

        ClsContestDiContribuzione contest1 = new ClsContestDiContribuzione();
        contest1.setId("1");
        img1.setIdContestAppartenenza(contest1);

        ClsImmagine im1 = new ClsImmagine();
        im1.setId("1");
        im1.setURL("testURLOld");
        im1.setIdCreatore("testCreatoreOld");
        im1.setIdNodoAssociato("testIDNodoOld");
        img1.setOldData(im1);

        ClsImmagine im2 = new ClsImmagine();
        im2.setId("1");
        im2.setURL("testURLNew");
        im2.setIdCreatore("testCreatoreNew");
        im2.setIdNodoAssociato("testIDNodoNew");
        img1.setNewData(im2);
        richiesteImmagini.add(img1);

        ClsRDCImmagine img2 = new ClsRDCImmagine();
        img2.setIdRichiesta("2");
        img2.setTipo(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        img2.setStato(EStatusRDC.ASSEGNATO);
        img2.setCreatore(utente);

        ClsCuratore c2 = new ClsCuratore();
        c2.setId("2");
        //img2.setResponsabile(c2);
        img2.setIdContestAppartenenza(contest1);

        img2.setOldData(im1);
        img2.setNewData(im2);
        richiesteImmagini.add(img2);
        ClsRDCImmagine img3 = new ClsRDCImmagine();
        img3.setIdRichiesta("3");
        img3.setTipo(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        img3.setStato(EStatusRDC.ASSEGNATO);
        img3.setCreatore(utente);

        ClsCuratore c3 = new ClsCuratore();
        c3.setId("3");
        //img3.setResponsabile(c3);
        img3.setIdContestAppartenenza(contest1);

        img3.setOldData(im1);
        img3.setNewData(im2);
        richiesteImmagini.add(img3);
        //endregion

        //region Creazione RDCNodo
        ClsRDCNodo n1 = new ClsRDCNodo();
        n1.setIdRichiesta("1");
        n1.setTipo(EAzioniDiContribuzione.INSERISCI_NODO);
        n1.setStato(EStatusRDC.ASSEGNATO);
        n1.setCreatore(utente);

        ClsCuratore cu1 = new ClsCuratore();
        cu1.setId("1");
        //n1.setResponsabile(cu1);
        n1.setIdContestAppartenenza(contest1);

        ClsNodo nodo1 = new ClsNodo();
        nodo1.setNome("nomeOld");
        nodo1.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
        nodo1.setIdComuneAssociato("comuneOld");
        nodo1.setaTempo(true);
        nodo1.setDescrizione("descrizioneOld");
        nodo1.setPosizione(new Posizione(14, 28));
        n1.setOldData(nodo1);

        nodo1.setNome("nomeNew");
        nodo1.setTipologiaNodo(CULINARIO);
        nodo1.setIdComuneAssociato("comuneNew");
        nodo1.setaTempo(false);
        nodo1.setDescrizione("descrizioneNew");
        nodo1.setPosizione(new Posizione(14, 28));
        n1.setNewData(nodo1);
        richiesteNodo.add(n1);

        ClsRDCNodo n2 = new ClsRDCNodo();
        n2.setIdRichiesta("2");
        n2.setTipo(EAzioniDiContribuzione.ELIMINA_NODO);
        n2.setStato(EStatusRDC.ASSEGNATO);
        n2.setCreatore(utente);

        ClsCuratore cu2 = new ClsCuratore();
        cu2.setId("2");
        //n2.setResponsabile(cu2);
        n2.setIdContestAppartenenza(contest1);

        nodo1.setNome("nomeOld");
        nodo1.setTipologiaNodo(ClsNodo.eTipologiaNodo.CULTURALE);
        nodo1.setIdComuneAssociato("comuneOld");
        nodo1.setaTempo(true);
        nodo1.setDescrizione("descrizioneOld");
        nodo1.setPosizione(new Posizione(14, 28));
        n2.setOldData(nodo1);

        nodo1.setNome("nomeNew");
        nodo1.setTipologiaNodo(CULINARIO);
        nodo1.setIdComuneAssociato("comuneNew");
        nodo1.setaTempo(false);
        nodo1.setDescrizione("descrizioneNew");
        nodo1.setPosizione(new Posizione(14, 28));
        n2.setNewData(nodo1);
        richiesteNodo.add(n2);
        //endregion
*/
        setRichiesteImmagini(richiesteImmagini);

        setRichiesteNodi(richiesteNodo);

        //region Combobox Scelta Immagini
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < richiesteImmagini.size(); i++) {
            items.add(richiesteImmagini.get(i).getIdRichiesta().toString());
        }

        this.sceltaAzioneImmagine.setItems(items);
        //endregion

        //region Combobox Scelta Immagini dettaglio
        ObservableList<String> itemss = FXCollections.observableArrayList();

        for (int i = 0; i < richiesteNodo.size(); i++) {
            itemss.add(richiesteNodo.get(i).getIdRichiesta().toString());
        }

        this.selezionaElementoDettaglioImmagine.setItems(itemss);
        //endregion

        //region Combobox Scelta Nodi
        ObservableList<String> it = FXCollections.observableArrayList();

        for (int i = 0; i < richiesteNodo.size(); i++) {
            it.add(richiesteNodo.get(i).getIdRichiesta().toString());
        }

        this.sceltaAzioneNodo.setItems(it);
        //endregion

        //region Combobox Scelta Nodi dettaglio
        ObservableList<String> ite = FXCollections.observableArrayList();

        for (int i = 0; i < richiesteNodo.size(); i++) {
            ite.add(richiesteNodo.get(i).getIdRichiesta().toString());
        }

        this.selezionaElementoDettaglioNodo.setItems(ite);
        //endregion

        //region Tabella Immagini
        idColonnaImmagine.setCellValueFactory(
                new PropertyValueFactory<>("idRichiesta"));

        azioneDiContribuzioneImmagine.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        statoImmagine.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        richiedenteImmagine.setCellValueFactory(
                new PropertyValueFactory<>("richiedente"));

        responsabileImmagine.setCellValueFactory(
                new PropertyValueFactory<>("responsabile"));

        idContestImmagine.setCellValueFactory(
                new PropertyValueFactory<>("idContest"));

        oldDataImmagine.setCellValueFactory(
                new PropertyValueFactory<>("oldData"));

        newDataImmagine.setCellValueFactory(
                new PropertyValueFactory<>("newData"));
        //endregion

        //region Tabella Nodi
        idColonnaNodo.setCellValueFactory(
                new PropertyValueFactory<>("idRichiesta"));

        azioneDiContribuzioneNodo.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        statoNodo.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        richiedenteNodo.setCellValueFactory(
                new PropertyValueFactory<>("richiedente"));

        responsabileNodo.setCellValueFactory(
                new PropertyValueFactory<>("responsabile"));

        idContestNodo.setCellValueFactory(
                new PropertyValueFactory<>("idContest"));

        oldDataNodo.setCellValueFactory(
                new PropertyValueFactory<>("oldData"));

        newDataNodo.setCellValueFactory(
                new PropertyValueFactory<>("newData"));
        //endregion
    }

    public void accettaAzioneImmagine(MouseEvent mouseEvent) {
        String IDValidazione = u.getValueFromCombobox(sceltaAzioneImmagine);
        if (IDValidazione != null && !Objects.equals(IDValidazione, "")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText("AZIONE (" + IDValidazione + ") Validata");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    public void rifiutaAzioneImmagine(MouseEvent mouseEvent) {
        String IDValidazione = u.getValueFromCombobox(sceltaAzioneImmagine);
        if (IDValidazione != null && !Objects.equals(IDValidazione, "")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText("AZIONE (" + IDValidazione + ") NON Validata");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    public void accettaAzioneNodo(MouseEvent mouseEvent) {
        String IDValidazione = u.getValueFromCombobox(sceltaAzioneNodo);
        if (IDValidazione != null && !Objects.equals(IDValidazione, "")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText("AZIONE (" + IDValidazione + ") Validata");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    public void rifiutaAzioneNodo(MouseEvent mouseEvent) {
        String IDValidazione = u.getValueFromCombobox(sceltaAzioneNodo);
        if (IDValidazione != null && !Objects.equals(IDValidazione, "")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText("AZIONE (" + IDValidazione + ") NON Validata");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    public void visualizzaDettaglioImmagine() {
        Long IDDaVisualizzare = Long.valueOf(u.getValueFromCombobox(selezionaElementoDettaglioImmagine));

        if (this.controllaConformitaID(IDDaVisualizzare)) {

            for (int i = 0; i < richiesteImmagini.size(); i++) {
                if (IDDaVisualizzare.equals(this.richiesteImmagini.get(i).getIdRichiesta())) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("OK");
                    alert.setContentText("Vecchi dati:" + richiesteImmagini.get(i).getOldData().visualizzaImmagine() + "\nNuovi Dati:" + richiesteImmagini.get(i).getNewData().visualizzaImmagine());
                    alert.show();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }
    }

    public void visualizzaDettaglioNodo() {
        Long IDDaVisualizzare = Long.valueOf(u.getValueFromCombobox(selezionaElementoDettaglioNodo));

        if (this.controllaConformitaID(IDDaVisualizzare)) {

            for (int i = 0; i < richiesteNodo.size(); i++) {
                if (IDDaVisualizzare.equals(this.richiesteNodo.get(i).getIdRichiesta())) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("OK");
                    alert.setContentText("Vecchi dati:" + richiesteNodo.get(i).getOldData().visualizzaNodo() + "\nNuovi Dati:" + richiesteNodo.get(i).getNewData().visualizzaNodo());
                    alert.show();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }
    }

    private void setRichiesteImmagini(List<ClsRDCImmagine> richiesteImmagini) {
        for (int i = 0; i < richiesteImmagini.size(); i++) {
            ClsRDCVisual c = u.convertFromRDCImmagine(richiesteImmagini.get(i));
            elencoRichiesteContribuzioneImmagine.getItems().add(c);
        }
    }

    private void setRichiesteNodi(List<ClsRDCNodo> richiesteNodo) {
        for (int i = 0; i < richiesteNodo.size(); i++) {
            ClsRDCVisual c = u.convertFromRDCNodo(richiesteNodo.get(i));
            elencoRichiesteContribuzioneNodo.getItems().add(c);
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

    private boolean controllaConformitaID(Long id) {
        /*boolean flag = false;

        for(int i = 0; i<richieste.size();i++)
        {
            if(richieste.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
    }


}
