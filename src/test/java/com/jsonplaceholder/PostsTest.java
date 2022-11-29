package com.jsonplaceholder;

import com.jsonplaceholder.models.Post;
import com.jsonplaceholder.steps.PostStep;
import com.jsonplaceholder.utils.JsonUtils;
import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostsTest extends BaseTest {

    private final PostStep postStep = new PostStep();

    @Test
    @Description("GET /posts response with code 200")
    void GET_PostsStatus200Test() {
        //WHEN
        var response = postStep.getAllPosts();

        //THEN
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);

    }

    @Test
    @Description("GET /posts/<id> - existing post - response with code 200")
    void verifyIfCustomerGetsPostWithProvidedCorrectId() {
        //GIVEN
        int id = 1;

        //WHEN
        var response = postStep.getPostWithId(id);

        //THEN
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);

        var post = postStep.parseResponseToObject(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(post.getId(), id);
        softAssert.assertAll();
    }

    @Test
    @Description("GET /posts/<id> - non-existing post - response with code 404")
    void verifyIfProvidingNonExistingPostIdGivesStatusNotFound() {
        //GIVEN
        int id = 10000;

        //WHEN
        var response = postStep.getPostWithId(id);

        //THEN
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Description ("GET /posts/<id> - existing post - verify response body")
    void verifyIfReceivedPostEqualsSamplePost() {
        //GIVEN
        int id = 1;

        JSONObject jsonObject = JsonUtils.parseJSONFile("src/test/resources/samples/post1_sample.json");
        Post samplePost = (Post) JsonUtils.jsonToObject(jsonObject, Post.class);

        //WHEN
        var response = postStep.getPostWithId(id);

        /* Approach with parsing response to Post Object */
        var post = postStep.parseResponseToObject(response);

        //THEN
        Assert.assertEquals(samplePost, post, String.format("Post with ID %s is not correct", id));
    }

    @Test()
    @Description ("POST new Post - JSON - happy path")
    void verifyNewPostResponseBody() {
        //GIVEN
        String body = "{\"userId\": \"1\", \"title\": \"Test title\", \"body\": \"Test body\"}";

        //WHEN
        var response = postStep.postNewPost(body);

        //THEN
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);

        SoftAssert softAssert = new SoftAssert();
        /* Approach with response.path() method */
        softAssert.assertEquals(response.path("title"), "Test title");
        softAssert.assertEquals(response.path("body"), "Test body");
        softAssert.assertEquals(response.path("userId"), "1");
        softAssert.assertAll();
    }

    @Test()
    @Description ("POST new Post - JSONObject from file - happy path")
    void verifyNewPostResponseBody2() {

        //GIVEN
        JSONObject jsonObject = JsonUtils.parseJSONFile("src/test/resources/samples/post101_sample.json");

        //WHEN
        var response = postStep.postNewPost(jsonObject);

        //THEN
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);

        Post samplePost = (Post) JsonUtils.jsonToObject(jsonObject, Post.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.path("title"), samplePost.getTitle());
        softAssert.assertEquals(response.path("body"), samplePost.getBody());
        softAssert.assertEquals((int) response.path("userId"), samplePost.getUserId());
    }

    @Test()
    @Description ("POST new Post - Post Object - happy path")
    void verifyNewPostResponseBody3() {

        //GIVEN
        Post newPost = new Post(1, "Test title","Test body");

        //WHEN
        var response = postStep.postNewPost(newPost);

        //THEN
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.path("title"), newPost.getTitle());
        softAssert.assertEquals(response.path("body"), newPost.getBody());
        softAssert.assertEquals((int)  response.path("userId"), newPost.getUserId());
    }

    @Test()
    @Description ("PUT ")
    void put(){

    }

    @Test()
    @Description ("PATCH ")
    void patch(){

    }


    @Test()
    @Description ("DELETE Post")
    void verifyIfDeleteExistingPostReturnStatus200() {

        //GIVEN
        int id = 1;

        //WHEN
        var response = postStep.deletePostWithId(id);

        //THEN
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
    }
}