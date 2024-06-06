package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.utenti.ClsAnimatore;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;
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
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.camerino.ids.fps.client.utils.TMP_ServizioAutenticazione.utentiLegit;

public class Controller_SezioneCuratoreGestioneUtenti implements Initializable {

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
    List<ClsTuristaAutenticato> utenti;
    List<String> ruoli; //Statico

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        utenti = utentiLegit;
        ruoli = new ArrayList<>();

        //region Creazioni ruoli possibili
        ruoli.add("TURISTA_AUTENTICATO");
        ruoli.add("CONTRIBUTOR");
        ruoli.add("CONTRIBUTOR_AUTORIZZATO");
        ruoli.add("ANIMATORE");
        //endregion

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < utenti.size(); i++) {
            items.add(utenti.get(i).getId().toString());
        }

        this.selezionaEliminaUtente.setItems(items);
        this.selezionaModificaUtente.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> tmp = FXCollections.observableArrayList();

        for (int i = 0; i < ruoli.size(); i++) {
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

    public void inserisciUtente() {
        String username = u.getValueFromTextField(usernameTF);
        String password = u.getValueFromTextField(passwordTF);
        String punteggio = u.getValueFromTextField(punteggioTF);
        String ruolo = u.getValueFromCombobox(sceltaRuoloInserimento);

        if (username != null && !username.isEmpty() &&
                password != null && !password.isEmpty() &&
                punteggio != null && !punteggio.isEmpty() &&
                ruolo != null && !ruolo.isEmpty()) {
            if (this.controllaValiditaCredenziali(username)) {
                switch (ruolo) {
                    case "TURISTA_AUTENTICATO":
                        if (true) {
                            ClsTuristaAutenticato ta = new ClsTuristaAutenticato();
                            Credenziali cta = new Credenziali();
                            cta.setUsername(username);
                            cta.setPassword(password);
                            ta.setCredenziali(cta);
                            ta.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO);

                            if (Integer.parseInt(punteggio) < 0) {
                                ta.setPunteggio(0);
                            } else {
                                if (Integer.parseInt(punteggio) > ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO.getValue()) {
                                    ta.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO.getValue());
                                } else {
                                    ta.setPunteggio(Integer.parseInt(punteggio));
                                }
                            }


                            ta.setId(utentiLegit.size() + 1L);
                            TMP_ServizioAutenticazione.inserisciUtente(ta);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Ok");
                            alert.setContentText(ta.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    case "CONTRIBUTOR":
                        if (true) {
                            ClsContributor c = new ClsContributor();
                            Credenziali cta = new Credenziali();
                            cta.setUsername(username);
                            cta.setPassword(password);
                            c.setCredenziali(cta);
                            c.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR);

                            if (Integer.parseInt(punteggio) <= ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO.getValue()) {
                                c.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO.getValue() + 1);
                            } else {
                                if (Integer.parseInt(punteggio) > ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR.getValue()) {
                                    c.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR.getValue());
                                } else {
                                    c.setPunteggio(Integer.parseInt(punteggio));
                                }
                            }


                            c.setId(utentiLegit.size() + 1L);
                            utentiLegit.add(c);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Ok");
                            alert.setContentText(c.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    case "CONTRIBUTOR_AUTORIZZATO":
                        if (true) {
                            ClsContributorAutorizzato ca = new ClsContributorAutorizzato();
                            Credenziali cta = new Credenziali();
                            cta.setUsername(username);
                            cta.setPassword(password);
                            ca.setCredenziali(cta);
                            ca.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO);


                            if (Integer.parseInt(punteggio) <= ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR.getValue()) {
                                ca.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR.getValue() + 1);
                            } else {
                                if (Integer.parseInt(punteggio) > ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue()) {
                                    ca.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue());
                                } else {
                                    ca.setPunteggio(Integer.parseInt(punteggio));
                                }
                            }


                            ca.setId(utentiLegit.size() + 1L);
                            utentiLegit.add(ca);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Ok");
                            alert.setContentText(ca.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    case "ANIMATORE":
                        if (true) {
                            ClsAnimatore a = new ClsAnimatore();
                            Credenziali cta = new Credenziali();
                            cta.setUsername(username);
                            cta.setPassword(password);
                            a.setCredenziali(cta);
                            a.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE);

                            if (Integer.parseInt(punteggio) <= ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue()) {
                                a.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue() + 1);
                            } else {
                                if (Integer.parseInt(punteggio) > ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE.getValue()) {
                                    a.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE.getValue());
                                } else {
                                    a.setPunteggio(Integer.parseInt(punteggio));
                                }
                            }

                            a.setId(utentiLegit.size() + 1L);
                            utentiLegit.add(a);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("OK");
                            alert.setContentText(a.visualizzaUtente());
                            alert.show();
                        }
                        break;

                    default:
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERRORE");
                        alert.setContentText("ERRORE");
                        alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Duplicato");
                alert.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Controlla le informazioni");
            alert.show();
        }
    }

    public void eliminaUtente() {
        Long IDDaEliminare = Long.valueOf(u.getValueFromCombobox(this.selezionaEliminaUtente));

        if (IDDaEliminare != null && this.controllaConformitaID(IDDaEliminare) && TMP_ServizioAutenticazione.eliminaUtente(IDDaEliminare)) {
            TMP_ServizioAutenticazione.eliminaUtente(IDDaEliminare);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminato: " + IDDaEliminare);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    public void modificaUtente() {
        Long IDDaModificare = Long.valueOf(this.eliminaUtenteCUSTOM());

        ClsTuristaAutenticato utente = this.ottieniUtente(IDDaModificare);

        if (utente != null &&
                IDDaModificare != null && !IDDaModificare.equals("") &&
                u.getValueFromTextField(usernameMODTF) != null && !Objects.equals(u.getValueFromTextField(usernameMODTF), "") &&
                u.getValueFromTextField(passwordMODTF) != null && !Objects.equals(u.getValueFromTextField(passwordMODTF), "") &&
                u.getValueFromTextField(punteggioMODTF) != null && !Objects.equals(u.getValueFromTextField(punteggioMODTF), "") &&
                u.getValueFromCombobox(selezionaModificaUtente) != null && !Objects.equals(u.getValueFromCombobox(selezionaModificaUtente), "") &&
                u.getValueFromCombobox(ruoloMOD) != null && !Objects.equals(u.getValueFromCombobox(ruoloMOD), "")) {
            String username = u.getValueFromTextField(usernameMODTF);
            String password = u.getValueFromTextField(passwordMODTF);
            String punteggio = u.getValueFromTextField(punteggioMODTF);
            String scelta = u.getValueFromCombobox(selezionaModificaUtente);
            String ruolo = u.getValueFromCombobox(ruoloMOD);

            Credenziali c = new Credenziali();
            c.setUsername(u.getValueFromTextField(usernameMODTF));
            c.setPassword(u.getValueFromTextField(passwordMODTF));

            utente.setCredenziali(c);
            utente.setRuoloUtente(ClsTuristaAutenticato.convertRuoloFromString(u.getValueFromCombobox(ruoloMOD)));
            switch (ruolo) {
                case "TURISTA_AUTENTICATO":
                    if (true) {
                        ClsTuristaAutenticato ta = new ClsTuristaAutenticato();
                        Credenziali cta = new Credenziali();
                        cta.setUsername(username);
                        cta.setPassword(password);
                        ta.setCredenziali(cta);
                        ta.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO);

                        if (Integer.parseInt(punteggio) < 0) {
                            ta.setPunteggio(0);
                        } else {
                            if (Integer.parseInt(punteggio) > ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO.getValue()) {
                                ta.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO.getValue());
                            } else {
                                ta.setPunteggio(Integer.parseInt(punteggio));
                            }
                        }
                        ta.setId(utente.getId());

                        if (TMP_ServizioAutenticazione.modificaUtente(ta, IDDaModificare)) {
                            TMP_ServizioAutenticazione.modificaUtente(ta, IDDaModificare);
                        }

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Ok");
                        alert.setContentText(ta.visualizzaUtente());
                        alert.show();
                    }
                    break;

                case "CONTRIBUTOR":
                    if (true) {
                        ClsContributor contr = new ClsContributor();
                        Credenziali cta = new Credenziali();
                        cta.setUsername(username);
                        cta.setPassword(password);
                        contr.setCredenziali(cta);
                        contr.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR);

                        if (Integer.parseInt(punteggio) <= ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO.getValue()) {
                            contr.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO.getValue() + 1);
                        } else {
                            if (Integer.parseInt(punteggio) > ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR.getValue()) {
                                contr.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR.getValue());
                            } else {
                                contr.setPunteggio(Integer.parseInt(punteggio));
                            }
                        }

                        contr.setId(utente.getId());

                        if (TMP_ServizioAutenticazione.modificaUtente(contr, IDDaModificare)) {
                            TMP_ServizioAutenticazione.modificaUtente(contr, IDDaModificare);
                        }

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Ok");
                        alert.setContentText(contr.visualizzaUtente());
                        alert.show();
                    }
                    break;

                case "CONTRIBUTOR_AUTORIZZATO":
                    if (true) {
                        ClsContributorAutorizzato ca = new ClsContributorAutorizzato();
                        Credenziali cta = new Credenziali();
                        cta.setUsername(username);
                        cta.setPassword(password);
                        ca.setCredenziali(cta);
                        ca.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO);


                        if (Integer.parseInt(punteggio) <= ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR.getValue()) {
                            ca.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR.getValue() + 1);
                        } else {
                            if (Integer.parseInt(punteggio) > ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue()) {
                                ca.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue());
                            } else {
                                ca.setPunteggio(Integer.parseInt(punteggio));
                            }
                        }
                        ca.setId(utente.getId());

                        if (TMP_ServizioAutenticazione.modificaUtente(ca, IDDaModificare)) {
                            TMP_ServizioAutenticazione.modificaUtente(ca, IDDaModificare);
                        }

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Ok");
                        alert.setContentText(ca.visualizzaUtente());
                        alert.show();
                    }
                    break;

                case "ANIMATORE":
                    if (true) {
                        ClsAnimatore a = new ClsAnimatore();
                        Credenziali cta = new Credenziali();
                        cta.setUsername(username);
                        cta.setPassword(password);
                        a.setCredenziali(cta);
                        a.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE);

                        if (Integer.parseInt(punteggio) <= ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue()) {
                            a.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue() + 1);
                        } else {
                            if (Integer.parseInt(punteggio) > ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE.getValue()) {
                                a.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE.getValue());
                            } else {
                                a.setPunteggio(Integer.parseInt(punteggio));
                            }
                        }

                        a.setId(utente.getId());

                        if (TMP_ServizioAutenticazione.modificaUtente(a, IDDaModificare)) {
                            TMP_ServizioAutenticazione.modificaUtente(a, IDDaModificare);
                        }

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("OK");
                        alert.setContentText(a.visualizzaUtente());
                        alert.show();
                    }
                    break;

                default:
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRORE");
                    alert.setContentText("ERRORE");
                    alert.show();
            }


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
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

    private void setUtenti(List<ClsTuristaAutenticato> utenti) {
        for (int i = 0; i < utenti.size(); i++) {
            ClsUtenteVisual c = u.convertFromClsTuristaAutenticato(utenti.get(i));
            elencoUtenti.getItems().add(c);
        }

        for (int i = 0; i < utenti.size(); i++) {
            ClsUtenteVisual c = u.convertFromClsTuristaAutenticato(utenti.get(i));
            elencoUtentiModifica.getItems().add(c);
        }
    }

    private boolean controllaValiditaCredenziali(String username) {
        for (int i = 0; i < utenti.size(); i++) {
            if (username.equals(utenti.get(i).getCredenziali().getUsername())) {
                return false;
            }
        }
        return true;
    }

    private boolean controllaConformitaID(Long id) {
        /*boolean flag = false;

        for(int i = 0; i<utenti.size();i++)
        {
            if(utenti.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
    }

    public String eliminaUtenteCUSTOM() {
        String IDDaEliminare = u.getValueFromCombobox(this.selezionaModificaUtente);

        if (!Objects.equals(IDDaEliminare, "")) {
            return IDDaEliminare;
        } else {
            return null;
        }
    }

    private ClsTuristaAutenticato ottieniUtente(Long id) {
        for (int i = 0; i < this.utenti.size(); i++) {
            if (Objects.equals(this.utenti.get(i).getId(), id)) {
                return this.utenti.get(i);
            }
        }
        return null;
    }

    public void visualizzaInformazioniAttualiUtente() {
        if (u.getValueFromCombobox(selezionaModificaUtente) != null && !Objects.equals(u.getValueFromCombobox(selezionaModificaUtente), "")) {
            Long ID = Long.valueOf(u.getValueFromCombobox(selezionaModificaUtente));

            ClsTuristaAutenticato tmp = this.ottieniUtente(ID);

            this.usernameMODTF.setText(tmp.getCredenziali().getUsername());
            this.passwordMODTF.setText(tmp.getCredenziali().getPassword());
            this.ruoloMOD.setValue(tmp.getRuoloUtente().toString());
            this.punteggioMODTF.setText(tmp.getPunteggio().toString());
        }
    }


}
