package com.exercise.pitufos.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class JsonUtil {
    static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //otra opcion sería algo como
        
        String str = mapper.writeValueAsString(object);
        return mapper.writeValueAsBytes(object);
    }
}
