package com.jsonplaceholder;

import com.jsonplaceholder.apiService.JsonPlaceholderEndpoints;
import io.restassured.RestAssured;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;

public class BaseTest {

    private static final Logger LOG = Logger.getLogger(BaseTest.class);

    protected String posts;
    protected String comments;

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = JsonPlaceholderEndpoints.BASE_URL;
        posts = JsonPlaceholderEndpoints.POSTS;
        comments = JsonPlaceholderEndpoints.COMMENTS;
    }

    @AfterTest
    public void cleanUp() {
    }
}
