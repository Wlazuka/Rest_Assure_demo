package com.jsonplaceholder;

import com.jsonplaceholder.endpoints.BaseEndpoint;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;

public class BaseTest {

    private static final Logger LOG = Logger.getLogger(BaseTest.class);

    @BeforeTest
    public void setup() {
//        RestAssured.baseURI = BaseEndpoint.BASE_URL;
    }

    @AfterTest
    public void cleanUp() {
    }
}
