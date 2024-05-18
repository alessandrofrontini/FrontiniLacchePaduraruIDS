package com.camerino.ids.fps.client.controllers.immagini;

import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.client.Utils;
import com.camerino.ids.fps.client.controllers.login.Controller_SezioneLogin;
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

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;

public class Controller_SezioneInserimentoImmagini implements Initializable
{
    //region Elementi FXML
    @FXML
    TableView<ClsNodoVisual> ElencoNodi;

    @FXML
    TableColumn<ClsNodoVisual,String> idColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> idComuneColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> nomeColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> posizioneColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> tipologiaColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> aTempoColonna;

    @FXML
    ComboBox sceltaNodo;

    @FXML
    TextField urlImmagine;
    //endregion

    ArrayList<ClsNodo> nodi;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        nodi = new ArrayList<ClsNodo>();

        //region Creazione nodi dummy
        ClsNodo nodo1 = new ClsNodo();
        nodo1.setId("2");
        nodo1.setIdComune("1");
        nodo1.setaTempo(true);
        nodo1.setTipologiaNodo(COMMERCIALE);
        nodo1.setUsernameCreatore("");
        nodo1.setDescrizione("Descrizione - Nodo 1");
        nodo1.setNome("Negozio");
        nodo1.setPosizione(new Posizione(104,104));
        nodi.add(nodo1);

        ClsNodo nodo2 = new ClsNodo();
        nodo2.setId("4");
        nodo2.setIdComune("3");
        nodo2.setaTempo(false);
        nodo2.setTipologiaNodo(CULTURALE);
        nodo2.setUsernameCreatore("");
        nodo2.setDescrizione("Descrizione - Nodo 2");
        nodo2.setNome("Statua");
        nodo2.setPosizione(new Posizione(114,114));
        nodi.add(nodo2);


        ClsNodo nodo3 = new ClsNodo();
        nodo3.setId("6");
        nodo3.setIdComune("5");
        nodo3.setaTempo(false);
        nodo3.setTipologiaNodo(CULINARIO);
        nodo3.setUsernameCreatore("");
        nodo3.setDescrizione("Descrizione - Nodo 3");
        nodo3.setNome("Ristorante");
        nodo3.setPosizione(new Posizione(124,124));
        nodi.add(nodo3);
        //endregion

        setNodi(nodi);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<nodi.size();i++)
        {
            items.add(nodi.get(i).getId());
        }

        this.sceltaNodo.setItems(items);
        //endregion

        //region setting up colonne tabella
        idColonna.setCellValueFactory(
                new PropertyValueFactory<>("ID"));

        idComuneColonna.setCellValueFactory(
                new PropertyValueFactory<>("IDComuneAssociato"));

        nomeColonna.setCellValueFactory(
                new PropertyValueFactory<>("Nome"));

        posizioneColonna.setCellValueFactory(
                new PropertyValueFactory<>("Posizione"));

        tipologiaColonna.setCellValueFactory(
                new PropertyValueFactory<>("Tipologia"));

        aTempoColonna.setCellValueFactory(
                new PropertyValueFactory<>("ATempo"));
        //endregion
    }

    public void inserisciImmagine(MouseEvent mouseEvent)
    {
        String IDNodoAssociatoImmagine = u.getValueFromCombobox(this.sceltaNodo);
        ClsImmagine i = new ClsImmagine();

        if((!Objects.equals(u.getValueFromCombobox(this.sceltaNodo), null)) && (!Objects.equals(u.getValueFromTextField(urlImmagine), "")) && this.controllaConformitaID(IDNodoAssociatoImmagine))
        {
            i.setId("");
            i.setUsernameCreatore(Controller_SezioneLogin.utente.getUsername());
            i.setIdCOntenutoAssociato(u.getValueFromCombobox(this.sceltaNodo));
            i.setURL(u.getValueFromTextField(urlImmagine));
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("Corretto");
            alert.setContentText(i.visualizzaImmagine());
            alert.show();
        }
        else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("SELEZIONA UN NODO");
            alert.setContentText("--SELEZIONA UN NODO--");
            alert.show();
        }
    }

    private void setNodi (ArrayList<ClsNodo> nodi)
    {
        for(int i = 0; i<nodi.size();i++)
        {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            ElencoNodi.getItems().add(c);
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

    private boolean controllaConformitaID (String id)
    {
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
        }

    }



}
