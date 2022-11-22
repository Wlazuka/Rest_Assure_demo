package com.jsonplaceholder;

import com.jsonplaceholder.apiService.JsonPlaceholderEndpoints;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.log4testng.Logger;

public class BaseTest {

    private static final Logger LOG = Logger.getLogger(BaseTest.class);

    public String posts;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = JsonPlaceholderEndpoints.BASE_URL;
        posts = JsonPlaceholderEndpoints.POSTS;
    }

    @AfterClass
    public void cleanUp() {
    }
}
