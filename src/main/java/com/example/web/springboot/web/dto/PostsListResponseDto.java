package com.example.web.springboot.web.dto;

import com.example.web.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private LocalDateTime modifiedDate;
    private Long id;
    private String title;
    private String author;

    public PostsListResponseDto(Posts entity) {
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.author=entity.getAuthor();
        this.modifiedDate=entity.getModifiedDate();
    }
}
