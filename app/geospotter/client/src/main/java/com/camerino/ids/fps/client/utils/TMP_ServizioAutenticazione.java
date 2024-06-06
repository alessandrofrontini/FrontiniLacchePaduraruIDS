package com.camerino.ids.fps.client.utils;

import com.camerino.ids.core.data.utenti.*;
import com.camerino.ids.core.data.utils.Credenziali;
import com.camerino.ids.fps.client.visual.ClsUtenteJWTDecode;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class TMP_ServizioAutenticazione
{
    public static List<ClsTuristaAutenticato> utenti = new ArrayList<>(); //Tutti gli utenti
    public static List<ClsTuristaAutenticato> utentiLegit = new ArrayList<>(); //Utenti gestibili
    public static String tmpJWT = "DJASIDJIQ09I4902JDIOAR8932";

    static
    {
        //Tutti gli utenti
        //region contributor
        ClsContributor con = new ClsContributor();
        con.setId(1L);
        Credenziali c1 = new Credenziali();
        c1.setUsername("c"); // Username univoco per ogni oggetto
        c1.setPassword("c"); // Password univoca per ogni oggetto

        con.setCredenziali(c1);

        con.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR);
        con.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR.getValue() - 5);
        utenti.add(con);
        //endregion

        //region contributor auth

            ClsContributorAutorizzato conAuth = new ClsContributorAutorizzato();
            conAuth.setId(2L);
            Credenziali c2 = new Credenziali();
            c2.setUsername("ca"); // Username univoco per ogni oggetto
            c2.setPassword("ca"); // Password univoca per ogni oggetto

            conAuth.setCredenziali(c2);

            conAuth.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO);
            conAuth.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO.getValue() - 100);

            utenti.add(conAuth);

        //endregion

        //region animatore

            ClsAnimatore anim = new ClsAnimatore();
            anim.setId(3L);
            Credenziali c3 = new Credenziali();
            c3.setUsername("a"); // Username univoco per ogni oggetto
            c3.setPassword("a"); // Password univoca per ogni oggetto

            anim.setCredenziali(c3);

            anim.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE);
            anim.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE.getValue());

            utenti.add(anim);

        //endregion

        //region curatore

            ClsCuratore cur = new ClsCuratore();
            cur.setId(4L);
            Credenziali c4 = new Credenziali();
            c4.setUsername("cur"); // Username univoco per ogni oggetto
            c4.setPassword("cur"); // Password univoca per ogni oggetto

            cur.setCredenziali(c4);

            cur.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.CURATORE);
            cur.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.CURATORE.getValue());

            utenti.add(cur);

        //endregion

        //region gestore

            ClsGestoreDellaPiattaforma gdp = new ClsGestoreDellaPiattaforma();
            gdp.setId(5L);
            Credenziali c8 = new Credenziali();
            c8.setUsername("gdp"); // Username univoco per ogni oggetto
            c8.setPassword("gdp"); // Password univoca per ogni oggetto

            gdp.setCredenziali(c8);

            gdp.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.GESTORE_DELLA_PIATTAFORMA);
            gdp.setPunteggio(ClsTuristaAutenticato.eRUOLI_UTENTE.GESTORE_DELLA_PIATTAFORMA.getValue());

            utenti.add(gdp);

        //endregion


        //Utenti gestibili
        for(int i = 0; i < utenti.size();i++)
        {
            //Tutti animatori o ruoli minori
            if(utenti.get(i).getRuoloUtente().getValue() <= 1000)
            {
                utentiLegit.add(utenti.get(i));
            }
        }
    }

    public static boolean login (Credenziali c)
    {
        utenti = mergeListsExcludingDuplicates(utenti,utentiLegit);

        for(int i = 0;i < utenti.size();i++)
        {
            if(Objects.equals(utenti.get(i).getCredenziali().getUsername(), c.getUsername())
              && Objects.equals(utenti.get(i).getCredenziali().getPassword(), c.getPassword()))
            {
                return true;
            }
        }
        return false;
    }

    public static ClsUtenteJWTDecode retrieveUtente (Credenziali c)
    {
        ClsUtenteJWTDecode utente = new ClsUtenteJWTDecode();

        //LOG PER TURISTA AUT = ta:ta
//        if(Objects.equals(c.getUsername(), "ta") && Objects.equals(c.getPassword(), "ta"))
//        {
//            utente.setJwt(tmpJWT);
//            utente.setUsername(c.getUsername());
//            utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.TURISTA_AUTENTICATO);
//            return utente;
//        }
//
//        //LOG PER CONTRIBUTOR = c:c
//        if(Objects.equals(c.getUsername(), "c") && Objects.equals(c.getPassword(), "c"))
//        {
//            utente.setJwt(tmpJWT);
//            utente.setUsername(c.getUsername());
//            utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR);
//            return utente;
//        }
//
//        //LOG PER CONTRIBUTOR AUT = ca:ca
//        if(Objects.equals(c.getUsername(), "ca") && Objects.equals(c.getPassword(), "ca"))
//        {
//            utente.setJwt(tmpJWT);
//            utente.setUsername(c.getUsername());
//            utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.CONTRIBUTOR_AUTORIZZATO);
//            return utente;
//        }
//
//        //LOG PER ANIMATORE = a:a
//        if(Objects.equals(c.getUsername(), "a") && Objects.equals(c.getPassword(), "a"))
//        {
//            utente.setJwt(tmpJWT);
//            utente.setUsername(c.getUsername());
//            utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.ANIMATORE);
//            return utente;
//        }
//
//        //LOG PER CURATORE = cur:cur
//        if(Objects.equals(c.getUsername(), "cur") && Objects.equals(c.getPassword(), "cur"))
//        {
//            utente.setJwt(tmpJWT);
//            utente.setUsername(c.getUsername());
//            utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.CURATORE);
//            return utente;
//        }
//
//        //LOG PER GDP = gdp:gdp
//        if(Objects.equals(c.getUsername(), "gdp") && Objects.equals(c.getPassword(), "gdp"))
//        {
//            utente.setJwt(tmpJWT);
//            utente.setUsername(c.getUsername());
//            utente.setRuolo(ClsTuristaAutenticato.eRUOLO_UTENTE.GESTORE_DELLA_PIATTAFORMA);
//            return utente;
//        }

        for(int i = 0; i<utenti.size();i++)
        {
            if(Objects.equals(utenti.get(i).getCredenziali().getUsername(), c.getUsername())
                && Objects.equals(utenti.get(i).getCredenziali().getPassword(), c.getPassword()))
            {
                utente.setJwt(tmpJWT);
                utente.setUsername(utenti.get(i).getCredenziali().getUsername());
                utente.setRuolo(utenti.get(i).getRuoloUtente());
                return utente;
            }
        }

        return null;
    }

    public static boolean inserisciUtente (ClsTuristaAutenticato utente)
    {
        TMP_ServizioAutenticazione.utentiLegit.add(utente);
        return true;
    }

    public static boolean eliminaUtente (Long id)
    {
        for(int i = 0; i < utentiLegit.size(); i++)
        {
            if(Objects.equals(utentiLegit.get(i).getId(), id))
            {
                utentiLegit.remove(i);

                for(int k = 0; k < utenti.size();k++)
                {
                    if(Objects.equals(utenti.get(k).getId(), id))
                    {
                        utenti.remove(k);
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public static boolean modificaUtente (ClsTuristaAutenticato nuovo, Long id)
    {
        for(int i = 0; i < utentiLegit.size(); i++)
        {
            if(Objects.equals(utentiLegit.get(i).getId(), id))
            {
                utentiLegit.set(i,nuovo);

                for(int k = 0; k < utenti.size();k++)
                {
                    if(Objects.equals(utenti.get(k).getId(), id))
                    {
                        utenti.remove(k);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void modificaPunteggioByNumero (ClsTuristaAutenticato utente, String punteggio, String sceltaAzione)
    {
        ClsTuristaAutenticato utenteBackUp = utente.clone();

        switch(sceltaAzione)
        {
            case "UP-RANK":
                if(punteggio != null && !Objects.equals(punteggio, ""))
                {
                    utente.setRuoloUtente(punteggioToEnum(utente.getPunteggio() + Integer.parseInt(punteggio)));
                    utente.setPunteggio(punteggioValido(utente.getPunteggio() + Integer.parseInt(punteggio)));
                    for(int i = 0; i < utentiLegit.size();i++)
                    {
                        if(Objects.equals(utente.getCredenziali().getUsername(), utentiLegit.get(i).getCredenziali().getUsername()))
                        {
                            utentiLegit.set(i,utente);
                        }
                    }

                    Alert alerttt = new Alert (Alert.AlertType.CONFIRMATION);
                    alerttt.setTitle("OK");
                    alerttt.setContentText("Prima: \n" + utenteBackUp.visualizzaUtente() + "\nDopo: \n" + utente.visualizzaUtente());
                    alerttt.show();

                    break;

                }
                else
                {
                    break;
                }


            case "DOWN-RANK":
                if(punteggio != null && !Objects.equals(punteggio, ""))
                {
                    utente.setRuoloUtente(punteggioToEnum(utente.getPunteggio() - Integer.parseInt(punteggio)));
                    utente.setPunteggio(punteggioValido(utente.getPunteggio() - Integer.parseInt(punteggio)));

                    for(int i = 0; i < utentiLegit.size();i++)
                    {
                        if(Objects.equals(utente.getCredenziali().getUsername(), utentiLegit.get(i).getCredenziali().getUsername()))
                        {
                            utentiLegit.set(i,utente);
                        }
                    }

                    Alert alerttt = new Alert (Alert.AlertType.CONFIRMATION);
                    alerttt.setTitle("OK");
                    alerttt.setContentText("Prima: \n" + utenteBackUp.visualizzaUtente() + "\nDopo: \n" + utente.visualizzaUtente());
                    alerttt.show();

                    break;
                }
                else
                {

                    break;
                }


            case "RESET-RANK":
                utente.setPunteggio(0);
                utente.setRuoloUtente(ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO);
                Alert alerttt = new Alert (Alert.AlertType.CONFIRMATION);
                alerttt.setTitle("OK");
                alerttt.setContentText("Prima: \n" + utenteBackUp.visualizzaUtente() + "\nDopo: \n" + utente.visualizzaUtente());
                alerttt.show();
                break;

            default:
                break;

        }
    }


    public static void modificaPunteggioByEnum (ClsTuristaAutenticato utente, ClsTuristaAutenticato.eRUOLI_UTENTE ruolo)
    {
        ClsTuristaAutenticato utenteBackUp = utente.clone();

        utente.setPunteggio(ruolo.getValue());
        utente.setRuoloUtente(ruolo);

        for(int i = 0; i < utentiLegit.size();i++)
        {
            if(Objects.equals(utente.getCredenziali().getUsername(), utentiLegit.get(i).getCredenziali().getUsername()))
            {
                utentiLegit.set(i,utente);
            }
        }

        Alert alerttt = new Alert (Alert.AlertType.CONFIRMATION);
        alerttt.setTitle("OK");
        alerttt.setContentText("Prima: \n" + utenteBackUp.visualizzaUtente() + "\nDopo: \n" + utente.visualizzaUtente());
        alerttt.show();
    }

    public static ClsTuristaAutenticato.eRUOLI_UTENTE punteggioToEnum (int punteggio)
    {
        if(punteggio>=0 && punteggio<50)
        {
            return ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO;
        }
        if(punteggio>=50 && punteggio<600)
        {
            return ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR;
        }
        if(punteggio>=600 && punteggio<999)
        {
            return ClsTuristaAutenticato.eRUOLI_UTENTE.CONTRIBUTOR_AUTORIZZATO;
        }
        if(punteggio>=1000)
        {
            return ClsTuristaAutenticato.eRUOLI_UTENTE.ANIMATORE;
        }
        if(punteggio < 0)
        {
            return ClsTuristaAutenticato.eRUOLI_UTENTE.TURISTA_AUTENTICATO;
        }


        return null;

    }

    public static int punteggioValido (int punteggio)
    {
      if(punteggio < 0)
          return 0;

      if(punteggio>=0 && punteggio<=1000)
          return punteggio;

      if (punteggio > 1000)
          return 1000;

      return -1;
    }

    public static <ClsTuristaAutenticato> List<ClsTuristaAutenticato> mergeListsExcludingDuplicates(List<ClsTuristaAutenticato> list1, List<ClsTuristaAutenticato> list2) {
        HashSet<ClsTuristaAutenticato> set = new HashSet<>();
        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<>(set);
    }

}
