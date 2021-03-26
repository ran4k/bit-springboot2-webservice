package com.example.web.springboot.service;

import com.example.web.springboot.domain.posts.Posts;
import com.example.web.springboot.domain.posts.PostsRepository;


import com.example.web.springboot.web.dto.PostsListResponseDto;
import com.example.web.springboot.web.dto.PostsResponseDto;
import com.example.web.springboot.web.dto.PostsSaveRequestDto;
import com.example.web.springboot.web.dto.PostsUpdateRequestDto;
import com.example.web.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
                                                //entity로 변환, Id리턴
        return postsRepository.save(requestDto.toEntity()).getId();
        //xxRepository: jpa로 db(mybatis같이)처리
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당사용자 없음 id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;

    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당사용자없음 id=" + id));
        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당사용자 없음. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                //람다식::->생성자참조, 객체생성-> return new PostsListResponseDto
                .collect(Collectors.toList());
    }
}
