package com.jsonplaceholder.forms;

public enum ENDPOINTS {

    POSTS("/posts/"),
    COMMENTS("/comments/"),
    ALBUMS("/albums/"),
    PHOTOS("/photos/"),
    TODOS("/todos/"),
    USERS("/users/");

    private final String url;

    ENDPOINTS(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
