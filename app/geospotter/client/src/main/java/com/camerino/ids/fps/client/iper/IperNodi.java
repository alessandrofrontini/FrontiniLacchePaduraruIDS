package com.camerino.ids.fps.client.iper;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.persistence.IPersistenceModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
//https://www.youtube.com/watch?v=9oq7Y8n1t00
public class IperNodi implements IPersistenceModel<ClsNodo> {
    String url = "http://localhost:8080/api/v1/nodi";
    HttpRequest getNodi = HttpRequest.newBuilder().uri(new URI(url)).GET()
            //.header("Authorization", "TOKEN SE C'è")
            .build();
    ObjectMapper mapper = new ObjectMapper();
    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public IperNodi() throws MalformedURLException, URISyntaxException {
    }
    @Override
    public ArrayList<ClsNodo> get(HashMap<String, Object> filters) {
        HttpClient httpClient = HttpClient.newHttpClient();
        ArrayList<ClsNodo> tmp;
        try {
            HttpResponse<String> resp = httpClient.send(getNodi, HttpResponse.BodyHandlers.ofString());
            System.out.println(resp.body());
            System.out.println(new ObjectMapper().readValue(resp.body(), ArrayList.class));
            tmp = mapper.readValue(resp.body(), new TypeReference<ArrayList<ClsNodo>>(){});
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return tmp;
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
