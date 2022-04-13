package com.woowang.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "comment_content",nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime commentDate;

    //== 양뱡향 연관관계 메서드 ==//
    public void setWriter(Member member){
        this.writer = member;
        member.getComments().add(this);
    }
    public void setPost(Post post){
        this.post = post;
        post.getComments().add(this);
    }

    //== 생성 메서드 ==//
    public static Comment createComment(Member member,Post post,String content){
        Comment comment = new Comment();

        comment.setWriter(member);
        comment.setPost(post);
        comment.content = content;
        comment.commentDate = LocalDateTime.now();

        return comment;
    }
}
