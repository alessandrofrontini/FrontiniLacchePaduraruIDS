package com.camerino.ids.fps.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller_SezioneVisualizzazione
{
    @FXML
    Button sezioneNavigazioneBTNConfermaAzione, sezioneVisualizzazioneBTNRegistrati,sezioneVisualizzazioneBTNLogIn;
    @FXML
    ComboBox sezioneVisualizzazioneSceltaAzione;

    public void reindirizzaToAzioneScelta(MouseEvent mouseEvent)
    {
       String azione = this.getValueFromCombobox(sezioneVisualizzazioneSceltaAzione);

       if(azione == null)
       {
           azione = "";
       }

       switch (azione)
       {
           case "":
               Alert alert = new Alert (Alert.AlertType.WARNING);
               alert.setTitle("Attenzione");
               alert.setContentText("Devi selezionare almeno un' azione");
               alert.show();
               break;

           case "Inserisci Nodo":
               this.navigateToSezioneInserimentoNodi(mouseEvent);
               break;

           case "Modifica Nodo":
               this.navigateToSezioneModificaNodi(mouseEvent);
               break;

           case "Elimina Nodo":
               this.navigateToSezioneEliminazioneNodi(mouseEvent);
               break;
       }
    }

    //region Navigazione
    public void navigateToSezioneInserimentoNodi (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneInserimentoNodi.fxml",mouseEvent);
    }
    public void navigateToSezioneModificaNodi (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneModificaNodi.fxml",mouseEvent);
    }
    public void navigateToSezioneEliminazioneNodi (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneEliminazioneNodi.fxml",mouseEvent);
    }
    public void navigateToSezioneRegistrazione(MouseEvent mouseEvent)
    {
       this.SwitchScene("SezioneRegistrazione.fxml", mouseEvent);
    }

    public void navigateToSezioneLogin(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneLogin.fxml", mouseEvent);
    }

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

    //endregion

    //region Utilities
    private String getValueFromCombobox (ComboBox c)
    {
       return (String) c.getValue();
    }
    //endregion

}
