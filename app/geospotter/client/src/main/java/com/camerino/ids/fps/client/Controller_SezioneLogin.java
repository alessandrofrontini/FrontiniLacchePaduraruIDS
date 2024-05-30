package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.fps.client.iper.*;
import com.camerino.ids.fps.client.utils.TMP_ServizioAutenticazione;
import com.camerino.ids.fps.client.visual.ClsUtenteJWTDecode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class Controller_SezioneLogin
{
    @FXML
    Button sezioneRegistrazioneBTNRegistrazione,sezioneRegistrazioneBTNVaiAllaHome,sezioneRegistrazioneBTNVaiAlLogin;

    @FXML
    TextField sezioneRegistrazioneTextBoxUsername, sezioneRegistrazioneTextBoxPassword;


    public static ClsUtenteJWTDecode utente = new ClsUtenteJWTDecode(); //todo: metti qua l'utente
    //region esempio padu TODO
    public static ClsTurista UTENTE = new ClsTurista();

    //endregion


    public void logga ()
    {
        String username = getNameFromTextField(sezioneRegistrazioneTextBoxUsername);
        String password = getNameFromTextField(sezioneRegistrazioneTextBoxPassword);

        Credenziali c = new Credenziali();
        c.setUsername(username);
        c.setPassword(password);

        ObjectMapper mapper = new ObjectMapper();

        HttpRequest.BodyPublisher bodyPublisher = null;
        try {
            bodyPublisher = HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(c));
            System.out.println(mapper.writeValueAsString(c));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .POST(bodyPublisher)
                .uri(URI.create("http://localhost:8080/api/v1/login"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            UTENTE = mapper.readValue(response.body(),new TypeReference<ClsTuristaAutenticato>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        InizializzaUtente();
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

    private ClsTurista AuthClient(String authorization) {
        if(authorization == null) {
            return CreaTurista();
        }
        return switch (authorization) {
            case "turista_aut" -> CreaTuristaAut();
            case "contr"-> CreaContributor();
            case "contr_aut" -> CreaContributorAut();
            case "curatore" -> CreaCuratore();
            case "animatore" -> CreaAnimatore();
            case "gdp" -> CreaGDP();
            default -> throw new RuntimeException("No user");
        };
    }

    private void InizializzaUtente() {
        UTENTE = switch (((ClsTuristaAutenticato)UTENTE).getRuoloUtente()) {
            case TURISTA_AUTENTICATO -> CreaTuristaAut();
            case CONTRIBUTOR-> CreaContributor();
            case CONTRIBUTOR_AUTORIZZATO -> CreaContributorAut();
            case CURATORE -> CreaCuratore();
            case ANIMATORE -> CreaAnimatore();
            case GESTORE_DELLA_PIATTAFORMA -> CreaGDP();
        };
    }

    //region CREA X
    private ClsTurista CreaTurista() {
        ClsTurista user = new ClsTurista();
        user.setpNodi(new IperNodi());
        user.setMockComuni(new IperComuni());
        user.setpItinerari(new IperItinerari());
        user.setIperRecensioni(new IperRecensioni());
        user.setIperSegnalazioni(new IperSegnalazioni());
        user.setpImmagini(new IperImmagini());

        return user;
    }

    private ClsTuristaAutenticato CreaTuristaAut() {
        ClsTuristaAutenticato user = new ClsTuristaAutenticato(CreaTurista());
        user.setId(1+"");
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);
        //user.setIperUtenti(new IperUtenti());
        user.setIperRDCImmagini(new IperRCDImmagini());
        return user;
    }

    private ClsContributor CreaContributor() {
        ClsContributor user = new ClsContributor(CreaTuristaAut());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);
        //user.setpRDC(this.iperRDC);
        //user.setpRDCI(this.iperRDCI);
        //user._setIperRDCNodi(this.iperRDCNodi);
        return user;
    }

    private ClsContributorAutorizzato CreaContributorAut() {
        ClsContributorAutorizzato user = new ClsContributorAutorizzato(CreaContributor());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
        return user;
    }

    private ClsAnimatore CreaAnimatore() {
        ClsAnimatore user = new ClsAnimatore(CreaContributorAut());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE);
        return user;
    }

    private ClsCuratore CreaCuratore() {
        ClsCuratore user = new ClsCuratore(CreaAnimatore());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE);
        return user;
    }

    private ClsGestoreDellaPiattaforma CreaGDP() {
        ClsGestoreDellaPiattaforma user = new ClsGestoreDellaPiattaforma(CreaCuratore());
        user.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA);
        return user;
    }
    //endregion

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
