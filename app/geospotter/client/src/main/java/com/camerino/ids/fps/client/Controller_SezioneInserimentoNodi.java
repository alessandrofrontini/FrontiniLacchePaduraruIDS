package com.camerino.ids.fps.client;

<<<<<<< Updated upstream
<<<<<<< HEAD
public class Controller_SezioneInserimentoNodi
{

=======

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller_SezioneInserimentoNodi
{
    @FXML
    TextField sezioneInserimentoNodiNome,sezioneInserimentoNodiComuneAssociato,sezioneInserimentoNodiPosizioneX,sezioneInserimentoNodiPosizioneY,sezioneInserimentoNodiDescrizione,sezioneInserimentoNodiDataInizio,sezioneInserimentoNodiDataFine;
    @FXML
    CheckBox sezioneInserimentoNodiIsTemporizzato;

    public void inserisciNodo()
    {
        String Nome = this.getNameFromTextField(sezioneInserimentoNodiNome);
        String ComuneAssociato = this.getNameFromTextField(sezioneInserimentoNodiComuneAssociato);
        String PosizioneX = this.getNameFromTextField(sezioneInserimentoNodiPosizioneX);
        String PosizioneY = this.getNameFromTextField(sezioneInserimentoNodiPosizioneY);
        String Descrizione = this.getNameFromTextField(sezioneInserimentoNodiDescrizione);

        if(this.sezioneInserimentoNodiIsTemporizzato.isSelected())
=======
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

<<<<<<< HEAD

    public class Controller_SezioneInserimentoNodi implements Initializable
    {
        boolean flag = false;
        @FXML
        TextField sezioneInserimentoNodiNome,sezioneInserimentoNodiComuneAssociato,sezioneInserimentoNodiPosizioneX,sezioneInserimentoNodiPosizioneY,sezioneInserimentoNodiDescrizione,sezioneInserimentoNodiDataInizio,sezioneInserimentoNodiDataFine;
        @FXML
        CheckBox sezioneInserimentoNodiIsTemporizzato;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle)
        {
            this.sezioneInserimentoNodiDataInizio.setVisible(flag);
            this.sezioneInserimentoNodiDataFine.setVisible(flag);
        }
        public void inserisciNodo()
        {
=======
public class Controller_SezioneInserimentoNodi
{
    @FXML
    TextField sezioneInserimentoNodiNome,sezioneInserimentoNodiComuneAssociato,sezioneInserimentoNodiPosizioneX,sezioneInserimentoNodiPosizioneY,sezioneInserimentoNodiDescrizione,sezioneInserimentoNodiDataInizio,sezioneInserimentoNodiDataFine;
    @FXML
    CheckBox sezioneInserimentoNodiIsTemporizzato;

    public void inserisciNodo()
    {
        String Nome = this.getNameFromTextField(sezioneInserimentoNodiNome);
        String ComuneAssociato = this.getNameFromTextField(sezioneInserimentoNodiComuneAssociato);
        String PosizioneX = this.getNameFromTextField(sezioneInserimentoNodiPosizioneX);
        String PosizioneY = this.getNameFromTextField(sezioneInserimentoNodiPosizioneY);
        String Descrizione = this.getNameFromTextField(sezioneInserimentoNodiDescrizione);

        if(this.sezioneInserimentoNodiIsTemporizzato.isSelected())
            {
                String DataInizio = this.getNameFromTextField(sezioneInserimentoNodiDataInizio);
                String DataFine = this.getNameFromTextField(sezioneInserimentoNodiDataFine);
                boolean isTemporizzato = true;
            }
>>>>>>> parent of dec0096 (.)

            String Nome = this.getNameFromTextField(sezioneInserimentoNodiNome);
            String ComuneAssociato = this.getNameFromTextField(sezioneInserimentoNodiComuneAssociato);
            String PosizioneX = this.getNameFromTextField(sezioneInserimentoNodiPosizioneX);
            String PosizioneY = this.getNameFromTextField(sezioneInserimentoNodiPosizioneY);
            String Descrizione = this.getNameFromTextField(sezioneInserimentoNodiDescrizione);

            if(this.sezioneInserimentoNodiIsTemporizzato.isSelected())
>>>>>>> Stashed changes
            {
                String DataInizio = this.getNameFromTextField(sezioneInserimentoNodiDataInizio);
                String DataFine = this.getNameFromTextField(sezioneInserimentoNodiDataFine);
                boolean isTemporizzato = true;
            }

<<<<<<< Updated upstream
        //Creazione oggetto?


    }
    //region Navigazione
    public void navigateToSezioneNavigazione(MouseEvent mouseEvent) {
        this.SwitchScene("SezioneVisualizzazione.fxml",mouseEvent);
    }

    //region Navigazione - Metodi Privati
    private void SwitchScene (String nomeScena, MouseEvent mouseEvent)
    {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(nomeScena)));
            //Utilities grafiche
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
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
>>>>>>> parent of dec0096 (.)
=======
            //Creazione oggetto?


        }
        public void visualizzaDate ()
        {
            flag = !flag;
            this.sezioneInserimentoNodiDataInizio.setVisible(flag);
            this.sezioneInserimentoNodiDataFine.setVisible(flag);

        }

        //region Navigazione
        public void navigateToSezioneNavigazione(MouseEvent mouseEvent) {
            this.SwitchScene("SezioneVisualizzazione.fxml",mouseEvent);
        }

        //region Navigazione - Metodi Privati
        private void SwitchScene (String nomeScena, MouseEvent mouseEvent)
        {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(nomeScena)));
                //Utilities grafiche
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
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
>>>>>>> Stashed changes
}
