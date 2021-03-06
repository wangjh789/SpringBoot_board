package com.woowang.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, name = "post_content")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, updatable = false)
    private LocalDateTime postDate;

    //== 연관관계 메서드 ==//
    private void setWriter(Member member){
        this.writer = member;
        member.getPosts().add(this);
    }
    private void setCategory(Category category){
        this.category = category;
        category.getPosts().add(this);
    }

    //==생성 메서드==//
    public static Post createPost(Member member,Category category,String title,String content){
        Post post = new Post();

        post.setWriter(member);
        post.setCategory(category);
        post.title = title;
        post.content = content;
        post.postDate = LocalDateTime.now();

        return post;
    }

}
