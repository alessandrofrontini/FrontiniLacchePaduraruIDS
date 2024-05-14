package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utils.Posizione;
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
import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.CULINARIO;

public class Controller_SezioneVisualizzazioneItinerari implements Initializable
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
    ComboBox selezionaElementoDettaglio, selezionaElementoSegnalazione;

    @FXML
    TextField descrizioneTF;
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

        for(int i = 0;i<itinerari.size();i++)
        {
            items.add(itinerari.get(i).getId());
        }

        this.selezionaElementoDettaglio.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> itemss = FXCollections.observableArrayList();

        for(int i = 0;i<itinerari.size();i++)
        {
            itemss.add(itinerari.get(i).getId());
        }

        this.selezionaElementoSegnalazione.setItems(itemss);
        //endregion

    }

    public void visualizzaDettaglio(MouseEvent mouseEvent)
    {
        String IDDaVisualizzare = u.getValueFromCombobox(selezionaElementoDettaglio);

        if(IDDaVisualizzare != null && !IDDaVisualizzare.equals("") && this.controllaConformitaID(IDDaVisualizzare))
        {
            ClsItinerario itinerario = new ClsItinerario();
            for(int i = 0; i<itinerari.size();i++)
            {
                if(IDDaVisualizzare.equals(this.itinerari.get(i).getId()))
                {
                    itinerario.setId(itinerari.get(i).getId());
                    itinerario.setTappe(itinerari.get(i).getTappe());
                    itinerario.setNome(itinerari.get(i).getNome());
                    itinerario.setOrdinato(itinerari.get(i).isOrdinato());
                }
            }
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setContentText(itinerario.visualizzaItinerario());
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }
    }

    public void inserisciSegnalazione(MouseEvent mouseEvent)
    {
        String descrizioneSegnalazione = u.getValueFromTextField(descrizioneTF);

        if(!Objects.equals(descrizioneSegnalazione, "") && descrizioneSegnalazione != null && u.getValueFromCombobox(selezionaElementoSegnalazione) != null)
        {
            String IDDaSegnalare = u.getValueFromCombobox(selezionaElementoSegnalazione);
            ClsSegnalazione segnalazione = new ClsSegnalazione();
            segnalazione.setId(""); //
            segnalazione.setDescrizione(descrizioneSegnalazione);
            segnalazione.setIdContenuto(IDDaSegnalare);

            for(int i = 0; i < this.itinerari.size(); i++)
            {
                if(Objects.equals(itinerari.get(i).getId(), IDDaSegnalare))
                {
                    segnalazione.setIdCuratore(itinerari.get(i).getTappe().get(0).getIdComune()); //todo: ottenere da spring
                }
            }
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setContentText(segnalazione.visualizzaSegnalazione());
            alert.show();


        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
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

    public void navigateToSezioneVisualizzazioneMappa(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazioneMappa.fxml",mouseEvent);
    }

    private boolean controllaConformitaID (String id)
    {
        boolean flag = false;

        for(int i = 0; i<itinerari.size();i++)
        {
            if(itinerari.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;
    }


}
