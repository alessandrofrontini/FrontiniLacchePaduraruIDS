package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.client.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class Controller_SezioneInserimentoNodi implements Initializable
{
    //region Elementi FXML
    @FXML
    TextField sezioneInserimentoNodiTextFieldNomeDelNodo, sezioneInserimentoNodiTextFieldComuneAssociato, sezioneInserimentoNodiTextFieldCoordinataX, sezioneInserimentoNodiTextFieldCoordinataY, sezioneInserimentoNodiTextFieldDescrizioneDelNodo, sezioneInserimentoNodiTextFieldDataInizio, sezioneInserimentoNodiTextFieldDataFine;

    @FXML
    CheckBox sezioneInserimentoNodiCheckBoxTemporizzato;

    @FXML
    ComboBox sezioneInserimentoNodiComboBoxTipologiaNodo;

    @FXML
    Button sezioneInserimentoNodiButtonConferma,sezioneInserimentoNodiButtonHomePage;
    //endregion

    boolean flag = false;
    Utils u = new Utils();

    List<ClsComune> comuni;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.sezioneInserimentoNodiTextFieldDataInizio.setVisible(flag);
        this.sezioneInserimentoNodiTextFieldDataFine.setVisible(flag);
        comuni = Controller_SezioneLogin.UTENTE.getAllComuni();
    }

    public void inserisciNodo()
    {
        ClsNodo nodo = new ClsNodo();
        Posizione posizione = new Posizione();

        try
        {
            if(!(Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataX)) == Double.NaN || Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataY)) == Double.NaN))
            {
                posizione.setX(Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataX)));
                posizione.setY(Double.parseDouble(u.getValueFromTextField(sezioneInserimentoNodiTextFieldCoordinataY)));
                nodo.setPosizione(posizione);

                if(u.getValueFromCombobox(sezioneInserimentoNodiComboBoxTipologiaNodo) != null ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo), "") ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato), "") ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo), "") ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo), null) ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato), null) ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo), null))
                {
                    nodo.setIdCreatore(0L);
                    nodo.seteTologiaNodoFormatoStringa(u.getValueFromCombobox(sezioneInserimentoNodiComboBoxTipologiaNodo));
                    nodo.setNome(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo));
                    nodo.setIdComuneAssociato(Long.valueOf(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato)));
                    nodo.setDescrizione(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo));
                    nodo.setaTempo(u.getValueFromCheckBox(sezioneInserimentoNodiCheckBoxTemporizzato));
                    nodo.setDataInizio(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDataInizio));
                    nodo.setDataFine(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDataFine));

                    if(!u.checkInfoNodo(nodo) /*|| !this.CheckValidita(nodo, comuni)*/)
                    {
                        Alert alert = new Alert (Alert.AlertType.ERROR);
                        alert.setTitle("Errore");
                        alert.setContentText("Controlla le informazioni");
                        alert.show();
                    }
                    else
                    {
                        ((ClsContributor)Controller_SezioneLogin.UTENTE).inserisciNodo(nodo);
                        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Info Nodo");
                        alert.setContentText(nodo.visualizzaNodo());
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
            else
            {
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Controlla le informazioni");
                alert.show();
            }
        }
        catch (NumberFormatException e)
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Controlla le informazioni");
            alert.show();
        }
        catch (NullPointerException e)
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Controlla le informazioni");
            alert.show();
        }
    }

    public void visualizzaInformazioniNodoTemporizzato()
    {
        flag = !flag;
        this.sezioneInserimentoNodiTextFieldDataInizio.setVisible(flag);
        this.sezioneInserimentoNodiTextFieldDataFine.setVisible(flag);

    }

    private void SwitchScene(String nomeScena, MouseEvent mouseEvent)
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

  public boolean CheckValidita (ClsNodo nodo, List<ClsComune> comuni)
  {
      boolean flagComuni = false;
      for(int i = 0; i < comuni.size(); i++)
      {
          if(Objects.equals(nodo.getIdComuneAssociato(), comuni.get(i).getId()))
          {
              flagComuni = true;
          }
      }
      return flagComuni;
  }
}