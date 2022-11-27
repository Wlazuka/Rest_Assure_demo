package com.jsonplaceholder.endpoints;

import com.jsonplaceholder.config.PropertyManager;

public class CommentsEndpoint {

    public static String commentsEndpointURI = PropertyManager.getProperty("comments.endpoint");

}
