package com.example.CRUD_BASIC.dto;

import com.example.CRUD_BASIC.entity.Post;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class PostForm {

    private Long id;
    private String title;
    private String content;

    public Post toEntity() {
        return new Post(id, title, content);
    }
}
