package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsContenuto;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsNodoVisual;
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

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;

public class Controller_SezioneInserimentoItinerari implements Initializable
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
    //endregion

    ArrayList<ClsNodo> nodi;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        nodi = Controller_SezioneLogin.UTENTE.getAllNodi();

        setNodi(nodi);

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

    public void inserisciItinerario(MouseEvent mouseEvent)
    {
        ClsItinerario itinerario = new ClsItinerario();
        String nodiCoinvolti = u.getValueFromTextField(sezioneInserimentoItinerariElencoTappe);
        String[] nodiCoinvoltiInArray = this.convertiNodiCoinvoltiInArray(nodiCoinvolti);

        ArrayList<ClsNodo> nodiAssociatiToItinerario = new ArrayList<>();

        if(nodiCoinvoltiInArray.length>1 && !Objects.equals(u.getValueFromTextField(sezioneInserimentoItinerariNomeItinerario), ""))
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
            itinerario.setId("0");
            itinerario.setUsernameCreatore(Controller_SezioneLogin.utente.getUsername());
            itinerario.setOrdinato(u.getValueFromCheckBox(sezioneInserimentoItinerariCheckBoxOrdinato));
            itinerario.setNome(u.getValueFromTextField(sezioneInserimentoItinerariNomeItinerario));
            itinerario.setTappe(nodiAssociatiToItinerario);

            ((ClsContributor)Controller_SezioneLogin.UTENTE).inserisciItinerario(itinerario);

            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("informazioni");
            alert.setContentText(itinerario.visualizzaItinerario());
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Ricontrolla le informazioni");
            alert.show();
        }




        //Ottineni array dagli id in nodiCoinvoltiInArray

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

    public void navigateToSezioneVisualizzazione(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazione.fxml",mouseEvent);
    }

    private String[] convertiNodiCoinvoltiInArray(String input)
    {
        String[] tmp = input.split("-");
        String[] nuova = this.pulisciIDnonPresenti(new ArrayList<>(Arrays.asList(tmp)), nodi);

        return nuova;
    }

    private String[] pulisciIDnonPresenti (ArrayList<String> input, ArrayList<ClsNodo> nodi)
    {
        // Create a HashSet from the string values of objects in listA for faster lookup
        HashSet<String> setAValues = new HashSet<>();
        for (ClsNodo obj : nodi) {
            setAValues.add(obj.getId());
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

    private ArrayList<String> rimuoviDuplicati(ArrayList<String> listaOriginale) {
        // Creiamo un HashSet che conterrà solo elementi unici
        HashSet<String> setUnici = new HashSet<>(listaOriginale);

        // Ricostruiamo l'ArrayList senza duplicati
        ArrayList<String> listaSenzaDuplicati = new ArrayList<>(setUnici);

        return listaSenzaDuplicati;
    }
}
