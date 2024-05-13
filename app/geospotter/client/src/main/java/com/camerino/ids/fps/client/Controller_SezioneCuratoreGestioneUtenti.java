package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Credenziali;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneCuratoreGestioneUtenti implements Initializable
{

    //region Elementi FXML
    @FXML
    TableView<ClsUtenteVisual> elencoUtenti;

    @FXML
    TableColumn<ClsUtenteVisual, String> idColonna;

    @FXML
    TableColumn<ClsUtenteVisual, String> usernameColonna;

    @FXML
    TableColumn<ClsUtenteVisual, String> punteggioColonna;

    @FXML
    ComboBox selezionaEliminaUtente;
    //endregion

    Utils u = new Utils();
    ArrayList<ClsTuristaAutenticato> utenti;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        utenti = new ArrayList<>();

        //region Creazione utenti dummy
        ClsTuristaAutenticato u1 = new ClsTuristaAutenticato();
        u1.setId("1");
        u1.setPunteggio(49);
        u1.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);
        Credenziali c1 = new Credenziali();
        c1.setUsername("utente1");
        c1.setPassword("password1");
        u1.setCredenziali(c1);
        utenti.add(u1);

        ClsTuristaAutenticato u2 = new ClsTuristaAutenticato();
        u2.setId("2");
        u2.setPunteggio(192);
        u2.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);
        Credenziali c2 = new Credenziali();
        c2.setUsername("utente2");
        c2.setPassword("password2");
        u2.setCredenziali(c2);
        utenti.add(u2);

        ClsTuristaAutenticato u3 = new ClsTuristaAutenticato();
        u3.setId("3");
        u3.setPunteggio(999);
        u3.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE);
        Credenziali c3 = new Credenziali();
        c3.setUsername("utente3");
        c3.setPassword("password3");
        u3.setCredenziali(c3);
        utenti.add(u3);
        //endregion

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<utenti.size();i++)
        {
            items.add(utenti.get(i).getId());
        }

        this.selezionaEliminaUtente.setItems(items);
        //endregion

        setUtenti(utenti);

        //region setting up colonne tabella
        idColonna.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        usernameColonna.setCellValueFactory(
                new PropertyValueFactory<>("username"));

        punteggioColonna.setCellValueFactory(
                new PropertyValueFactory<>("punteggio"));
        //endregion

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

    private void setUtenti (ArrayList<ClsTuristaAutenticato> utenti)
    {
        for(int i = 0; i< utenti.size(); i++)
        {
            ClsUtenteVisual c = u.convertFromClsTuristaAutenticato(utenti.get(i));
            elencoUtenti.getItems().add(c);
        }
    }

    public void eliminaUtente(MouseEvent mouseEvent)
    {

    }
}
