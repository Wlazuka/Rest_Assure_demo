package com.jsonplaceholder.steps;

import com.jsonplaceholder.endpoints.CommentsEndpoint;
import com.jsonplaceholder.models.Comment;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.log4testng.Logger;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CommentStep extends BaseStep {

    private static final Logger LOG = Logger.getLogger(CommentStep.class);
    private final String COMMENTS_URI = CommentsEndpoint.commentsEndpointURI;
    private final String URI = baseURI + COMMENTS_URI;

    public CommentStep() {
    }

    @Step("Get comments response with PostId: {0}")
    public Response getCommentsWithPostId(int id) {
        return given()
                .param("postId", id)
                .get(URI);
    }

    @Step("Get comments response with PostId: {0}")
    public Response getCommentsWithPostId(String id) {
        RequestSpecification requestSpecification = given().param("postId", id);
        requestSpecification.log().method().log().uri().log().parameters();

        return requestSpecification.get(URI);
    }

    @Step("Get comments List with parametr postId: {0}")
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
