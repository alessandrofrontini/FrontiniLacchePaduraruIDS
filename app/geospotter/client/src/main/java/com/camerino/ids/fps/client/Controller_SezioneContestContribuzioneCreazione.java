package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsComuneVisual;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller_SezioneContestContribuzioneCreazione implements Initializable {
    //region Elementi FXML
    @FXML
    TableView<ClsComuneVisual> elencoComuni;

    @FXML
    TableColumn<ClsComuneVisual, String> id;

    @FXML
    TableColumn<ClsComuneVisual, String> nome;

    @FXML
    TableColumn<ClsComuneVisual, String> posizione;

    @FXML
    TableColumn<ClsComuneVisual, String> abitanti;

    @FXML
    TableColumn<ClsComuneVisual, String> superficie;

    @FXML
    TableColumn<ClsComuneVisual, String> curatori;

    @FXML
    ComboBox sceltaComune;

    @FXML
    TextField dataFine;

    @FXML
    CheckBox suInvito;

    @FXML
    Tab tabPartecipanti;

    @FXML
    TextField invitatiContestTF;
    //endregion

    boolean flag = false;
    Utils u = new Utils();
    List<ClsComune> comuni;
    List<ClsTuristaAutenticato> utenti;

    List<ClsContributor> contributors;

    List<ClsContributorAutorizzato> contributorAutorizzatos;
    List<ClsCuratore> Curatori = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.comuni = Controller_SezioneLogin.UTENTE.getAllComuni();

        setComuni(comuni);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < comuni.size(); i++) {
            items.add(comuni.get(i).getId().toString());
        }

        this.sceltaComune.setItems(items);
        //endregion

        //region setting up colonne tabella
        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        nome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        posizione.setCellValueFactory(
                new PropertyValueFactory<>("posizione"));

        abitanti.setCellValueFactory(
                new PropertyValueFactory<>("abitanti"));

        superficie.setCellValueFactory(
                new PropertyValueFactory<>("superficie"));

        curatori.setCellValueFactory(
                new PropertyValueFactory<>("curatori"));
        //endregion
    }

    public void inserisciContest(MouseEvent mouseEvent) {
        ClsContestDiContribuzione contest = new ClsContestDiContribuzione();
        ClsComune comune = new ClsComune();

        Date data = this.parseStringToDate(u.getValueFromTextField(dataFine));

        if (data != null && u.getValueFromCombobox(this.sceltaComune) != null) {
            contest.setDurata(data);
            comune = new ClsComune();
            comune.setId(Long.valueOf(u.getValueFromCombobox(this.sceltaComune)));
            contest.setLocation(comune);


            ((ClsAnimatore)Controller_SezioneLogin.UTENTE).inserisciContest(contest);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText(contest.visualizzaContest());
            alert.show();


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Ricontrolla le informazioni e riprova");
            alert.show();
        }


    }

    private void setComuni(List<ClsComune> comuni) {
        for (int i = 0; i < comuni.size(); i++) {
            ClsComuneVisual c = u.convertFromClsComune(comuni.get(i));

            elencoComuni.getItems().add(c);
        }
    }


    private boolean controllaConformitaID(Long id) {
        /*boolean flag = false;

        for(int i = 0; i<comuni.size();i++)
        {
            if(comuni.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
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

    public void visualizzaTabInvitati(MouseEvent mouseEvent) {
        this.tabPartecipanti.setDisable(flag);
        this.flag = !flag;

    }


    private String[] convertiUtentiCoinvoltiInArray(String input) {
        String[] tmp = input.split("-");
        String[] nuova = this.pulisciIDnonPresenti(new ArrayList<>(Arrays.asList(tmp)), utenti);

        return nuova;
    }

    private String[] pulisciIDnonPresenti(List<String> input, List<ClsTuristaAutenticato> utenti) {
        // Create a HashSet from the string values of objects in listA for faster lookup
        HashSet<String> setAValues = new HashSet<>();
        for (ClsTuristaAutenticato obj : utenti) {
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

    private Date parseStringToDate(String dateString) {
        // Rimuovi tutti i caratteri non numerici dalla stringa
        String numericString = dateString.replaceAll("[^0-9]", "");

        // Definisci il formato della data
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

        try {
            // Parsa la stringa in una data
            Date date = dateFormat.parse(numericString);
            return date;
        } catch (ParseException e) {
            // Se si verifica un'eccezione di parsing, restituisci null
            return null;
        }

    }


}
