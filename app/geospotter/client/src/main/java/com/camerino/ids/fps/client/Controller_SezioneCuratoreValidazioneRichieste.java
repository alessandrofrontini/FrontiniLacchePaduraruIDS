package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzione;
import com.camerino.ids.core.data.azioni.ClsRichiestaAzioneDiContribuzioneItinerario;
import com.camerino.ids.core.data.azioni.EAzioniDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsImmagine;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsRichiestaAzioneDiContribuzioneItinerarioVisual;
import com.camerino.ids.fps.client.visual.ClsRichiestaAzioneDiContribuzioneVisual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.*;
import static com.camerino.ids.core.data.contenuti.ClsNodo.eTologiaNodo.CULINARIO;

public class Controller_SezioneCuratoreValidazioneRichieste implements Initializable
{

    //region Elementi FXML
    @FXML
    TableView<ClsRichiestaAzioneDiContribuzioneVisual> elencoRichieste;

    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneVisual,String> idColonna;

    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneVisual,String> azioneColonna;
    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneVisual,String> tipoContenutoColonna;
    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneVisual,String> idRichiedenteColonna;

    @FXML
    ComboBox sceltaAzione,selezionaElementoDettaglio;

    @FXML
    TableView<ClsRichiestaAzioneDiContribuzioneItinerarioVisual> elencoRichiesteItinerari;

    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneItinerarioVisual, String> idColonnaItinerari;

    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneItinerarioVisual, String> usernameRichiedenteItinerari;

    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneItinerarioVisual, String> idItinerarioColonna;

    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneItinerarioVisual, String> tappeItinerario;

    @FXML
    TableColumn<ClsRichiestaAzioneDiContribuzioneItinerarioVisual, String> azioneContribuzione;



    @FXML
    ComboBox sceltaAzioneItinerari,selezionaElementoDettaglioItinerari;
    //endregion

    Utils u = new Utils();

    ArrayList<ClsRichiestaAzioneDiContribuzione> richieste;
    ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> richiesteItinerari;

    ArrayList<ClsNodo> nodi = new ArrayList<ClsNodo>();
    ArrayList<ClsItinerario> itinerari = new ArrayList<ClsItinerario>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.richieste = new ArrayList<ClsRichiestaAzioneDiContribuzione>();

        this.richiesteItinerari = new ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario>();

        //region Creazione richieste dummy
        ClsRichiestaAzioneDiContribuzione c1 = new ClsRichiestaAzioneDiContribuzione();
        c1.setId("1");
        c1.setIdContest("1");
        c1.seteAzioneDiContribuzione(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        c1.setDatiNodo(null);
        c1.setUsernameCreatoreRichiesta("Username 1");
        ClsImmagine i1 = new ClsImmagine();
        i1.setId("1");
        c1.setDatiImmagine(i1);
        richieste.add(c1);

        ClsRichiestaAzioneDiContribuzione c2 = new ClsRichiestaAzioneDiContribuzione();
        c2.setId("2");
        c2.setIdContest("2");
        c2.seteAzioneDiContribuzione(EAzioniDiContribuzione.INSERISCI_IMMAGINE);
        c2.setDatiNodo(null);
        c2.setUsernameCreatoreRichiesta("Username 2");
        ClsImmagine i2 = new ClsImmagine();
        i2.setId("2");
        c2.setDatiImmagine(i2);
        richieste.add(c2);

        ClsRichiestaAzioneDiContribuzione c3 = new ClsRichiestaAzioneDiContribuzione();
        c3.setId("3");
        c3.setIdContest("3");
        c3.seteAzioneDiContribuzione(EAzioniDiContribuzione.INSERISCI_NODO);
        c3.setDatiImmagine(null);
        c3.setUsernameCreatoreRichiesta("Username 2");
        ClsNodo n1 = new ClsNodo();
        n1.setId("1");

        Posizione p1 = new Posizione();
        p1.setY(12);
        p1.setX(21);
        n1.setPosizione(p1);
        c3.setDatiNodo(n1);
        richieste.add(c3);

        ClsRichiestaAzioneDiContribuzione c4 = new ClsRichiestaAzioneDiContribuzione();
        c4.setId("4");
        c4.setIdContest("4");
        c4.seteAzioneDiContribuzione(EAzioniDiContribuzione.INSERISCI_NODO);
        c4.setDatiImmagine(null);
        c4.setUsernameCreatoreRichiesta("Username 4");
        ClsNodo n2 = new ClsNodo();
        Posizione p2 = new Posizione();
        p2.setY(22);
        p2.setX(22);
        n2.setPosizione(p2);
        n2.setId("4");
        c4.setDatiNodo(n2);
        richieste.add(c4);
        //endregion

        //region Creazione itinerari dummy
        ClsNodo nodo1 = new ClsNodo();
        nodo1.setId("2");
        nodo1.setIdComune("1");
        nodo1.setaTempo(true);
        nodo1.setTipologiaNodo(COMMERCIALE);
        nodo1.setUsernameCreatore("");
        nodo1.setDescrizione("Descrizione - Nodo 1");
        nodo1.setNome("Negozio");
        nodo1.setPosizione(new Posizione(104,104));
        nodi.add(nodo1);

        ClsNodo nodo2 = new ClsNodo();
        nodo2.setId("4");
        nodo2.setIdComune("3");
        nodo2.setaTempo(false);
        nodo2.setTipologiaNodo(CULTURALE);
        nodo2.setUsernameCreatore("");
        nodo2.setDescrizione("Descrizione - Nodo 2");
        nodo2.setNome("Statua");
        nodo2.setPosizione(new Posizione(114,114));
        nodi.add(nodo2);

        ClsItinerario itinerario = new ClsItinerario();
        itinerario.setId("1");
        itinerario.setUsernameCreatore("test");
        itinerario.setOrdinato(true);
        itinerario.setNome("Itinerario1");
        itinerario.setTappe(nodi);
        itinerari.add(itinerario);

        ClsNodo nodo3 = new ClsNodo();
        nodo3.setId("6");
        nodo3.setIdComune("5");
        nodo3.setaTempo(false);
        nodo3.setTipologiaNodo(CULINARIO);
        nodo3.setUsernameCreatore("");
        nodo3.setDescrizione("Descrizione - Nodo 3");
        nodo3.setNome("Ristorante");
        nodo3.setPosizione(new Posizione(124,124));
        nodi.add(nodo3);

        ClsItinerario itinerario1 = new ClsItinerario();
        itinerario1.setId("2");
        itinerario1.setUsernameCreatore("test");
        itinerario1.setOrdinato(false);
        itinerario1.setNome("Itinerario2");
        itinerario1.setTappe(nodi);
        itinerari.add(itinerario1);

        ClsNodo nodo4 = new ClsNodo();
        nodo4.setId("8");
        nodo4.setIdComune("3");
        nodo4.setaTempo(false);
        nodo4.setTipologiaNodo(CULINARIO);
        nodo4.setUsernameCreatore("");
        nodo4.setDescrizione("Descrizione - Nodo 4");
        nodo4.setNome("Ristorante");
        nodo4.setPosizione(new Posizione(124,124));
        nodi.add(nodo4);

        ClsItinerario i3 = new ClsItinerario();
        i3.setId("3");
        i3.setUsernameCreatore("test");
        i3.setOrdinato(true);
        i3.setNome("Itinerario3");
        i3.setTappe(nodi);
        itinerari.add(i3);
        //endregion

        //region creazione richieste itinerari dummy
        ClsRichiestaAzioneDiContribuzioneItinerario ri1 = new ClsRichiestaAzioneDiContribuzioneItinerario();
        ri1.setId("1");
        ri1.setUsernameCreatore("test");
        ri1.seteAzioniDiContribuzione(EAzioniDiContribuzione.ELIMINA_ITINERARIO);
        ri1.setDatiItinerario(itinerario);
        richiesteItinerari.add(ri1);

        ClsRichiestaAzioneDiContribuzioneItinerario ri2 = new ClsRichiestaAzioneDiContribuzioneItinerario();
        ri2.setId("2");
        ri2.setUsernameCreatore("test");
        ri2.seteAzioniDiContribuzione(EAzioniDiContribuzione.INSERISCI_ITINERARIO);
        ri2.setDatiItinerario(itinerario1);
        richiesteItinerari.add(ri2);

        ClsRichiestaAzioneDiContribuzioneItinerario ri3 = new ClsRichiestaAzioneDiContribuzioneItinerario();
        ri3.setId("3");
        ri3.setUsernameCreatore("test");
        ri3.seteAzioniDiContribuzione(EAzioniDiContribuzione.MODIFICA_ITINERARIO);
        ri3.setDatiItinerario(i3);
        richiesteItinerari.add(ri3);
        //endregion

        setRichieste(richieste);

        setRichiesteItinerari(richiesteItinerari);

        //region combobox
        ObservableList<String> items = FXCollections.observableArrayList();

        for(int i = 0;i<richieste.size();i++)
        {
            items.add(richieste.get(i).getId());
        }

        this.sceltaAzione.setItems(items);
        //endregion

        //region combobox
        ObservableList<String> itemss = FXCollections.observableArrayList();

        for(int i = 0;i<richieste.size();i++)
        {
            itemss.add(richieste.get(i).getId());
        }

        this.selezionaElementoDettaglio.setItems(itemss);
        //endregion

        //region combobox
        ObservableList<String> itemsItinerari = FXCollections.observableArrayList();

        for(int i = 0;i<richiesteItinerari.size();i++)
        {
            itemsItinerari.add(richiesteItinerari.get(i).getId());
        }

        this.sceltaAzioneItinerari.setItems(itemsItinerari);
        //endregion

        //region combobox
        ObservableList<String> itemssItinerari = FXCollections.observableArrayList();

        for(int i = 0;i < richiesteItinerari.size(); i++)
        {
            itemssItinerari.add(richiesteItinerari.get(i).getId());
        }

        this.selezionaElementoDettaglioItinerari.setItems(itemssItinerari);
        //endregion

        //region setting up colonne tabella
        idColonna.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        idRichiedenteColonna.setCellValueFactory(
                new PropertyValueFactory<>("usernameCreatore"));

        azioneColonna.setCellValueFactory(
                new PropertyValueFactory<>("azioneDiContribuzione"));

        tipoContenutoColonna.setCellValueFactory(
                new PropertyValueFactory<>("tipoContenuto"));
        //endregion

        //region setting up colonne tabella itinerari
        idColonnaItinerari.setCellValueFactory(
                new PropertyValueFactory<>("idd"));

        usernameRichiedenteItinerari.setCellValueFactory(
                new PropertyValueFactory<>("usernameCreatoree"));

        idItinerarioColonna.setCellValueFactory(
                new PropertyValueFactory<>("idItinerario"));

        tappeItinerario.setCellValueFactory(
                new PropertyValueFactory<>("tappe"));

        azioneContribuzione.setCellValueFactory(
                new PropertyValueFactory<>("azione"));
        //endregion

    }

    public void accettaAzione(MouseEvent mouseEvent)
    {
        String IDValidazione = u.getValueFromCombobox(sceltaAzione);
        if(IDValidazione != null && !Objects.equals(IDValidazione, ""))
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText("AZIONE (" + IDValidazione + ") Validata");
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    public void rifiutaAzione(MouseEvent mouseEvent)
    {
        String IDValidazione = u.getValueFromCombobox(sceltaAzione);
        if(IDValidazione != null && !Objects.equals(IDValidazione, ""))
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText("AZIONE (" + IDValidazione + ") NON Validata");
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    private void setRichieste (ArrayList<ClsRichiestaAzioneDiContribuzione> richieste)
    {
        for(int i = 0; i< richieste.size(); i++)
        {
            ClsRichiestaAzioneDiContribuzioneVisual c = u.convertFromRichiestaAzioneContribuzione(richieste.get(i));

            elencoRichieste.getItems().add(c);
        }
    }

    private void setRichiesteItinerari (ArrayList<ClsRichiestaAzioneDiContribuzioneItinerario> richieste)
    {
        for(int i = 0; i< richiesteItinerari.size(); i++)
        {
            ClsRichiestaAzioneDiContribuzioneItinerarioVisual c = u.convertFromRichiestaAzioneContribuzioneItinerario(richieste.get(i));

            elencoRichiesteItinerari.getItems().add(c);
        }
    }

    public void visualizzaDettaglio()
    {
        String IDDaVisualizzare = u.getValueFromCombobox(selezionaElementoDettaglio);

        if(IDDaVisualizzare != null && !IDDaVisualizzare.equals("") && this.controllaConformitaID(IDDaVisualizzare))
        {
            ClsRichiestaAzioneDiContribuzione richiesta = new ClsRichiestaAzioneDiContribuzione();
            for(int i = 0; i<richieste.size();i++)
            {
                if(IDDaVisualizzare.equals(this.richieste.get(i).getId()))
                {
                    richiesta.setId(richieste.get(i).getId());
                    richiesta.setIdContest(richieste.get(i).getIdContest());
                    richiesta.seteAzioneDiContribuzione(richieste.get(i).geteAzioneDiContribuzione());
                    richiesta.setUsernameCreatoreRichiesta(richieste.get(i).getUsernameCreatoreRichiesta());

                    if(richieste.get(i).getDatiNodo() != null)
                    {
                        richiesta.setDatiNodo(richieste.get(i).getDatiNodo());

                        Alert alert = new Alert (Alert.AlertType.INFORMATION);
                        alert.setTitle("OK");
                        alert.setContentText("Richiesta di azione: \n" + richiesta.visualizzaRichiesta() + "\nNodo:" + richiesta.getDatiNodo().visualizzaNodo());
                        alert.show();
                    }
                    if(richieste.get(i).getDatiImmagine() != null)
                    {
                        richiesta.setDatiImmagine(richieste.get(i).getDatiImmagine());

                        Alert alert = new Alert (Alert.AlertType.INFORMATION);
                        alert.setTitle("OK");
                        alert.setContentText(richiesta.visualizzaRichiesta() + "\nImmagine:" + richiesta.getDatiImmagine().visualizzaImmagine());
                        alert.show();
                    }

                }
            }

        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }

    }

    private boolean controllaConformitaID (String id)
    {
        boolean flag = false;

        for(int i = 0; i<richieste.size();i++)
        {
            if(richieste.get(i).getId() == id)
            {
                flag = true;
            }
        }
        return flag;
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

    public void accettaRichiestaItinerario()
    {
        String IDValidazione = u.getValueFromCombobox(sceltaAzioneItinerari);
        if(IDValidazione != null && !Objects.equals(IDValidazione, ""))
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText("AZIONE (" + IDValidazione + ") Validata");
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    public void rifiutaAzioneItinerario(MouseEvent mouseEvent)
    {
        String IDValidazione = u.getValueFromCombobox(sceltaAzioneItinerari);
        if(IDValidazione != null && !Objects.equals(IDValidazione, ""))
        {
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setContentText("AZIONE (" + IDValidazione + ") NON Validata");
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("ERRORE");
            alert.show();
        }
    }

    public void visualizzaDettaglioItinerario()
    {
        String IDDaVisualizzare = u.getValueFromCombobox(selezionaElementoDettaglioItinerari);

        if(IDDaVisualizzare != null && !IDDaVisualizzare.equals("") && this.controllaConformitaID(IDDaVisualizzare))
        {
            ClsRichiestaAzioneDiContribuzioneItinerario richiesta = new ClsRichiestaAzioneDiContribuzioneItinerario();
            for(int i = 0; i<richiesteItinerari.size();i++)
            {
                if(IDDaVisualizzare.equals(this.richiesteItinerari.get(i).getId()))
                {
                    richiesta.setId(richiesteItinerari.get(i).getId());
                    richiesta.setUsernameCreatore(richiesteItinerari.get(i).getUsernameCreatore());
                    richiesta.seteAzioniDiContribuzione(richiesteItinerari.get(i).geteAzioniDiContribuzione());
                    richiesta.setDatiItinerario(richiesteItinerari.get(i).getDatiItinerario());

                    Alert alert = new Alert (Alert.AlertType.INFORMATION);
                    alert.setTitle("OK");
                    alert.setContentText("Richiesta di azione:" + richiesta.visualizzaRichiestaItinerario() + "\nItinerario:" + richiesta.getDatiItinerario().visualizzaItinerario());
                    alert.show();

                }
            }

        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Controlla le informazioni e riprova");
            alert.show();
        }

    }

}
