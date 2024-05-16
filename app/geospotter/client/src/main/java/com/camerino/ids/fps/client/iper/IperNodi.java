package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class IperNodi implements IPersistenceModel<ClsNodo> {
    URL url = new URL("http://8080/api/v1/nodi");

    public IperNodi() throws MalformedURLException {
    }
//https://www.baeldung.com/java-http-request
    @Override
    public ArrayList<ClsNodo> get(HashMap<String, Object> filters) {
        try {//Mega try catch eheeh
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            if (false)
                con.setRequestProperty("Bearer", "TOKEN_JWT");
            //A quanto pare si invia coì la richiesta http
            //https://stackoverflow.com/questions/1359689/how-to-send-http-request-in-java
            Reader streamReader = new InputStreamReader(con.getInputStream());
            if (con.getResponseCode() != 200)
                return null;//Eccezione? Array vuoto? Null?
            con.disconnect();
            //Processare JSON
            //Non mi sembra proprio giusto leggere la response così ma basta che funzioni :P
            //https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
            Scanner s = new Scanner(streamReader).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            System.out.println(result);//Bisogna aggiungere con Jakarta la deserializzazione
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean update(HashMap<String, Object> filters, ClsNodo object) {
        return false;
    }

    @Override
    public boolean insert(ClsNodo object) {
        return false;
    }

    @Override
    public boolean delete(HashMap<String, Object> filters) {
        return false;
    }
}
