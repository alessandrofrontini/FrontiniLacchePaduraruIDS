package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utils.Posizione;
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

public class Controller_SezioneModificaNodi implements Initializable {
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

    @FXML
    TextField sezioneInserimentoNodiTextFieldNomeDelNodo, sezioneInserimentoNodiTextFieldComuneAssociato, sezioneInserimentoNodiTextFieldCoordinataX, sezioneInserimentoNodiTextFieldCoordinataY, sezioneInserimentoNodiTextFieldDescrizioneDelNodo, sezioneInserimentoNodiTextFieldDataInizio, sezioneInserimentoNodiTextFieldDataFine;

    @FXML
    CheckBox sezioneInserimentoNodiCheckBoxTemporizzato;

    @FXML
    ComboBox sezioneInserimentoNodiComboBoxTipologiaNodo;

    @FXML
    Button sezioneInserimentoNodiButtonConferma, sezioneInserimentoNodiButtonHomePage, sezioneModificaNodiButtonConferma;

    //endregion

    boolean flag = false;
    Utils u = new Utils();
    ObservableList<String> itemsComboBox;
    List<ClsNodo> nodi;
    List<ClsComune> comuni;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.sezioneInserimentoNodiTextFieldDataInizio.setVisible(flag);
        this.sezioneInserimentoNodiTextFieldDataFine.setVisible(flag);

        nodi = (List<ClsNodo>) ((ClsContributor) Controller_SezioneLogin.UTENTE).getNodiPossessore();
        comuni = Controller_SezioneLogin.UTENTE.getAllComuni();

        //endregion
        setNodi(nodi);

        //region combobox
        //items della combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < nodi.size(); i++) {
            items.add(nodi.get(i).getId().toString());
        }

        this.sezioneEliminazioneNodiComboBoxSceltaNodoID.setItems(items);
        //endregion

        //region setting up tabella
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

    public void modificaNodo(MouseEvent mouseEvent) {
        if(!Objects.equals(this.eliminaNodo(mouseEvent), "") && this.inserisciNodo(mouseEvent) != null){
            ClsNodo nuovoNodo = this.inserisciNodo(mouseEvent);
            Long IDDaModificare = Long.valueOf(this.eliminaNodo(mouseEvent));


            if (!Objects.equals(IDDaModificare, "") && this.controllaConformitaID(IDDaModificare) && nuovoNodo != null && this.CheckValidita(nuovoNodo,comuni)) {
                nuovoNodo.setId(IDDaModificare);
                ((ClsContributor) Controller_SezioneLogin.UTENTE).modificaNodo(IDDaModificare, nuovoNodo);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("FATTO");
                alert.setContentText("ID: " + IDDaModificare + "\n\n" + nuovoNodo.visualizzaNodo());
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Attenzione");
                alert.setContentText("Controlla le informazioni e riprova");
                alert.show();
            }
        }
        else{
            u.alertError();
        }



    }

    private String eliminaNodo(MouseEvent mouseEvent) {
        String nodoDaEliminare = u.getValueFromCombobox(this.sezioneEliminazioneNodiComboBoxSceltaNodoID);

        if (!Objects.equals(nodoDaEliminare, "")) {
            return nodoDaEliminare;
        } else {
            return "";
        }
    }

    private ClsNodo inserisciNodo(MouseEvent mouseEvent) {

        ClsNodo nodo = new ClsNodo();
        Posizione posizione = new Posizione();

        try {
            if (!(Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataX)) == Double.NaN || Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataY)) == Double.NaN)) {
                posizione.setX(Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataX)));
                posizione.setY(Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataY)));
                nodo.setPosizione(posizione);

                if (u.getValueFromCombobox(sezioneInserimentoNodiComboBoxTipologiaNodo) != null ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo), "") ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato), "") ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo), "") ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo), null) ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato), null) ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo), null)) {

                    nodo.setIdCreatore( ((ClsContributor) Controller_SezioneLogin.UTENTE).getId());
                    nodo.seteTologiaNodoFormatoStringa(u.getValueFromCombobox(sezioneInserimentoNodiComboBoxTipologiaNodo));
                    nodo.setNome(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo));
                    nodo.setIdComuneAssociato(Long.valueOf(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato)));
                    nodo.setDescrizione(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo));
                    nodo.setaTempo(u.getValueFromCheckBox(sezioneInserimentoNodiCheckBoxTemporizzato));
                    nodo.setDataInizio(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDataInizio));
                    nodo.setDataFine(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDataFine));

                    if (!u.checkInfoNodo(nodo) && this.checkInfoNodo(nodo, comuni)) {
                        return null;
                    } else {
                        return nodo;
                    }
                } else {
                    return null;
                }


            } else {
                return null;
            }

        } catch (NumberFormatException e) {

        } catch (NullPointerException e) {

        }
        return null;
    }

    public boolean checkInfoNodo(ClsNodo nodo, List<ClsComune> comuni) {
        boolean flag = false;
        if (
                Objects.equals(nodo.getNome(), "") ||
                        Objects.equals(nodo.getTipologiaNodoFormatoStringa(), "") ||
                        Objects.equals(nodo.getIdComuneAssociato(), "") ||
                        Objects.equals(nodo.getDescrizione(), "") ||
                        //Objects.equals(nodo.getId(), null) ||
                        Objects.equals(nodo.getNome(), null) ||
                        Objects.equals(nodo.getTipologiaNodoFormatoStringa(), null) ||
                        Objects.equals(nodo.getIdComuneAssociato(), null) ||
                        Objects.equals(nodo.getDescrizione(), null)) {
            return flag;
        } else {
            flag = true;
            boolean flagComuni = false;
            for (int i = 0; i < comuni.size(); i++) {
                if (Objects.equals(nodo.getId(), comuni.get(i).getId())) {
                    flagComuni = true;
                }
            }
            return flagComuni;
        }

    }

    private void setNodi(List<ClsNodo> nodi) {
        for (int i = 0; i < nodi.size(); i++) {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            sezioneEliminazioneNodiTableView.getItems().add(c);
        }
    }

    public void visualizzaInformazioniAttualiNodo() {
        String valore = u.getValueFromCombobox(this.sezioneEliminazioneNodiComboBoxSceltaNodoID);

        if (valore != null) {
            for (int i = 0; i < this.nodi.size(); i++) {
                if (Objects.equals(Long.valueOf(valore), nodi.get(i).getId())) {

                    ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

                    this.sezioneInserimentoNodiTextFieldNomeDelNodo.setText(c.Nome);
                    this.sezioneInserimentoNodiTextFieldComuneAssociato.setText(c.IDComuneAssociato);
                    this.sezioneInserimentoNodiTextFieldDataFine.setText(nodi.get(i).getDataFine());
                    this.sezioneInserimentoNodiTextFieldDataInizio.setText(nodi.get(i).getDataInizio());
                    this.sezioneInserimentoNodiTextFieldCoordinataX.setText(nodi.get(i).getPosizione().getX().toString());
                    this.sezioneInserimentoNodiTextFieldCoordinataY.setText(nodi.get(i).getPosizione().getY().toString());
                    this.sezioneInserimentoNodiTextFieldDescrizioneDelNodo.setText(nodi.get(i).getDescrizione());
                    this.sezioneInserimentoNodiComboBoxTipologiaNodo.setValue(nodi.get(i).getTipologiaNodoFormatoStringa());
                }
            }
        }


    }

    public void visualizzaInformazioniNodoTemporizzato(MouseEvent mouseEvent) {
        flag = !flag;
        this.sezioneInserimentoNodiTextFieldDataInizio.setVisible(flag);
        this.sezioneInserimentoNodiTextFieldDataFine.setVisible(flag);
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

    private boolean controllaConformitaID(Long id) {/*
        boolean flag = false;

        for(int i = 0; i<nodi.size();i++)
        {
            if (Objects.equals(nodi.get(i).getId(), id)) {
                flag = true;
                break;
            }
        }
        return flag;*/
        return true;
    }

    public boolean CheckValidita(ClsNodo nodo, List<ClsComune> comuni) {
        boolean flagComuni = false;
        for (int i = 0; i < comuni.size(); i++) {
            if (Objects.equals(nodo.getIdComuneAssociato(), comuni.get(i).getId())) {
                flagComuni = true;
            }
        }
        return flagComuni;
    }

}
