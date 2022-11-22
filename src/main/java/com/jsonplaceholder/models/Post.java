package com.jsonplaceholder.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Post {

//    @JsonProperty("id")
    private int id;
//    @JsonProperty("title")
    private String title;
//    @JsonProperty("body")
    private String body;
//    @JsonProperty("userId")
    private int userId;
}
