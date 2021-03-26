package com.example.web.springboot.web;

import com.example.web.springboot.service.PostsService;
import com.example.web.springboot.web.dto.PostsListResponseDto;
import com.example.web.springboot.web.dto.PostsResponseDto;
import com.example.web.springboot.web.dto.PostsResponseDto;
import com.example.web.springboot.web.dto.PostsSaveRequestDto;
import com.example.web.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostsService postsService;
    //객체주입: 1)autowired 2)생성자메소드 3)setter를 통해서,
    // 여기선 @Required~: 생성자메소드로 객체 주입

    @PostMapping("/api/v1/posts")//request가 위의 uri로 날라오면
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {

        return postsService.save(requestDto);
        //parameter로 온 requestDto를  service에서 save 호출해서 db에 저장
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }



}
