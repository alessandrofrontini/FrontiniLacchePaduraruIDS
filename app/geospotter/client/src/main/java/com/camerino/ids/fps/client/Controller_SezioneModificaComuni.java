package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.data.utils.Posizione;
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
import java.util.*;

public class Controller_SezioneModificaComuni implements Initializable
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
    ComboBox sceltaComune;

    @FXML
    TableView<ClsCuratoreVisual> elencoCuratori;
    @FXML
    TableColumn<ClsCuratoreVisual,String> idCuratore;
    @FXML
    TableColumn<ClsCuratoreVisual, String> usernameCuratore;
    @FXML
    TextField textFieldCuratori,nomeTF,descrizioneTF,coordinataXTF,coordinataYTF,abitantiTF,superficieTF;
    //endregion

    ArrayList<ClsCuratore> Curatori;
    ArrayList<ClsComune> comuni;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.Curatori = new ArrayList<ClsCuratore>();
        this.comuni = new ArrayList<ClsComune>();

        //region Creazione Curatori Dummy
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

        setCuratori(Curatori);

        //region setting up colonne tabella
        idCuratore.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        usernameCuratore.setCellValueFactory(
                new PropertyValueFactory<>("username"));
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

        setComuni(comuni);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<Curatori.size();i++)
        {
            items.add(Curatori.get(i).getId());
        }

        this.sceltaComune.setItems(items);
        //endregion

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

    public void modificaComune(MouseEvent mouseEvent)
    {
        String IDDaEliminare = this.eliminaComune(mouseEvent);
        ClsComune nuovoComune = this.inserisciComune(mouseEvent);

        if(!Objects.equals(IDDaEliminare, null) && !Objects.equals(IDDaEliminare, "") && (nuovoComune != null))
        {
            nuovoComune.setId(IDDaEliminare);
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("FATTO");
            alert.setContentText("ID: " + IDDaEliminare + "\n\n NuovoNodo:" + nuovoComune.visualizzaComune());
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Attenzione");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }

    }

    private void setCuratori (ArrayList<ClsCuratore> curatori)
    {
        for(int i = 0; i<curatori.size();i++)
        {
            ClsCuratoreVisual c = u.convertFromClsCuratore(curatori.get(i));
            elencoCuratori.getItems().add(c);
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

    public void navigateToSezioneVisualizzazione (MouseEvent mouseEvent)
    {
        this.SwitchScene("SezioneVisualizzazione.fxml",mouseEvent);
    }

    private String[] convertiCuratoriCoinvoltiInArray(String input)
    {
        String[] tmp = input.split("-");
        return tmp;
    }

    public String eliminaComune(MouseEvent mouseEvent)
    {
        String IDdaEliminare = u.getValueFromCombobox(this.sceltaComune);

        if(!(Objects.equals(IDdaEliminare, "")))
        {
            return IDdaEliminare;
        }
        else
        {
            return "";
        }
    }

    public ClsComune inserisciComune(MouseEvent mouseEvent)
    {
        ClsComune comune = new ClsComune();
        String curatoriCoinvolti = u.getValueFromTextField(textFieldCuratori);
        String[] curatoriCoinvoltiArray = this.convertiCuratoriCoinvoltiInArray(curatoriCoinvolti);

        ArrayList<ClsCuratore> curatoriAssociatiToComune = new ArrayList<ClsCuratore>();

        for(int i = 0; i<Curatori.size();i++)
        {
            for(int j = 0; j<curatoriCoinvoltiArray.length;j++)
            {
                if(Objects.equals(Curatori.get(i).getId(), curatoriCoinvoltiArray[j]))
                {
                    curatoriAssociatiToComune.add(Curatori.get(i));
                }
            }
        }

        if(!curatoriAssociatiToComune.isEmpty() &&
                !Objects.equals(u.getValueFromTextField(coordinataXTF), "") &&
                !Objects.equals(u.getValueFromTextField(coordinataYTF), "") &&
                !Objects.equals(u.getValueFromTextField(descrizioneTF), "") &&
                !Objects.equals(u.getValueFromTextField(nomeTF), "") &&
                !Objects.equals(u.getValueFromTextField(abitantiTF), "") &&
                !Objects.equals(u.getValueFromTextField(superficieTF), ""))
        {
            comune.setId("");
            comune.setUsernameCreatore(Controller_SezioneLogin.utente.getUsername());
            comune.setPosizione(new Posizione(Double.parseDouble(u.getValueFromTextField(coordinataXTF)), Double.parseDouble(u.getValueFromTextField(coordinataYTF))));
            comune.setNome(u.getValueFromTextField(nomeTF));
            comune.setDescrizione(u.getValueFromTextField(descrizioneTF));
            comune.setAbitanti(Integer.parseInt(u.getValueFromTextField(abitantiTF)));
            comune.setSuperficie(Double.parseDouble(u.getValueFromTextField(superficieTF)));
            comune.setCuratoriAssociati(new ClsCuratore[0]);

          return comune;
        }
        else
        {
            return null;
        }
    }

    public void vediInformazoniAttualiComune(MouseEvent mouseEvent)
    {
        String idComune = u.getValueFromCombobox(sceltaComune);

        ClsComuneVisual c = new ClsComuneVisual();

        for(int i=0;i<comuni.size();i++)
        {
            if(Objects.equals(comuni.get(i).getId(), idComune))
            {
                c = u.convertFromClsComune(comuni.get(i));
                this.nomeTF.setText(c.getNome());
                this.descrizioneTF.setText(c.getDescrizione());
                this.coordinataXTF.setText(comuni.get(i).getPosizione().getX()+"");
                this.coordinataYTF.setText(comuni.get(i).getPosizione().getY()+"");
                this.textFieldCuratori.setText(c.getCuratori());
                this.abitantiTF.setText(c.getAbitanti()+"");
                this.superficieTF.setText(c.getSuperficie()+"");
            }
        }
    }



}
