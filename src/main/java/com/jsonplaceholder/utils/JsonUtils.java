package com.jsonplaceholder.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {

    private static final Logger LOG = Logger.getLogger(JsonUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    protected JsonUtils() {
    }

    public static <T> Object jsonToObject(final JSONObject json, final Class<T> objectClass) {
        Object object;
        LOG.debug("Parsing JSON to " + objectClass);
        try {
            object = MAPPER.readValue(json.toString(), objectClass);
            LOG.debug("Result object : " + object.toString().replaceAll("&quot;", "\""));
        } catch (IOException e) {
            String msg = "Cannot parse JSON [" + json + "] to object [" + objectClass + "].";
            LOG.error(msg);
            throw new IllegalStateException(e);
        }
        return object;
    }


    public static JSONObject parseJSONFile(String filename){

        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(content);
    }
}
