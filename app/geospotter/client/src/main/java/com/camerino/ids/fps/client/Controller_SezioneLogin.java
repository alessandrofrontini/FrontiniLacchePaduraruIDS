package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.fps.client.iper.*;
import com.camerino.ids.fps.client.utils.TMP_ServizioAutenticazione;
import com.camerino.ids.fps.client.visual.ClsUtenteJWTDecode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.sql.ast.tree.from.UnionTableReference;

import java.io.IOException;
import java.util.Objects;

public class Controller_SezioneLogin
{
    @FXML
    Button sezioneRegistrazioneBTNRegistrazione,sezioneRegistrazioneBTNVaiAllaHome,sezioneRegistrazioneBTNVaiAlLogin;

    @FXML
    TextField sezioneRegistrazioneTextBoxUsername, sezioneRegistrazioneTextBoxPassword;


    public static ClsUtenteJWTDecode utente = new ClsUtenteJWTDecode(); //todo: metti qua l'utente
    //region esempio padu TODO
    public static ClsTurista UTENTE = new ClsCuratore();
    static {
        ((ClsTuristaAutenticato)UTENTE).setId(1+"");
        UTENTE.setpNodi(new IperNodi());
        UTENTE.setIperSegnalazioni(new IperSegnalazioni());
        UTENTE.setpItinerari(new IperItinerari());
        UTENTE.setIperRecensioni(new IperRecensioni());
        UTENTE.setpImmagini(new IperImmagini());
        UTENTE.setMockComuni(new IperComuni());
        ((ClsTuristaAutenticato)UTENTE).setIperRDCImmagini(new IperRCDImmagini());
    }
    //endregion


    public void logga ()
    {
        String username = getNameFromTextField(sezioneRegistrazioneTextBoxUsername);
        String password = getNameFromTextField(sezioneRegistrazioneTextBoxPassword);

        Credenziali c = new Credenziali();
        c.setUsername(username);
        c.setPassword(password);

        //Controllo server
        if(TMP_ServizioAutenticazione.login(c))
        {
            if(TMP_ServizioAutenticazione.retrieveUtente(c) != null)
            {
                utente = TMP_ServizioAutenticazione.retrieveUtente(c);
                Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Benvenuto");
                alert.show();
            }
            else
            {
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle("Credenziali invalide");
                alert.show();
            }

        }

        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Reinserisci password");
            alert.show();
        }

    }

    public void loggaBypass()
    {
        Credenziali c = new Credenziali();
        c.setUsername("gdp");
        c.setPassword("gdp");
        if(TMP_ServizioAutenticazione.retrieveUtente(c) != null)
        {
            utente = TMP_ServizioAutenticazione.retrieveUtente(c);
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("Benvenuto");
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
