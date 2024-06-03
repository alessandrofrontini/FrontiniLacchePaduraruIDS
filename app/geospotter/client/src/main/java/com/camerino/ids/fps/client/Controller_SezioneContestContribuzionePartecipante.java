package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsContestDiContribuzioneVisual;
import com.camerino.ids.fps.client.visual.ClsNodoVisual;
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
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTipologiaNodo.*;

public class Controller_SezioneContestContribuzionePartecipante implements Initializable
{
    //region Elementi FXML
    @FXML
    TextField sezioneInserimentoNodiTextFieldNomeDelNodo, sezioneInserimentoNodiTextFieldComuneAssociato, sezioneInserimentoNodiTextFieldCoordinataX, sezioneInserimentoNodiTextFieldCoordinataY, sezioneInserimentoNodiTextFieldDescrizioneDelNodo, sezioneInserimentoNodiTextFieldDataInizio, sezioneInserimentoNodiTextFieldDataFine;

    @FXML
    CheckBox sezioneInserimentoNodiCheckBoxTemporizzato;

    @FXML
    ComboBox sezioneInserimentoNodiComboBoxTipologiaNodo, sceltaContest;


    @FXML
    TableView<ClsContestDiContribuzioneVisual> elencoContest;

    @FXML
    TableColumn<ClsContestDiContribuzioneVisual,String> idColonna = new TableColumn<>("ID");
    @FXML
    TableColumn <ClsContestDiContribuzioneVisual,String> creatoreColonna = new TableColumn<>("CREATORE");
    @FXML
    TableColumn <ClsContestDiContribuzioneVisual,String> durataColonna = new TableColumn<>("DURATA");
    @FXML
    TableColumn <ClsContestDiContribuzioneVisual,String> locationColonna = new TableColumn<>("LOCATION");
    @FXML
    TableColumn <ClsContestDiContribuzioneVisual,String> apertoColonna = new TableColumn<>("APERTO");


    @FXML
    TableView<ClsNodoVisual> ElencoNodi;

    @FXML
    TableColumn<ClsNodoVisual,String> idImmagineColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> idComuneColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> nomeColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> posizioneColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> tipologiaColonna;

    @FXML
    TableColumn<ClsNodoVisual,String> aTempoColonna;

    @FXML
    ComboBox sceltaNodo;

    @FXML
    TextField urlImmagine;


    @FXML
    TableView<ClsNodoVisual> sezioneEliminazioneNodiTableView;

    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnID = new TableColumn<>("ID");
    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnIDComuneAssociato = new TableColumn<>("Comune Associato");
    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnNome = new TableColumn<>("Nome");
    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnPosizione = new TableColumn<>("Posizione");
    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnTipologia = new TableColumn<>("Tipologia");
    @FXML
    TableColumn <ClsNodoVisual,String> sezioneEliminazioneNodiTableColumnATempo = new TableColumn<>("E' Temporizzato?");
    //endregion

    boolean flag = false;
    ArrayList<ClsContestDiContribuzione> contests;
    ArrayList<ClsNodo> nodi;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        contests = new ArrayList<ClsContestDiContribuzione>();
        nodi = new ArrayList<ClsNodo>();

        this.sezioneInserimentoNodiTextFieldDataInizio.setVisible(flag);
        this.sezioneInserimentoNodiTextFieldDataFine.setVisible(flag);

        //region Creazione Contest dummy
        ClsContestDiContribuzione c1 = new ClsContestDiContribuzione();
        c1.setId("1");
        c1.setDurata(new Date("01/01/2024"));
        c1.setUsernameCreatore("test1");
        ClsComune com1 = new ClsComune();
        com1.setNome("Comune1");
        c1.setLocation(com1);
        c1.setAperto(true);
        contests.add(c1);

        ClsContestDiContribuzione c2 = new ClsContestDiContribuzione();
        c2.setId("2");
        c2.setDurata(new Date("02/02/2024"));
        c2.setUsernameCreatore("test2");
        ClsComune com2 = new ClsComune();
        com2.setNome("Comune2");
        c2.setLocation(com2);
        c2.setAperto(false);
        contests.add(c2);

        ClsContestDiContribuzione c3 = new ClsContestDiContribuzione();
        c3.setId("3");
        c3.setDurata(new Date("03/03/2024"));
        c3.setUsernameCreatore("test3");
        ClsComune com3 = new ClsComune();
        com3.setNome("Comune3");
        c3.setLocation(com3);
        c3.setAperto(true);
        contests.add(c3);
        //endregion

        //region Creazione nodi dummy
        ClsNodo nodo1 = new ClsNodo();
        nodo1.setId("2");
        nodo1.setIdComune("1");
        nodo1.setaTempo(true);
        nodo1.setTipologiaNodo(COMMERCIALE);
        nodo1.setIdCreatore("");
        nodo1.setDescrizione("Descrizione - Nodo 1");
        nodo1.setNome("Negozio");
        nodo1.setPosizione(new Posizione(104,104));
        nodi.add(nodo1);

        ClsNodo nodo2 = new ClsNodo();
        nodo2.setId("4");
        nodo2.setIdComune("3");
        nodo2.setaTempo(false);
        nodo2.setTipologiaNodo(CULTURALE);
        nodo2.setIdCreatore("");
        nodo2.setDescrizione("Descrizione - Nodo 2");
        nodo2.setNome("Statua");
        nodo2.setPosizione(new Posizione(114,114));
        nodi.add(nodo2);


        ClsNodo nodo3 = new ClsNodo();
        nodo3.setId("6");
        nodo3.setIdComune("5");
        nodo3.setaTempo(false);
        nodo3.setTipologiaNodo(CULINARIO);
        nodo3.setIdCreatore("");
        nodo3.setDescrizione("Descrizione - Nodo 3");
        nodo3.setNome("Ristorante");
        nodo3.setPosizione(new Posizione(124,124));
        nodi.add(nodo3);
        //endregion

        setContest(contests);
        setNodi(nodi);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<contests.size();i++)
        {
            items.add(contests.get(i).getId());
        }

        this.sceltaContest.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> itemes = FXCollections.observableArrayList();

        for(int i = 0;i<nodi.size();i++)
        {
            itemes.add(nodi.get(i).getId());
        }

        this.sceltaNodo.setItems(itemes);
        //endregion

        //region setting up colonne tabella
        idColonna.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        creatoreColonna.setCellValueFactory(
                new PropertyValueFactory<>("usernameCreatore"));

        durataColonna.setCellValueFactory(
                new PropertyValueFactory<>("durata"));

        locationColonna.setCellValueFactory(
                new PropertyValueFactory<>("locationComune"));

        apertoColonna.setCellValueFactory(
                new PropertyValueFactory<>("isAperto"));
        //endregion

        //region setting up colonne tabella
        idImmagineColonna.setCellValueFactory(
                new PropertyValueFactory<>("ID"));

        idComuneColonna.setCellValueFactory(
                new PropertyValueFactory<>("IDComuneAssociato"));

        nomeColonna.setCellValueFactory(
                new PropertyValueFactory<>("Nome"));

        posizioneColonna.setCellValueFactory(
                new PropertyValueFactory<>("Posizione"));

        tipologiaColonna.setCellValueFactory(
                new PropertyValueFactory<>("Tipologia"));

        aTempoColonna.setCellValueFactory(
                new PropertyValueFactory<>("ATempo"));
        //endregion

        //region setting up colonne tabella
        sezioneEliminazioneNodiTableColumnID.setCellValueFactory(
                new PropertyValueFactory<>("ID"));

        sezioneEliminazioneNodiTableColumnIDComuneAssociato.setCellValueFactory(
                new PropertyValueFactory<>("IDComuneAssociato"));

        sezioneEliminazioneNodiTableColumnNome.setCellValueFactory(
                new PropertyValueFactory<>("Nome"));

        sezioneEliminazioneNodiTableColumnPosizione.setCellValueFactory(
                new PropertyValueFactory<>("Posizione"));

        sezioneEliminazioneNodiTableColumnTipologia.setCellValueFactory(
                new PropertyValueFactory<>("Tipologia"));

        sezioneEliminazioneNodiTableColumnATempo.setCellValueFactory(
                new PropertyValueFactory<>("ATempo"));
        //endregion

    }

    public void inserisciNodo()
    {
        ClsNodo nodo = new ClsNodo();
        Posizione posizione = new Posizione();
        String IDContest = this.partecipaContest(null);

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
                        Objects.equals(u.getValueFromCombobox(sceltaContest), "") ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo), null) ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato), null) ||
                        Objects.equals(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo), null) ||
                        Objects.equals(u.getValueFromCombobox(sceltaContest), null))
                {
                    nodo.setIdCreatore(Controller_SezioneLogin.utente.getUsername());
                    nodo.seteTologiaNodoFormatoStringa(u.getValueFromCombobox(sezioneInserimentoNodiComboBoxTipologiaNodo));
                    nodo.setNome(u.getValueFromTextField(sezioneInserimentoNodiTextFieldNomeDelNodo));
                    //nodo.setIdComune(u.getValueFromTextField(sezioneInserimentoNodiTextFieldComuneAssociato));
                    nodo.setDescrizione(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDescrizioneDelNodo));
                    nodo.setaTempo(u.getValueFromCheckBox(sezioneInserimentoNodiCheckBoxTemporizzato));
                    nodo.setDataInizio(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDataInizio));
                    nodo.setDataFine(u.getValueFromTextField(sezioneInserimentoNodiTextFieldDataFine));

                    if(!u.checkInfoNodo(nodo) || Objects.equals(u.getValueFromCombobox(sceltaContest), null) || Objects.equals(u.getValueFromCombobox(sceltaContest), ""))
                    {
                        Alert alert = new Alert (Alert.AlertType.ERROR);
                        alert.setTitle("Errore");
                        alert.setContentText("Controlla le informazioni");
                        alert.show();
                    }
                    else
                    {
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

    public String partecipaContest(MouseEvent mouseEvent)
    {
        String IDDaEliminare = u.getValueFromCombobox(sceltaContest);

        if(IDDaEliminare != null && controllaConformitaID(IDDaEliminare))
        {
            return IDDaEliminare;
        }
        else {
            return "";
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

    private boolean controllaConformitaID (String id)
    {
        /*
        boolean flag = false;

        for(int i = 0; i<contests.size();i++)
        {
            if(contests.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;*/
        return true;
    }

    private boolean controllaConformitaIDNodi (String id)
    {/*
        if(id==null)
            return false;
        else{
            boolean flag = false;

            for(int i = 0; i<nodi.size();i++)
            {
                if(Objects.equals(nodi.get(i).getId(), id))
                {
                    flag = true;
                }
            }
            return flag;
        }*/
        return true;
    }

    private void setContest (ArrayList<ClsContestDiContribuzione> contests)
    {
        for(int i = 0; i<contests.size();i++)
        {
            ClsContestDiContribuzioneVisual c = u.convertFromaClsContestDiContribuzione(contests.get(i));

            elencoContest.getItems().add(c);
        }
    }

    private void setNodi (ArrayList<ClsNodo> nodi)
    {
        for(int i = 0; i<nodi.size();i++)
        {
            ClsNodoVisual c = u.convertFromClsNodo(nodi.get(i));

            ElencoNodi.getItems().add(c);
            sezioneEliminazioneNodiTableView.getItems().add(c);
        }
    }

    public void inserisciImmagine(MouseEvent mouseEvent)
    {
        String IDContest = u.getValueFromCombobox(this.sceltaContest);
        String IDNodoAssociatoImmagine = u.getValueFromCombobox(this.sceltaNodo);
        ClsImmagine i = new ClsImmagine();

        if((!Objects.equals(u.getValueFromCombobox(this.sceltaContest), null)) && (!Objects.equals(u.getValueFromCombobox(this.sceltaNodo), null)) && (!Objects.equals(u.getValueFromTextField(urlImmagine), "")) && this.controllaConformitaIDNodi(IDNodoAssociatoImmagine))
        {
            i.setIdCreatore(Controller_SezioneLogin.utente.getUsername());
            i.setIdNodo(u.getValueFromCombobox(this.sceltaNodo));
            i.setURL(u.getValueFromTextField(urlImmagine));

            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("Corretto");
            alert.setContentText(i.visualizzaImmagine() + "\n\n IDCONTEST: "+ IDContest);
            alert.show();
        }
        else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Errore");
            alert.show();
        }
    }
}
