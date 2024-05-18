package com.camerino.ids.fps.client.controllers.itinerari;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.client.Utils;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;

public class Controller_SezioneEliminazioneItinerari implements Initializable
{
    //region Elementi FXML
    @FXML
    TableView<ClsItinerarioVisual> sezioneEliminazioneItinerariTableView;

    @FXML
    TableColumn<ClsItinerarioVisual,String> sezioneEliminazioneItinerariTableColumnID = new TableColumn<>("ID");
    @FXML
    TableColumn <ClsItinerarioVisual,String> sezioneEliminazioneItinerariTableColumnNome = new TableColumn<>("Nome");
    @FXML
    TableColumn <ClsItinerarioVisual,String> sezioneInserimentoItinerariTableColumnOrdinato = new TableColumn<>("E' ordinato?");
    @FXML
    TableColumn <ClsItinerarioVisual,String> sezioneInserimentoItinerariTableColumnTappe = new TableColumn<>("Tappe");

    @FXML
    ComboBox sezioneEliminazioneItinerariComboBoxSceltaItinerarioID;

    @FXML
    Button sezioneEliminazioneItinerariButtonConferma;
    //endregion

    ArrayList<ClsItinerario> itinerari;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        itinerari = new ArrayList<ClsItinerario>();
        ArrayList<ClsNodo> nodi = new ArrayList<ClsNodo>();

        nodi = new ArrayList<ClsNodo>();

        //region Creazione itinerari dummy
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

        ClsItinerario i1 = new ClsItinerario();
        i1.setId("1");
        i1.setUsernameCreatore("test");
        i1.setOrdinato(true);
        i1.setNome("Itinerario1");
        i1.setTappe(nodi);
        itinerari.add(i1);

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

        ClsItinerario i2 = new ClsItinerario();
        i2.setId("2");
        i2.setUsernameCreatore("test");
        i2.setOrdinato(false);
        i2.setNome("Itinerario2");
        i2.setTappe(nodi);
        itinerari.add(i2);

        ClsNodo nodo4 = new ClsNodo();
        nodo3.setId("8");
        nodo3.setIdComune("3");
        nodo3.setaTempo(false);
        nodo3.setTipologiaNodo(CULINARIO);
        nodo3.setUsernameCreatore("");
        nodo3.setDescrizione("Descrizione - Nodo 4");
        nodo3.setNome("Ristorante");
        nodo3.setPosizione(new Posizione(124,124));
        nodi.add(nodo3);

        ClsItinerario i3 = new ClsItinerario();
        i3.setId("3");
        i3.setUsernameCreatore("test");
        i3.setOrdinato(true);
        i3.setNome("Itinerario3");
        i3.setTappe(nodi);
        itinerari.add(i3);
        //endregion

        this.setItinerari(itinerari);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<itinerari.size();i++)
        {
            items.add(itinerari.get(i).getId());
        }

        this.sezioneEliminazioneItinerariComboBoxSceltaItinerarioID.setItems(items);
        //endregion

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

    }

    public void eliminaNodo(MouseEvent mouseEvent)
    {
        String IDDaEliminare = u.getValueFromCombobox(sezioneEliminazioneItinerariComboBoxSceltaItinerarioID);

        if(IDDaEliminare != null && controllaConformitaID(IDDaEliminare))
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle(IDDaEliminare);
            alert.setContentText("--"+IDDaEliminare+"--");
            alert.show();
        }
        else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("SELEZIONA UN NODO");
            alert.setContentText("--SELEZIONA UN NODO--");
            alert.show();
        }
    }

    private void setItinerari (ArrayList<ClsItinerario> itinerari)
    {
        for(int i = 0; i<itinerari.size();i++)
        {
            ClsItinerarioVisual c = u.convertFromClsItinerario(itinerari.get(i));
            sezioneEliminazioneItinerariTableView.getItems().add(c);
        }
    }

    private boolean controllaConformitaID (String id)
    {
        boolean flag = false;

        for(int i = 0; i<itinerari.size();i++)
        {
            if(Objects.equals(itinerari.get(i).getId(), id))
            {
                flag = true;
            }
        }
        return flag;
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

}
