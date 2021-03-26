package com.example.web.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After//단위테스트가 끝나면 호출
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void insertNreadArticle(){
        //given
        String title="테스트 게시글";
        String content="테스트 본문";

        postsRepository.save(Posts.builder() //builder design pattern
        .title(title)
        .content(content)
        .author("aaa@gmail.com")
        .build());//저장

        //when postsRepository에서 모두 찾아서 list에 넣는다
        List<Posts> postsList = postsRepository.findAll();

        //then list에서 꺼낸 후 is equalto 같은지 확인
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void registBaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.of(2021,3,25,0,0,0);
        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println(">>>>>>> createDate=" + posts.getCreatedDate() + "modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}

//JpaRepository 인터페이스 안에 crud 인터페이스가 모두 들어있기 때문에
//postsRepository .save, .findAll, .deleteAll 등을 사용할 수 있다.