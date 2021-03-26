package com.example.web.springboot.domain.posts;

import com.example.web.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor //param 없는 생성자 메소드 만듦
@Entity//jpa annotation: db table과 mapping될 class
public class Posts extends BaseTimeEntity {

    @Id //primary key field
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //primary key를 만드는 생성규칙(전략)=auto increment(orcle의 sequence와 같은 기능)
    private Long id;

    @Column(length = 500, nullable = false) //=not null
    private String title;

    @Column(columnDefinition = "TEXT", nullable= false)
    //TEXT->varchar(255) 이상 변경 가능
    private String content;
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title= title;
        this.content=content;
        this.author=author;
    }

    public void update(String title, String content) {
        this.title=title;
        this.content=content;
    }
}
