package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.segnalazioni.ClsSegnalazione;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsComuneVisual;
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

public class Controller_SezioneVisualizzazioneComuni implements Initializable
{

    //region Elementi FXML
    @FXML
    TableView<ClsComuneVisual> elencoComuni;

    @FXML
    TableColumn<ClsComuneVisual,String> id;

    @FXML
    TableColumn<ClsComuneVisual,String> nome;

    @FXML
    TableColumn<ClsComuneVisual,String> posizione;

    @FXML
    TableColumn<ClsComuneVisual,String> abitanti;

    @FXML
    TableColumn<ClsComuneVisual,String> superficie;

    @FXML
    TableColumn<ClsComuneVisual,String> curatori;

    @FXML
    ComboBox selezionaElementoDettaglio, selezionaElementoSegnalazione;

    @FXML
    TextField descrizioneTF;

    //endregion

    Utils u = new Utils();
    ArrayList<ClsComune> comuni;
    ArrayList<ClsCuratore> Curatori = new ArrayList<ClsCuratore>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.comuni = new ArrayList<ClsComune>();

        //region Creazione Curatori dummy
        ClsCuratore c1 = new ClsCuratore();
        c1.setId("1");
        Credenziali cred1 = new Credenziali();
        cred1.setUsername("USERNAME1");
        cred1.setPassword("1");
        c1.setCredenziali(cred1);
        Curatori.add(c1);

        ClsCuratore c2 = new ClsCuratore();
        c2.setId("2");
        Credenziali cred2 = new Credenziali();
        cred2.setUsername("USERNAME2");
        cred2.setPassword("2");
        c2.setCredenziali(cred2);
        Curatori.add(c2);

        ClsCuratore c3 = new ClsCuratore();
        c3.setId("3");
        Credenziali cred3 = new Credenziali();
        cred3.setUsername("USERNAME3");
        cred3.setPassword("3");
        c3.setCredenziali(cred3);
        Curatori.add(c3);
        //endregion

        //region Creazione Comuni dummy
        ClsComune com1 = new ClsComune();
        com1.setId("1");
        com1.setNome("COMUNE1");
        com1.setDescrizione("DESCRIZIONE1");
        com1.setSuperficie(1000);
        Posizione p1 = new Posizione();
        p1.setX(1);
        p1.setY(2);
        com1.setPosizione(p1);
        com1.setCuratoriAssociati(Curatori.toArray(new ClsCuratore[Curatori.size()]));
        comuni.add(com1);

        ClsComune com2 = new ClsComune();
        com2.setId("2");
        com2.setNome("COMUNE2");
        com2.setDescrizione("DESCRIZIONE2");
        com2.setSuperficie(2000);
        Posizione p2 = new Posizione();
        p2.setX(2);
        p2.setY(2);
        com2.setPosizione(p2);
        com2.setCuratoriAssociati(Curatori.toArray(new ClsCuratore[Curatori.size()]));
        comuni.add(com2);

        ClsComune com3 = new ClsComune();
        com3.setId("3");
        com3.setNome("COMUNE3");
        com3.setDescrizione("DESCRIZIONE3");
        com3.setSuperficie(3000);
        Posizione p3 = new Posizione();
        p3.setX(3);
        p3.setY(3);
        com3.setPosizione(p3);
        com3.setCuratoriAssociati(Curatori.toArray(new ClsCuratore[Curatori.size()]));
        comuni.add(com3);
        //endregion

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<comuni.size();i++)
        {
            items.add(comuni.get(i).getId());
        }

        this.selezionaElementoDettaglio.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> itemss = FXCollections.observableArrayList();

        for(int i = 0;i<comuni.size();i++)
        {
            itemss.add(comuni.get(i).getId());
        }

        this.selezionaElementoSegnalazione.setItems(itemss);
        //endregion

        setComuni(comuni);

        //region setting up colonne tabella
        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        nome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        posizione.setCellValueFactory(
                new PropertyValueFactory<>("posizione"));

        abitanti.setCellValueFactory(
                new PropertyValueFactory<>("abitanti"));

        superficie.setCellValueFactory(
                new PropertyValueFactory<>("superficie"));

        curatori.setCellValueFactory(
                new PropertyValueFactory<>("curatori"));
        //endregion

    }

    public void inserisciSegnalazione()
    {
        String descrizioneSegnalazione = u.getValueFromTextField(descrizioneTF);

        if(!Objects.equals(descrizioneSegnalazione, "") && descrizioneSegnalazione != null && u.getValueFromCombobox(selezionaElementoSegnalazione) != null)
        {
            String IDDaSegnalare = u.getValueFromCombobox(selezionaElementoSegnalazione);
            ClsSegnalazione segnalazione = new ClsSegnalazione();
            segnalazione.setDescrizione(descrizioneSegnalazione);
            segnalazione.setIdContenuto(IDDaSegnalare);

            for(int i = 0; i < this.comuni.size(); i++)
            {
                if(Objects.equals(comuni.get(i).getId(), IDDaSegnalare))
                {
                    segnalazione.setIdCuratore(comuni.get(i).getCuratoriAssociati()[0].getId());
                }
            }
            Controller_SezioneLogin.UTENTE.segnalaContenuto(segnalazione);
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setContentText(segnalazione.visualizzaSegnalazione());
            alert.show();


        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }
    }

    public void visualizzaDettaglio ()
    {
        String IDDaVisualizzare = u.getValueFromCombobox(selezionaElementoDettaglio);

        if(IDDaVisualizzare != null && !IDDaVisualizzare.equals("") && this.controllaConformitaID(IDDaVisualizzare))
        {
            ClsComune c = new ClsComune();
            for(int i = 0; i<comuni.size();i++)
            {
                if(IDDaVisualizzare.equals(this.comuni.get(i).getId()))
                {
                    c.setId(comuni.get(i).getId());
                    c.setNome(comuni.get(i).getNome());
                    c.setCuratoriAssociati(comuni.get(i).getCuratoriAssociati());
                    c.setSuperficie(comuni.get(i).getSuperficie());
                    c.setDescrizione(comuni.get(i).getDescrizione());
                    c.setUsernameCreatore(comuni.get(i).getUsernameCreatore());
                    c.setAbitanti(comuni.get(i).getAbitanti());
                    c.setPosizione(comuni.get(i).getPosizione());
                }
            }
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setContentText(c.visualizzaComune());
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }
    }

    private void setComuni (ArrayList<ClsComune> comuni)
    {
        for(int i = 0; i<comuni.size();i++)
        {
            ClsComuneVisual c = u.convertFromClsComune(comuni.get(i));

            elencoComuni.getItems().add(c);
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
    public void navigateToSezioneVisualizzazioneMappa(MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazioneMappa.fxml",mouseEvent);
    }

    private boolean controllaConformitaID (String id)
    {/*
        boolean flag = false;

        for(int i = 0; i<comuni.size();i++)
        {
            if(comuni.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
    }
}
