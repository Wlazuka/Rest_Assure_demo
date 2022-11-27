package com.jsonplaceholder.endpoints;

import com.jsonplaceholder.config.PropertyManager;

public class PostsEndpoint {

    public static String postsEndpointURI = PropertyManager.getProperty("posts.endpoint");


}
