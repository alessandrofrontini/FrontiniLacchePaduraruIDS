package com.camerino.ids.fps.client;

import com.camerino.ids.core.data.utenti.ClsCuratore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLOutput;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(new ClsCuratore()));
        HelloApplication.main(args);
    }
}
