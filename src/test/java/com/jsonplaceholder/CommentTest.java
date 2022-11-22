package com.jsonplaceholder;

import com.jsonplaceholder.models.Comment;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CommentTest extends BaseTest{

    private static final Logger LOG = Logger.getLogger(CommentTest.class);

    @Test(description = "GET /comments?postId=1 response with code 200")
    public void GET_CommentStatus200Test() {
        given()
                .param("postId", 1)
                .get(comments)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test(description = "GET /comments?postId=1 response with code 200")
    public void GET_CommentBodyTest() {

        List<Comment> commentsList = RestAssured
                .given()
                .param("postId", 1)
                .get(comments)
                .then()
                .extract()
                .body().as(new TypeRef<>() {});
        Assert.assertEquals(commentsList.size(), 5, "There should be 5 comments for post 1");
    }
}