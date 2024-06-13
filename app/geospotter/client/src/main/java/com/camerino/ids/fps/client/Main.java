package com.camerino.ids.fps.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(new ClsCuratore()));
        HelloApplication.main(args);
    }
}
