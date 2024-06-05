package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.fps.client.utils.TMP_ServizioAutenticazione;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsUtenteVisual;
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

public class Controller_SezioneCuratorePunteggioUtenti implements Initializable
{
    //region Elementi FXML
    @FXML
    ComboBox sceltaUtente, sceltaAzione, sceltaRuolo;

    @FXML
    TextField inserimentoPunteggioTF;

    @FXML
    TableView<ClsUtenteVisual> elencoUtenti;

    @FXML
    TableColumn<ClsUtenteVisual, String> idColonna;

    @FXML
    TableColumn<ClsUtenteVisual, String> usernameColonna;

    @FXML
    TableColumn<ClsUtenteVisual, String> punteggioColonna;
    //endregion

    ArrayList<ClsTuristaAutenticato> utenti;

    ArrayList<String> azioni;
    ArrayList<String> ruoli;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        utenti = TMP_ServizioAutenticazione.utentiLegit;
        azioni = new ArrayList<>();
        ruoli = new ArrayList<>();

        inserimentoPunteggioTF.setVisible(false);

        //region Creazione azioni possibili
        azioni.add("UP-RANK");
        azioni.add("DOWN-RANK");
        azioni.add("RESET-RANK");
        //endregion

        //region Creazioni ruoli possibili
        ruoli.add("TURISTA_AUTENTICATO");
        ruoli.add("CONTRIBUTOR");
        ruoli.add("CONTRIBUTOR_AUTORIZZATO");
        ruoli.add("ANIMATORE");
        //endregion

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<azioni.size();i++)
        {
            items.add(azioni.get(i));
        }

        this.sceltaAzione.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> itemes = FXCollections.observableArrayList();

        for(int i = 0;i<utenti.size();i++)
        {
            itemes.add(utenti.get(i).getCredenziali().getUsername());
        }

        this.sceltaUtente.setItems(itemes);
        //endregion

        //region combobox
        ObservableList<String> tmp = FXCollections.observableArrayList();

        for(int i = 0;i<ruoli.size();i++)
        {
            tmp.add(ruoli.get(i));
        }

        this.sceltaRuolo.setItems(tmp);
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

    private void setUtenti (ArrayList<ClsTuristaAutenticato> utenti)
    {
        for(int i = 0; i< utenti.size(); i++)
        {
            ClsUtenteVisual c = u.convertFromClsTuristaAutenticato(utenti.get(i));
            elencoUtenti.getItems().add(c);
        }
    }

    public void settaTextField()
    {
        if(u.getValueFromCombobox(sceltaAzione) != null && u.getValueFromCombobox(sceltaAzione) != "")
        {
            switch (u.getValueFromCombobox(sceltaAzione))
            {
                case ("UP-RANK"):
                    inserimentoPunteggioTF.setVisible(true);
                    break;

                case ("DOWN-RANK"):
                    inserimentoPunteggioTF.setVisible(true);
                    break;

                case ("RESET-RANK"):
                    inserimentoPunteggioTF.setVisible(false);
                    break;

                default:
                    inserimentoPunteggioTF.setVisible(false);
                    break;
            }
        }
    }

    public void modificaInfoUtenti()
    {
        if(u.getValueFromCombobox(sceltaUtente) != null && !Objects.equals(u.getValueFromCombobox(sceltaUtente), "") &&
                u.getValueFromCombobox(sceltaAzione) != null && !Objects.equals(u.getValueFromCombobox(sceltaAzione), ""))
        {
            ClsTuristaAutenticato utente = this.ottieniUtente(u.getValueFromCombobox(sceltaUtente));
            ClsTuristaAutenticato utenteBackUp = utente.clone();

            if(u.getValueFromCombobox(sceltaAzione) != null && !Objects.equals(u.getValueFromCombobox(sceltaAzione), ""))
            {
                TMP_ServizioAutenticazione.modificaPunteggioByNumero(utente,u.getValueFromTextField(inserimentoPunteggioTF),u.getValueFromCombobox(sceltaAzione));

            }
            else
            {
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle("Attenzione");
                alert.show();
            }
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Attenzione");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }

    }

    public void modificaRuoloUtente()
    {
        if(u.getValueFromCombobox(sceltaUtente) != null && !Objects.equals(u.getValueFromCombobox(sceltaUtente), "") &&
                u.getValueFromCombobox(sceltaRuolo) != null && !Objects.equals(u.getValueFromCombobox(sceltaRuolo), ""))
        {
            ClsTuristaAutenticato utente = this.ottieniUtente(u.getValueFromCombobox(sceltaUtente));
            ClsTuristaAutenticato utenteBackUp = utente.clone();

            if(u.getValueFromCombobox(sceltaRuolo) != null && !Objects.equals(u.getValueFromCombobox(sceltaRuolo), ""))
            {
                switch(u.getValueFromCombobox(sceltaRuolo))
                {
                    case "TURISTA_AUTENTICATO":
                        if(true)
                        {
                            TMP_ServizioAutenticazione.modificaPunteggioByEnum(utente,ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);
                        }
                        break;

                    case "CONTRIBUTOR":
                        if(true)
                        {
                            TMP_ServizioAutenticazione.modificaPunteggioByEnum(utente,ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);
                        }
                        break;

                    case "CONTRIBUTOR_AUTORIZZATO":
                        if(true)
                        {
                            TMP_ServizioAutenticazione.modificaPunteggioByEnum(utente,ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
                        }
                        break;

                    case "ANIMATORE":
                        if(true)
                        {
                            TMP_ServizioAutenticazione.modificaPunteggioByEnum(utente,ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE);
                        }
                        break;

                    default:
                        Alert alerttttt = new Alert (Alert.AlertType.ERROR);
                        alerttttt.setTitle("Attenzione");
                        alerttttt.setContentText("Controlla le informazioni e riprova");
                        alerttttt.show();
                        break;


                }
            }
            else
            {
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle("Attenzione");
                alert.setContentText("Controlla le informazioni e riprova");
                alert.show();
            }

        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Attenzione");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }
    }

    private ClsTuristaAutenticato ottieniUtente(String username)
    {
        for(int i = 0; i < utenti.size();i++)
        {
            if(utenti.get(i).getCredenziali().getUsername() == username)
            {
                return utenti.get(i);
            }
        }
        return null;
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

}
