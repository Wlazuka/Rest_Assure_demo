package com.jsonplaceholder.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.log4testng.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

    public static <T> List<T> jsonArrayToObject(final JSONArray jsonArr, final Class<T> objectClass) {
        List<T> object;
        LOG.debug("Parsing JSON to " + objectClass);
        try {
            object = MAPPER.readValue(jsonArr.toString(), MAPPER.getTypeFactory().constructCollectionType(List.class, objectClass));
            LOG.debug("Result object : " + object);
        } catch (IOException e) {
            String msg = "Cannot parse JSON [" + jsonArr + "] to List of objects [" + objectClass + "].";
            LOG.error(msg);
            throw new IllegalStateException(e);
        }
        return object;
    }

    public static JSONObject parseJSONFile(String filePath){
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(content);
    }

}
