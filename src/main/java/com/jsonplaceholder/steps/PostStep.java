package com.jsonplaceholder.steps;

import com.jsonplaceholder.endpoints.PostsEndpoint;
import com.jsonplaceholder.models.Post;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostStep extends BaseStep {

    private final String POSTS_URI = PostsEndpoint.postsEndpointURI;
    private final String URI = baseURI + POSTS_URI;

    @Step("GET Post response with postID: {1}")
    public Response getPostWithId(int id) {
        return given()
                .param("postId", id)
                .get(URI);
    }

    @Step ("POST new post: {body}")
    public Response postNewPost(String body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(URI);
    }

    @Step ("POST new post: {jsonObject})")
    public Response postNewPost(JSONObject jsonObject) {
        return given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .post(URI);
    }

    @Step ("POST new post: {postObject})")
    public Response postNewPost(Post postObject) {
        return given()
                .contentType(ContentType.JSON)
                .body(postObject)
                .post(URI);
    }

    @Step ("DELETE post with postID: {id})")
    public Response deletePostWithId(int id) {
        return given()
                .basePath(String.valueOf(id))
                .delete(URI);
    }

    @Override
    @Step("Parse response to Post")
    public Post parseResponseToObject(Response response) {
        return response.as(Post.class);
    }


}
