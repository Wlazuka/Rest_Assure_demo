package com.jsonplaceholder;

import com.jsonplaceholder.models.Post;
import com.jsonplaceholder.utils.JsonUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import static io.restassured.RestAssured.given;

public class PostTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(BaseTest.class);

    @Test
    public void GET_PostsStatus200Test() {
        LOG.info("Verify GET /posts code 200");
        given()
                .when()
                .get(posts)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void GET_PostID1Test() {
        LOG.info("GET post ID");
        Post post =  given()
                .get(posts + "1")
                .as(Post.class);
        Assert.assertEquals(post.getId(), 1, "Post ID should be: 1");
    }

    @Test
    public void GET_PostID1_2Test() {
        String id = "1";

        JSONObject jsonObject = JsonUtils.parseJSONFile("src/test/resources/post1_sample.json");
        Post samplePost = (Post) JsonUtils.jsonToObject(jsonObject, Post.class);

        LOG.info(String.format("GET post ID #%s", id));
        Post post =  given()
                .get(posts + "1")
                .as(Post.class);
        Assert.assertEquals(samplePost, post, String.format("Post with ID %s is not correct", id));
    }
}