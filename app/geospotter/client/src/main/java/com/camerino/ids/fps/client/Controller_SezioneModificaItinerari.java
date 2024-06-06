package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsItinerarioVisual;
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
import java.util.*;

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
    List<ClsNodo> nodi;
    List<ClsItinerario> itinerari;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        nodi = Controller_SezioneLogin.UTENTE.getAllNodi();
        itinerari = Controller_SezioneLogin.UTENTE.getAllItinerari();
        this.setItinerari(itinerari);
        this.setNodi(nodi);
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
            items.add(itinerari.get(i).getId().toString());
        }

        this.sezioneEliminazioneItinerariComboBoxSceltaItinerarioID.setItems(items);
        //endregion
    }

    public void modificaItinerario(MouseEvent mouseEvent)
    {
        ClsItinerario nuovoItinerario = this.inserisciItinerario(mouseEvent);
        Long IDDaModificare = Long.valueOf(u.getValueFromCombobox(sezioneEliminazioneItinerariComboBoxSceltaItinerarioID));

        if(nuovoItinerario != null && this.controllaConformitaIDItinerario(IDDaModificare) && nuovoItinerario.getTappe().size()>=2)
        {
            nuovoItinerario.setId(IDDaModificare);
            ((ClsContributor)Controller_SezioneLogin.UTENTE).modificaItinerario(nuovoItinerario, IDDaModificare);
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("FATTO");
            alert.setContentText("ID: " + IDDaModificare + "\n\n NuovoNodo:" + nuovoItinerario.visualizzaItinerario());
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

    public ClsItinerario inserisciItinerario(MouseEvent mouseEvent)
    {
        ClsItinerario itinerario = new ClsItinerario();
        String nodiCoinvolti = u.getValueFromTextField(sezioneInserimentoItinerariElencoTappe);
        String[] nodiCoinvoltiInArray = this.convertiNodiCoinvoltiInArray(nodiCoinvolti);

        List<ClsNodo> nodiAssociatiToItinerario = new ArrayList<ClsNodo>();

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

            if((u.getValueFromTextField(sezioneInserimentoItinerariNomeItinerario) != null || !Objects.equals(u.getValueFromTextField(sezioneInserimentoItinerariNomeItinerario), "")) && nodiAssociatiToItinerario.size() >= 2)
            {
                itinerario.setId(0L);
                itinerario.setIdCreatore(0L);
                itinerario.setOrdinato(u.getValueFromCheckBox(sezioneInserimentoItinerariCheckBoxOrdinato));
                itinerario.setNome(u.getValueFromTextField(sezioneInserimentoItinerariNomeItinerario));
                itinerario.setTappe(nodiAssociatiToItinerario);

                return itinerario;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    private void setNodi (List<ClsNodo> nodi)
    {
        for(int i = 0; i<nodi.size();i++)
        {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            sezioneEliminazioneNodiTableView.getItems().add(c);
        }
    }

    private void setItinerari (List<ClsItinerario> itinerari)
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

    public void vediInformazoniAttualiItinerario(MouseEvent mouseEvent)
    {
        Long idItinerarioDaVisualizzare = Long.valueOf(u.getValueFromCombobox(sezioneEliminazioneItinerariComboBoxSceltaItinerarioID));

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

    private boolean controllaConformitaIDItinerario (Long id)
    {/*
        boolean flag = false;

        for(int i = 0; i<itinerari.size();i++)
        {
            if(Objects.equals(itinerari.get(i).getId(), id))
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
    }

    private String[] convertiNodiCoinvoltiInArray(String input)
    {
        String[] tmp = input.split("-");
        String[] nuova = this.pulisciIDnonPresenti(new ArrayList<>(Arrays.asList(tmp)), nodi);;
        return nuova;
    }

    private String[] pulisciIDnonPresenti (List<String> input, List<ClsNodo> nodi)
    {
        // Create a HashSet from the string values of objects in listA for faster lookup
        HashSet<String> setAValues = new HashSet<>();
        for (ClsNodo obj : nodi) {
            setAValues.add(obj.getId().toString());
        }

        // Use an Iterator to safely remove elements from listB
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            // If element is not present in setAValues, remove it from listB
            if (!setAValues.contains(element)) {
                iterator.remove();
            }
        }

        input = this.rimuoviDuplicati(input);

        return input.toArray(new String[input.size()]);
    }

    private List<String> rimuoviDuplicati(List<String> listaOriginale) {
        // Creiamo un HashSet che conterrà solo elementi unici
        HashSet<String> setUnici = new HashSet<>(listaOriginale);

        // Ricostruiamo l'ArrayList senza duplicati
        List<String> listaSenzaDuplicati = new ArrayList<>(setUnici);

        return listaSenzaDuplicati;
    }

}
