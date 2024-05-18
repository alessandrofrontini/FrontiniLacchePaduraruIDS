package com.camerino.ids.fps.client.controllers.curatore;

import com.camerino.ids.core.data.utenti.ClsAnimatore;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
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
    ComboBox selezionaEliminaUtente, sceltaRuoloInserimento;

    @FXML
    TextField usernameTF, passwordTF, punteggioTF;

    @FXML
    TableView<ClsUtenteVisual> elencoUtentiModifica;

    @FXML
    TableColumn<ClsUtenteVisual, String> idColonnaModifica;

    @FXML
    TableColumn<ClsUtenteVisual, String> usernameColonnaModifica;

    @FXML
    TableColumn<ClsUtenteVisual, String> punteggioColonnaModifica;

    @FXML
    ComboBox ruoloMOD, selezionaModificaUtente;
    @FXML
    TextField usernameMODTF, passwordMODTF, punteggioMODTF;
    //endregion

    Utils u = new Utils();
    ArrayList<ClsTuristaAutenticato> utenti;
    ArrayList<String> ruoli;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        utenti = new ArrayList<>();
        ruoli = new ArrayList<>();

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

        //region Creazioni ruoli possibili
        ruoli.add("TURISTA_AUTENTICATO");
        ruoli.add("CONTRIBUTOR");
        ruoli.add("CONTRIBUTOR_AUTORIZZATO");
        ruoli.add("ANIMATORE");
        //endregion

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<utenti.size();i++)
        {
            items.add(utenti.get(i).getId());
        }

        this.selezionaEliminaUtente.setItems(items);
        this.selezionaModificaUtente.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> tmp = FXCollections.observableArrayList();

        for(int i = 0;i<ruoli.size();i++)
        {
            tmp.add(ruoli.get(i));
        }

        this.sceltaRuoloInserimento.setItems(tmp);
        this.ruoloMOD.setItems(tmp);
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

        //region setting up colonne tabella
        idColonnaModifica.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        usernameColonnaModifica.setCellValueFactory(
                new PropertyValueFactory<>("username"));

        punteggioColonnaModifica.setCellValueFactory(
                new PropertyValueFactory<>("punteggio"));
        //endregion

    }

    public void inserisciUtente()
    {
        String username = u.getValueFromTextField(usernameTF);
        String password = u.getValueFromTextField(passwordTF);
        String punteggio = u.getValueFromTextField(punteggioTF);
        String ruolo = u.getValueFromCombobox(sceltaRuoloInserimento);

        if(username != null && !username.isEmpty() &&
            password != null && !password.isEmpty() &&
            punteggio != null && !punteggio.isEmpty() &&
            ruolo != null && !ruolo.isEmpty() )
        {
            if(this.controllaValiditaCredenziali(username))
            {
                switch (ruolo)
                {
                    case "TURISTA_AUTENTICATO":
                        if(true)
                        {
                            ClsTuristaAutenticato ta = new ClsTuristaAutenticato();
                            Credenziali cta = new Credenziali();
                            cta.setUsername(username);
                            cta.setPassword(password);
                            ta.setCredenziali(cta);
                            ta.setId("");
                            ta.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);
                            ta.setPunteggio(Integer.parseInt(punteggio));

                            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                            alert.setTitle("OK");
                            alert.setContentText(ta.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    case "CONTRIBUTOR":
                        if(true)
                        {
                            ClsContributor c = new ClsContributor();
                            Credenziali cta = new Credenziali();
                            cta.setUsername(username);
                            cta.setPassword(password);
                            c.setCredenziali(cta);
                            c.setId("");
                            c.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);
                            c.setPunteggio(Integer.parseInt(punteggio));

                            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                            alert.setTitle("OK");
                            alert.setContentText(c.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    case "CONTRIBUTOR_AUTORIZZATO":
                        if(true)
                        {
                            ClsContributorAutorizzato ca = new ClsContributorAutorizzato();
                            Credenziali cta = new Credenziali();
                            cta.setUsername(username);
                            cta.setPassword(password);
                            ca.setCredenziali(cta);
                            ca.setId("");
                            ca.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
                            ca.setPunteggio(Integer.parseInt(punteggio));

                            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                            alert.setTitle("OK");
                            alert.setContentText(ca.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    case "ANIMATORE":
                        if(true)
                        {
                            ClsAnimatore a = new ClsAnimatore();
                            Credenziali cta = new Credenziali();
                            cta.setUsername(username);
                            cta.setPassword(password);
                            a.setCredenziali(cta);
                            a.setId("");
                            a.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE);
                            a.setPunteggio(Integer.parseInt(punteggio));

                            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                            alert.setTitle("OK");
                            alert.setContentText(a.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    default:
                        Alert alert = new Alert (Alert.AlertType.ERROR);
                        alert.setTitle("ERRORE");
                        alert.setContentText("ERRORE");
                        alert.show();
                }
            }
            else
            {
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Duplicato");
                alert.show();
            }

        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Controlla le informazioni");
            alert.show();
        }
    }

    public void eliminaUtente()
    {
        String IDDaEliminare = u.getValueFromCombobox(this.selezionaEliminaUtente);

        if(IDDaEliminare != null && this.controllaConformitaID(IDDaEliminare))
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle(IDDaEliminare);
            alert.setContentText("--"+IDDaEliminare+"--");
            alert.show();
        }
        else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    public void modificaUtente()
    {
     String IDDaModificare = this.eliminaUtenteCUSTOM();

     ClsTuristaAutenticato utente = this.ottieniUtente(IDDaModificare);

     if(utente != null &&
             IDDaModificare != null && !IDDaModificare.equals("") &&
             u.getValueFromTextField(usernameMODTF) != null && !Objects.equals(u.getValueFromTextField(usernameMODTF), "") &&
             u.getValueFromTextField(passwordMODTF) != null && !Objects.equals(u.getValueFromTextField(passwordMODTF), "") &&
             u.getValueFromTextField(punteggioMODTF) != null && !Objects.equals(u.getValueFromTextField(punteggioMODTF), "") &&
             u.getValueFromCombobox(selezionaModificaUtente) != null && !Objects.equals(u.getValueFromCombobox(selezionaModificaUtente), "") &&
             u.getValueFromCombobox(ruoloMOD) != null && !Objects.equals(u.getValueFromCombobox(ruoloMOD), ""))
     {
         Credenziali c = new Credenziali();
         c.setUsername(u.getValueFromTextField(usernameMODTF));
         c.setPassword(u.getValueFromTextField(passwordMODTF));

         utente.setCredenziali(c);
         utente.setRuoloUtente(ClsTuristaAutenticato.convertRuoloFromString(u.getValueFromCombobox(ruoloMOD)));
         utente.setPunteggio(Integer.parseInt(u.getValueFromTextField(punteggioMODTF)));

         Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
         alert.setTitle("OK");
         alert.setContentText(utente.visualizzaUtente());
         alert.show();
     }
     else
     {
         Alert alert = new Alert (Alert.AlertType.ERROR);
         alert.setTitle("ERRORE");
         alert.setContentText("ERRORE");
         alert.show();
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

    private void setUtenti (ArrayList<ClsTuristaAutenticato> utenti)
    {
        for(int i = 0; i< utenti.size(); i++)
        {
            ClsUtenteVisual c = u.convertFromClsTuristaAutenticato(utenti.get(i));
            elencoUtenti.getItems().add(c);
        }

        for(int i = 0; i< utenti.size(); i++)
        {
            ClsUtenteVisual c = u.convertFromClsTuristaAutenticato(utenti.get(i));
            elencoUtentiModifica.getItems().add(c);
        }
    }

    private boolean controllaValiditaCredenziali(String username)
    {
            for(int i = 0; i < utenti.size(); i++)
            {
                if(username.equals(utenti.get(i).getCredenziali().getUsername()))
                {
                    return false;
                }
            }
            return true;
    }

    private boolean controllaConformitaID (String id)
    {
        boolean flag = false;

        for(int i = 0; i<utenti.size();i++)
        {
            if(utenti.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;
    }

    public String eliminaUtenteCUSTOM()
    {
        String IDDaEliminare = u.getValueFromCombobox(this.selezionaModificaUtente);

        if(!Objects.equals(IDDaEliminare, ""))
        {
            return IDDaEliminare;
        }
        else {
            return null;
        }
    }

    private ClsTuristaAutenticato ottieniUtente (String id)
    {
        for(int i = 0; i < this.utenti.size(); i++)
        {
            if(Objects.equals(this.utenti.get(i).getId(), id))
            {
                return this.utenti.get(i);
            }
        }
        return null;
    }

    public void visualizzaInformazioniAttualiUtente()
    {
        if(u.getValueFromCombobox(selezionaModificaUtente) != null && !Objects.equals(u.getValueFromCombobox(selezionaModificaUtente), ""))
        {
            String ID = u.getValueFromCombobox(selezionaModificaUtente);

            ClsTuristaAutenticato tmp = this.ottieniUtente(ID);

            this.usernameMODTF.setText(tmp.getCredenziali().getUsername());
            this.passwordMODTF.setText(tmp.getCredenziali().getPassword());
            this.ruoloMOD.setValue(tmp.getRuoloUtente().toString());
            this.punteggioMODTF.setText(tmp.getPunteggio()+"");
        }
    }



}
