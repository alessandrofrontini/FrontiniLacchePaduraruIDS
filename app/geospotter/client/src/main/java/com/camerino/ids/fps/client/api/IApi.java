package com.camerino.ids.fps.client.api;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.core.data.utenti.ClsContributor;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public interface IApi<T> {
    ArrayList<T> Get(ClsTurista user, String query);
    boolean Post(ClsContributor user, T object);
    boolean Put(ClsContributor user, T object);
    boolean Delete(ClsTurista user, Pair<String, String> name_id);

    default HttpRequest.BodyPublisher createBody(T object){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HttpRequest.BodyPublisher bodyPublisher = null;
        try {
            bodyPublisher = HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return bodyPublisher;
    }
    default HttpResponse<String> execute(HttpRequest request){
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    default ArrayList<T> deserialize(String responseBody, TypeReference<List<T>> type){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return new ArrayList<>(mapper.readValue(responseBody, type));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
}

