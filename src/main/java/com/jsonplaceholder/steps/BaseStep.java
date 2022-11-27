package com.jsonplaceholder.steps;

import com.jsonplaceholder.endpoints.BaseEndpoint;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.*;

import static org.apache.http.HttpStatus.SC_OK;

class BaseStep {

    protected String baseURI;

    public BaseStep() {
       baseURI = BaseEndpoint.BASE_URL;
    }

    @Step("Verify status code 200")
    public void verifySuccessfulStatusCode(Response response) {
        assertThat(response.getStatusCode())
                .as("Response status code")
                .isEqualTo(SC_OK);
    }

    @Step("Verify status code {1}")
    public void verifyStatusCode(Response response, int statusCode) {
        assertThat(response.getStatusCode())
                .as("Response status code")
                .isEqualTo(statusCode);
    }

    public Object parseResponseToObject(Response response) {
        return response.as(Object.class);
    }
}
