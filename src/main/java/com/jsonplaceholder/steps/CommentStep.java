package com.jsonplaceholder.steps;

import com.jsonplaceholder.endpoints.CommentsEndpoint;
import com.jsonplaceholder.models.Comment;
import com.jsonplaceholder.models.Post;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CommentStep extends BaseStep {

    private final String COMMENTS_URI = CommentsEndpoint.commentsEndpointURI;
    private final String URI = baseURI + COMMENTS_URI;

    public CommentStep() {
    }

    @Step("Get comments response with PostId: {1}")
    public Response getCommentsWithPostId(int id) {
        return given()
                .param("postId", id)
                .get(URI);
    }

    @Step("Get comments response with PostId: {1}")
    public Response getCommentsWithPostId(String id) {
        return given()
                .param("postId", id)
                .get(URI);
    }

    @Step("Get comments List with parametr postId: {1}")
    public List<Comment> getListOfCommentsWithPostIdAsParam(int id) {
        return given()
                .param("postId", id)
                .get(URI)
                .then()
                .extract()
                .body().as(new TypeRef<>() {
                });
    }

    @Override
    @Step("Parse response to Comment")
    public Comment parseResponseToObject(Response response) {
        return response.as(Comment.class);
    }
}
