package com.camerino.ids.fps.client;

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
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneModificaNodi implements Initializable
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
    Button sezioneInserimentoNodiButtonConferma,sezioneInserimentoNodiButtonHomePage,sezioneModificaNodiButtonConferma;

    //endregion

    boolean flag = false;
    Utils u = new Utils();
    ObservableList<String> itemsComboBox;
    ArrayList<ClsNodo> nodi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.sezioneInserimentoNodiTextFieldDataInizio.setVisible(flag);
        this.sezioneInserimentoNodiTextFieldDataFine.setVisible(flag);

        nodi = Controller_SezioneLogin.UTENTE.getAllNodi();
        //endregion
        setNodi(nodi);

        //region combobox
        //items della combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<nodi.size();i++)
        {
            items.add(nodi.get(i).getId());
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

    public void modificaNodo(MouseEvent mouseEvent)
    {
        ClsNodo nuovoNodo = this.inserisciNodo(mouseEvent);
        String IDDaModificare = this.eliminaNodo(mouseEvent);



        if(!Objects.equals(IDDaModificare, "") && this.controllaConformitaID(IDDaModificare) && nuovoNodo != null)
        {
            nuovoNodo.setId(IDDaModificare);
            ((ClsContributor)Controller_SezioneLogin.UTENTE).modificaNodo(IDDaModificare, nuovoNodo);
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("FATTO");
            alert.setContentText("ID: " + IDDaModificare + "\n\n NuovoNodo:" + nuovoNodo.visualizzaNodo());
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Attenzione");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }

    }

    private String eliminaNodo(MouseEvent mouseEvent)
    {
        String nodoDaEliminare = u.getValueFromCombobox(this.sezioneEliminazioneNodiComboBoxSceltaNodoID);

        if(!Objects.equals(nodoDaEliminare, ""))
        {
            return nodoDaEliminare;
        }
        else
        {
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

                    nodo.setUsernameCreatore(Controller_SezioneLogin.utente.getUsername());
                    nodo.seteTologiaNodoFormatoStringa(u.getValueFromCombobox(sezioneInserimentoNodiComboBoxTipologiaNodo));
                    nodo.setNome(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo));
                    nodo.setIdComune(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato));
                    nodo.setDescrizione(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo));
                    nodo.setaTempo(u.getValueFromCheckBox(sezioneInserimentoNodiCheckBoxTemporizzato));
                    nodo.setDataInizio(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDataInizio));
                    nodo.setDataFine(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDataFine));

                    if (!u.checkInfoNodo(nodo)) {
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

        }
        catch (NumberFormatException e)
        {

        }
        catch (NullPointerException e)
        {

        }
        return null;
    }

    private void setNodi (ArrayList<ClsNodo> nodi)
    {
        for(int i = 0; i<nodi.size();i++)
        {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            sezioneEliminazioneNodiTableView.getItems().add(c);
        }
    }

    public void visualizzaInformazioniAttualiNodo()
    {
        String valore = u.getValueFromCombobox(this.sezioneEliminazioneNodiComboBoxSceltaNodoID);

        if(valore != null)
        {
            for(int i = 0; i<this.nodi.size();i++)
            {
                if(Objects.equals(valore, nodi.get(i).getId()))
                {

                    ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

                    this.sezioneInserimentoNodiTextFieldNomeDelNodo.setText(c.Nome);
                    this.sezioneInserimentoNodiTextFieldComuneAssociato.setText(c.IDComuneAssociato);
                    this.sezioneInserimentoNodiTextFieldDataFine.setText(nodi.get(i).getDataFine());
                    this.sezioneInserimentoNodiTextFieldDataInizio.setText(nodi.get(i).getDataInizio());
                    this.sezioneInserimentoNodiTextFieldCoordinataX.setText(nodi.get(i).getPosizione().getX()+"");
                    this.sezioneInserimentoNodiTextFieldCoordinataY.setText(nodi.get(i).getPosizione().getY()+"");
                    this.sezioneInserimentoNodiTextFieldDescrizioneDelNodo.setText(nodi.get(i).getDescrizione());
                    this.sezioneInserimentoNodiComboBoxTipologiaNodo.setValue(nodi.get(i).getTipologiaNodoFormatoStringa());
                }
            }
        }




    }

    public void visualizzaInformazioniNodoTemporizzato(MouseEvent mouseEvent)
    {
        flag = !flag;
        this.sezioneInserimentoNodiTextFieldDataInizio.setVisible(flag);
        this.sezioneInserimentoNodiTextFieldDataFine.setVisible(flag);
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

    public void navigateToSezioneVisualizzazione(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazione.fxml",mouseEvent);
    }

    private boolean controllaConformitaID (String id)
    {/*
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

}
