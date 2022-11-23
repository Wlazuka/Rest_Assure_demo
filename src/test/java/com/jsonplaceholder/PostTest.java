package com.jsonplaceholder;

import com.jsonplaceholder.models.Post;
import com.jsonplaceholder.utils.JsonUtils;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import static io.restassured.RestAssured.given;

public class PostTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(PostTest.class);

    @DataProvider
    public static Object[][] postsId() {
        return new Object[][]{
                {1},{2},{3}
        };
    }

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

    @Test()
    public void POST_NewPostStatus201Test() {

                given()
                .contentType("application/json")
                .body("{\"userId\": 1, \"title\": \"Test title\", \"body\": \"Test body\"}")
                .post(posts)
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test()
    public void POST_NewPostRequestTest() {

        JSONObject jsonObject = JsonUtils.parseJSONFile("src/test/resources/samples/post101_sample.json");
        Post samplePost = (Post) JsonUtils.jsonToObject(jsonObject, Post.class);

        Response response = given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .post(posts)
                .then()
                .extract().response();
        Assert.assertEquals(response.path("title"), samplePost.getTitle());
        Assert.assertEquals(response.path("body"), samplePost.getBody());
        Assert.assertEquals((Integer) response.path("userId"), samplePost.getUserId());
    }

    @Test(dataProvider = "postsId")
    public void DELETE_ExistingPostStatusTest(int postId) {
        given()
                .delete(posts + postId)
                .then()
                .assertThat()
                .statusCode(200).log().all();
    }
}