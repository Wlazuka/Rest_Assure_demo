package com.jsonplaceholder;

import com.jsonplaceholder.steps.CommentStep;
import com.jsonplaceholder.steps.PostStep;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CommentsTest extends BaseTest {

    private final CommentStep commentStep = new CommentStep();
    private final PostStep postStep = new PostStep();

    @Test
    @Description("GET /comments?postId=1 ")
    void verifyNumberOfCommentsForPost() {
        //GIVEN
        int id = 1;
        int expectedCommentsAmount = 5;

        //WHEN
        var commentsList = commentStep.getListOfCommentsWithPostIdAsParam(id);

        //THEN
        Assert.assertEquals(commentsList.size(), 5, String.format("There should be %s comments for post %s", expectedCommentsAmount, id));

        SoftAssert softAssert = new SoftAssert();
        commentsList.forEach(c -> softAssert.assertEquals(c.getPostId(), 1, "PostID should be: " + id));
        softAssert.assertAll();
    }

    @Test
    @Description("GET GET /posts/<id>/comments ")
    void get() {
        //GIVEN
        int id = 1;

        //WHEN
        var commentsList = postStep.getListOfCommentsForPost(id);

        //THEN
//        Assert.assertEquals(commentsList.size(), 5, String.format("There should be %s comments for post %s", expectedCommentsAmount, id));
//
//        SoftAssert softAssert = new SoftAssert();
//        commentsList.forEach(c -> softAssert.assertEquals(c.getPostId(), 1, "PostID should be: " + id));
//        softAssert.assertAll();
    }
}