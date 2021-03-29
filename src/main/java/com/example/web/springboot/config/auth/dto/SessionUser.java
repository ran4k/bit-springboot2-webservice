package com.example.web.springboot.config.auth.dto;

import com.example.web.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
//인증된(로그인된) 사용자 정보를 가지는 객체
public class SessionUser implements Serializable { //직렬화
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
