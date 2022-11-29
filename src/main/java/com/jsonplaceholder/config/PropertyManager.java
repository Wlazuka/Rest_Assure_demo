package com.jsonplaceholder.config;

import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyManager {

    private static final Logger LOG = Logger.getLogger(PropertyManager.class);
    private static final String PATH = "src/test/resources/test.properties";
    private static final Properties properties = new Properties();

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    static {
        try (FileInputStream inputStream = new FileInputStream(PATH)){
            properties.load(inputStream);
        } catch (Throwable e) {
            String msg = "Can't initialize configuration properties bundle.";
            LOG.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    public static String getBaseURL() {
        return properties.getProperty("base.url");
    }

    public static String getAllureResultsDirectory() {
        return properties.getProperty("allure.results.directory");
    }
}
