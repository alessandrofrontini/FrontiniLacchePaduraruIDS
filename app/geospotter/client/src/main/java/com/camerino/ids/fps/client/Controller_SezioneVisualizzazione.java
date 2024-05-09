package com.camerino.ids.fps.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneVisualizzazione implements Initializable
{
    @FXML
    Button sezioneNavigazioneBTNConfermaAzione, sezioneVisualizzazioneBTNRegistrati,sezioneVisualizzazioneBTNLogIn;
    @FXML
    ComboBox sceltaAzioneTuristaAutenticato, sceltaAzioneGestore,sceltaAzioneContributor;

    Utils u = new Utils();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void reindirizzaToAzioneSceltaTurista(MouseEvent mouseEvent)
    {
        String azione = u.getValueFromCombobox(sceltaAzioneTuristaAutenticato);

        switch (azione)
        {
            case "Inserisci Recensione":
                this.navigateToSezioneInserimentoRecensioni(mouseEvent);
                break;

            case "Elimina Recensione":
                this.navigateToSezioneEliminazioneRecensioni(mouseEvent);
                break;

            case "Modifica Recensione":
                this.navigateToSezioneModificaRecensioni(mouseEvent);
                break;

            case "Inserisci Immagine":
                this.navigateToSezioneInserimentoImmagini(mouseEvent);
                break;

            default:
                Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle(azione);
                alert.setContentText("--"+azione+"--");
                alert.show();
                break;
        }
    }
    public void reindirizzaToAzioneSceltaContributor(MouseEvent mouseEvent)
    {
       String azione = u.getValueFromCombobox(sceltaAzioneContributor);

       switch (azione)
       {
           case "Inserisci Nodo":
               this.navigateToSezioneInserimentoNodi(mouseEvent);
               break;

           case "Elimina Nodo":
               this.navigateToSezioneEliminazioneNodi(mouseEvent);
               break;

           case "Modifica Nodo":
               this.navigateToSezioneModificaNodi(mouseEvent);
               break;

           case "Inserisci Itinerario":
               this.navigateToSezioneInserimentoItinerari(mouseEvent);
               break;

           case "Elimina Itinerario":
               this.navigateToSezioneEliminazioneItinerari(mouseEvent);
               break;

           case "Modifica Itinerario":
               this.navigateToSezioneModificaItinerari(mouseEvent);
               break;

           case "Inserisci Recensione":
               this.navigateToSezioneInserimentoRecensioni(mouseEvent);
               break;

           case "Elimina Recensione":
               this.navigateToSezioneEliminazioneRecensioni(mouseEvent);
               break;

           case "Modifica Recensione":
               this.navigateToSezioneModificaRecensioni(mouseEvent);
               break;

           case "Contest di Contribuzione":
               this.navigateToSezioneContestDiContribuzioneRedirect(mouseEvent);
               break;

           default:
                Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle(azione);
                alert.setContentText("--"+azione+"--");
                alert.show();
                break;
       }
    }


    public void reindirizzaToAzioneSceltaGestore (MouseEvent mouseEvent)
    {
        String azione = u.getValueFromCombobox(sceltaAzioneGestore);

        switch (azione)
        {
            case "Inserisci Comune":
                this.navigateToSezioneInserimentoComuni(mouseEvent);
                break;

            case "Elimina Comune":
                this.navigateToSezioneEliminazioneComuni(mouseEvent);
                break;

            case "Modifica Comune":
                this.navigateToSezioneModificaComuni(mouseEvent);
                break;

            default:
                Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle(azione);
                alert.setContentText("--"+azione+"--");
                alert.show();
                break;
        }
    }




    //region Navigazione

    public void navigateToSezioneInserimentoImmagini (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneInserimentoImmagini.fxml",mouseEvent);
    }
    public void navigateToSezioneContestDiContribuzioneRedirect (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneContestContribuzioneRedirect.fxml",mouseEvent);
    }
    public void navigateToSezioneInserimentoNodi (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneInserimentoNodi.fxml",mouseEvent);
    }

    public void navigateToSezioneRegistrazione(MouseEvent mouseEvent)
    {
       this.SwitchScene("SezioneRegistrazione.fxml", mouseEvent);
    }

    public void navigateToSezioneLogin(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneLogin.fxml", mouseEvent);
    }

    public void navigateToSezioneEliminazioneNodi (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneEliminazioneNodi.fxml",mouseEvent);
    }

    public void navigateToSezioneModificaNodi (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneModificaNodi.fxml",mouseEvent);
    }

    public void navigateToSezioneInserimentoItinerari (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneInserimentoItinerari.fxml",mouseEvent);
    }

    public void navigateToSezioneModificaItinerari (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneModificaItinerari.fxml",mouseEvent);
    }

    public void navigateToSezioneEliminazioneItinerari (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneEliminazioneItinerari.fxml",mouseEvent);
    }

    public void navigateToSezioneInserimentoRecensioni (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneInserimentoRecensioni.fxml",mouseEvent);
    }

    public void navigateToSezioneModificaRecensioni (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneModificaRecensioni.fxml",mouseEvent);
    }

    public void navigateToSezioneEliminazioneRecensioni (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneEliminazioneRecensioni.fxml",mouseEvent);
    }

    //*

    public void navigateToSezioneInserimentoComuni (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneInserimentoComuni.fxml",mouseEvent);
    }

    public void navigateToSezioneModificaComuni (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneModificaComuni.fxml",mouseEvent);
    }

    public void navigateToSezioneEliminazioneComuni (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneEliminazioneComuni.fxml",mouseEvent);
    }

    //endregion

    //region Navigazione - Metodi privati
    /**
     * Metodo di comodo che utile alla navigazione tra le scene
     * @param nomeScena scena che si vuole cambiare
     */
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


    //endregion




}
