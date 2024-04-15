package com.camerino.ids.fps.geospotter.cli.menu;

import com.camerino.ids.fps.geospotter.server.data.contenuti.ClsNodo;
import com.camerino.ids.fps.geospotter.server.data.utils.Posizione;

import java.util.Scanner;

import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.print;
import static com.camerino.ids.fps.geospotter.cli.loggers.ClsConsoleLogger.println;

public class Input {
    public static ClsNodo richiediNodo(){
        boolean ok = false;
        Scanner in = new Scanner(System.in);
        ClsNodo nodo = new ClsNodo();
        Posizione pos = new Posizione();
        while (!ok){
            print("Inserisci nome: ");
            nodo.setNome(in.nextLine());
            print("Inserisci tipo:\n 1)Commerciale\n2)Culturale\n3)Culinario\n>> ");
            switch (in.nextLine()){
                case "1" -> nodo.setTipologiaNodo(ClsNodo.TipologiaNodo.COMMERCIALE);
                case "2" -> nodo.setTipologiaNodo(ClsNodo.TipologiaNodo.CULTURALE);
                case "3" -> nodo.setTipologiaNodo(ClsNodo.TipologiaNodo.CULINARIO);
            }
            print("Inserisci coordinata X del nodo: ");
            pos.setX(in.nextDouble());
            print("Inserisci coordinata Y del nodo: ");
            pos.setY(in.nextDouble());
            nodo.setPosizione(pos);
            print("Inserisci id del comune di appartenenza: ");
            nodo.setIdComune(in.nextLine());
            //TODO: aggiungere inserimento "aTempo" e durata
            //TODO: aggiungere eventuali controlli sui dati inseriti
            ok = true;
        }
        return nodo;
    }
}
