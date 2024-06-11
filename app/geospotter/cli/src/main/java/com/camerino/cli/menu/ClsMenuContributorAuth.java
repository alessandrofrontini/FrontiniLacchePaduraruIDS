package com.camerino.cli.menu;

import com.camerino.ids.core.data.utenti.ClsContributorAutorizzato;

import java.util.Scanner;

public class ClsMenuContributorAuth implements IMenu{
    private ClsContributorAutorizzato user;
    private ClsMenuContributor menuc;
    public ClsMenuContributor getMenuc(){return menuc;}
    Scanner in = new Scanner(System.in);
    public ClsMenuContributorAuth(ClsContributorAutorizzato c){user = c;}
    /**
     * siccome il contributor autorizzato effettua le stesse azioni del contributor senza il processo di verifica, il menu rimane lo stesso.
     * A cambiare sono i comportamenti nei confronti dei Contenuti, descritti nella classe ClsContributorAutorizzato.
     */
    @Override
    public void menu() {
       menuc = new ClsMenuContributor(user);
       menuc.menu();
    }

}
