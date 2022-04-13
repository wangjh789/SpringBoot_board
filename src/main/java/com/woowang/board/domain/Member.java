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

    @Column(unique = true,nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "writer",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public static Member createMember(String nickname,String email,String password){
        Member member = new Member();
        member.nickname = nickname;
        member.email = email;
        member.password = password;
        return member;
    }

    public void updateNick(String newNick){
        this.nickname = newNick;
    }
}
