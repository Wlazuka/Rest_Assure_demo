package com.jsonplaceholder.apiService;


import com.jsonplaceholder.forms.ENDPOINTS;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JsonPlaceholderEndpoints extends BaseEndpoint{

    public JsonPlaceholderEndpoints(){}

    public static final String POSTS = ENDPOINTS.POSTS.getUrl();
    public static final String COMMENTS = ENDPOINTS.COMMENTS.getUrl();
    public static final String ALBUMS =  ENDPOINTS.ALBUMS.getUrl();
    public static final String PHOTOS =  ENDPOINTS.PHOTOS.getUrl();
    public static final String TODOS =  ENDPOINTS.TODOS.getUrl();
    public static final String USERS = ENDPOINTS.USERS.getUrl();

}
