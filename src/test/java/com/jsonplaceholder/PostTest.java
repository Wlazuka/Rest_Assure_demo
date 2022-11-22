package com.jsonplaceholder;

import com.jsonplaceholder.models.Post;
import com.jsonplaceholder.utils.JsonUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import static io.restassured.RestAssured.given;

public class PostTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(PostTest.class);

    @Test(description = "GET /posts response with code 200")
    public void GET_PostsStatus200Test() {
        given()
                .get(posts)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test(description = "GET /posts/{id} - existing post - response with code 200")
    public void GET_ExistingPostStatusTest() {
        given()
                .get(posts + "1")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test(description = "GET /posts/{id} - non-existing post - response with code 404")
    public void GET_NonExistingPostStatusTest() {
        given()
                .get(posts + "10000")
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test(description = "GET /posts/{id} - existing post - verify response body")
    public void GET_PostTest() {
        String id = "1";

        JSONObject jsonObject = JsonUtils.parseJSONFile("src/test/resources/samples/post1_sample.json");
        Post samplePost = (Post) JsonUtils.jsonToObject(jsonObject, Post.class);

        Post post =  given()
                .get(posts + id)
                .as(Post.class);
        Assert.assertEquals(samplePost, post, String.format("Post with ID %s is not correct", id));
    }


}