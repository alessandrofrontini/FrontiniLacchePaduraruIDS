package com.camerino.ids.fps.client.api;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static com.camerino.ids.fps.client.api.BaseURL.BASE_URL;

public class ApiComuni {
    static final URI endpoint = URI.create(BASE_URL+"/comuni");

    public static ArrayList<ClsComune> GetAllNodi(ClsTurista user){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(endpoint).GET();
        if(user instanceof ClsTuristaAutenticato)
            builder.header("Authorization", FakeTokens.getToken(user));
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response;;
        try {
            response = httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        ArrayList<ClsComune> data;
        try {
            data = mapper.readValue(response.body(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
