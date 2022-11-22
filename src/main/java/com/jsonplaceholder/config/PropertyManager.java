package com.jsonplaceholder.config;

import org.testng.log4testng.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyManager {

    private static final Logger LOG = Logger.getLogger(PropertyManager.class);
    private static final String PATH = "test.properties";
    private static final Properties properties = new Properties();

    public PropertyManager() {
        loadProperties(System.getProperty(PATH));
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    private void loadProperties(String filePath) {
        InputStream inputStream = PropertyManager.class.getClassLoader().getResourceAsStream(filePath);
        try {
            assert inputStream != null;
            try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                properties.load(reader);
            }
        } catch (Throwable e) {
            String msg = "Can't initialize configuration properties bundle.";
            LOG.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
