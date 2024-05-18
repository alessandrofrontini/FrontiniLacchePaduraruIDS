package com.camerino.ids.fps.client.controllers.curatore;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.fps.client.Utils;
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
        utenti = new ArrayList<>();
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
                //todo:Cambio ruolo by spring
                switch(u.getValueFromCombobox(sceltaAzione))
                {


                    case "UP-RANK":
                        if(u.getValueFromTextField(inserimentoPunteggioTF) != null && !Objects.equals(u.getValueFromTextField(inserimentoPunteggioTF), ""))
                        {
                            utente.setPunteggio(utente.getPunteggio() + Integer.parseInt(u.getValueFromTextField(inserimentoPunteggioTF)));
                            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                            alert.setTitle("OK");
                            alert.setContentText("Prima: \n" + utenteBackUp.visualizzaUtente() + "\nDopo: \n" + utente.visualizzaUtente());
                            alert.show();
                            break;
                        }
                        else
                        {
                            Alert alert = new Alert (Alert.AlertType.ERROR);
                            alert.setTitle("Attenzione");
                            alert.setContentText("Controlla le informazioni e riprova");
                            alert.show();
                            break;
                        }


                    case "DOWN-RANK":
                        if(u.getValueFromTextField(inserimentoPunteggioTF) != null && !Objects.equals(u.getValueFromTextField(inserimentoPunteggioTF), ""))
                        {
                            utente.setPunteggio(utente.getPunteggio() - Integer.parseInt(u.getValueFromTextField(inserimentoPunteggioTF)));
                            Alert alertt = new Alert (Alert.AlertType.CONFIRMATION);
                            alertt.setTitle("OK");
                            alertt.setContentText("Prima: \n" + utenteBackUp.visualizzaUtente() + "\nDopo: \n" + utente.visualizzaUtente());
                            alertt.show();
                            break;
                        }
                        else
                        {
                            Alert alert = new Alert (Alert.AlertType.ERROR);
                            alert.setTitle("Attenzione");
                            alert.setContentText("Controlla le informazioni e riprova");
                            alert.show();
                            break;
                        }


                    case "RESET-RANK":
                        utente.setPunteggio(0);

                        Alert alerttt = new Alert (Alert.AlertType.CONFIRMATION);
                        alerttt.setTitle("OK");
                        alerttt.setContentText("Prima: \n" + utenteBackUp.visualizzaUtente() + "\nDopo: \n" + utente.visualizzaUtente());
                        alerttt.show();
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
                            utente.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO.getValue());
                            utente.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);

                            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                            alert.setTitle("OK");
                            alert.setContentText("Prima:" +utenteBackUp.visualizzaUtente() + "\nDopo:" + utente.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    case "CONTRIBUTOR":
                        if(true) {
                            utente.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR.getValue());
                            utente.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);

                            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                            alert.setTitle("OK");
                            alert.setContentText("Prima:" +utenteBackUp.visualizzaUtente() + "\nDopo:" + utente.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    case "CONTRIBUTOR_AUTORIZZATO":
                        if(true)
                        {
                            utente.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue());
                            utente.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);

                            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                            alert.setTitle("OK");
                            alert.setContentText("Prima:" +utenteBackUp.visualizzaUtente() + "\nDopo:" + utente.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    case "ANIMATORE":
                        if(true)
                        {
                        utente.setPunteggio(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE.getValue());
                        utente.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE);

                        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                        alert.setTitle("OK");
                        alert.setContentText("Prima:" +utenteBackUp.visualizzaUtente() + "\nDopo:" + utente.visualizzaUtente());
                        alert.show();
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
