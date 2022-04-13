package com.woowang.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String nickname;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "writer",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public static Member createMember(String username){
        Member member = new Member();
        member.nickname = username;
        return member;
    }
}
