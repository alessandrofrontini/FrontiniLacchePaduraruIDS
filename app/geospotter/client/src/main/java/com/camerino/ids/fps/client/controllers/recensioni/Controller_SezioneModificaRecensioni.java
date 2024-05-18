package com.camerino.ids.fps.client.controllers.recensioni;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.contenuti.ClsRecensione;
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

public class Controller_SezioneModificaRecensioni implements Initializable
{

    //region Elementi FXML
    @FXML
    TableView<ClsRecensione> sezioneEliminazioneRecensioniTableView;

    @FXML
    TableColumn<ClsRecensione,String> sezioneEliminazioneRecensioniTableColumnID;

    @FXML
    TableColumn<ClsRecensione,String> sezioneEliminazioneRecensioniTableColumnIDContenutoAssociato;

    @FXML
    TableColumn<ClsRecensione,String> sezioneEliminazioneRecensioniTableColumnOggetto;

    @FXML
    TableColumn<ClsRecensione, Double> sezioneEliminazioneRecensioniTableColumnValutazione;

    @FXML
    TableColumn<ClsRecensione, String> getSezioneEliminazioneRecensioniTableColumnContenuto;

    @FXML
    ComboBox sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID;

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
    ComboBox sezioneEliminazioneNodiComboBoxSceltaNodoID;

    @FXML
    TextField oggetto, contenuto, valutazione;
    //endregion

    ArrayList<ClsRecensione> recensioni;
    ArrayList<ClsNodo> nodi;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        recensioni = new ArrayList<ClsRecensione>();

        //region Creazione nodi dummy
        ClsRecensione r1 = new ClsRecensione();
        r1.setId("1");
        r1.setUsernameCreatore("test");
        r1.setOggetto("Oggetto1");
        r1.setContenuto("Contenuto1");
        r1.setValutazione(1);
        r1.setIdContenutoAssociato("ContenutoAssociato1");
        recensioni.add(r1);

        ClsRecensione r2 = new ClsRecensione();
        r2.setId("2");
        r2.setUsernameCreatore("test");
        r2.setOggetto("Oggetto2");
        r2.setContenuto("Contenuto2");
        r2.setValutazione(2);
        r2.setIdContenutoAssociato("ContenutoAssociato2");
        recensioni.add(r2);

        ClsRecensione r3 = new ClsRecensione();
        r3.setId("3");
        r3.setUsernameCreatore("test");
        r3.setOggetto("Oggetto3");
        r3.setContenuto("Contenuto3");
        r3.setValutazione(3);
        r3.setIdContenutoAssociato("ContenutoAssociato3");
        recensioni.add(r3);
        //endregion

        setRecensioni(recensioni);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<recensioni.size();i++)
        {
            items.add(recensioni.get(i).getId());
        }

        this.sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID.setItems(items);
        //endregion

        //region setting up colonne tabella
        sezioneEliminazioneRecensioniTableColumnID.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        sezioneEliminazioneRecensioniTableColumnIDContenutoAssociato.setCellValueFactory(
                new PropertyValueFactory<>("idContenutoAssociato"));

        sezioneEliminazioneRecensioniTableColumnOggetto.setCellValueFactory(
                new PropertyValueFactory<>("oggetto"));

        sezioneEliminazioneRecensioniTableColumnValutazione.setCellValueFactory(
                new PropertyValueFactory<>("valutazione"));

        getSezioneEliminazioneRecensioniTableColumnContenuto.setCellValueFactory(
                new PropertyValueFactory<>("contenuto"));

        //endregion

        //--

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
        ObservableList<String> itemes = FXCollections.observableArrayList();

        for(int i = 0;i<nodi.size();i++)
        {
            itemes.add(nodi.get(i).getId());
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

    public void modificaRecensione(MouseEvent mouseEvent)
    {
        ClsRecensione r = new ClsRecensione();

        String id = u.getValueFromCombobox(this.sezioneEliminazioneNodiComboBoxSceltaNodoID);
        String oggetto = u.getValueFromTextField(this.oggetto);
        String contenuto = u.getValueFromTextField(this.contenuto);
        String valutazione = u.getValueFromTextField(this.valutazione);

        if(id != null && !id.isEmpty() && oggetto != null && !oggetto.isEmpty() && contenuto != null && !contenuto.isEmpty() && valutazione != null && !valutazione.isEmpty() && u.getValueFromCombobox(sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID) != null && !Objects.equals(u.getValueFromCombobox(sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID), ""))
        {

            r.setId("");//??
            r.setUsernameCreatore(Controller_SezioneLogin.utente.getUsername());
            r.setIdContenutoAssociato(id);
            r.setOggetto(oggetto);
            r.setContenuto(contenuto);
            r.setValutazione(Double.parseDouble(valutazione));


            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText(r.visualizzaRecensione());
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Inserisci tutte le informazioni necessarie");
            alert.show();
        }
    }

    private void setRecensioni (ArrayList<ClsRecensione> recensioni)
    {
        for(int i = 0; i<recensioni.size();i++)
        {
            sezioneEliminazioneRecensioniTableView.getItems().add(recensioni.get(i));
        }
    }

    private void setNodi (ArrayList<ClsNodo> nodi)
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

    public void visualizzaInformazioniAttualiRecensione (MouseEvent mouseEvent)
    {
        String tmp = u.getValueFromCombobox(this.sezioneEliminazioneRecensioniComboBoxSceltaRecensioneID);

        if(!(Objects.equals(tmp,null) || tmp.isEmpty()))
        {
            for(int i = 0; i<recensioni.size();i++)
            {
                if(recensioni.get(i).getId() == tmp)
                {
                    this.sezioneEliminazioneNodiComboBoxSceltaNodoID.setValue(recensioni.get(i).getId());
                    this.oggetto.setText(recensioni.get(i).getOggetto());
                    this.contenuto.setText(recensioni.get(i).getContenuto());
                    this.valutazione.setText(recensioni.get(i).getValutazione()+"");
                }
            }
        }
    }


}
