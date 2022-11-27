package com.jsonplaceholder;

import com.jsonplaceholder.steps.CommentStep;
import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class CommentTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(CommentTest.class);
    private final CommentStep commentStep = new CommentStep();

    @Test
    @Description("GET /comments?postId=1 response with code 200")
    void verifyIfProvidingCorrectIdValueGivesStatusOK() {
        //GIVEN
        int id = 1;

        //WHEN
        var response = commentStep.getCommentsWithPostId(id);

        //THEN
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
    }

    @Test
    @Description("GET /comments?postId=a response with code 400")
    void verifyIfProvidingIncorrectIdValueGivesBadResponse() {
        //GIVEN
        String id = "1";

        //WHEN
        var response = commentStep.getCommentsWithPostId(id);

        //THEN
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Description("GET /comments?postId=1 ")
    void verifyNumberOfCommentsForPost() {
        //GIVEN
        int id = 1;
        int expectedCommentsAmount = 5;

        //WHEN
        var response = commentStep.getListOfCommentsWithPostIdAsParam(id);

        //THEN
        Assert.assertEquals(response.size(), 5, String.format("There should be %s comments for post %s", expectedCommentsAmount, id));
    }
}