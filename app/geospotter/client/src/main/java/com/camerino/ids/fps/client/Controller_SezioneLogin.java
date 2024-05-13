package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller_SezioneLogin
{
    @FXML
    Button sezioneRegistrazioneBTNRegistrazione,sezioneRegistrazioneBTNVaiAllaHome,sezioneRegistrazioneBTNVaiAlLogin;

    @FXML
    TextField sezioneRegistrazioneTextBoxUsername, sezioneRegistrazioneTextBoxPassword;

    private String tmpJWT = "DJASIDJIQ09I4902JDIOAR8932";
   public static ClsUtenteJWTDecode utente = new ClsUtenteJWTDecode(); //todo: metti qua l'utente

    public void logga ()
    {

        //!!!!!! BYPASS
        utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA);
        utente.setUsername("ERMAGODAAPECORONA69");
        //!!!!!! BYPASS
        String username = getNameFromTextField(sezioneRegistrazioneTextBoxUsername);
        String password = getNameFromTextField(sezioneRegistrazioneTextBoxPassword);

        //Controllo server
        if(password.equals(password))
        {
            //LOG PER TURISTA AUT = ta:ta
           if(Objects.equals(username, "ta") && password.equals("ta"))
           {
               utente.setJwt(tmpJWT);
               utente.setUsername(username);
               utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);
           }
            //LOG PER CONTRIBUTOR = c:c
            if(Objects.equals(username, "c") && password.equals("c"))
            {
                utente.setJwt(tmpJWT);
                utente.setUsername(username);
                utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);
            }

            //LOG PER CONTRIBUTOR AUT = ca:ca
            if(Objects.equals(username, "ca") && password.equals("ca"))
            {
                utente.setJwt(tmpJWT);
                utente.setUsername(username);
                utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
            }

            //LOG PER ANIMATORE = a:a
            if(Objects.equals(username, "a") && password.equals("a"))
            {
                utente.setJwt(tmpJWT);
                utente.setUsername(username);
                utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE);
            }

            //LOG PER CURATORE = cur:cur
            if(Objects.equals(username, "cur") && password.equals("cur"))
            {
                utente.setJwt(tmpJWT);
                utente.setUsername(username);
                utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE);
            }

            //LOG PER GDP = gdp:gdp
            if(Objects.equals(username, "gdp") && password.equals("gdp"))
            {
                utente.setJwt(tmpJWT);
                utente.setUsername(username);
                utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA);
            }

        }

        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Reinserisci password");
            alert.show();
        }

    }

    //region Navigazione

    public void navigateToSezioneVisualizzazione(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazione.fxml", mouseEvent);
    }

    public void navigateToSezioneRegistrazione(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneRegistrazione.fxml", mouseEvent);
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
    /**
     * Metodo di comodo che elimina il contenuto di una textArea
     * @param t textArea
     */
    private void clearTextFromTextField(TextField t)
    {
        t.clear();
    }

    /**
     * Metodo di comodo che estrae il contenuto da una textField
     * @param tf textField
     * @return Contenuto
     */
    private String getNameFromTextField (TextField tf)
    {
        String name = tf.getText();
        tf.clear();
        return name;
    }

    //endregion





}
