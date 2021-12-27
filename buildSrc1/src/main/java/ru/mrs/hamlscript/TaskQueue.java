package ru.mrs.hamlscript;

import org.gradle.internal.impldep.com.fasterxml.jackson.core.JsonProcessingException;
import org.gradle.internal.impldep.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.gradle.internal.impldep.com.fasterxml.jackson.databind.ObjectMapper;

public class TaskQueue {

    String json, json1;
    HashMap<String, String> sourcesQueue = new HashMap<>();

    public TaskQueue(String first) throws IOException {
        json = new String(Files.readAllBytes(Paths.get(first)));
        try {
            Map<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

}
