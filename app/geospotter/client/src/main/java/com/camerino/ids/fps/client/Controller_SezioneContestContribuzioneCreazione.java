package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsContestDiContribuzione;
import com.camerino.ids.core.data.contenuti.ClsPartecipazioneContestDiContribuzione;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsComuneVisual;
import com.camerino.ids.fps.client.visual.ClsUtenteInvitoContestVisual;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller_SezioneContestContribuzioneCreazione implements Initializable
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
    TextField dataFine;

    @FXML
    CheckBox suInvito;



    @FXML
    TableView<ClsUtenteInvitoContestVisual> elencoInvitati;

    @FXML
    TableColumn<ClsUtenteInvitoContestVisual, String> idInvitato;

    @FXML
    TableColumn<ClsUtenteInvitoContestVisual, String> usernameInvitato;

    @FXML
    TableColumn<ClsUtenteInvitoContestVisual, String> punteggioInvitato;

    @FXML
    TableColumn<ClsUtenteInvitoContestVisual, String> ruoloInvitato;

    @FXML
    Tab tabPartecipanti;

    @FXML
    TextField invitatiContestTF;
    //endregion

    boolean flag = false;
    Utils u = new Utils();
    ArrayList<ClsComune> comuni;
    ArrayList<ClsTuristaAutenticato> utenti;
    ArrayList<ClsCuratore> Curatori = new ArrayList<ClsCuratore>();
    ArrayList<ClsPartecipazioneContestDiContribuzione> partecipazioni;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.tabPartecipanti.setDisable(true);
        this.comuni = new ArrayList<ClsComune>();

        this.utenti = new ArrayList<ClsTuristaAutenticato>();

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



        //region Creazione Utenti dummy
        ClsTuristaAutenticato ta1 = new ClsTuristaAutenticato();
        ta1.setId("1");
        ta1.setPunteggio(190);
        Credenziali credenz1 = new Credenziali();
        credenz1.setUsername("username1");
        ta1.setCredenziali(credenz1);
        ta1.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);
        utenti.add(ta1);

        ClsTuristaAutenticato ta2 = new ClsTuristaAutenticato();
        ta2.setId("2");
        ta2.setPunteggio(290);
        Credenziali credenz2 = new Credenziali();
        credenz2.setUsername("username2");
        ta2.setCredenziali(credenz2);
        ta2.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);
        utenti.add(ta2);

        ClsTuristaAutenticato ta3 = new ClsTuristaAutenticato();
        ta3.setId("3");
        ta3.setPunteggio(220);
        Credenziali credenz3 = new Credenziali();
        credenz3.setUsername("username3");
        ta3.setCredenziali(credenz3);
        ta3.setRuoloUtente(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
        utenti.add(ta3);
        //endregion

        setUtenti(utenti);

        //region setting up colonne tabella
        idInvitato.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        usernameInvitato.setCellValueFactory(
                new PropertyValueFactory<>("username"));

        punteggioInvitato.setCellValueFactory(
                new PropertyValueFactory<>("punteggio"));

        ruoloInvitato.setCellValueFactory(
                new PropertyValueFactory<>("ruolo"));
        //endregion

    }

    public void inserisciContest(MouseEvent mouseEvent)
    {
        ClsContestDiContribuzione contest = new ClsContestDiContribuzione();
        ClsComune comune = new ClsComune();
        this.partecipazioni = new ArrayList<>();

        Date data = this.parseStringToDate(u.getValueFromTextField(dataFine));
        ArrayList<ClsTuristaAutenticato> utentiInvitatiAContest = new ArrayList<ClsTuristaAutenticato>();

        if(data != null && u.getValueFromCombobox(this.sceltaComune) != null)
        {
            contest.setUsernameCreatore(Controller_SezioneLogin.utente.getUsername());
            contest.setDurata(data);
            comune = new ClsComune();
            comune.setId(u.getValueFromCombobox(this.sceltaComune));
            contest.setLocation(comune);

            //non su invito
            if(!u.getValueFromCheckBox(this.suInvito))
            {
                contest.setAperto(false);

                for(int i = 0; i<utenti.size();i++)
                {
                    ClsPartecipazioneContestDiContribuzione p = new ClsPartecipazioneContestDiContribuzione();
                    p.setId("test");
                    p.setUsernamePartecipante(utenti.get(i).getCredenziali().getUsername());
                    p.setIdContest(contest.getId());

                    this.partecipazioni.add(p);
                }

                for(int i = 0 ; i < this.partecipazioni.size(); i++)
                {
                    System.out.println(this.partecipazioni.get(i).visualizzaPartecipazione());
                }

                Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("OK");
                alert.setContentText(contest.visualizzaContest());
                alert.show();

            }
            //su invito
            else
            {

                contest.setAperto(true);
                String IDUtentiDaInvitare = u.getValueFromTextField(this.invitatiContestTF);
                String[] IDUtentiDaInvitareArray = this.convertiUtentiCoinvoltiInArray(IDUtentiDaInvitare);

                if(IDUtentiDaInvitareArray.length > 0)
                {
                    for(int j = 0; j < this.utenti.size(); j++)
                    {
                        for(int i = 0; i < IDUtentiDaInvitareArray.length ; i++)
                        {
                            if(Objects.equals(IDUtentiDaInvitareArray[i], utenti.get(j).getId()))
                            {
                                ClsPartecipazioneContestDiContribuzione p = new ClsPartecipazioneContestDiContribuzione();
                                p.setId("test");
                                p.setUsernamePartecipante(utenti.get(j).getCredenziali().getUsername());
                                p.setIdContest(contest.getId());

                                this.partecipazioni.add(p);
                            }

                        }

                    }


                    for(int i = 0 ; i < this.partecipazioni.size(); i++)
                    {
                        System.out.println(this.partecipazioni.get(i).visualizzaPartecipazione());
                    }

                    Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                    alert.setTitle("OK");
                    alert.setContentText(contest.visualizzaContest());
                    alert.show();
                }
                else{
                    Alert alert = new Alert (Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Ricontrolla le informazioni e riprova");
                    alert.show();
                }

            }


        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Ricontrolla le informazioni e riprova");
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

    private void setUtenti (ArrayList<ClsTuristaAutenticato> utenti)
    {
        for(int i = 0; i<utenti.size();i++)
        {
            ClsUtenteInvitoContestVisual c = u.convertFromTuristaAutenticato(utenti.get(i));

            elencoInvitati.getItems().add(c);
        }
    }

    private boolean controllaConformitaID (String id)
    {
        /*boolean flag = false;

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

    public void visualizzaTabInvitati (MouseEvent mouseEvent)
    {
        this.tabPartecipanti.setDisable(flag);
        this.flag = !flag;

    }


    private String[] convertiUtentiCoinvoltiInArray(String input)
    {
        String[] tmp = input.split("-");
        String[] nuova = this.pulisciIDnonPresenti(new ArrayList<>(Arrays.asList(tmp)), utenti);

        return nuova;
    }

    private String[] pulisciIDnonPresenti (ArrayList<String> input, ArrayList<ClsTuristaAutenticato> utenti)
    {
        // Create a HashSet from the string values of objects in listA for faster lookup
        HashSet<String> setAValues = new HashSet<>();
        for (ClsTuristaAutenticato obj : utenti) {
            setAValues.add(obj.getId());
        }

        // Use an Iterator to safely remove elements from listB
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            // If element is not present in setAValues, remove it from listB
            if (!setAValues.contains(element)) {
                iterator.remove();
            }
        }

        input = this.rimuoviDuplicati(input);

        return input.toArray(new String[input.size()]);
    }

    private ArrayList<String> rimuoviDuplicati(ArrayList<String> listaOriginale) {
        // Creiamo un HashSet che conterrà solo elementi unici
        HashSet<String> setUnici = new HashSet<>(listaOriginale);

        // Ricostruiamo l'ArrayList senza duplicati
        ArrayList<String> listaSenzaDuplicati = new ArrayList<>(setUnici);

        return listaSenzaDuplicati;
    }

    private Date parseStringToDate(String dateString) {
        // Rimuovi tutti i caratteri non numerici dalla stringa
        String numericString = dateString.replaceAll("[^0-9]", "");

        // Definisci il formato della data
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

        try {
            // Parsa la stringa in una data
            Date date = dateFormat.parse(numericString);
            return date;
        } catch (ParseException e) {
            // Se si verifica un'eccezione di parsing, restituisci null
            return null;
        }

}
}
