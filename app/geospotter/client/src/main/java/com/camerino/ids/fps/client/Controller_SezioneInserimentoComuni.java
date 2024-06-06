package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.camerino.ids.core.data.utenti.ClsGestoreDellaPiattaforma;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.camerino.ids.core.data.utils.Posizione;
import com.camerino.ids.fps.client.utils.Utils;
import com.camerino.ids.fps.client.visual.ClsCuratoreVisual;
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

public class Controller_SezioneInserimentoComuni implements Initializable
{
    //region Elementi FXML
    @FXML
    TableView<ClsCuratoreVisual> elencoCuratori;
    @FXML
    TableColumn<ClsCuratoreVisual,String> idCuratore;
    @FXML
    TableColumn<ClsCuratoreVisual, String> usernameCuratore;

    @FXML
    TextField textFieldCuratori,nome,descrizione,coordinataX,coordinataY,abitanti,superficie;

    @FXML
    Button home, conferma;
    //endregion

    List<ClsCuratore> curatori;
    Utils u = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        curatori = ((ClsGestoreDellaPiattaforma)Controller_SezioneLogin.UTENTE).getUtentiByRuolo(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR)
                .stream().map(u->{
                    ClsCuratore tmp = new ClsCuratore();
                    tmp.setId(u.getId());
                    return tmp;
                }).toList();

        setCuratori(curatori);

        //region setting up colonne tabella
        idCuratore.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        usernameCuratore.setCellValueFactory(
                new PropertyValueFactory<>("username"));
        //endregion
    }

    public void inserisciComune(MouseEvent mouseEvent)
    {
        ClsComune comune = new ClsComune();

        String curatoriCoinvolti = u.getValueFromTextField(textFieldCuratori);
        String[] curatoriCoinvoltiArray = this.convertiCuratoriCoinvoltiInArray(curatoriCoinvolti);

        List<ClsCuratore> curatoriAssociatiToComune = new ArrayList<>();

        if(curatoriCoinvoltiArray.length > 0 &&
                !Objects.equals(u.getValueFromTextField(coordinataX), "") &&
                !Objects.equals(u.getValueFromTextField(coordinataY), "") &&
                !Objects.equals(u.getValueFromTextField(descrizione), "") &&
                !Objects.equals(u.getValueFromTextField(nome), "") &&
                !Objects.equals(u.getValueFromTextField(abitanti), null) &&
                !Objects.equals(u.getValueFromTextField(superficie), ""))
        {
            comune.setIdCreatore(Controller_SezioneLogin.utente.getUsername());
            comune.setPosizione(new Posizione(Double.parseDouble(u.getValueFromTextField(coordinataX)), Double.parseDouble(u.getValueFromTextField(coordinataY))));
            comune.setNome(u.getValueFromTextField(nome));
            comune.setDescrizione(u.getValueFromTextField(descrizione));
            comune.setAbitanti(Integer.parseInt(u.getValueFromTextField(abitanti)));
            comune.setSuperficie(Double.parseDouble(u.getValueFromTextField(superficie)));

            comune.setCuratoriAssociati(this.ottieniCuratoriAssociati(new ArrayList<>(curatori),curatoriCoinvoltiArray));//todo:aggiungere

            ((ClsGestoreDellaPiattaforma)Controller_SezioneLogin.UTENTE).inserisciComune(comune);
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("AGGIUNTO");
            alert.setContentText(comune.visualizzaComune());
            alert.show();
        }
        else
        {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Devi inserire tutte le informazioni");
            alert.show();
        }
    }

    public List<ClsCuratore> ottieniCuratoriAssociati (List<ClsCuratore> curatori, String[] idCuratori)
    {
        List<ClsCuratore> tmp = new ArrayList<>();
        for(int i = 0; i < curatori.size();i++)
        {
            for(int j = 0; j < idCuratori.length; j++)
            {
                if(Objects.equals(curatori.get(i).getId(), idCuratori[j]))
                {
                    tmp.add(curatori.get(i));
                }
            }
        }
        return tmp;
    }

    private void setCuratori (List<ClsCuratore> curatori)
    {
        for(int i = 0; i<curatori.size();i++)
        {
            ClsCuratoreVisual c = u.convertFromClsCuratore(curatori.get(i));
            elencoCuratori.getItems().add(c);
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
        String[] nuova = this.pulisciIDnonPresenti(new ArrayList<>(Arrays.asList(tmp)), curatori);;
        return nuova;
    }

    private String[] pulisciIDnonPresenti (List<String> input, List<ClsCuratore> Curatori)
    {
        // Create a HashSet from the string values of objects in listA for faster lookup
        HashSet<String> setAValues = new HashSet<>();
        for (ClsCuratore obj : Curatori) {
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

    private List<String> rimuoviDuplicati(List<String> listaOriginale) {
        // Creiamo un HashSet che conterrà solo elementi unici
        HashSet<String> setUnici = new HashSet<>(listaOriginale);

        // Ricostruiamo l'ArrayList senza duplicati
        List<String> listaSenzaDuplicati = new ArrayList<>(setUnici);

        return listaSenzaDuplicati;
    }

    private List<ClsCuratore> ottieniCuratori (String[] idCuratori)
    {
        List<ClsCuratore> tmp = new ArrayList<ClsCuratore>();
        for(int i = 0; i < this.curatori.size(); i++)
        {
            for(int k = 0; k< idCuratori.length; k++)
            {
                if(Objects.equals(curatori.get(i).getId(), idCuratori[k]))
                {
                    tmp.add(curatori.get(i));
                }
            }
        }

        return tmp;
    }
}
