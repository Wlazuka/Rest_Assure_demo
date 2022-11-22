package com.jsonplaceholder.apiService;

import com.jsonplaceholder.config.PropertyManager;

public class BaseEndpoint {

    public static final String BASE_URL2 = PropertyManager.getProperty("base.url");
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";


    public static void main(String[] args) {
        System.out.println(BASE_URL2);
    }
}
