package com.camerino.ids.fps.client.api;

import com.camerino.ids.core.data.contenuti.ClsComune;
import com.camerino.ids.core.data.contenuti.ClsItinerario;
import com.camerino.ids.core.data.utenti.ClsTurista;
import com.camerino.ids.core.data.utenti.ClsTuristaAutenticato;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.util.Pair;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static com.camerino.ids.fps.client.api.BaseURL.BASE_URL;

public class ApiItinerari implements IApi<ClsItinerario> {
    static final URI endpoint = URI.create(BASE_URL+"/itinerari");

    @Override
    public ArrayList<ClsItinerario> Get(ClsTurista user, String query){
        if(query==null)
            query = "";

        HttpRequest.Builder request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(String.format("%s?%s",endpoint,query)))
                .GET();
        if (user instanceof ClsTuristaAutenticato)
            request.header("Authorization", FakeTokens.getToken(user));

        HttpResponse<String> response = execute(request.build());
        if(response.statusCode() != 200)
            return new ArrayList<>();

        return deserialize(response.body(), new TypeReference<>(){});
    }
    @Override
    public boolean Post(ClsTurista user, ClsItinerario nodo){
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", FakeTokens.getToken(user))
                .header("Content-Type", "application/json")
                .POST(createBody(nodo))
                .uri(endpoint)
                .build();

        return execute(request).statusCode() == 200;
    }
    @Override
    public boolean Put(ClsTurista user, ClsItinerario nodo){
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", FakeTokens.getToken(user))
                .header("Content-Type", "application/json")
                .PUT(createBody(nodo))
                .uri(endpoint)
                .build();
        return execute(request).statusCode() == 200;
    }
    @Override
    public boolean Delete(ClsTurista user, Pair<String, String> name_id){
        if(name_id == null)
            return false;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s?%s=%s",endpoint,name_id.getKey(),name_id.getValue())))
                .header("Content-Type", "application/json")
                .header("Authorization", FakeTokens.getToken(user))
                .DELETE()
                .build();

        return execute(request).statusCode() == 200;
    }
}
