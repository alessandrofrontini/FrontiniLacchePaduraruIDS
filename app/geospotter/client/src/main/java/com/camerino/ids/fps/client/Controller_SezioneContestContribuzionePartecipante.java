package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsContestDiContribuzioneVisual;
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

public class Controller_SezioneContestContribuzionePartecipante implements Initializable {
    //region Elementi FXML
    @FXML
    TextField sezioneInserimentoNodiTextFieldNomeDelNodo, sezioneInserimentoNodiTextFieldComuneAssociato, sezioneInserimentoNodiTextFieldCoordinataX, sezioneInserimentoNodiTextFieldCoordinataY, sezioneInserimentoNodiTextFieldDescrizioneDelNodo, sezioneInserimentoNodiTextFieldDataInizio, sezioneInserimentoNodiTextFieldDataFine;

    @FXML
    CheckBox sezioneInserimentoNodiCheckBoxTemporizzato;

    @FXML
    ComboBox sezioneInserimentoNodiComboBoxTipologiaNodo, sceltaContest;


    @FXML
    TableView<ClsContestDiContribuzioneVisual> elencoContest;

    @FXML
    TableColumn<ClsContestDiContribuzioneVisual, String> idColonna = new TableColumn<>("ID");
    @FXML
    TableColumn<ClsContestDiContribuzioneVisual, String> creatoreColonna = new TableColumn<>("CREATORE");
    @FXML
    TableColumn<ClsContestDiContribuzioneVisual, String> durataColonna = new TableColumn<>("DURATA");
    @FXML
    TableColumn<ClsContestDiContribuzioneVisual, String> locationColonna = new TableColumn<>("LOCATION");
    @FXML
    TableColumn<ClsContestDiContribuzioneVisual, String> apertoColonna = new TableColumn<>("APERTO");


    @FXML
    ComboBox sceltaNodo;

    @FXML
    TextField urlImmagine;


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
    //endregion

    boolean flag = false;
    List<ClsContestDiContribuzione> contests;
    List<ClsNodo> nodi;
    List<ClsNodo> nodiParsati;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contests = ((ClsContributor)Controller_SezioneLogin.UTENTE).getAllContest();
        nodi = Controller_SezioneLogin.UTENTE.getAllNodi();

        this.sezioneInserimentoNodiTextFieldDataInizio.setVisible(flag);
        this.sezioneInserimentoNodiTextFieldDataFine.setVisible(flag);

        setContest(contests);
        setNodi(nodi);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < contests.size(); i++) {
            items.add(contests.get(i).getId().toString());
        }

        this.sceltaContest.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> itemes = FXCollections.observableArrayList();

        for (int i = 0; i < nodi.size(); i++) {
            itemes.add(nodi.get(i).getId().toString());
        }

        this.sceltaNodo.setItems(itemes);
        //endregion

        //region setting up colonne tabella
        idColonna.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        creatoreColonna.setCellValueFactory(
                new PropertyValueFactory<>("idCreatore"));

        durataColonna.setCellValueFactory(
                new PropertyValueFactory<>("durata"));

        locationColonna.setCellValueFactory(
                new PropertyValueFactory<>("locationComune"));

        apertoColonna.setCellValueFactory(
                new PropertyValueFactory<>("isAperto"));
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

    public void inserisciNodo()
    {

        if(!Objects.equals(u.getValueFromCombobox(sceltaContest), "") && u.getValueFromCombobox(sceltaContest) != null)
        {
            ClsNodo nodo = new ClsNodo();
            Posizione posizione = new Posizione();
            Long IDContest = Long.valueOf(this.partecipaContest(null));
            ClsContestDiContribuzione contest = this.contests.stream().filter(c->c.getId().equals(IDContest)).findFirst().get();

            try {

                if (!(Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataX)) == Double.NaN || Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataY)) == Double.NaN)) {
                    posizione.setX(Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataX)));
                    posizione.setY(Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataY)));
                    nodo.setPosizione(posizione);

                    if (u.getValueFromCombobox(sezioneInserimentoNodiComboBoxTipologiaNodo) != null ||
                            Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo), "") ||
                            Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato), "") ||
                            Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo), "") ||
                            Objects.equals(u.getValueFromCombobox(sceltaContest), "") ||
                            Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo), null) ||
                            Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato), null) ||
                            Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo), null) ||
                            Objects.equals(u.getValueFromCombobox(sceltaContest), null)) {
                        nodo.setIdCreatore(1L);
                        //nodo.setIdComuneAssociato(contest.getLocation().getId());
                        nodo.seteTologiaNodoFormatoStringa(u.getValueFromCombobox(sezioneInserimentoNodiComboBoxTipologiaNodo));
                        nodo.setNome(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo));
                        nodo.setDescrizione(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo));
                        nodo.setaTempo(u.getValueFromCheckBox(sezioneInserimentoNodiCheckBoxTemporizzato));
                        nodo.setDataInizio(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDataInizio));
                        nodo.setDataFine(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDataFine));
                        nodo.setIdComuneAssociato(IDContest);

                        if (!u.checkInfoNodo(nodo)) {
                            u.alertError();
                        } else {
                            ((ClsContributor)Controller_SezioneLogin.UTENTE).inserisciNodo(nodo, contest);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Info Nodo");
                            alert.setContentText(nodo.visualizzaNodo());
                            alert.show();
                        }
                    } else {
                        u.alertError();
                    }


                } else {
                    u.alertError();
                }

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Controlla le informazioni");
                alert.show();
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Controlla le informazioni");
                alert.show();
            }
        }
        else {
            u.alertError();
        }


    }


    public void visualizzaInformazioniNodoTemporizzato() {
        flag = !flag;
        this.sezioneInserimentoNodiTextFieldDataInizio.setVisible(flag);
        this.sezioneInserimentoNodiTextFieldDataFine.setVisible(flag);

    }

    public String partecipaContest(MouseEvent mouseEvent) {
        Long IDDaEliminare = Long.valueOf(u.getValueFromCombobox(sceltaContest));

        if (controllaConformitaID(IDDaEliminare)) {
            return IDDaEliminare.toString();
        } else {
            return "";
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
        /*
        boolean flag = false;

        for(int i = 0; i<contests.size();i++)
        {
            if(contests.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
    }

    private boolean controllaConformitaIDNodi(Long id) {/*
        if(id==null)
            return false;
        else{
            boolean flag = false;

            for(int i = 0; i<nodi.size();i++)
            {
                if(Objects.equals(nodi.get(i).getId(), id))
                {
                    flag = true;
                }
            }
            return flag;
        }*/
        return true;
    }

    private void setContest(List<ClsContestDiContribuzione> contests) {
        for (int i = 0; i < contests.size(); i++) {
            ClsContestDiContribuzioneVisual c = u.convertFromaClsContestDiContribuzione(contests.get(i));

            elencoContest.getItems().add(c);
        }
    }

    private void setNodi(List<ClsNodo> nodi) {
        for (int i = 0; i < nodi.size(); i++) {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            sezioneEliminazioneNodiTableView.getItems().add(c);
        }
    }




    public void inserisciImmagine(MouseEvent mouseEvent)
    {
        if(!Objects.equals(u.getValueFromCombobox(sceltaContest), "") && u.getValueFromCombobox(sceltaContest) != null && !Objects.equals(u.getValueFromCombobox(this.sceltaNodo), "") && u.getValueFromCombobox(this.sceltaNodo) != null){
            Long IDContest = Long.valueOf(u.getValueFromCombobox(this.sceltaContest));
            Long IDNodoAssociatoImmagine = Long.valueOf(u.getValueFromCombobox(this.sceltaNodo));
            ClsImmagine i = new ClsImmagine();
            ClsContestDiContribuzione contest = this.contests.stream().filter(c->c.getId().equals(IDContest)).findFirst().get();

            if ((!Objects.equals(u.getValueFromCombobox(this.sceltaContest), null)) && (!Objects.equals(u.getValueFromCombobox(this.sceltaNodo), null)) && (!Objects.equals(u.getValueFromTextField(urlImmagine), "")) && this.controllaConformitaIDNodi(IDNodoAssociatoImmagine)) {
            i.setIdCreatore(((ClsTuristaAutenticato)Controller_SezioneLogin.UTENTE).getId());
            i.setIdNodoAssociato(Long.valueOf(u.getValueFromCombobox(this.sceltaNodo)));
            i.setURL(u.getValueFromTextField(urlImmagine));

            ((ClsContributor)Controller_SezioneLogin.UTENTE).inserisciImmagine(i, contest);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Corretto");
            alert.setContentText(i.visualizzaImmagine() + "\n\n IDCONTEST: " + IDContest);
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



}
