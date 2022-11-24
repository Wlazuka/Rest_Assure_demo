package com.jsonplaceholder.endpoints;

import com.jsonplaceholder.config.PropertyManager;

public class BaseEndpoint {

    public static final String BASE_URL = PropertyManager.getProperty("base.url");

}
