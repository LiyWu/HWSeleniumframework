package com.helen.framework.helpers;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    private static final Logger LOG = LoggerFactory.getLogger(JsonHelper.class);
    private static final String RESOURCEPATH;
    public static List<String> products = new ArrayList<>();
    static{
        String fs = File.separator;
        RESOURCEPATH = System.getProperty("user.dir") + fs + "src" + fs + "test" + fs + "resources" + fs + "data" + fs;
        try {
            products = loadProducts("_products.json");
        } catch (Exception e) {
            LOG.error("failing to load product:" +e.getMessage());
        }
    }

    public static List<String> loadProducts(String fileName) throws Exception{
        List<String> products = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        JsonArray productList = (JsonArray) jsonParser.parse(new FileReader(getResourcePath(fileName)));
        for (Object obj : productList) {
            JsonObject product = (JsonObject) obj;
            Gson gson = new GsonBuilder().create();
           // products.add(gson.fromJson(product.toString()).Product);
        }
        return products;
    }
    private static String getResourcePath(String fileName) {
        String path;
        if (fileName.contains("products")||fileName.contains("GiftCard")||fileName.contains("messages")||fileName.contains("card-details")) {
            path = RESOURCEPATH + fileName;
        } else {
            path = RESOURCEPATH + fileName;
        }
        return path;
    }
}
