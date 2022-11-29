package com.jsonplaceholder.responses;

import com.jsonplaceholder.models.Post;

import java.util.List;

public class PostsResponse extends SimpleResponse {

    public List<Post> postList;

    public PostsResponse() {}

}
