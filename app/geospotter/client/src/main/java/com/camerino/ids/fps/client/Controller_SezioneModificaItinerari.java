package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
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

public class Controller_SezioneModificaItinerari implements Initializable
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
    TextField sezioneInserimentoItinerariElencoTappe,sezioneInserimentoItinerariNomeItinerario;

    @FXML
    CheckBox sezioneInserimentoItinerariCheckBoxOrdinato;

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
    //endregion

    Utils u = new Utils();
    ArrayList<ClsNodo> nodi;
    ArrayList<ClsItinerario> itinerari;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        nodi = new ArrayList<ClsNodo>();

        itinerari = new ArrayList<ClsItinerario>();

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

        //region Creazione itinerari dummy
        ClsNodo nod1 = new ClsNodo();
        nod1.setId("2");
        nod1.setIdComune("1");
        nod1.setaTempo(true);
        nod1.setTipologiaNodo(COMMERCIALE);
        nod1.setUsernameCreatore("");
        nod1.setDescrizione("Descrizione - Nodo 1");
        nod1.setNome("Negozio");
        nod1.setPosizione(new Posizione(104,104));
        nodi.add(nodo1);

        ClsNodo nod2 = new ClsNodo();
        nod2.setId("4");
        nod2.setIdComune("3");
        nod2.setaTempo(false);
        nod2.setTipologiaNodo(CULTURALE);
        nod2.setUsernameCreatore("");
        nod2.setDescrizione("Descrizione - Nodo 2");
        nod2.setNome("Statua");
        nod2.setPosizione(new Posizione(114,114));
        nodi.add(nodo2);

        ClsItinerario i1 = new ClsItinerario();
        i1.setId("1");
        i1.setUsernameCreatore("test");
        i1.setOrdinato(true);
        i1.setNome("Itinerario1");
        i1.setTappe(nodi);
        itinerari.add(i1);

        ClsNodo nod3 = new ClsNodo();
        nod3.setId("6");
        nod3.setIdComune("5");
        nod3.setaTempo(false);
        nod3.setTipologiaNodo(CULINARIO);
        nod3.setUsernameCreatore("");
        nod3.setDescrizione("Descrizione - Nodo 3");
        nod3.setNome("Ristorante");
        nod3.setPosizione(new Posizione(124,124));
        nodi.add(nodo3);

        ClsItinerario i2 = new ClsItinerario();
        i2.setId("2");
        i2.setUsernameCreatore("test");
        i2.setOrdinato(false);
        i2.setNome("Itinerario2");
        i2.setTappe(nodi);
        itinerari.add(i2);

        ClsNodo nod4 = new ClsNodo();
        nod3.setId("8");
        nod3.setIdComune("3");
        nod3.setaTempo(false);
        nod3.setTipologiaNodo(CULINARIO);
        nod3.setUsernameCreatore("");
        nod3.setDescrizione("Descrizione - Nodo 4");
        nod3.setNome("Ristorante");
        nod3.setPosizione(new Posizione(124,124));
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

        //region setting up colonne tabella nodi
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

        //region setting up colonne tabella itinerari
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

        this.sezioneEliminazioneItinerariComboBoxSceltaItinerarioID.setItems(items);
        //endregion
    }

    public void modificaItinerario(MouseEvent mouseEvent)
    {
        ClsItinerario nuovoItinerario = this.inserisciItinerario(mouseEvent);
        String id = u.getValueFromCombobox(sezioneEliminazioneItinerariComboBoxSceltaItinerarioID);

        if(id == null || (
                        u.getValueFromTextField(this.sezioneInserimentoItinerariNomeItinerario) == null ||
                        u.getValueFromTextField(this.sezioneInserimentoItinerariElencoTappe) == null))
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Attenzione");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("FATTO");
            alert.setContentText("ID: " + id + "\n\n NuovoNodo:" + nuovoItinerario.visualizzaItinerario());
            alert.show();
        }
    }

    public ClsItinerario inserisciItinerario(MouseEvent mouseEvent)
    {
        ClsItinerario itinerario = new ClsItinerario();
        String nodiCoinvolti = u.getValueFromTextField(sezioneInserimentoItinerariElencoTappe);
        String[] nodiCoinvoltiInArray = this.convertiNodiCoinvoltiInArray(nodiCoinvolti);

        ArrayList<ClsNodo> nodiAssociatiToItinerario = new ArrayList<ClsNodo>();

        if(nodiCoinvoltiInArray.length>0)
        {
            for(int i = 0; i<nodi.size();i++)
            {
                for(int j = 0; j<nodiCoinvoltiInArray.length;j++)
                {
                    if(Objects.equals(nodi.get(i).getId(), nodiCoinvoltiInArray[j]))
                    {
                        nodiAssociatiToItinerario.add(nodi.get(i));
                    }
                }
            }
        }

        itinerario.setId("");
        itinerario.setOrdinato(u.getValueFromCheckBox(sezioneInserimentoItinerariCheckBoxOrdinato));
        itinerario.setNome(u.getValueFromTextField(sezioneInserimentoItinerariNomeItinerario));
        itinerario.setTappe(nodiAssociatiToItinerario);
        itinerario.setUsernameCreatore("");

        return itinerario;
    }

    private void setNodi (ArrayList<ClsNodo> nodi)
    {
        for(int i = 0; i<nodi.size();i++)
        {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            sezioneEliminazioneNodiTableView.getItems().add(c);
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

    private String[] convertiNodiCoinvoltiInArray(String input)
    {
        String[] tmp = input.split("-");
        return tmp;
    }

    public void vediInformazoniAttualiItinerario(MouseEvent mouseEvent)
    {
        String idItinerarioDaVisualizzare = u.getValueFromCombobox(sezioneEliminazioneItinerariComboBoxSceltaItinerarioID);

        ClsItinerarioVisual c = new ClsItinerarioVisual();
        for(int i=0;i<itinerari.size();i++)
        {
            if(Objects.equals(itinerari.get(i).getId(), idItinerarioDaVisualizzare))
            {
                c = u.convertFromClsItinerario(itinerari.get(i));
                this.sezioneInserimentoItinerariElencoTappe.setText(c.getTappe());
                this.sezioneInserimentoItinerariNomeItinerario.setText(c.getNome());
                this.sezioneInserimentoItinerariCheckBoxOrdinato.setSelected(itinerari.get(i).isOrdinato());
            }
        }
    }


}
