package com.helen.services.restAPI;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonHandle {
    public static void main(String arGS[]) throws FileNotFoundException {
        Gson gson = new Gson();
        String testjson = "{\"name\":\"helen\"}";
        String s1 = gson.fromJson(testjson,JsonObject.class).toString();

        System.out.println("s1:" + s1);

        //ReadJson from json file
        JsonReader reader = new JsonReader(new FileReader("src/test/resources/user.json"));
        String s = JsonParser.parseReader(reader).getAsJsonObject().get("users").getAsJsonArray().get(0).toString();
        System.out.println("s:" + s);
    }

}
